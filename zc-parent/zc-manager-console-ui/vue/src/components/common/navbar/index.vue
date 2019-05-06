<template lang="pug">
  .navbar(mode='horizontal')
    .app-title
      | 51租车 - 管理中心
    el-dropdown.avatar-container
      .avatar-wrapper
        | {{ userName || '未登录'}}
        i.el-icon-caret-bottom
      el-dropdown-menu.user-dropdown(slot='dropdown')
        el-dropdown-item
          span(@click='logout') 退出登录
</template>

<script>
import { clearToken } from '@/libs/util'
import env from '@/libs/env'

export default {
  name: 'Navbar',
  data () {
    return {
      userName: null
    }
  },
  created () {
    this.getUserInfo()
  },
  methods: {
    logout () {
      clearToken()
      window.location.href = env.baseURL
    },
    handleClickMenu (item) {
      this.$router.push({
        name: item.route
      })
    },
    async getUserInfo () {
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
    margin-left: 0px;
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
  .screenfull {
    position: absolute;
    right: 90px;
    top: 16px;
    color: red;
  }
  .avatar-container {
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 35px;
    color: #ffffff;
    .avatar-wrapper {
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
