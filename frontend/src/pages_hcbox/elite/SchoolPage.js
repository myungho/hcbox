// material-ui
import EliteTable from 'components_hcbox/table/EliteTable';
// project import
import MainCard from 'components/MainCard';
import {useEffect} from "react";
import {get} from 'utils/Axios';
import { useKeycloak } from '@react-keycloak/web';
// ==============================|| SAMPLE PAGE ||============================== //
const initColumns =
    [
      {title: 'Name', field: 'name'},
      {title: 'Surname', field: 'surname'},
      {title: 'Birth Year', field: 'birthYear', type: 'numeric'},
      {
        title: 'Birth City',
        field: 'birthCity',
        lookup: {1: 'Linz', 2: 'Vöcklabruck', 3: 'Salzburg'}
      }
    ]

const initData = [
  {
    name: 'Max',
    surname: 'Mustermann',
    birthYear: 1987,
    birthCity: 1
  },
  {
    name: 'Cindy',
    surname: 'Musterfrau',
    birthYear: 1995,
    birthCity: 2
  }
]
const SchoolPage = () => {
  const { keycloak } = useKeycloak();
  const getData = async () => {
    const response = get('/orders/schools/retrieve', keycloak.token)
  }

  useEffect(() => {
    getData();
  });

  return (
      <MainCard title="Sample Card">
        <EliteTable tableColumns={initColumns} tableData={initData}/>
      </MainCard>
  );

};

export default SchoolPage;
