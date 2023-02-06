import {useEffect, useState} from 'react';

// material-ui
import {Grid} from '@mui/material';

// project import
import Axios from 'utils/Axios';

// ==============================|| DASHBOARD - DEFAULT ||============================== //

const DashboardDefault = () => {


  useEffect(() => {
  }, []);

    return (
        <Grid container rowSpacing={4.5} columnSpacing={2.75}>
            Dashboard
        </Grid>
    );
};

export default DashboardDefault;
