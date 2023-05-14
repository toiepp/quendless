import {Navigate, useParams} from 'react-router-dom';
import makeRequest from '../../requests/base';
import {Group, ServerMessage} from '../../types';
import {useEffect, useState} from 'react';
import {ContentWrapper} from '../primitives/ContentWrapper';
import {Panel} from '../primitives/Panel';
import {GroupCard} from '../cards/GroupCard';
import {setViewMode} from '../../store/slices/groupSlice';
import {useDispatch, useSelector} from 'react-redux';
import {GroupQueuesPanel} from '../panels/GroupQueuesPanel';
import {GroupMembersPanel} from '../panels/GroupMembersPanel';

async function makeGetGroupRequest({id}: {id: string}): Promise<Group> {
    const response = await makeRequest({
        relativeUrl: `/groups/${id}`,
        method: 'get'
    })
    return response as Group
}

export function GroupPage(props: any) {
    const params = useParams()
    const [forbidden, setForbidden] = useState(false);
    const [group, setGroup]: [Group, any] = useState({name: '', description: ''})
    const viewMode = useSelector((state: any) => state.group.viewMode);
    const dispatch = useDispatch();
    useEffect(() => {
        const interval = setInterval(() => {
            makeGetGroupRequest({id: params.groupId!})
                .then((group) => {
                    if ((group as unknown as ServerMessage).message === 'access denied')
                        setForbidden(true)
                    setGroup(group)
                })
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    })
    return (
        <ContentWrapper>
            {forbidden && <Navigate to={'/groups'}/>}
            <Panel>
                <GroupCard group={group} view={{editable: true}}/>
                <button className={'btn btn-outline-primary m-2'} onClick={() => {
                    dispatch(setViewMode(viewMode === 'queues' ? 'members' : 'queues'))
                }}>
                    {viewMode === 'queues' ? 'Участники' : 'Группы'}
                </button>
                {viewMode === 'queues' ? <GroupQueuesPanel group={group}/> : <GroupMembersPanel group={group}/>}
            </Panel>
        </ContentWrapper>
    )
}