import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      redirect: '/dashboard'
    },
    {
      path: '/',
      component: require(`@/components/Main.vue`),
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          icon: 'fa fa-home',
          component: require(`@/components/modules/dashboard`),
          meta: {
            desc: '首页'
          }
        },
        {
          path: '/rent',
          name: 'rent',
          icon: 'fa fa-car',
          component: require(`@/components/modules/dashboard`),
          meta: {
            desc: '租车',
          },
          children: [
            {
              path: '/rent/order',
              name: 'order',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '订单中心'
              }
            },
            {
              path: '/rent/pay',
              name: 'pay',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '支付中心'
              }
            },
            {
              path: '/rent/settlement',
              name: 'settlement',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '结算中心'
              }
            },
            {
              path: '/rent/reconciliation',
              name: 'reconciliation',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '对账中心'
              }
            }
          ]
        },
        {
          path: '/data',
          name: 'data',
          icon: 'fa fa-windows',
          component: require(`@/components/modules/dashboard`),
          meta: {
            desc: '数据'
          },
          children: [
            {
              path: '/data/orderAnalyze',
              name: 'orderAnalyze',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '订单分析'
              }
            },
            {
              path: '/data/incomeAnalyze',
              name: 'incomeAnalyze',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '收入分析'
              }
            },
            {
              path: '/data/carAnalyze',
              name: 'carAnalyze',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '车辆分析'
              }
            }
          ]
        },
        {
          path: '/base',
          name: 'base',
          icon: 'fa fa-windows',
          component: require(`@/components/modules/dashboard`),
          meta: {
            desc: '基础'
          },
          children: [
            {
              path: '/base/distribution',
              name: 'distribution',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '收益分成'
              }
            },
            {
              path: '/base/tenant',
              name: 'tenant',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '租户管理'
              }
            },
            {
              path: '/base/merchant',
              name: 'merchant',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '商户管理'
              }
            },
            {
              path: '/base/car',
              name: 'car',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '车辆管理'
              }
            }
          ]
        },
        {
          path: '/system',
          name: 'system',
          icon: 'fa fa-cogs',
          component: require(`@/components/modules/dashboard`),
          meta: {
            desc: '系统'
          },
          children: [
            {
              path: '/system/user',
              name: 'user',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '用户管理'
              }
            },
            {
              path: '/system/role',
              name: 'role',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '角色管理'
              }
            },
            {
              path: '/system/permission',
              name: 'permission',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '权限管理'
              }
            },
            {
              path: '/system/dict',
              name: 'dict',
              component: require(`@/components/modules/dashboard`),
              meta: {
                desc: '字典管理'
              }
            }
          ]
        },
        {
          path: '/message',
          name: 'message',
          hidden: true,
          component: require(`@/components/modules/message`),
          meta: {
            desc: '消息中心',
            breadcrumb: [
              {name: 'dashboard'}
            ]
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: require(`@/components/Login.vue`)
    },
    {
      path: '/403',
      name: '403',
      component: require(`@/components/app/error/403.vue`)
    },
    {
      path: '/404',
      name: '404',
      component: require(`@/components/app/error/404.vue`)
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})
