const getters = {
  token: state => state.user.token,
  status: state => state.user.status,
  userName: state => state.user.userName,
  roles: state => state.user.roles,
  introduce: state => state.user.introduce,
  length: state => state.user.length,
  surplus: state => state.user.surplus,
  point: state => state.user.point,

  routes: state => state.permission.routes,
  addRoutes: state => state.permission.addRoutes,

  opened: state => {
    if (state.app.opened === 'false') {
      return false
    } else if (state.app.opened === 'true') {
      return true
    }
  },

  msgIsShow: state => state.app.msgIsShow,
  showDriver: state => state.app.showDriver
}
export default getters
