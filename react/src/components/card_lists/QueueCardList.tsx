import {QueueCard} from '../cards/QueueCard';
import {CardViewSettings, Queue} from '../../types';

export function QueueCardList({queues, view, emptyMessage}: {queues: Queue[], view: CardViewSettings, emptyMessage: string}) {
    if (queues.length === 0)
        return (
            <>
                <p>{emptyMessage}</p>
            </>
        )
    return (
        <>
            {queues.map((queue, index) => (
                <QueueCard queue={queue} view={view} key={index}/>
            ))}
        </>
    );
}