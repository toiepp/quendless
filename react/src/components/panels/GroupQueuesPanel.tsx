import {Group, Queue} from '../../types';
import makeRequest from '../../requests/base';
import {useEffect, useState} from 'react';
import {QueueCardList} from '../card_lists/QueueCardList';
import {QueueCreatingForm} from '../creating_forms/QueueCreatingForm';

async function makeGetGroupQueuesRequest(group: Group): Promise<Queue[]> {
    if (group.groupId === undefined)
        return []
    const response = await makeRequest({
        relativeUrl: `/groups/${group.groupId}/queues`,
        method: 'get',
    })
    return response as Queue[]
}

export function GroupQueuesPanel({group}: {group: Group}) {
    const [queues, setQueues]: [Queue[], any] = useState([])
    useEffect(() => {
        const interval = setInterval(() => {
            makeGetGroupQueuesRequest(group).then((queues) => setQueues(queues));
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    }, [group])
    return (
        <>
            <h2>Очереди группы</h2>
            { group.editable && <QueueCreatingForm group={group}/> }
            <QueueCardList queues={queues} view={{editable: true}}
                           emptyMessage={'Список очередей группы. Создайте новую очередь, если имеете права модератора'}/>
        </>
    );
}