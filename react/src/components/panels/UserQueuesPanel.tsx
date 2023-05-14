import {Queue} from '../../types';
import {useEffect, useState} from 'react';
import makeRequest from '../../requests/base';
import {QueueCardList} from '../card_lists/QueueCardList';

async function makeGetUserQueuesRequest(): Promise<Queue[]> {
    const response = await makeRequest({
        relativeUrl: '/queues',
        method: 'get',
    })
    return response as Queue[]
}

export function UserQueuesPanel() {
    const [queues, setQueues]: [Queue[], any] = useState([])
    useEffect(() => {
        const interval = setInterval(() => {
            makeGetUserQueuesRequest().then((queues) => setQueues(queues));
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    }, [])
    return (
        <>
            <h2>Ваши очереди</h2>
            <QueueCardList queues={queues} view={{editable: true}}
                           emptyMessage={'Список ваших очередей пуст. Создайте или вступите в существующую в одной из ваших групп'}/>
        </>
    );
}