import {useDispatch, useSelector} from 'react-redux';
import {setCreateGroupDescription, setCreateGroupName, setCreateMode} from '../../store/slices/groupSlice';
import {Group} from '../../types';
import makeRequest from '../../requests/base';
import {useState} from 'react';

function validateCreateGroupForm(group: Group): string[] {
    const errors = []
    if (group.name.length === 0)
        errors.push('Название группы не заполнено')
    return errors
}

async function makeCreateGroupRequest(group: Group): Promise<Group> {
    const response = await makeRequest({
        relativeUrl: '/groups',
        method: 'post',
        body: JSON.stringify(group),
    })
    return response as Group
}

export function GroupCreatingForm() {
    const create = useSelector((state: any) => state.group.create);
    const dispatch = useDispatch()
    const [errors, setErrors]: [string[], any] = useState([])
    if (!create.enabled) {
        return (
            <>
                <button className='btn btn-outline-primary m-1' onClick={() => dispatch(setCreateMode(true))}>Создать</button>
            </>
        )
    }
    return (
        <>
            <div className={'bg-light rounded p-3 col-3'}>
                <h5>Создать группу</h5>
                <label htmlFor='groupName'>Название: </label>
                <input className='form-control' id='groupName' type='text' placeholder={'Название группы'}
                       value={create.name} onChange={(event) => dispatch(setCreateGroupName(event.target.value))}/>
                <label htmlFor='groupDescription'>Описание: </label>
                <textarea className='form-control align-content-stretch' id='groupDescription'
                          placeholder='Описание группы' value={create.description}
                          onChange={(event) => dispatch(setCreateGroupDescription(event.target.value))}></textarea>
                {errors.length !== 0 && errors.map((error: string, index: number) => <p className={'text-danger'} key={index}>{error}</p>)}
                <div>
                    <button className='btn btn-outline-primary m-1' onClick={() => dispatch(setCreateMode(false))}>Отмена</button>
                    <button className='btn btn-outline-primary m-1' onClick={async () => {
                        const group = {name: create.name, description: create.description}
                        let errors = validateCreateGroupForm(group)
                        setErrors(errors)
                        if (errors.length > 0)
                            return
                        const newGroup = await makeCreateGroupRequest(group)
                        if (newGroup.groupId !== undefined && newGroup.groupId !== null)
                            dispatch(setCreateMode(false))
                    }}>Создать</button>
                </div>
            </div>
            <div className='text-danger'>

            </div>
        </>
    );
}