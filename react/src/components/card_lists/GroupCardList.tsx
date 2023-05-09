import {Group} from "../../types";
import {GroupCard} from "../cards/GroupCard";

export function GroupCardList({groups}: {groups: Group[]}) {
    if (groups.length === 0)
        return (
            <>
                <p>Список групп пуст. Создайте новую или вступите в существующую</p>
            </>
        )
    return (
        <>
            {groups.map((group, index) => (
                <GroupCard group={group} key={index}/>
            ))}
        </>
    );
}