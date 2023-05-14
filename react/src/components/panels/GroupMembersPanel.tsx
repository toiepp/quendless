import {Group, GroupMember, User} from '../../types';
import makeRequest from '../../requests/base';
import {useEffect, useState} from 'react';
import {UserCardList} from '../card_lists/UserCardList';

async function makeGetGroupMembersRequest(group: Group): Promise<GroupMember[]> {
    if (group.groupId === undefined)
        return []
    const response = await makeRequest({
        relativeUrl: `/groups/${group.groupId}/members`,
        method: 'get',
    })
    return response as GroupMember[]
}

export function GroupMembersPanel({group}: {group: Group}) {
    const [users, setUsers]: [User[], any] = useState([])
    useEffect(() => {
        const interval = setInterval(() => {
            makeGetGroupMembersRequest(group).then((groupMembers) => {
                const users = groupMembers.map((groupMember) => groupMember.user)
                setUsers(users)
            });
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    }, [group])
    return (
        <>
            <h2>Участники группы</h2>
            <UserCardList users={users} emptyMessage={'В группе нет участников'}/>
        </>
    );
}