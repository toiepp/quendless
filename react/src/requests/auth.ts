import makeRequest from "./base";
import {User} from "../types";

export async function checkAuth(): Promise<boolean> {
    const data: User = await makeRequest({
        relativeUrl: '/users/me',
        method: 'get',
    })
    return data.login !== 'anonymousUser';
}

export async function logout() {
    await makeRequest({
        relativeUrl: '/users/logout',
        method: 'get'
    })
}

