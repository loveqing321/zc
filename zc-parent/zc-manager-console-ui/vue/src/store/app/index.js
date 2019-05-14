export default {
  namespaced: true,
  state: {
    // env环境
    env: null,
    // sidebar状态
    sidebarStatus: true,
    // 许可列表
    permissions: []
  },
  mutations: {
    setEnv (state, newEnv) {
      state.env = newEnv
    },
    changeSidebarStatus (state, val) {
      state.sidebarStatus = val
    },
    setPermissions (state, val) {
      state.permissions = val
    }
  }
}
