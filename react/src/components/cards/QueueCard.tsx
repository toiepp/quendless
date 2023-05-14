import {Card} from '../primitives/Card';
import {CardRow} from '../primitives/CardRow';
import {CardViewSettings, Queue} from '../../types';
import {useDispatch, useSelector} from 'react-redux';
import {useState} from 'react';
import {NavLink} from 'react-router-dom';
import {
    setEditMode,
    setEditQueueDescription,
    setEditQueueEventBegin,
    setEditQueueEventEnd,
    setEditQueueId,
    setEditQueueName,
    setRemoveMode,
    setRemoveQueueId
} from '../../store/slices/queueSlice';
import makeRequest from '../../requests/base';
import {validateQueueForm} from '../creating_forms/QueueCreatingForm';

async function makeDeleteQueueRequest(queue: Queue) {
    await makeRequest({
        relativeUrl: `/queues/${queue.queueId}`,
        method: 'delete',
    })
}

async function makeEditQueueRequest(queue: Queue) {
    await makeRequest({
        relativeUrl: `/queues`,
        method: 'put',
        body: JSON.stringify(queue)
    })
}

async function makeJoinQueueRequest(queue: Queue) {
    await makeRequest({
        relativeUrl: `/queues/${queue.queueId}/join`,
        method: 'post'
    })
}

async function makeLeaveQueueRequest(queue: Queue) {
    await makeRequest({
        relativeUrl: `/queues/${queue.queueId}/leave`,
        method: 'post'
    })
}

function isEditing(edit: {enabled: boolean, queueId: string}, queue: Queue): boolean {
    return edit.enabled && edit.queueId === queue.queueId
}

function isRemoving(remove: {enabled: boolean, queueId: string}, queue: Queue): boolean {
    return remove.enabled && remove.queueId === queue.queueId
}

