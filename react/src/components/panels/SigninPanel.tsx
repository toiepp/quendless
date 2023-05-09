import {Panel} from "../primitives/Panel";
import {useDispatch, useSelector} from "react-redux";
import {
    setLocalIsAuth,
    switchToSignUp,
    updateLogin,
    updatePassword,
} from "../../store/slices/authSlice";
import {ServerMessage, User} from "../../types";
import makeRequest from "../../requests/base";
import {useState} from "react";

async function makeSignInRequest(user: User): Promise<ServerMessage> {
    const formBody = Object.keys(user).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(user[key])).join('&');
    const data = await makeRequest({
        relativeUrl: '/users/login',
        method: 'post',
        body: formBody,
        contentType: 'application/x-www-form-urlencoded'
    })
    return data as ServerMessage
}

async function validateSignInForm({login, password}: User): Promise<string[]> {
    const errors = []
    if (login === '')
        errors.push('Поле "Логин" пустое')
    if (password === '')
        errors.push('Поле "Пароль" пустое')
    if (errors.length > 0) {
        return errors
    }
    const data = await makeSignInRequest({login: login, password: password})
    console.log(data)
    if (data.message !== "Ok")
        return [data.message]
    return []
}

export function SigninPanel () {
    const login = useSelector((state: any) => state.auth.login)
    const password = useSelector((state: any) => state.auth.password)
    const dispatch = useDispatch()
    const [errors, setErrors]: [string[], any] = useState([])
    return (
            <Panel>
                <div className={'d-flex flex-row justify-content-start'}>
                    <h2 className={'m-2'}>Авторизация</h2>
                    <button className={'btn btn-secondary m-2'} onClick={() => dispatch(switchToSignUp())}>
                        Зарегистрироваться
                    </button>
                </div>
                <form>
                    <div className="form-group m-1">
                        <label htmlFor="sign_in_login">Логин</label>
                        <input type="text" className="form-control" id="sign_in_login" placeholder="Введите логин"
                               value={login} onChange={(event) => dispatch(updateLogin(event.target.value))}/>
                    </div>
                    <div className="form-group m-1">
                        <label htmlFor="sign_in_password">Пароль</label>
                        <input type="password" className="form-control" id="sign_in_password" placeholder="Пароль"
                               value={password} onChange={(event) => dispatch(updatePassword(event.target.value))}/>
                    </div>
                    {errors.length !== 0 && errors.map((error: string, index: number) => <p className={'text-danger'} key={index}>{error}</p>)}
                </form>
                <button className="btn btn-primary m-2"
                        onClick={async () => {
                            let errors = await validateSignInForm({login: login, password: password})
                            setErrors(errors)
                            dispatch(setLocalIsAuth(errors.length === 0))
                        }}>Войти</button>
            </Panel>
    );
}