import { createSlice } from '@reduxjs/toolkit'
import {ServerMessage} from "../../types";

const errors: string[] = []

export const authSlice = createSlice({
    name: 'auth',
    initialState: {
        mode: 'signUp',
        agree: false,
        login: '',
        password: '',
        passwordAgain: '',
        errors: errors,
        isAuth: false
    },
    reducers: {
        switchToSignUp: (state) => {
            state.mode = 'signUp'
            state.errors.length = 0
        },
        switchToSignIn: (state) => {
            state.mode = 'signIn'
            state.errors.length = 0
        },
        toggleAgree: (state) => {
            state.agree = !state.agree
        },
        updateSignUpValidationErrors: (state, {payload}: {payload: string[]}) => {
            state.errors = payload;
        },
        updateSignInValidationErrors: (state, {payload}: {payload: string[]}) => {
            state.errors = payload;
        },
        handleSignInResponse: (state, action: {payload: ServerMessage}) => {
            if (action.payload.message === 'Ok') {
                state.isAuth = true;
            } else {
                state.errors.length = 0
                state.errors.push(action.payload.message)
            }
        },
        handleSignUpResponse: (state, action: {payload: ServerMessage}) => {
            if (action.payload.message === 'Ok') {
                state.isAuth = true;
            } else {
                state.errors.length = 0
                state.errors.push(action.payload.message)
            }
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
        logout: (state) => {
            state.isAuth = false
        },
        setLocalIsAuth: (state, action: {payload: boolean}) => {
            state.isAuth = action.payload
        }
    },
})

// Action creators are generated for each case reducer function
export const {
    switchToSignUp, switchToSignIn, toggleAgree, updateLogin, updatePassword, updatePasswordAgain, logout,
    handleSignUpResponse, handleSignInResponse, updateSignUpValidationErrors, updateSignInValidationErrors,
    setLocalIsAuth
} = authSlice.actions
