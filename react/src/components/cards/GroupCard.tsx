import {Card} from '../primitives/Card';
import {CardRow} from '../primitives/CardRow';
import {CardViewSettings, Group} from '../../types';
import {NavLink} from 'react-router-dom';
import {useDispatch, useSelector} from 'react-redux';
import {
    setEditGroupDescription,
    setEditGroupId, setEditGroupName,
    setEditMode, setRemoveGroupId, setRemoveMode
} from '../../store/slices/groupSlice';
import makeRequest from '../../requests/base';
import {useState} from 'react';

async function makeDeleteGroupRequest(group: Group) {
    await makeRequest({
        relativeUrl: `/groups/${group.groupId}`,
        method: 'delete',
    })
}

function validateEditGroupForm(group: Group) {
    const errors = []
    if (group.name.length === 0)
        errors.push('Имя группы пустое')
    return errors
}

async function makeEditGroupRequest(group: Group) {
    await makeRequest({
        relativeUrl: `/groups`,
        method: 'put',
        body: JSON.stringify(group)
    })
}

async function makeJoinGroupRequest(group: Group) {
    await makeRequest({
        relativeUrl: `/groups/${group.groupId}/join`,
        method: 'post'
    })
}

async function makeLeaveGroupRequest(group: Group) {
    await makeRequest({
        relativeUrl: `/groups/${group.groupId}/leave`,
        method: 'post'
    })
}

function isEditing(edit: {enabled: boolean, groupId: string}, group: Group): boolean {
    return edit.enabled && edit.groupId === group.groupId
}

function isRemoving(remove: {enabled: boolean, groupId: string}, group: Group): boolean {
    return remove.enabled && remove.groupId === group.groupId
}

export function GroupCard({group, view}: {group: Group, view: CardViewSettings}) {
    const edit = useSelector((state: any) => state.group.edit)
    const remove = useSelector((state: any) => state.group.remove)
    const dispatch = useDispatch()
    const [errors, setErrors]: [string[], any] = useState([])
    return (
        <Card>
            <CardRow>
                {
                    isEditing(edit, group) ?
                        <>
                            <label htmlFor='groupName'>Название: </label>
                            <input className='form-control' id='groupName' type='text' placeholder={'Название группы'}
                                   value={edit.name} onChange={(event) => dispatch(setEditGroupName(event.target.value))}/>
                        </>
                        :
                        <h5><NavLink to={`/groups/${group.groupId}`}>{group.name}</NavLink></h5>
                }
            </CardRow>
            <CardRow>
                {
                    isEditing(edit, group) ?
                        <>
                            <label htmlFor='groupDescription'>Описание: </label>
                            <textarea className='form-control align-content-stretch' id='groupDescription'
                                      placeholder='Описание группы' value={edit.description}
                                      onChange={(event) => dispatch(setEditGroupDescription(event.target.value))}></textarea>
                        </>
                        :
                        <p>
                            {(group.description !== undefined && group.description !== null && group.description !== '') ?
                            group.description : 'Без описания'}
                        </p>
                }
            </CardRow>
            {errors.length !== 0 && errors.map((error: string, index: number) => <p className={'text-danger'} key={index}>{error}</p>)}
            <CardRow>
                {
                    group.membership ?
                        <button className={'btn btn-outline-warning m-2'} onClick={async () => {
                            await makeLeaveGroupRequest(group)
                        }}>
                            Выйти
                        </button>
                        :
                        <button className={'btn btn-outline-success m-2'} onClick={async () => {
                            await makeJoinGroupRequest(group)
                        }}>
                            Вступить
                        </button>
                }
                {
                    (view.editable && group.editable) &&
                    <>
                        {
                            isEditing(edit, group) ?
                                <>
                                    <button className={'btn btn-outline-secondary m-2'} onClick={() => {
                                        dispatch(setEditMode(false))
                                    }}>
                                        Отмена
                                    </button>
                                    <button className={'btn btn-outline-secondary m-2'} onClick={async () => {
                                        if (group.groupId !== null && group.groupId !== undefined) {
                                            const group = {groupId: edit.groupId, name: edit.name, description: edit.description}
                                            const errors = validateEditGroupForm(group)
                                            setErrors(errors)
                                            if (errors.length > 0)
                                                return
                                            await makeEditGroupRequest(group)
                                            dispatch(setEditMode(false))
                                        }
                                    }}>
                                        Сохранить
                                    </button>
                                </>
                                :
                                <button className={'btn btn-outline-secondary m-2'} onClick={() => {
                                    if (group.groupId !== null && group.groupId !== undefined) {
                                        dispatch(setEditMode(true))
                                        dispatch(setRemoveMode(false))
                                        dispatch(setEditGroupId(group.groupId))
                                        dispatch(setEditGroupName(group.name))
                                        dispatch(setEditGroupDescription(group.description!))
                                    }
                                }}>
                                    Изменить
                                </button>
                        }
                        {
                            isRemoving(remove, group) ?
                                <>
                                    <p className={'align-self-center'}>Удалить?</p>
                                    <button className={'btn btn-outline-danger m-2'} onClick={() => {
                                        dispatch(setRemoveMode(false))
                                    }}>
                                        Не удалять
                                    </button>
                                    <button className={'btn btn-outline-danger m-2'} onClick={async () => {
                                        await makeDeleteGroupRequest(group)
                                        dispatch(setRemoveMode(false))
                                    }}>
                                        Удалить
                                    </button>
                                </>
                                :
                                <button className={'btn btn-outline-danger m-2'} onClick={() => {
                                    if (group.groupId !== null && group.groupId !== undefined) {
                                        dispatch(setRemoveMode(true))
                                        dispatch(setEditMode(false))
                                        dispatch(setRemoveGroupId(group.groupId))
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