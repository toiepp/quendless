import {QueueCard} from "../cards/QueueCard";
import {Queue} from "../../types";

export function QueueCardList({queues}: {queues: Queue[]}) {
    if (queues.length === 0)
        return (
            <>
                <p>Список групп пуст. Создайте новую или вступите в существующую</p>
            </>
        )
    return (
        <>
            {queues.map((queue, index) => (
                <QueueCard queue={queue} key={index}/>
            ))}
        </>
    );
}