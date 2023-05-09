import {Panel} from "../primitives/Panel";
import {NavLink} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {
    handleSignUpResponse,
    switchToSignIn,
    toggleAgree,
    updateLogin,
    updatePassword,
    updatePasswordAgain,
    updateSignUpValidationErrors,
} from "../../store/slices/authSlice";
import {ServerMessage, User} from "../../types";
import makeRequest from "../../requests/base";

async function makeSignUpRequest(user: User): Promise<ServerMessage> {
    const data = await makeRequest({
        relativeUrl: '/users/register',
        method: 'post',
        body: JSON.stringify(user)
    })
    return data as ServerMessage
}

interface SignUpForm {
    login: string,
    password: string,
    passwordAgain: string
}

function validateSignUpForm({login, password, passwordAgain}: SignUpForm): string[] {
    const errors = []
    if (login === '')
        errors.push('Поле "Логин" пустое')
    if (password === '')
        errors.push('Поле "Пароль" пустое')
    if (password !== passwordAgain)
        errors.push('Пароли не совпадают')
    return errors
}

export function SignupPanel() {
    const agree: boolean = useSelector((state: any) => state.auth.agree)
    const errors = useSelector((state: any) => state.auth.errors)
    const login = useSelector((state: any) => state.auth.login)
    const password = useSelector((state: any) => state.auth.password)
    const passwordAgain = useSelector((state: any) => state.auth.passwordAgain)
    const dispatch = useDispatch()
    return (
        <Panel>
            <div className={'d-flex flex-row justify-content-start'}>
                <h2 className={'m-2'}>Регистрация</h2>
                <button className={'btn btn-secondary m-2'} onClick={() => dispatch(switchToSignIn())}>
                    Войти
                </button>
            </div>
            <form>
                <div className="form-group m-1">
                    <label htmlFor="login">Логин</label>
                    <input type="text" className="form-control" id="login" placeholder="Введите логин"
                           value={login} onChange={(event) => dispatch(updateLogin(event.target.value))}/>
                </div>
                <div className="form-group m-1">
                    <label htmlFor="sign_up_password">Пароль</label>
                    <input type="password" className="form-control" id="sign_up_password" placeholder="Пароль"
                           value={password} onChange={(event) => dispatch(updatePassword(event.target.value))}/>
                </div>
                <div className="form-group m-1">
                    <label htmlFor="sign_up_password_again">Повторите пароль</label>
                    <input type="password" className="form-control" id="sign_up_password_again"
                           placeholder="Пароль ещё раз" value={passwordAgain} onChange={(event) => {dispatch(updatePasswordAgain(event.target.value))}}/>
                </div>
                <div className="form-check m-1">
                    <input type="checkbox" className="form-check-input" id="agree" checked={agree} onChange={() => dispatch(toggleAgree())}/>
                    <label className="form-check-label" htmlFor="agree">
                        Я прочитал и соглашаюсь с <NavLink to={'/user_agreement'}>Пользовательским
                        соглашением</NavLink>
                    </label>
                </div>
                {errors.length !== 0 && errors.map((error: string, index: number) => <p className={'text-danger'} key={index}>{error}</p>)}
            </form>
            <button className="btn btn-primary m-2" disabled={!agree}
                    onClick={async () => {
                        let errors = validateSignUpForm({login: login, password: password, passwordAgain: passwordAgain})
                        if (errors.length > 0) {
                            dispatch(updateSignUpValidationErrors(errors))
                            return
                        }
                        const data = await makeSignUpRequest({login: login, password: password})
                        console.log(data)
                        dispatch(handleSignUpResponse(data))
                    }}>Зарегистрироваться</button>

        </Panel>
    );
}