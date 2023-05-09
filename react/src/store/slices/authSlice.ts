import { createSlice } from '@reduxjs/toolkit'


export const authSlice = createSlice({
    name: 'auth',
    initialState: {
        mode: 'signUp',
        agree: false,
        login: '',
        password: '',
        passwordAgain: '',
        localIsAuth: false
    },
    reducers: {
        switchToSignUp: (state) => {
            state.mode = 'signUp'
        },
        switchToSignIn: (state) => {
            state.mode = 'signIn'
        },
        toggleAgree: (state) => {
            state.agree = !state.agree
        },
        updateLogin: (state, action: {payload: string}) => {
            state.login = action.payload
        },
        updatePassword: (state, action: {payload: string}) => {
            state.password = action.payload
        },
        updatePasswordAgain: (state, action: {payload: string}) => {
            state.passwordAgain = action.payload
        },
        setLocalIsAuth: (state, action: {payload: boolean}) => {
            state.localIsAuth = action.payload
        }
    },
})

// Action creators are generated for each case reducer function
export const {
    switchToSignUp, switchToSignIn, toggleAgree, updateLogin, updatePassword, updatePasswordAgain,
    setLocalIsAuth
} = authSlice.actions
