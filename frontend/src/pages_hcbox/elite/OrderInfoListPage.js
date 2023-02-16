// material-ui
import EliteTable from 'components_hcbox/table/EliteTable';
// project import
import MainCard from 'components/MainCard';
import {del, get, put, post} from 'utils/Axios';
import {useKeycloak} from '@react-keycloak/web';
import {useDispatch, useSelector} from 'react-redux';
import {changePage, changePageSize} from 'store/reducers/table';
// ==============================|| SAMPLE PAGE ||============================== //


const OrderInfoListPage = () => {
  const {keycloak} = useKeycloak();

  const dispatch = useDispatch();
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
    get('/orders/order-request/retrieve', {pageNo: query.page, pageSize: query.pageSize}, keycloak.token)
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
