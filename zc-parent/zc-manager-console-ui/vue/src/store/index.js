import Vue from 'vue'
import Vuex from 'vuex'
import app from './app/index'

Vue.use(Vuex)

export default new Vuex.Store({
  // 开发阶段，启动严格模式，修改state必须通过mutation
  // strict: process.env.NODE_ENV != 'production',
  state: {
    //
  },
  mutations: {
    //
  },
  // 模块
  modules: {
    app
  }
})
