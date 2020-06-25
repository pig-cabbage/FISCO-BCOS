import { login,login_business,login_client, getInfo,register,register_business,register_client} from '@/api/login'
import { getAllBanks } from '@/api/roles'
import { getAllDeal} from '@/api/dashboard'
import { Message } from 'element-ui'
import router, { resetRouter } from '@/router'
import store from "../index";

const state = {
  token: localStorage.getItem('token') ? localStorage.getItem('token') : '', // 认证凭证'
  status: localStorage.getItem('status') ? localStorage.getItem('status') : '', // 认证身份'
  userName: localStorage.getItem('name') ? localStorage.getItem('name') : '',
  length: localStorage.getItem('length') ? localStorage.getItem('length') : '',
  surplus: localStorage.getItem('surplus') ? localStorage.getItem('surplus') : '',
  point: localStorage.getItem('point') ? localStorage.getItem('point') : '',
  roles: [],
  introduce: ''
}
const mutations = {
  SET_TOKEN(state, val) {
    state.token = val
    localStorage.setItem('token', val)
  },
  SET_STATUS(state,val){
    state.status=val
    localStorage.setItem('status',val)
  },
  SET_LENGTH(state,val){
    state.length = val
    localStorage.setItem('length',val)
  },
  SET_SURPLUS(state,val){
    state.surplus = val
    localStorage.setItem('surplus',val)
  },
  SET_POINT(state,val){
    state.point = val
    localStorage.setItem('point',val)
  },
  DEL_TOKEN(state) {
    state.token = ''
    state.status=''
    state.userName = ''
    state.roles = ''
    state.introduce = ''
    localStorage.removeItem('token')
    localStorage.removeItem('status')
    localStorage.removeItem('name')
    localStorage.removeItem('length')
    localStorage.removeItem('surplus')
    localStorage.removeItem('point')
  },
  SET_ROLES(state, payload) {
    state.roles = payload
  },
  SET_NAME(state, payload) {
    state.userName = payload
    localStorage.setItem('name',payload)
  },
  SET_INTRODUCE(state, payload) {
    state.introduce = payload
  }
}
const actions = {
  // user login
  _login({ commit }, formdatas) {
    return new Promise((resolve, reject) => {
      login(formdatas)
        .then(res => {
          if (res) {
            if (res) {
              Message.success(res)
              commit('SET_TOKEN', res)
              commit('SET_NAME',formdatas.account)
            } else {
              Message.error(res)
            }
            resolve(res)
          }
        })
        .catch(error => {
          reject(error)
        })
    })
  },
  _login_business({ commit }, formdatas) {
    return new Promise((resolve, reject) => {
      login_business(formdatas)
          .then(res => {
            if (res) {
              if (res) {
                //Message.success(res)
                commit('SET_TOKEN', res)
                commit('SET_NAME',formdatas.businessId)
                commit('SET_POINT',res.score)
                commit('SET_SURPLUS',res.response_data.surplus)
              } else {
                console.log(error)
              }
              resolve(res)
            }
          })
          .catch(error => {
            reject(error)
          })
      /*let name = {businessId:localStorage.getItem('name')}
      getAllDeal(name)
          .then(res => {
            if (res) {
              if (res) {
                console.log(res)
                commit('SET_LENGTH',res.response.length)
              } else {
                console.log(error)
              }
              resolve(res)
            }
          })
          .catch(error => {
            reject(error)
          })*/
    })
  },
  _login_client({ commit }, formdatas) {
    return new Promise((resolve, reject) => {
      login_client(formdatas)
          .then(res => {
            if (res) {
              if (res) {
                Message.success(res)
                commit('SET_TOKEN', res)
                commit('SET_NAME',formdatas.account)
                commit('SET_SURPLUS',res.userinformation.surplus)
                commit('SET_POINT',res.score)
              } else {
                Message.error(res)
              }
              resolve(res)
            }
          })
          .catch(error => {
            reject(error)
          })
    })
  },
  _register({ commit }, formdatas) {
    return new Promise((resolve, reject) => {
      register(formdatas)
          .then(res => {
            if (res) {
              if (res) {
                Message.success(res)
                commit('SET_TOKEN', res)
                commit('SET_NAME',formdatas.account)
              } else {
                Message.error(res)
              }
              resolve(res)
            }
          })
          .catch(error => {
            reject(error)
          })
    })
  },
  _register_business({ commit }, formdatas) {
    return new Promise((resolve, reject) => {
      register_business(formdatas)
          .then(res => {
            if (res) {
              if (res) {
                Message.success(res)
                commit('SET_TOKEN', res)
                commit('SET_NAME',formdatas.businessId)
              } else {
                Message.error(res)
              }
              resolve(res)
            }
          })
          .catch(error => {
            reject(error)
          })
    })
  },
  _register_client({ commit }, formdatas) {
    return new Promise((resolve, reject) => {
      register_client(formdatas)
          .then(res => {
            if (res) {
              if (res) {
                Message.success(res)
                commit('SET_TOKEN', res)
                commit('SET_NAME',formdatas.userAccount)
              } else {
                Message.error(res)
              }
              resolve(res)
            }
          })
          .catch(error => {
            reject(error)
          })
    })
  },
  loginOut({ commit }) {
    commit('DEL_TOKEN')
    resetRouter()
    router.push({
      path: '/login',
      query: {
        redirect: '/'
      }
    })
  },
  _getInfo({ commit },params) {
    return new Promise((resolve, reject) => {
      getInfo(params)
        .then(res => {
          if (res.code === 0) {
            const { roles,name,introduce} = res.data
            commit('SET_ROLES', roles)
            commit('SET_NAME', name)
            commit('SET_INTRODUCE', introduce)
          } else {
            Message.error(res.msg)
          }
          resolve(res.data)
        })
        .catch(error => {
          reject(error)
        })
    })
  }
}
export default {
  namespaced: true,
  state,
  mutations,
  actions
}
