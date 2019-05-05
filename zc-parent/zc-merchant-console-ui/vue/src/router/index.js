import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      hidden: true,
      redirect: {
        name: 'dashboard'
      }
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: require(`@/components/modules/dashboard`),
      meta: {
        desc: '驾驶舱'
      }
    },
    {
      path: '/error403',
      name: 'error403',
      component: require(`@/components/app/error/error403`),
      meta: {
        desc: '403'
      }
    }
  ]
})
