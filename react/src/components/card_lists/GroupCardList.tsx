import {CardViewSettings, Group} from "../../types";
import {GroupCard} from "../cards/GroupCard";

export function GroupCardList({groups, view}: {groups: Group[], view: CardViewSettings}) {
    if (groups.length === 0)
        return (
            <>
                <p>Список групп пуст. Создайте новую или вступите в существующую</p>
            </>
        )
    return (
        <>
            {groups.map((group, index) => (
                <GroupCard group={group} view={view} key={index}/>
            ))}
        </>
    );
}