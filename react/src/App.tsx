import React, {useEffect, useState} from "react";
import { BrowserRouter as Router} from 'react-router-dom';
import {PageTemplate} from "./components/layout/PageTemplate";
import {PageHeader} from "./components/layout/PageHeader";
import {PageFooter} from "./components/layout/PageFooter";
import './App.css';
import {AuthUserRoutes} from "./routes/AuthUserRoutes";
import {GuestRoutes} from "./routes/GuestRoutes";
import {useDispatch, useSelector} from "react-redux";
import {checkAuth} from "./requests/auth";
import {setLocalIsAuth} from "./store/slices/authSlice";

export function App() {
    const isAuth = useSelector((state: any) => state.auth.isAuth)
    const dispatch = useDispatch()
    useEffect(() => {
        checkAuth().then((response) => {
            dispatch(setLocalIsAuth(response))
        })
    })
    return (
            <PageTemplate>
                <Router>
                    <PageHeader isAuth={isAuth}/>
                    {isAuth ? <AuthUserRoutes/> : <GuestRoutes/>}
                    <PageFooter/>
                </Router>
            </PageTemplate>
    );
}
