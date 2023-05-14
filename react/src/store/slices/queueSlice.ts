import {createSlice} from '@reduxjs/toolkit';

function getTimeFormat(date: Date): string {
    return new Date(date
        .toString()
        .split('GMT')[0]+' UTC')
        .toISOString()
        .split('.')[0]
}

export const queueSlice = createSlice({
    name: 'queue',
    initialState: {
        create: {
            enabled: false,
            name: '',
            description: '',
            eventBegin: getTimeFormat(new Date()),
            eventEnd: getTimeFormat(new Date()),
        },
        edit: {
            enabled: false,
            queueId: '',
            name: '',
            description: '',
            eventBegin: getTimeFormat(new Date()),
            eventEnd: getTimeFormat(new Date()),
        },
        remove: {
            enabled: false,
            queueId: ''
        },
    },
    reducers: {
        setCreateMode: (state, action: {payload: boolean}) => {
            state.create.enabled = action.payload
        },
        setEditMode: (state, action: {payload: boolean}) => {
            state.edit.enabled = action.payload
        },
        setRemoveMode: (state, action: {payload: boolean}) => {
            state.remove.enabled = action.payload
        },
        setCreateQueueName: (state, action: {payload: string}) => {
            state.create.name = action.payload
        },
        setCreateQueueDescription: (state, action: {payload: string}) => {
            state.create.description = action.payload
        },
        setCreateQueueEventBegin: (state, action: {payload: string}) => {
            state.create.eventBegin = action.payload.toString();
        },
        setCreateQueueEventEnd: (state, action: {payload: string}) => {
            state.create.eventEnd = action.payload.toString();
        },
        setEditQueueId: (state, action: {payload: string}) => {
            state.edit.queueId = action.payload
        },
        setEditQueueName: (state, action: {payload: string}) => {
            state.edit.name = action.payload
        },
        setEditQueueDescription: (state, action: {payload: string}) => {
            state.edit.description = action.payload
        },
        setEditQueueEventBegin: (state, action: {payload: string}) => {
            state.edit.eventBegin = action.payload.toString();
        },
        setEditQueueEventEnd: (state, action: {payload: string}) => {
            state.edit.eventEnd = action.payload.toString();
        },
        setRemoveQueueId: (state, action: {payload: string}) => {
            state.remove.queueId = action.payload
        }
    },
})

// Action creators are generated for each case reducer function
export const {
    setCreateMode, setEditMode, setCreateQueueName, setCreateQueueDescription,
    setEditQueueName, setEditQueueDescription, setEditQueueId, setRemoveMode, setRemoveQueueId,
    setEditQueueEventBegin, setEditQueueEventEnd, setCreateQueueEventBegin, setCreateQueueEventEnd
} = queueSlice.actions
