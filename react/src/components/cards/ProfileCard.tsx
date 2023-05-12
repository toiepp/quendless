import {Card} from "../primitives/Card";
import {CardRow} from "../primitives/CardRow";
import pencilImage from "../../res/images/pencil.svg";

type profile = {
    login: string,
    name?: string,
    imageUrl: string,
    email?: string,
}

export function ProfileCard({profile}: {profile: profile}) {
    return (
        <Card>
            <CardRow>
                <div className="m-2">
                    <img src={profile.imageUrl} alt={"avatar"} style={{display: "block", maxWidth: "100px", maxHeight: "100px", width: "auto", height: "auto"}}/>
                </div>
                <div className={'d-flex flex-column justify-content-center'}>
                    {profile.email !== undefined && <h3>{profile.name}</h3>}
                    <h3>{profile.login}</h3>
                    {profile.email !== undefined && <p>{profile.email}</p>}
                </div>
                <div className="m-2 d-flex flex-column justify-content-center">
                    <img src={pencilImage} alt={"edit icon"} onClick={() => {console.log('not implemented')}} style={{display: "block", maxWidth: "32px", maxHeight: "32px", width: "auto", height: "auto"}}></img>
                </div>
            </CardRow>
        </Card>
    );
}