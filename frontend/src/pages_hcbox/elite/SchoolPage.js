// material-ui
import EliteTable from 'components_hcbox/table/EliteTable';
// project import
import MainCard from 'components/MainCard';
import {useEffect, useState} from "react";
import {del, get, patch, post} from 'utils/Axios';
import {useKeycloak} from '@react-keycloak/web';
import {useDispatch, useSelector} from 'react-redux';
import {changePage, changePageSize} from 'store/reducers/table';
// ==============================|| SAMPLE PAGE ||============================== //
const initColumns =
    [
      {title: '아이디', field: 'id', hidden: true},
      {title: '이름', field: 'name'},
      {title: '직원 이름', field: 'staffName'},
      {title: '전화번호', field: 'phone'}
    ]

const initData = [
  {
    id: 1,
    name: '',
    staffName: '',
    phoner: '',
  }
]

const SchoolPage = () => {
  const {keycloak} = useKeycloak();
  const [data, setData] = useState(initData);
  const conditionData = "";
  const dispatch = useDispatch();
  const {page, pageSize} = useSelector((state) => state.table);

  const searchData = async () => {
    let searchParam = {
      ...pageOption
    };

    if (searchParam.sortField === "") {
      delete searchParam.sortField;
      delete searchParam.sortDirection;
    }

    const response = await get('/orders/schools/retrieve', searchParam,
        keycloak.token);
    setData(response.content);
    console.info(pageSize);
  }

  const postRequest = (newData) => {
    const response = post('orders/schools', newData, keycloak.token);
  }

  const deleteRequest = (id) => {
    const response = del(`orders/schools/${id}`, keycloak.token);
  }

  const updateRequest = (oldValue, newValue) => {
    const response = patch(`orders/schools/${oldValue.tableData.id}`, newValue,
        keycloak.token);
  }

  const [pageOption, setPageOption] = useState(
      {
        page: page,
        pageSize: pageSize
      }
  );

  useEffect(() => {
    searchData();
  }, [
    pageOption.page,
    pageOption.rowsPerPage,
  ]);

  return (
      <MainCard title="학교 정보">
        <EliteTable
            tableColumns={initColumns}
            tableData={data}
            post={postRequest}
            update={updateRequest}
            del={deleteRequest}
            page={page}
            pageSize={pageSize}
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
