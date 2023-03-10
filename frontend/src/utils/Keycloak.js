import Keycloak from 'keycloak-js';

const KEYCLOAK_REALM_NAME = 'hcbox' // keycloak realm
const KEYCLOAK_CLIENT_ID = 'hcbox-app'; // 사용할 client
const KEYCLOAK_URL = 'http://localhost:8080/auth/'; // keycloak url

// 위에 설정한 realm, client_id, url 로 keycloak instance 를 생성한다.
const keycloak: Keycloak.KeycloakInstance = Keycloak({
  realm: KEYCLOAK_REALM_NAME,
  clientId: KEYCLOAK_CLIENT_ID,
  url: KEYCLOAK_URL
});

// initOption 지정
export const initOptions = {
  onLoad: 'login-required',
  checkLoginIframe: false,
};

// keycloak Event 를 보기 위한 함수 정의
// keycloak provider 의 onEvent 에 넣어준다.
export const onKeycloakEvent = (event: any, error: any) => {
  console.log('keycloak event ', event, error);
  switch (event) {
    case 'onAuthLogout':
      keycloak.logout();
      break;
    case 'onAuthRefreshError':
      keycloak.logout();
      break;
    case 'onAuthRefreshSuccess':
      console.log('auth token:  ' + keycloak.token);
      console.log('refresh token:  ' + keycloak.refreshToken);
      break;
    default:
      break;
  }
};

export default keycloak;
