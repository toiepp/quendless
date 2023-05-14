import {Queue, ServerMessage} from '../../types';
import makeRequest from '../../requests/base';
import {Navigate, useParams} from 'react-router-dom';
import {useEffect, useState} from 'react';
import {ContentWrapper} from '../primitives/ContentWrapper';
import {Panel} from '../primitives/Panel';
import {QueueCard} from '../cards/QueueCard';
import {QueueMembersPanel} from '../panels/QueueMembersPanel';

async function makeGetQueueRequest({id}: {id: string}): Promise<Queue> {
    const response = await makeRequest({
        relativeUrl: `/queues/${id}`,
        method: 'get'
    })
    return response as Queue
}

export function QueuePage() {
    const params = useParams()
    const [forbidden, setForbidden] = useState(false);
    const [queue, setQueue]: [Queue, any] = useState({name: '', description: ''})
    useEffect(() => {
        const interval = setInterval(() => {
            makeGetQueueRequest({id: params.queueId!})
                .then((queue) => {
                    if ((queue as unknown as ServerMessage).message === 'access denied')
                        setForbidden(true)
                    setQueue(queue)
                })
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    })
    return (
        <ContentWrapper>
            {forbidden && <Navigate to={'/queues'}/>}
            <Panel>
                <QueueCard queue={queue} view={{editable: true}}/>
                <QueueMembersPanel queue={queue}/>
            </Panel>
        </ContentWrapper>
    )
}