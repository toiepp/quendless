import {Panel} from "../primitives/Panel";
import {ProfileCard} from "../cards/ProfileCard";
import defaultIcon from "../../res/images/user_icon.png"
import {ContentWrapper} from "../primitives/ContentWrapper";
import {useDispatch, useSelector} from "react-redux";

export function ProfilePage() {
    const displayLogin = useSelector((state: any) => state.auth.displayLogin)
    return (
        <ContentWrapper>
            <Panel>
                <ProfileCard profile={{
                    login: displayLogin,
                    imageUrl: defaultIcon
                }}/>
            </Panel>
            <Panel>
                <h2>Recent Activity</h2>
                <p>There's no recent activity</p>
            </Panel>
        </ContentWrapper>
    );
}