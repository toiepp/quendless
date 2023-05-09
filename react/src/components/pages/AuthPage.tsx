import {SignupPanel} from "../panels/SignupPanel";
import {SigninPanel} from "../panels/SigninPanel";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";

export function AuthPage() {
    const mode = useSelector((state: any) => state.auth.mode)
    const localIsAuth = useSelector((state: any) => state.auth.localIsAuth)
    return (
        <>
            {mode === 'signUp' ? <SignupPanel/> : <SigninPanel/>}
            {localIsAuth && <Navigate to={'/profile'}/>}
        </>
    );
}