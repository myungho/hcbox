// material-ui
import EliteTable from 'components_hcbox/table/EliteTable';
// project import
import MainCard from 'components/MainCard';
import {del, get, put, post} from 'utils/Axios';
import {useKeycloak} from '@react-keycloak/web';
import {useDispatch, useSelector} from 'react-redux';
import {changePage, changePageSize} from 'store/reducers/table';
// ==============================|| SAMPLE PAGE ||============================== //


const SchoolPage = () => {
  const {keycloak} = useKeycloak();

  const dispatch = useDispatch();
  const {page, pageSize} = useSelector((state) => state.table);
  const initColumns =
      [
        {title: '아이디', field: 'id', hidden: true},
        {title: '이름', field: 'name'},
        {title: '직원 이름', field: 'staffName'},
        {title: '전화번호', field: 'phone'}
      ]

  const searchData = query => new Promise((resolve, reject) => {
    get('/orders/schools/retrieve', {pageNo: query.page, pageSize: query.pageSize}, keycloak.token)
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
    post('orders/schools', newData, keycloak.token);
  }

  const deleteRequest = (id) => {
    del(`orders/schools/${id}`, keycloak.token);
  }

  const updateRequest = (id, newValue) => {
    console.info(newValue);
    put(`orders/schools/${id}`, newValue, keycloak.token);
  }

  return (
      <MainCard title="학교 정보">
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

export default SchoolPage;
