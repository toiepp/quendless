import {Queue, QueueMember, User} from '../../types';
import makeRequest from '../../requests/base';
import {useEffect, useState} from 'react';
import {UserCardList} from '../card_lists/UserCardList';
import {Card} from '../primitives/Card';
import {CardRow} from '../primitives/CardRow';

async function makeGetQueueMembersRequest(queue: Queue): Promise<QueueMember[]> {
    if (queue.queueId === undefined)
        return []
    const response = await makeRequest({
        relativeUrl: `/queues/${queue.queueId}/members`,
        method: 'get',
    })
    return response as QueueMember[]
}

async function makeGetCurrentUserRequest(): Promise<User> {
    const response = await makeRequest({
        relativeUrl: `/users/me`,
        method: 'get',
    })
    return response as User;
}

interface QueueStat {
    size: number,
    position: number
}

export function QueueMembersPanel({queue}: {queue: Queue}) {
    const [users, setUsers]: [User[], any] = useState([])
    const [me, setMe]: [User, any] = useState({login: ''})
    const [queueStat, setQueueStat]: [QueueStat, any] = useState({size: 0, position: -1})
    useEffect(() => {
        const interval = setInterval(() => {
            makeGetQueueMembersRequest(queue).then((queueMembers) => {
                const users = queueMembers.map((queueMember) => queueMember.user)
                setUsers(users)
                const newQueueStat = {size: users.length, position: -1}
                if (me.login !== '') {
                    for (let i = 0; i < users.length; i++) {
                        if (me.userId === users[i].userId)
                            newQueueStat.position = i
                    }
                }
                setQueueStat(newQueueStat)
            });
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    }, [me.login, me.userId, queue])
    useEffect(() => {
        makeGetCurrentUserRequest().then((user) => setMe(user))
    })
    return (
        <>
            <h2>Участники очереди</h2>
            <Card>
                <CardRow>Размер очереди: {queueStat.size}</CardRow>
                <CardRow>
                    {queueStat.position === -1 ?
                        'Вы не стоите в этой очереди'
                        :
                        `Ваша позиция: ${queueStat.position + 1}`}
                </CardRow>
            </Card>
            <UserCardList users={users} emptyMessage={'В группе нет участников'}/>
        </>
    );
}