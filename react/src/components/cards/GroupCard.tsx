import {Card} from "../primitives/Card";
import {CardRow} from "../primitives/CardRow";
import {Group} from "../../types";

export function GroupCard({group}: {group: Group}) {
    return (
        <Card>
            <CardRow>
                <h5>{group.name}</h5>
            </CardRow>
        </Card>
    );
}