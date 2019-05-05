// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/index'
import store from './store/index'
import Element from 'element-ui'
import VueHighcharts from 'vue-highcharts'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
import 'codemirror/lib/codemirror.css'

require('codemirror/mode/javascript/javascript.js')
require('codemirror/mode/sql/sql.js')
require('codemirror/addon/hint/show-hint.js')
require('codemirror/addon/hint/show-hint.css')
require('codemirror/addon/hint/javascript-hint.js')
require('codemirror/addon/hint/sql-hint.js')

// import {env} from '@/libs/env'
// if (env === 'development')require('@/mock')

Vue.use(Element, { size: 'small' })
Vue.use(VueHighcharts)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
