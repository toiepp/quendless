import {ContentWrapper} from "../primitives/ContentWrapper";
import {Panel} from "../primitives/Panel";
import {Group} from "../../types";
import {useEffect, useState} from "react";
import makeRequest from "../../requests/base";
import {GroupCardList} from "../card_lists/GroupCardList";
import {GroupCreatingForm} from "../creating_forms/GroupCreatingForm";

async function getGroups(): Promise<Group[]> {
    const data = await makeRequest({
        relativeUrl: "/groups",
        method: "get"
    })
    console.log(data)
    return data as Group[]
}

export function GroupsPage() {
    const [groups, setGroups]: [Group[], any] = useState([])
    useEffect(() => {
        const interval = setInterval(() => {
            getGroups().then((groups) => setGroups(groups));
        }, 1000);

        return () => {
            console.log(`clearing interval`);
            clearInterval(interval);
        };
    }, [])
    return (
        <ContentWrapper>
            <Panel>
                <h2>Groups</h2>
                <GroupCreatingForm/>
                <GroupCardList groups={groups} view={{editable: true}}/>
            </Panel>
        </ContentWrapper>
    )
}