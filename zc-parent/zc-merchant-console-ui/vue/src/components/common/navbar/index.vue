<template lang="pug">
  .navbar(mode='horizontal')
    .app-title
      | 51租车 - 共享中心
    .avatar-container
      .btn-fullscreen(@click="handleFullScreen")
        i(class="el-icon-rank")
      .btn-bell
        el-tooltip(effect="dark" :content="message?`有${message}条未读消息`:`消息中心`" placement="bottom")
          router-link(to="/message")
            i(class="el-icon-bell")
        span(class="btn-bell-badge" v-if="message")
      el-dropdown
        .avatar-wrapper
          | {{ userName}}
          i.el-icon-caret-bottom
        el-dropdown-menu.user-dropdown(slot='dropdown')
          el-dropdown-item
            span(@click='logout') 退出登录
</template>

<script>
import { clearToken, clearCsrfToken, clearUserInfo, getUserInfo } from '@/libs/util'
import { mapMutations } from 'vuex'
import { logout } from '@/api/system/app'

export default {
  name: 'Navbar',
  data () {
    return {
      fullscreen: false,
      message: 1
    }
  },
  computed: {
    userName () {
      const userInfo = getUserInfo()
      if (userInfo && userInfo.cname) {
        return userInfo.cname
      }
      return ''
    }
  },
  methods: {
    ...mapMutations('app', [
      'changeSidebarStatus'
    ]),
    async logout () {
      try {
        await logout()
      } catch (e) {
        console.log(e)
      } finally {
        // 清除token
        clearToken()
        // 清除csrf token
        clearCsrfToken()
        // 清除用户信息
        clearUserInfo()
        // 跳转到登录页面
        this.$router.push({
          name: 'login'
        })
      }
    },
    handleClickMenu (item) {
      this.$router.push({
        name: item.route
      })
    },
    handleFullScreen () {
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen()
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen()
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen()
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen()
        }
      } else {
        let element = document.documentElement
        if (element.requestFullscreen) {
          element.requestFullscreen()
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen()
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen()
        } else if (element.msRequestFullscreen) {
          // IE11
          element.msRequestFullscreen()
        }
      }
      this.fullscreen = !this.fullscreen
    }
  },
  mounted () {
  }
}
</script>

<style scoped lang="less">
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0 !important;
  background-color: #2f323f;
  .app-title {
    display: inline-block;
    font-size: 18px;
    font-weight: 700;
    line-height: 50px;
    margin: 0 30px;
    color: #ffffff;
    float: left;
  }
  .header-menu {
    display: inline-block;
    font-size: 14px;
    line-height: 50px;
    margin-left: 0;
    border-left: 1px solid #9c9b9b;
    .header-menu-item {
      float: left;
      width: 120px;
      text-align: center;
      border-right: 1px solid #9c9b9b;
      color: #ffffff;
      cursor: pointer;
    }
    .header-menu-item.active {
      background: #387fc7;
    }
  }
  .errLog-container {
    display: inline-block;
    position: absolute;
    right: 150px;
  }
  .avatar-container {
    height: 50px;
    position: absolute;
    right: 35px;
    display: flex;
    align-items: center;
    .btn-bell, .btn-fullscreen{
      position: relative;
      text-align: center;
      border-radius: 15px;
      cursor: pointer;
      font-size: 22px;
      margin-right: 10px;
    }
    .btn-fullscreen{
      transform: rotate(45deg);
      color: #9a9a9a;
    }
    .btn-bell {
      .el-icon-bell{
        color: #9a9a9a;
      }
      .btn-bell-badge{
        position: absolute;
        right: 0;
        top: 14px;
        width: 8px;
        height: 8px;
        border-radius: 4px;
        background: #f56c6c;
        color: #fff;
      }
    }
    .avatar-wrapper {
      color: #ffffff;
      cursor: pointer;
      position: relative;
      .user-avatar {
        width: 40px;
        height: 40px;
        border-radius: 10px;
      }
      .el-icon-caret-bottom {
        font-size: 12px;
      }
    }
  }
}
</style>
