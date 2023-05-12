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
            name: '',
            description: ''
        },
    },
    reducers: {
        setCreateMode: (state, action: {payload: boolean}) => {
            state.create.enabled = action.payload
        },
        setEditMode: (state, action: {payload: boolean}) => {
            state.edit.enabled = action.payload
        }
    },
})

// Action creators are generated for each case reducer function
export const {setCreateMode, setEditMode} = groupSlice.actions
