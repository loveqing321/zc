<template lang="pug">
  .app-wrapper
    .main-container
      navbar
      app-main
</template>

<script>
import Sidebar from './common/sidebar'
import Navbar from './common/navbar'
import AppMain from './common/app-main'
import { getToken } from '@/libs/util'
import { getUserPermissions } from '@/api/system/permission'
import { mapMutations } from 'vuex'

export default {
  name: 'Main',
  components: {
    Sidebar,
    Navbar,
    AppMain
  },
  data () {
    return {
    }
  },
  methods: {
    ...mapMutations('app', [
      'setPermissions'
    ]),
    async init () {
      try {
        // 1. 获取许可信息，用于过滤菜单
        const perms = await getUserPermissions()
        this.setPermissions(perms)
        // 2.
        console.log(perms)

      } catch (e) {
        this.$message.error(e.message)
      }
    }
  },
  created () {
  },
  mounted () {
    // 1. 判断是否已经有token，如果有的话，则直接跳转到home
    const token = getToken()
    if (!token) {
      this.$router.push({
        name: 'login'
      })
      return
    }

    // 2. 必要的数据初始化
    this.init()
  }
}
</script>

<style lang="less" scoped>
.app-wrapper {
  height: 100%;
  width: 100%;
  .main-container {
    transition: all 0.28s ease-out;
    height: 100%;
  }
}
</style>
