// types
import { createSlice } from '@reduxjs/toolkit';

// initial state
const initialState = {
    page: 0,
    pageSize: 5
};


const table = createSlice({
    name: 'table',
    initialState,
    reducers: {
        changePage(state, action) {
            state.page = action.payload.page;
        },

        changePageSize(state, action) {
            state.pageSize = action.payload.pageSize;
        }
    }
});

export default table.reducer;

export const { changePage, changePageSize } = table.actions;
