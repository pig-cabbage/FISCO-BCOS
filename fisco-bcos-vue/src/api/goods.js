import $axios from './index'
//import http from './public'
// 商品列表
/*export const getAllGoods = (params) => {
    return http.fetchGet('/goods/allGoods', params)
}*/
export function getAllGoods(params) {
    const url = '/api/splitByScore'
    return $axios.get(url, params)
}
// 加入购物车
/*export const addCart = (params) => {
    return http.fetchPost('/member/addCart', params)
}*/
export function addCartS(params) {
    const url = '/api/buy/money'
    return $axios.get(url, params)
}
export function addPointS(params) {
    const url = '/api/buy/score'
    return $axios.get(url, params)
}
// 商品详情
/*export const productDet = (params) => {
    return http.fetchGet('/goods/productDet', params)
}*/
export function productDet(params){
    const url ='/api/splitByName'
    return $axios.get(url,params)
}