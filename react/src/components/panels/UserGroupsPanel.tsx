import {GroupCardList} from '../card_lists/GroupCardList';
import {Group} from '../../types';
import {GroupCreatingForm} from '../creating_forms/GroupCreatingForm';
import {setCreateMode, setSearchMode} from '../../store/slices/groupSlice';
import {useDispatch} from 'react-redux';
import {useEffect, useState} from 'react';
import makeRequest from '../../requests/base';

async function getGroups(): Promise<Group[]> {
    const data = await makeRequest({
        relativeUrl: '/groups',
        method: 'get'
    })
    console.log(data)
    return data as Group[]
}

export function UserGroupsPanel() {
    const dispatch = useDispatch()
    const [groups, setGroups]: [Group[], any] = useState([])
    useEffect(() => {
        const interval = setInterval(() => {
            getGroups().then((groups) => setGroups(groups));
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    }, [])
    return (
        <>
            <button className='btn btn-outline-primary m-1' onClick={() => {
                dispatch(setSearchMode(true))
                dispatch(setCreateMode(false))
            }}>Поиск</button>
            <GroupCreatingForm/>
            <GroupCardList groups={groups} view={{editable: true}}/>
        </>
    );
}