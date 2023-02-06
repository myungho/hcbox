import * as React from 'react';
import MaterialTable from 'material-table';
import {createTheme, ThemeProvider} from '@mui/material';


const EliteTable = (props) => {

  const defaultMaterialTheme = createTheme();
  const {
    tableColumns,
    tableData
  } = props;

  return (
      <div style={{width: '100%', height: '100%'}}>
        <ThemeProvider theme={defaultMaterialTheme}>
          <MaterialTable
              columns={tableColumns}
              data={tableData}
              title="Personen"
          />
        </ThemeProvider>


      </div>
  );
}
export default EliteTable;
