import {Route, Routes} from 'react-router-dom';
import {AuthPage} from '../components/pages/AuthPage';
import {ProfilePage} from '../components/pages/ProfilePage';
import {GroupsPage} from '../components/pages/GroupsPage';
import {GroupPage} from '../components/pages/GroupPage';
import {QueuesPage} from '../components/pages/QueuesPage';
import {QueuePage} from '../components/pages/QueuePage';
import React from 'react';
import {LogoutPage} from '../components/pages/LogoutPage';

export const AuthUserRoutes = () => {
    return (
        <Routes>
            <Route path='/auth' element={<AuthPage/>}/>
            <Route path='/profile' element={<ProfilePage/>}/>
            <Route path={'/groups/:groupId'} element={<GroupPage/>}/>
            <Route path='/groups' element={<GroupsPage/>}/>
            <Route path={`/queues/:queueId`} element={<QueuePage/>}/>
            <Route path='/queues' element={<QueuesPage/>}/>
            <Route path='/logout' element={<LogoutPage/>}/>
            <Route path='/' element={<ProfilePage/>}/>
        </Routes>
    );
};