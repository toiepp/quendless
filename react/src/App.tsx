import React from "react";
import { BrowserRouter as Router} from 'react-router-dom';
import {PageTemplate} from "./components/layout/PageTemplate";
import {PageHeader} from "./components/layout/PageHeader";
import {PageFooter} from "./components/layout/PageFooter";
import './App.css';
import {AuthUserRoutes} from "./routes/AuthUserRoutes";
import {GuestRoutes} from "./routes/GuestRoutes";
import {useSelector} from "react-redux";
import {AuthChecker} from "./components/utils/AuthChecker";

export function App() {
    const localIsAuth = useSelector((state: any) => state.auth.localIsAuth)
    return (
            <PageTemplate>
                <AuthChecker/>
                <Router>
                    <PageHeader isAuth={localIsAuth}/>
                    {localIsAuth ? <AuthUserRoutes/> : <GuestRoutes/>}
                    <PageFooter/>
                </Router>
            </PageTemplate>
    );
}
