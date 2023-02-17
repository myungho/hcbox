// material-ui
import EliteTable from 'components_hcbox/table/EliteTable';
// project import
import MainCard from 'components/MainCard';
import {del, get, put} from 'utils/Axios';
import React, {useState} from 'react';
import {useKeycloak} from '@react-keycloak/web';
import {useDispatch, useSelector} from 'react-redux';
import {changePage, changePageSize} from 'store/reducers/table';
import {
  Box, Button,
  Grid,
  InputLabel,
  NativeSelect,
  TextField,
  Typography
} from "@mui/material";
import {SaveOutlined} from "@ant-design/icons";
// ==============================|| SAMPLE PAGE ||============================== //

const searchInit = {
  studentName: "",
  phone: "",
  schoolId: "",
  statusCode: ""
};

const OrderInfoListPage = () => {
  const {keycloak} = useKeycloak();

  const dispatch = useDispatch();
  const [search, setSearch] = useState(searchInit);

  const {page, pageSize} = useSelector((state) => state.table);
  const initColumns =
      [
        {title: '아이디', field: 'id', hidden: true},
        {title: '이름', field: 'studentName'},
        {title: '전화번호', field: 'phone'},
        {title: '학교', field: 'school.name'},
        {title: '주문상태', field: 'statusCode'},
        {title: '주문일시', field: 'orderDate'}
      ]

  const searchData = query => new Promise((resolve, reject) => {
    get('/orders/order-request/retrieve',
        {...search, pageNo: query.page, pageSize: query.pageSize},
        keycloak.token)
    .then(response => {
      resolve({
        data: response.data.content,
        page: response.data.number,
        totalCount: response.data.totalElements
      });
    });
  });

  const options = {
    paginationType: "stepped",
    pageSize: pageSize,
    search: false
  }

  const postRequest = (newData) => {
    // post('orders/schools', newData, keycloak.token);
  }

  const deleteRequest = (id) => {
    del(`orders/order-request/${id}`, keycloak.token);
  }

  const updateRequest = (id, newValue) => {
    console.info(newValue);
    put(`orders/order-request/${id}`, newValue, keycloak.token);
  }

  return (
      <MainCard title="주문 정보 리스트">

        <Grid container spacing={3}>
          <Grid item xs={2} sm={2}>
            <Typography  gutterBottom>
              이름
            </Typography>
          </Grid>
          <Grid item xs={4} sm={4}>
            <TextField
                required
                id="searchStudentName"
                name="searchStudentName"
                label="이름"
                fullWidth
                variant="outlined"
                onChange={(e) => {
                  setSearch({...search, studentName: e.target.value});
                }}
            />
          </Grid>
          <Grid item xs={6} sm={6}></Grid>

          <Grid item xs={2} sm={2}>
            <Typography  gutterBottom>
              전화번호
            </Typography>
          </Grid>
          <Grid item xs={4} sm={4}>
            <TextField
                required
                id="searchPhone"
                name="searchPhone"
                label="전화번호"
                fullWidth
                variant="outlined"
                onChange={(e) => {
                  setSearch({...search, phone: e.target.value});
                }}
            />
          </Grid>
          <Grid item xs={6} sm={6}></Grid>

          <Grid item xs={2} sm={2}>
            <Typography  gutterBottom>
              주문상태
            </Typography>
          </Grid>
          <Grid item xs={4} sm={4}>
            <NativeSelect
                fullWidth
                label={"searchStatusCode"}
                defaultValue={search.statusCode}
                inputProps={{
                  name: 'searchStatusCode',
                  id: 'searchStatusCode',
                }}
                onChange={(e) => {
                  setSearch(
                      {...search, statusCode: e.target.value});
                }}
            >
              <option key={99} value={""}></option>
              <option key={0} value={0}>대기</option>
              <option key={1} value={1}>입고</option>
              <option key={2} value={2}>반출</option>
              <option key={3} value={3}>반품</option>
            </NativeSelect>
          </Grid>
          <Grid item xs={6} sm={6}></Grid>
          <Grid item xs={12}  sx={{mb: 3}}>
            <Box display="flex" justifyContent="flex-end">
              <Button
                  type="submit"
                  variant="contained"
                  color="info"
                  onClick={searchData}
              >
                검색 &nbsp;
                <SaveOutlined/>
              </Button>
            </Box>
          </Grid>
        </Grid>


        <EliteTable
            tableColumns={initColumns}
            searchData={searchData}
            post={postRequest}
            update={updateRequest}
            del={deleteRequest}
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
  );
};

export default OrderInfoListPage;
