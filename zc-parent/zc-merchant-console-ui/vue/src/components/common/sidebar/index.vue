<template lang="pug">
  .sidebar-container
    .sidebar-header
      .header-text
        span.header-icon
          i(v-if="sidebarStatus" class="fa fa-list-ul")
          i(v-else class="fa fa-chevron-right" style="cursor: pointer;" @click="openSidebar")
        span
          | 所有功能
        span.close-menu
          i(class="fa fa-chevron-left" style="cursor: pointer;" @click="closeSidebar")
    .menu-box
      el-menu(
        ref="menu"
        :default-active='$route.name'
        mode='vertical'
        unique-opened=''
        :default-openeds='openedSubs'
        background-color='#2f323f'
        text-color='#fff'
        active-text-color='#ffd04b'
      )
        sidebar-item(:routes='routes')
</template>

<script>
  import SidebarItem from './SidebarItem'
  import { mapState, mapMutations } from 'vuex'

  export default {
    name: 'Sidebar',
    components: {
      SidebarItem
    },
    data () {
      return {
        openedSubs: []
      }
    },
    computed: {
      ...mapState('app', [
        'sidebarStatus'
      ]),
      routes () {
        // 使用主页面的路由来初始化菜单
        return this.$router.options.routes[1].children
      }
    },
    methods: {
      ...mapMutations('app', [
        'changeSidebarStatus'
      ]),
      openSidebar () {
        this.changeSidebarStatus(true)
      },
      closeSidebar () {
        this.changeSidebarStatus(false)
        this.openedSubs = []
      }
    }
  }
</script>

<style scoped lang="less">
  .sidebar-container {
    height: 100%;
    transition: all 0.28s ease-out;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    display: flex;
    flex-direction: column;
    .sidebar-header {
      margin-right: 1px;
      padding: 4px 0;
      border-top: 1px solid hsla(0,0%,100%,.1);
      border-bottom: 1px solid hsla(0,0%,100%,.1);
      background-color: #2f323f;
      .header-text {
        height: 40px;
        line-height: 40px;
        font-size: 14px;
        color: #fff;
        position: relative;
        .header-icon {
          display: inline-block;
          width: 50px;
          height: 40px;
          line-height: 40px;
          text-align: center;
          vertical-align: middle;
        }
        .close-menu {
          position: absolute;
          top: 0;
          right: 0;
          display: inline-block;
          width: 50px;
          height: 40px;
          line-height: 40px;
          text-align: center;
          vertical-align: middle;
        }
      }
    }
    .menu-box {
      flex: 1;
      background-color: #2f323f;
      overflow-y: scroll;
      .el-menu {
        padding-top: 5px;
      }
    }
  }
</style>
