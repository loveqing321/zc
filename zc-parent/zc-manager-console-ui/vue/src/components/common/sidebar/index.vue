<template lang="pug">
  el-menu(
    :default-active='$route.name'
    mode='vertical'
    unique-opened=''
    background-color='#2d3a4b'
    text-color='#fff'
    active-text-color='#ffd04b'
  )
    sidebar-item(:routes='routes')
</template>

<script>
import SidebarItem from './SidebarItem'
export default {
  name: 'Sidebar',
  components: {
    SidebarItem
  },
  computed: {
    routes () {
      return this.$router.options.routes
    }
  }
}
</script>

<style scoped lang="less">
.el-menu {
  min-height: 100%;
}
</style>
