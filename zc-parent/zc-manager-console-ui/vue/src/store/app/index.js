export default {
  namespaced: true,
  state: {
    env: null
  },
  mutations: {
    setEnv (state, newEnv) {
      state.env = newEnv
    }
  }
}
