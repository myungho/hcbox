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

export async function getAsync(url, params, token) {
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
export function get(url, params, token) {
  return axiosInstance({
    method: 'GET',
    url,
    headers: {
      Authorization: `Bearer ${token}`
    },
    params: params
  })
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

export function put(url, data, token) {
  configAxios()
  return axiosInstance({
    method: 'PUT',
    url,
    data,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": 'application/json'
    },
  })
}
