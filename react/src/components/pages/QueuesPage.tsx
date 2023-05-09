import {Panel} from "../primitives/Panel";
import {ContentWrapper} from "../primitives/ContentWrapper";
import {QueueCardList} from "../card_lists/QueueCardList";
import {Queue} from "../../types";

function getQueues(): Queue[] {
    return [
        {
            name: "PIS 27.04"
        },
        {
            name: "RKSP 27.04"
        },
    ]
}

export function QueuesPage() {
    return (
        <ContentWrapper>
            <Panel>
                <h2>Queues</h2>
                <QueueCardList queues={getQueues()}/>
            </Panel>
        </ContentWrapper>
    );
}