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

  },
  mounted () {

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
