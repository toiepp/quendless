import {Panel} from '../primitives/Panel';
import {ContentWrapper} from '../primitives/ContentWrapper';
import {UserQueuesPanel} from '../panels/UserQueuesPanel';

export function QueuesPage() {
    return (
        <ContentWrapper>
            <Panel>
                <UserQueuesPanel/>
            </Panel>
        </ContentWrapper>
    );
}