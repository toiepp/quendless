import {useDispatch} from "react-redux";
import {useEffect} from "react";
import {getCurrentUser} from "../../requests/auth";
import {setDisplayLogin, setLocalIsAuth} from "../../store/slices/authSlice";


export function AuthChecker() {
    const dispatch = useDispatch()

    function checkCurrentUser() {
        getCurrentUser().then((response) => {
            dispatch(setLocalIsAuth(response.login !== 'anonymousUser'))
            dispatch(setDisplayLogin(response.login))
        })
        console.log('AuthCheck')
    }

    checkCurrentUser()
    useEffect(() => {
        const interval = setInterval(() => {
            checkCurrentUser()
        }, 60000);
        return () => {
            clearInterval(interval);
        };
    })
    return (
        <></>
    );
}