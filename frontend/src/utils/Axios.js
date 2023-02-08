import axios from 'axios'
const API_URL = 'http://localhost:8087'

let axiosInstance = axios.create({
  baseURL: API_URL,
})

function configAxios() {
  axiosInstance = axios.create({
    baseURL: API_URL,
  })
}

export function post(url, data, token) {
  configAxios()
  return axiosInstance({
    method: 'POST',
    url,
    data,
    headers: {
      Authorization: `Bearer ${token}`
    },
  })
}

// delete is a reserved name
export function del(url, token) {
  configAxios()
  return axiosInstance({
    method: 'DELETE',
    url,
    headers: {
      Authorization: `Bearer ${token}`
    },
  })
}

export async function get(url, params, token) {
  const { data } = await axiosInstance({
    method: 'GET',
    url,
    headers: {
      Authorization: `Bearer ${token}`
    },
    params: params
  })
  return data
}

export function patch(url, data, token) {
  configAxios()
  return axiosInstance({
    method: 'PATCH',
    url,
    data,
    headers: {
      Authorization: `Bearer ${token}`
    },
  })
}
