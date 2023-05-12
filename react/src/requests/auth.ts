import makeRequest from "./base";
import {User} from "../types";

interface ServerUser {
    userId?: any,
    name?: any,
    login: string,
    password?: any,
}

export async function getCurrentUser(): Promise<ServerUser> {
    const data: User = await makeRequest({
        relativeUrl: '/users/me',
        method: 'get',
    })
    return data as ServerUser;
}

export async function logout() {
    await makeRequest({
        relativeUrl: '/users/logout',
        method: 'get'
    })
}

