import $axios from './index'

export function login(params) {
  const url = '/loginBank'
  return $axios.get(url, params)
}
export function login_business(params) {
  const url = '/loginBusiness'
  return $axios.get(url, params)
}
export function login_client(params) {
  const url = '/api/login'
  return $axios.get(url, params)
}
export function getInfo(params) {
  const url = '/getInfo'
  return $axios.get(url,params)
}
export function register(params) {
  const url = '/registBank'
  return $axios.get(url,params)
}
export function register_business(params) {
  const url = '/registBusiness'
  return $axios.get(url,params)
}
export function register_client(params) {
  const url = '/api/register'
  return $axios.get(url,params)
}
