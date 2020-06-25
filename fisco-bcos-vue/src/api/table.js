import $axios from './index'

export function getPageTab1(params) {
  const url = '/getPageData1'
  return $axios.get(url, params)
}
export function getPageTab2(params) {
  const url = '/findGoodsByBusinessId'
  return $axios.get(url,params)
}
export function addGoods(data) {
  const url = '/addGoods'
  return $axios.post(url, data)
}
export function addGoodsP(data) {
  const url = '/addGoods/pic'
  return $axios.post(url, data)
}