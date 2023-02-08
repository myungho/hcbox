// third-party
import { combineReducers } from 'redux';

// project import
import menu from './menu';
import table from './table';

// ==============================|| COMBINE REDUCERS ||============================== //

const reducers = combineReducers({ menu, table });

export default reducers;
