import {GroupCardList} from '../card_lists/GroupCardList';
import {useEffect, useState} from 'react';
import {Group} from '../../types';
import {setSearchMode, setSearchTemplate} from '../../store/slices/groupSlice';
import {useDispatch, useSelector} from 'react-redux';
import makeRequest from '../../requests/base';

async function makeGroupSearchRequest({template}: {template: string}): Promise<Group[]> {
    const response = await makeRequest({
        relativeUrl: `/groups?template=${template}`,
        method: 'get'
    })
    return response as Group[]
}

export function GroupSearchPanel() {
    const search = useSelector((state: any) => state.group.search)
    const dispatch = useDispatch()
    const [groups, setGroups]: [Group[], any] = useState([])
    useEffect(() => {
        const interval = setInterval(() => {
            if (search.template.length > 0)
                makeGroupSearchRequest({template: search.template}).then((groups) => setGroups(groups))
        }, 1000);

        return () => {
            clearInterval(interval);
        };

    })
    return (
        <>
            <button className='btn btn-outline-primary m-1' onClick={() => dispatch(setSearchMode(false))}>
                Мои группы
            </button>
            <div className={'d-flex flex-rows justify-content-start align-items-center'}>
                <label htmlFor='groupName'>Шаблон: </label>
                <input className='form-control m-1' id='groupName' type='text' placeholder={'Название группы'}
                       value={search.template} onChange={(event) => dispatch(setSearchTemplate(event.target.value))}/>
                <button className={'btn btn-outline-primary m-1'} onClick={async () => {
                    console.log(search.template)
                    if (search.template.length === 0)
                        return
                    const groups = await makeGroupSearchRequest({template: search.template})
                    setGroups(groups)
                }}>
                    Поиск
                </button>
            </div>
            <GroupCardList groups={groups} view={{editable: false}} emptyMessage={'Нет групп, удовлетворяющих шаблону поиска'}/>
        </>
    );
}