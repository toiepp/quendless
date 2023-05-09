import {useDispatch} from "react-redux";
import {Navigate} from "react-router-dom";
import {setLocalIsAuth} from "../../store/slices/authSlice";
import {useEffect} from "react";
import {logout} from "../../requests/auth";

export function LogoutPage() {
    const dispatch = useDispatch()
    useEffect(() => {
        logout().then()
        dispatch(setLocalIsAuth(false))
    })

    return (
        <>
            <Navigate to={'/'}/>
        </>
    );
}