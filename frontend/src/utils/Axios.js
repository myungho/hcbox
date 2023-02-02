import axios from 'axios'

// const {REACT_APP_API_URL} = window["runConfig"];
const {REACT_APP_API_URL} = 'http://localhost:8087';

const instance = axios.create({
  baseUrl: REACT_APP_API_URL, timeout: 3000
});

instance.interceptors.request.use(
    function (config) {
      return config;
    },
    function (error) {
      return Promise.reject(error);
    }
);

instance.interceptors.response.use(
    function (response) {
      return response;
    },

    function (error) {
      return Promise.reject(error);
    }
);

export default instance
