// project import
import Routes from 'routes_hcbox';
import ThemeCustomization from 'themes';
import ScrollTop from 'components_hcbox/ScrollTop';

// ==============================|| APP - THEME, ROUTER, LOCAL  ||============================== //

import { useKeycloak } from '@react-keycloak/web';

const App = props => {
  const { keycloak } = useKeycloak();

  return (
      <div>
        <div>{`User is ${
            !keycloak.authenticated ? 'NOT ' : ''
        }authenticated`}</div>

        {!!keycloak.authenticated && (
            <button type="button" onClick={() => keycloak.logout()}>
              Logout
            </button>
        )}
        <ThemeCustomization>
          <ScrollTop>
            <Routes/>
          </ScrollTop>
        </ThemeCustomization>
      </div>

  );

};

export default App;
