import $axios from './index'
export function getAllRolse() {
  const url = '/getRoles'
  return $axios.get(url)
}
export function getclientRolse() {
  const url = '/getclientRoles'
  return $axios.get(url)
}
export function getPoints(params) {
  const url = '/shenQingOrChengDuiJiFen'
  return $axios.get(url,params)
}
export function getAll(params) {
  const url = '/selectByBusinessId'
  return $axios.get(url,params)
}
export function getAllBanks() {
  const url = '/findAllRecord'
  return $axios.get(url)
}
export function bankChuli(params) {
  const url = '/shenQingOrChengDuiJiFenHandler'
  return $axios.get(url,params)
}
export function transferScore(params) {
  const url = '/api/transferScore'
  return $axios.get(url,params)
}
export function addMoney(params) {
  const url = '/api/addMoney'
  return $axios.get(url,params)
}