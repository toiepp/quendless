import {createSlice} from '@reduxjs/toolkit';

export const queueSlice = createSlice({
    name: 'queue',
    initialState: {
        create: {
            enabled: false,
            name: '',
            description: ''
        },
        edit: {
            enabled: false,
            queueId: '',
            name: '',
            description: ''
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
        setEditQueueId: (state, action: {payload: string}) => {
            state.edit.queueId = action.payload
        },
        setEditQueueName: (state, action: {payload: string}) => {
            state.edit.name = action.payload
        },
        setEditQueueDescription: (state, action: {payload: string}) => {
            state.edit.description = action.payload
        },
        setRemoveQueueId: (state, action: {payload: string}) => {
            state.remove.queueId = action.payload
        }
    },
})

// Action creators are generated for each case reducer function
export const {
    setCreateMode, setEditMode, setCreateQueueName, setCreateQueueDescription,
    setEditQueueName, setEditQueueDescription, setEditQueueId, setRemoveMode, setRemoveQueueId
} = queueSlice.actions
