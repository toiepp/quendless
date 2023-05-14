import {Group, Queue} from '../../types';
import makeRequest from '../../requests/base';
import {useDispatch, useSelector} from 'react-redux';
import {useState} from 'react';
import {
    setCreateQueueDescription,
    setCreateQueueName,
    setCreateMode, setCreateQueueEventBegin, setCreateQueueEventEnd
} from '../../store/slices/queueSlice';

export function validateQueueForm(queue: Queue): string[] {
    const errors = []
    if (queue.name.length === 0)
        errors.push('Название очереди не заполнено')
    if (queue.eventBegin === undefined || queue.eventBegin === null)
        errors.push('Начало события задано')
    else if (Date.parse(queue.eventBegin) < Date.now())
        errors.push("Начало события в прошлом")
    if (queue.eventEnd === undefined || queue.eventEnd === null)
        errors.push("Время конца события не задано")
    else if (Date.parse(queue.eventEnd) < Date.now())
        errors.push("Конец события наступает раньше его начала")
    return errors
}

async function makeCreateQueueRequest(group: Group, queue: Queue): Promise<Queue> {
    const response = await makeRequest({
        relativeUrl: `/groups/${group.groupId}/queues`,
        method: 'post',
        body: JSON.stringify(queue),
    })
    return response as Queue
}

export function QueueCreatingForm({group}: {group: Group}) {
    const create = useSelector((state: any) => state.queue.create);
    const dispatch = useDispatch()
    const [errors, setErrors]: [string[], any] = useState([])
    if (!create.enabled) {
        return (
            <button className='btn btn-outline-primary m-1' onClick={() => {
                dispatch(setCreateMode(true))
            }}>Создать</button>
        )
    }
    return (
        <>
            <div className={'bg-light rounded p-3 col-3'}>
                <h5>Создать очередь</h5>
                <label htmlFor='queueCreateName'>Название: </label>
                <input className='form-control' id='queueCreateName' type='text' placeholder={'Название очереди'}
                       value={create.name} onChange={(event) => dispatch(setCreateQueueName(event.target.value))}/>
                <label htmlFor='queueCreateDescription'>Описание: </label>
                <textarea className='form-control align-content-stretch' id='queueCreateDescription'
                          placeholder='Описание очереди' value={create.description}
                          onChange={(event) => dispatch(setCreateQueueDescription(event.target.value))}></textarea>
                <label htmlFor="queueCreateEventBegin">Начало события</label>
                <input className="form-control" id="queueCreateEventBegin" type="datetime-local"
                       value={create.eventBegin}
                       onChange={(event) => dispatch(setCreateQueueEventBegin(event.target.value))}/>
                <label htmlFor="queueCreateEventEnd">Окончание события</label>
                <input className="form-control" id="queueCreateEventEnd" type="datetime-local"
                       value={create.eventEnd}
                       onChange={(event) => dispatch(setCreateQueueEventEnd(event.target.value))} />
                {errors.length !== 0 && errors.map((error: string, index: number) => <p className={'text-danger'} key={index}>{error}</p>)}
                <div>
                    <button className='btn btn-outline-primary m-1' onClick={() => dispatch(setCreateMode(false))}>Отмена</button>
                    <button className='btn btn-outline-primary m-1' onClick={async () => {
                        const queue = {
                            name: create.name,
                            description: create.description,
                            eventBegin: create.eventBegin.toString(),
                            eventEnd: create.eventEnd.toString(),
                        }
                        let errors = validateQueueForm(queue)
                        setErrors(errors)
                        if (errors.length > 0)
                            return
                        const newQueue = await makeCreateQueueRequest(group, queue)
                        if (newQueue.queueId !== undefined && newQueue.queueId !== null)
                            dispatch(setCreateMode(false))
                    }}>Создать</button>
                </div>
            </div>
        </>
    );
}