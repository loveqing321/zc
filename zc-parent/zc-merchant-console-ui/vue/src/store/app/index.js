export default {
  namespaced: true,
  state: {
    // env环境
    env: null,
    // sidebar状态
    sidebarStatus: true,
    // 用户信息
    userInfo: {}
  },
  mutations: {
    setEnv (state, newEnv) {
      state.env = newEnv
    },
    changeSidebarStatus (state, val) {
      state.sidebarStatus = val
    },
    setUserInfo (state, val) {
      state.userInfo = val
    }
  }
}
