import {useDispatch} from "react-redux";
import {Navigate} from "react-router-dom";
import {logout} from "../../store/slices/authSlice";
import {useEffect} from "react";

export function LogoutPage() {
    const dispatch = useDispatch()
    useEffect(() => {
        dispatch(logout())
    })

    return (
        <>
            <Navigate to={'/'}/>
        </>
    );
}