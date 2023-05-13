import {Navigate, useParams} from 'react-router-dom';
import makeRequest from '../../requests/base';
import {Group, ServerMessage} from '../../types';
import {useEffect, useState} from 'react';
import {ContentWrapper} from '../primitives/ContentWrapper';
import {Panel} from '../primitives/Panel';
import {GroupCard} from '../cards/GroupCard';

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
            </Panel>
        </ContentWrapper>
    )
}