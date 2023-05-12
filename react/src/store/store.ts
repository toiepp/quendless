import {combineReducers, legacy_createStore as createStore} from '@reduxjs/toolkit'
import {authSlice} from "./slices/authSlice";
import {groupSlice} from "./slices/groupSlice";

const saveToLocalStorage = (state: any) => {
    try {
        localStorage.setItem('state', JSON.stringify(state));
    } catch (e) {
        console.error(e);
    }
};

const loadFromLocalStorage = () => {
    try {
        const stateStr = localStorage.getItem('state');
        return stateStr ? JSON.parse(stateStr) : undefined;
    } catch (e) {
        console.error(e);
        return undefined;
    }
};

const rootReducer = combineReducers({
    auth: authSlice.reducer,
    group: groupSlice.reducer,
});

const persistedStore = loadFromLocalStorage();

const store = createStore(rootReducer, persistedStore);

store.subscribe(() => {
    saveToLocalStorage(store.getState());
});

export default store;
