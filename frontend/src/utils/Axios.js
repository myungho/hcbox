import axios from 'axios'
import { useKeycloak } from '@react-keycloak/web';
// const {REACT_APP_API_URL} = window["runConfig"];
// Need define API URL in constants or .env
const API_URL = 'http://localhost:8087'

let axiosInstance = axios.create({
  baseURL: API_URL,
})

function configAxios() {
  axiosInstance = axios.create({
    baseURL: API_URL,
  })
}
//

function getHeaders() {
  const token = 'eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJJV1M3bVBhUTBZVmdieDhiM25OemdUYnVjZWlNM0pSRHlQWEljSzlnWlNJIn0.eyJleHAiOjE2NzU3NTgxODUsImlhdCI6MTY3NTc1Nzg4NSwiYXV0aF90aW1lIjoxNjc1NzM4MjIyLCJqdGkiOiJmZDM5MDRhNC0xNDA2LTQ5YmUtODMzOC0wYzkwOTFkZjljODgiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvaGNib3giLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZmYyNjg3YjEtNTNjMy00YjAxLTljZWQtMTMxYWExNWUzMTAyIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiaGNib3gtYXBwIiwibm9uY2UiOiJmZTU4NGY0YS1mNDM1LTRhMjYtODRlMC1jOGMxMzMyYjcxNmYiLCJzZXNzaW9uX3N0YXRlIjoiNjZlYWRhZTMtMTQwMC00YmE4LWIyNzMtN2FjYjU1NjljN2YzIiwiYWNyIjoiMCIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLWhjYm94Iiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiI2NmVhZGFlMy0xNDAwLTRiYTgtYjI3My03YWNiNTU2OWM3ZjMiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJNeWVvbmdobyBIYW4iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0ZWRkeSIsImdpdmVuX25hbWUiOiJNeWVvbmdobyIsImZhbWlseV9uYW1lIjoiSGFuIiwiZW1haWwiOiJwcm9taDkwQGdtYWlsLmNvbSJ9.V89wIKXa-h8b7tVQT_TWQplVelYtyZVwD1R37GN0kqLb72NzAXrffyMFfjg3D71iezwvJ0EsmMD0H7RWkjzgFENlMlJnxjCqpdgS4J3WG9yoXDxdIJisA5tpl-ei6plHbGOQlbp1te494DPrNcpPupHjaeuFwtwCJQYKq-qs0JEBjzPdYUwZdrHxONac_7eQzMBqM3TnKSSAxR2yi4yoTXVCiq2La9WRRwEtHtbEaPhJNJj5KhBg-GM7EIb0IW1eIyyQNy_eycobGV4xbbJmWe2cBQrTOAzEePjpGoY6m8CFbIkFQad99fcdBRhmVecPlBrS_rgnk1qeDKReeWXNug'
  return {
    Authorization: token && `Bearer ${token}`,
    Accept: '*/*'
  }
}

export function post(url, data, headers = true) {
  configAxios()
  return axiosInstance({
    method: 'POST',
    url,
    data,
    headers: headers ? getHeaders() : {},
  })
}

// delete is a reserved name
export function del(url) {
  configAxios()
  return axiosInstance({
    method: 'DELETE',
    url,
    headers: getHeaders(),
  })
}

export async function get(url, token) {
  const { data } = await axiosInstance({
    method: 'GET',
    url,
    headers: {
      Authorization: `Bearer ${token}`
    },
  })
  return data
}

export function patch(url, data) {
  configAxios()
  return axiosInstance({
    method: 'PATCH',
    url,
    data,
    headers: getHeaders(),
  })
}
