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
  },
  created () {
    // 获取用户信息

    // 获取

  },
  mounted () {
    // 判断是否已经有token，如果有的话，则直接跳转到home
    const token = getToken()
    if (!token) {
      this.$router.push({
        name: 'login'
      })
      return
    }
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
