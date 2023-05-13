import {createSlice} from "@reduxjs/toolkit";

export const groupSlice = createSlice({
    name: 'group',
    initialState: {
        create: {
            enabled: false,
            name: '',
            description: ''
        },
        edit: {
            enabled: false,
            groupId: '',
            name: '',
            description: ''
        },
        remove: {
            enabled: false,
            groupId: ''
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
        setCreateGroupName: (state, action: {payload: string}) => {
            state.create.name = action.payload
        },
        setCreateGroupDescription: (state, action: {payload: string}) => {
            state.create.description = action.payload
        },
        setEditGroupId: (state, action: {payload: string}) => {
            state.edit.groupId = action.payload
        },
        setEditGroupName: (state, action: {payload: string}) => {
            state.edit.name = action.payload
        },
        setEditGroupDescription: (state, action: {payload: string}) => {
            state.edit.description = action.payload
        },
        setRemoveGroupId: (state, action: {payload: string}) => {
            state.remove.groupId = action.payload
        }
    },
})

// Action creators are generated for each case reducer function
export const {
    setCreateMode, setEditMode, setCreateGroupName, setCreateGroupDescription,
    setEditGroupName, setEditGroupDescription, setEditGroupId, setRemoveMode, setRemoveGroupId
} = groupSlice.actions
