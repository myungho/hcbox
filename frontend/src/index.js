import {StrictMode} from 'react';
import {createRoot} from 'react-dom/client';
import {BrowserRouter} from 'react-router-dom';

// scroll bar
import 'simplebar/src/simplebar.css';

// third-party
import {Provider as ReduxProvider} from 'react-redux';

// apex-chart
import 'assets/third-party/apex-chart.css';

// project import
import App from './App';
import {store} from 'store';

import {ReactKeycloakProvider} from '@react-keycloak/web';
import keycloak, { initOptions, onKeycloakEvent } from './utils/Keycloak';

// ==============================|| MAIN - REACT DOM RENDER  ||============================== //

const container = document.getElementById('root');
const root = createRoot(container); // createRoot(container!) if you use TypeScript

export const eventLogger = (event, error) => {
  console.log('onKeycloakEvent', event, error)
}

export const tokenLogger = (tokens) => {
  console.log('onKeycloakTokens', tokens)
}

root.render(
    <ReactKeycloakProvider
        authClient={keycloak}
        initOptions={initOptions}
        onEvent={onKeycloakEvent}
        onTokens={tokenLogger}
    >
      <StrictMode>
        <ReduxProvider store={store}>
          <BrowserRouter basename="/">
            <App/>
          </BrowserRouter>
        </ReduxProvider>
      </StrictMode>
    </ReactKeycloakProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
