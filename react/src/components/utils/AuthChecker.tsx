import {useDispatch} from "react-redux";
import {useEffect} from "react";
import {checkAuth} from "../../requests/auth";
import {setLocalIsAuth} from "../../store/slices/authSlice";


export function AuthChecker() {
    const dispatch = useDispatch()
    checkAuth().then((response) => {
        dispatch(setLocalIsAuth(response))
    })
    console.log('AuthCheck')
    useEffect(() => {
        const interval = setInterval(() => {
            checkAuth().then((response) => {
                dispatch(setLocalIsAuth(response))
            })
            console.log('AuthCheck')
        }, 60000);
        return () => {
            clearInterval(interval);
        };
    })
    return (
        <></>
    );
}