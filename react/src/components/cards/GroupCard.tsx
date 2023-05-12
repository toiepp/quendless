import {Card} from "../primitives/Card";
import {CardRow} from "../primitives/CardRow";
import {Group} from "../../types";

export function GroupCard({group}: {group: Group}) {
    return (
        <Card>
            <CardRow>
                <h5>{group.name}</h5>
            </CardRow>
            <CardRow>
                <p>{(group.description !== undefined && group.description !== null) && group.description}</p>
            </CardRow>
        </Card>
    );
}