export function QueueCard({queue, view}: {queue: Queue, view: CardViewSettings}) {
    const edit = useSelector((state: any) => state.queue.edit)
    const remove = useSelector((state: any) => state.queue.remove)
    const dispatch = useDispatch()
    const [errors, setErrors]: [string[], any] = useState([])
    return (
        <Card>
            <CardRow>
                {
                    isEditing(edit, queue) ?
                        <>
                            <label htmlFor='queueName'>Название: </label>
                            <input className='form-control' id='queueName' type='text' placeholder={'Название очереди'}
                                   value={edit.name} onChange={(event) => dispatch(setEditQueueName(event.target.value))}/>
                        </>
                        :
                        <>
                            {
                                queue.membership ?
                                    <h5><NavLink to={`/queues/${queue.queueId}`}>{queue.name}</NavLink></h5>
                                    :
                                    <h5>{queue.name}</h5>
                            }
                        </>
                }
            </CardRow>
            {(queue.groupId !== undefined && queue.groupId !== null) &&
                <NavLink to={`/groups/${queue.groupId}`}>Ссылка на группу</NavLink>}
            <CardRow>
                {
                    isEditing(edit, queue) ?
                        <>
                            <label htmlFor='queueDescription'>Описание: </label>
                            <textarea className='form-control align-content-stretch' id='queueDescription'
                                      placeholder='Описание очереди' value={edit.description}
                                      onChange={(event) => dispatch(setEditQueueDescription(event.target.value))}></textarea>
                        </>
                        :
                        <>
                            {(queue.description !== undefined && queue.description !== null) &&
                                <>
                                    {queue.description.length !== 0 ? queue.description : 'Без описания'}
                                </>}
                        </>
                }
            </CardRow>
            <CardRow>
                {
                    isEditing(edit, queue) ?
                        <>
                            <label htmlFor="queueEditEventBegin">Начало события</label>
                            <input className="form-control" id="queueEditEventBegin" type="datetime-local"
                                   value={edit.eventBegin}
                                   onChange={(event) => dispatch(setEditQueueEventBegin(event.target.value))}/>
                        </>
                        :
                        <>
                            {(queue.eventBegin !== undefined && queue.eventBegin !== null) &&
                                <>
                                    Начало: {queue.eventBegin}
                                </>}
                        </>
                }
            </CardRow>
            <CardRow>
                {
                    isEditing(edit, queue) ?
                        <>
                            <label htmlFor="queueEventEnd">Окончание события</label>
                            <input className="form-control" id="queueEventEnd" type="datetime-local"
                                   value={edit.eventEnd}
                                   onChange={(event) => dispatch(setEditQueueEventEnd(event.target.value))} />
                        </>
                        :
                        <>
                            {(queue.eventEnd !== undefined && queue.eventEnd !== null) &&
                                <>
                                    Конец: {queue.eventEnd}
                                </>}
                        </>
                }
            </CardRow>
            {errors.length !== 0 && errors.map((error: string, index: number) => <p className={'text-danger'} key={index}>{error}</p>)}
            <CardRow>
                {
                    queue.membership ?
                        <button className={'btn btn-outline-warning m-2'} onClick={async () => {
                            await makeLeaveQueueRequest(queue)
                        }}>
                            Выйти
                        </button>
                        :
                        <button className={'btn btn-outline-success m-2'} onClick={async () => {
                            await makeJoinQueueRequest(queue)
                        }}>
                            Вступить
                        </button>
                }
                {
                    (view.editable && queue.editable) &&
                    <>
                        {
                            isEditing(edit, queue) ?
                                <>
                                    <button className={'btn btn-outline-secondary m-2'} onClick={() => {
                                        dispatch(setEditMode(false))
                                    }}>
                                        Отмена
                                    </button>
                                    <button className={'btn btn-outline-secondary m-2'} onClick={async () => {
                                        if (queue.queueId !== null && queue.queueId !== undefined) {
                                            const queue = {
                                                queueId: edit.queueId,
                                                name: edit.name,
                                                description: edit.description,
                                                eventBegin: edit.eventBegin.toString(),
                                                eventEnd: edit.eventEnd.toString(),
                                            }
                                            const errors = validateQueueForm(queue)
                                            setErrors(errors)
                                            if (errors.length > 0)
                                                return
                                            await makeEditQueueRequest(queue)
                                            dispatch(setEditMode(false))
                                        }
                                    }}>
                                        Сохранить
                                    </button>
                                </>
                                :
                                <button className={'btn btn-outline-secondary m-2'} onClick={() => {
                                    if (queue.queueId !== null && queue.queueId !== undefined) {
                                        dispatch(setEditMode(true))
                                        dispatch(setRemoveMode(false))
                                        dispatch(setEditQueueId(queue.queueId))
                                        dispatch(setEditQueueName(queue.name))
                                        dispatch(setEditQueueDescription(queue.description!))
                                        dispatch(setEditQueueEventBegin(queue.eventBegin!))
                                        dispatch(setEditQueueEventEnd(queue.eventEnd!))
                                    }
                                }}>
                                    Изменить
                                </button>
                        }
                        {
                            isRemoving(remove, queue) ?
                                <>
                                    <p className={'align-self-center'}>Удалить?</p>
                                    <button className={'btn btn-outline-danger m-2'} onClick={() => {
                                        dispatch(setRemoveMode(false))
                                    }}>
                                        Не удалять
                                    </button>
                                    <button className={'btn btn-outline-danger m-2'} onClick={async () => {
                                        await makeDeleteQueueRequest(queue)
                                        dispatch(setRemoveMode(false))
                                    }}>
                                        Удалить
                                    </button>
                                </>
                                :
                                <button className={'btn btn-outline-danger m-2'} onClick={() => {
                                    if (queue.queueId !== null && queue.queueId !== undefined) {
                                        dispatch(setRemoveMode(true))
                                        dispatch(setEditMode(false))
                                        dispatch(setRemoveQueueId(queue.queueId))
                                    }
                                }}>
                                    Удалить
                                </button>
                        }
                    </>
                }
            </CardRow>
        </Card>
    );
}