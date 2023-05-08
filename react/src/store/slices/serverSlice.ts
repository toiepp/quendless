import { createSlice } from '@reduxjs/toolkit'

export const serverSlice = createSlice({
    name: 'auth',
    initialState: {
        address: 'localhost:8080'
    },
    reducers: {

    },
})

// Action creators are generated for each case reducer function
// export const {} = serverSlice.actions