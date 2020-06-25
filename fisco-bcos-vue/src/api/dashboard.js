import $axios from './index'

export function getCardsData() {
  const url = '/getCardsData'
  return $axios.get(url)
}
export function getBankTableData() {
  const url = '/findCurrentBusinessScore'
  return $axios.get(url)
}
export function getBankData() {
  const url = '/findCurrentBusinessScore'
  return $axios.get(url)
}
export function getBusinessData() {
  const url = '/findCurrentBusinessScore'
  return $axios.get(url)
}
export function getAllDeal(params) {
  const url = '/findAllDealsByBusinessId'
  return $axios.get(url,params)
}
export function getBTableData(params) {
  const url = '/api/getDeal'
  return $axios.get(url,params)
}
export function getClientData() {
  const url = '/getClientData'
  return $axios.get(url)
}
/*export function getLineData() {
  const url = '/getLineData'
  return $axios.get(url)
}
export function getBarData() {
  const url = '/getBarData'
  return $axios.get(url)
}*/
