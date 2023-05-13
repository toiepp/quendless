import {ContentWrapper} from '../primitives/ContentWrapper';
import {Panel} from '../primitives/Panel';
import {useSelector} from 'react-redux';
import {UserGroupsPanel} from '../panels/UserGroupsPanel';
import {GroupSearchPanel} from '../panels/GroupSearchPanel';

export function GroupsPage() {
    const search = useSelector((state: any) => state.group.search)

    return (
        <ContentWrapper>
            <Panel>
                <h2>Группы</h2>
                {search.enabled ? <GroupSearchPanel/> : <UserGroupsPanel/>}
            </Panel>
        </ContentWrapper>
    )
}