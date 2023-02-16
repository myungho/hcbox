// material-ui
// project import
import MainCard from 'components/MainCard';
import {useKeycloak} from "@react-keycloak/web";
import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {getAsync} from "../../utils/Axios";
import {changePage, changePageSize} from "../../store/reducers/table";
import EliteTable from "../../components_hcbox/table/EliteTable";
import {
  FormControl,
  InputLabel,
  NativeSelect, Typography,
} from '@mui/material';

import {openDrawer} from "../../store/reducers/menu";
import AnalyticEcommerce
  from "../../components/cards/statistics/AnalyticEcommerce";
// ==============================|| SAMPLE PAGE ||============================== //

const ProductPage = () => {
  const {keycloak} = useKeycloak();
  const [tableData, setTableData] = useState([]);
  const [schools, setSchools] = useState([]);
  const [id, setId] = useState(1);

  const dispatch = useDispatch();
  const {page, pageSize} = useSelector((state) => state.table);
  const initColumns =
      [
        {title: '아이디', field: 'id', hidden: true},
        {title: '계절', field: 'seasonType'},
        {title: '구분', field: 'name'},
        {title: '가격', field: 'price'}
      ]

  const options = {
    paginationType: "stepped",
    pageSize: pageSize,
    search: false
  };

  const searchSchool = async () => {
    const response = await getAsync(`/orders/schools/list`, null, keycloak.token)
    setSchools(response);
  };

  const searchData = async () => {
    const response = await getAsync(`/views/products/schools/${id}/retrieve`, null, keycloak.token)
    response.productDtoList.map((data) => {
      if(data.seasonType === 0)
        data.seasonType = '하계';
      else if(data.seasonType === 1) {
        data.seasonType = '동계';
      }
    });
    setTableData(response.productDtoList)
  };

  useEffect(() => {
    searchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  useEffect(() => {
    searchSchool();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <MainCard title="상품 정보 리스트">
    <FormControl fullWidth>
      <Typography variant="h6" gutterBottom>
        학교
      </Typography>
      <NativeSelect
          defaultValue={id}
          inputProps={{
            name: 'school',
            id: 'uncontrolled-native',
          }}
          onChange={(e) => setId(e.target.value)}
      >
        {schools.map((school) => <option key={school.id} value={school.id}>{school.name}</option>)}
      </NativeSelect>
    </FormControl>

    <EliteTable
        tableColumns={initColumns}
        tableData={tableData}
        page={page}
        options={options}
        onChangePage={(event, newPage) => {
          dispatch(changePage({page: newPage}));
        }}
        onChangeRowsPerPage={event => {
          dispatch(changePageSize({page: event}));
        }}
    />
  </MainCard>

};

export default ProductPage;
