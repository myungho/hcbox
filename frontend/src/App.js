// project import
import Routes from 'routes_hcbox';
import ThemeCustomization from 'themes';
import ScrollTop from 'components_hcbox/ScrollTop';

// ==============================|| APP - THEME, ROUTER, LOCAL  ||============================== //

import { useKeycloak } from '@react-keycloak/web';
import { useEffect } from "react";

const App = props => {
  const { keycloak, initialized } = useKeycloak();

  useEffect(() => {
    console.info(keycloak)
  }, []);

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
