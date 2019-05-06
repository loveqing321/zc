export default {
  namespaced: true,
  state: {
    env: null,
    // sidebar状态
    sidebarStatus: true
  },
  mutations: {
    setEnv (state, newEnv) {
      state.env = newEnv
    },
    changeSidebarStatus (state, val) {
      state.sidebarStatus = val
    }
  }
}
