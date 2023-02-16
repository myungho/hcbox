import * as React from 'react';
import {forwardRef} from 'react';
import MaterialTable from 'material-table';
import {createTheme, ThemeProvider} from '@mui/material';
import AddBox from '@material-ui/icons/AddBox';
import ArrowDownward from '@material-ui/icons/ArrowDownward';
import Check from '@material-ui/icons/Check';
import ChevronLeft from '@material-ui/icons/ChevronLeft';
import ChevronRight from '@material-ui/icons/ChevronRight';
import Clear from '@material-ui/icons/Clear';
import DeleteOutline from '@material-ui/icons/DeleteOutline';
import Edit from '@material-ui/icons/Edit';
import FilterList from '@material-ui/icons/FilterList';
import FirstPage from '@material-ui/icons/FirstPage';
import LastPage from '@material-ui/icons/LastPage';
import Remove from '@material-ui/icons/Remove';
import SaveAlt from '@material-ui/icons/SaveAlt';
import Search from '@material-ui/icons/Search';
import ViewColumn from '@material-ui/icons/ViewColumn';
const EliteTable = (props) => {

  const defaultMaterialTheme = createTheme();
  const {
    tableColumns,
    tableData,
    searchData,
    post,
    update,
    del,
    page,
    options,
    onChangePage,
    onChangeRowsPerPage
  } = props;

  const tableIcons = {
    Add: forwardRef((props, ref) => <AddBox {...props} ref={ref}/>),
    Check: forwardRef((props, ref) => <Check {...props} ref={ref}/>),
    Clear: forwardRef((props, ref) => <Clear {...props} ref={ref}/>),
    Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref}/>),
    DetailPanel: forwardRef(
        (props, ref) => <ChevronRight {...props} ref={ref}/>),
    Edit: forwardRef((props, ref) => <Edit {...props} ref={ref}/>),
    Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref}/>),
    Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref}/>),
    FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref}/>),
    LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref}/>),
    NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref}/>),
    PreviousPage: forwardRef(
        (props, ref) => <ChevronLeft {...props} ref={ref}/>),
    ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref}/>),
    Search: forwardRef((props, ref) => <Search {...props} ref={ref}/>),
    SortArrow: forwardRef(
        (props, ref) => <ArrowDownward {...props} ref={ref}/>),
    ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref}/>),
    ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref}/>)
  };

  const editable = () => {
    let editable = {};
    if(post !== undefined){
      editable.onRowAdd = newData =>
        new Promise((resolve, reject) => {
          setTimeout(() => {
            post(newData);

            resolve();
          }, 1000);
        })
    }

    if(update !== undefined) {
      editable.onRowUpdate = (newData, oldData) =>
            new Promise((resolve, reject) => {
              setTimeout(() => {
                update(oldData.id, newData);

                resolve();
              }, 1000);
            })
      }

    if(del !== undefined) {
      editable.onRowDelete = oldData =>
            new Promise((resolve, reject) => {
              setTimeout(() => {
                del(oldData.id);

                resolve();
              }, 1000);
            })
    }

    return editable;
  }

  return (
      <div style={{width: '100%', height: '100%'}}>
        <ThemeProvider theme={defaultMaterialTheme}>
          <MaterialTable
              columns={tableColumns}
              data={tableData === undefined ? searchData : tableData}
              title=""
              icons={tableIcons}
              page={page}
              options={options}
              onChangePage={(event, newPage) => onChangePage(event, newPage)}
              onChangeRowsPerPage={event => onChangeRowsPerPage(event)}
              editable={editable()}
          />
        </ThemeProvider>
      </div>
  );
}
export default EliteTable;
