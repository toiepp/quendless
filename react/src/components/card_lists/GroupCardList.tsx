import {CardViewSettings, Group} from '../../types';
import {GroupCard} from '../cards/GroupCard';

export function GroupCardList({groups, view, emptyMessage}: {groups: Group[], view: CardViewSettings, emptyMessage: string}) {
    if (groups.length === 0)
        return (
            <>
                <p>{emptyMessage}</p>
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