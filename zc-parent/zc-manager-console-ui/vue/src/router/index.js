import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/dashboard'
    },
    {
      path: '/',
      component: require(`@/components/Main.vue`),
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          icon: 'fa fa-list',
          component: require(`@/components/modules/dashboard`),
          meta: {
            desc: '驾驶舱'
          }
        },
        {
          path: '/asset',
          name: 'asset',
          icon: 'fa fa-list',
          component: require(`@/components/modules/dashboard`),
          meta: {
            desc: '资产'
          },
          children: [
            {
              path: '/assets1',
              name: 'assets1',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '资产1'
              }
            },
          ]
        }
      ]
    },
    {
      path: '/login',
      component: require(`@/components/Login.vue`)
    },
    {
      path: '/403',
      component: require(`@/components/app/error/403.vue`)
    },
    {
      path: '/404',
      component: require(`@/components/app/error/404.vue`)
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})
