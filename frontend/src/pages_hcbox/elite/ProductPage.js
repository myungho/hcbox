// material-ui
// project import
import MainCard from 'components/MainCard';
import {useKeycloak} from "@react-keycloak/web";
import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {get, getAsync} from "../../utils/Axios";
import {changePage, changePageSize} from "../../store/reducers/table";
import EliteTable from "../../components_hcbox/table/EliteTable";
import {openDrawer} from "../../store/reducers/menu";
// ==============================|| SAMPLE PAGE ||============================== //

const ProductPage = () => {
  const {keycloak} = useKeycloak();
  const [tableData, setTableData] = useState([]);
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
  }
  const searchData = async () => {
    const response = await getAsync(`/views/products/schools/${id}/retrieve`, null, keycloak.token)
    setTableData(response.productDtoList)
  };

  useEffect(() => {
    searchData();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  return (
      <MainCard title="상품 정보">
        <EliteTable
            tableColumns={initColumns}
            tableData={tableData}
            // post={postRequest}
            // update={updateRequest}
            // del={deleteRequest}
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
  )

};

export default ProductPage;
