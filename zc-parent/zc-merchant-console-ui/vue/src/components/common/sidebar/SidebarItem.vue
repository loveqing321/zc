<template lang="pug">
  div
    template(v-for='item in routes')
      el-submenu(
        v-if='validParentRoute(item)'
        :index='item.name'
        :key='item.path'
      )
        template(slot='title')
          i.menu-icon(v-if='item.icon' :class='item.icon')
          span
            | {{ item.meta.desc }}
        sidebar-item.menu-indent(
          :routes='item.children'
          :key='item.name'
        )
      router-link(
        v-else-if='validRoute(item)'
        :to="item.path"
        :key='item.path'
      )
        el-menu-item(:index="item.name")
          i.menu-icon(v-if='item.icon' :class='item.icon')
          | {{ (item.meta && item.meta.desc) || item.name }}
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'SidebarItem',
  props: {
    routes: {
      type: Array,
      required: true
    }
  },
  computed: {
    ...mapState('app', [
      'permissions'
    ]),
    validParentRoute () {
      return (item) => {
        if (!item.children || item.children.length === 0) {
          return false
        }
        if (item.hidden) return false
        if (this.permissions.indexOf('*') >= 0) return true
        if (!item.meta || !item.meta.perm) return true
        return this.permissions.indexOf(item.meta.perm) >= 0
      }
    },
    validRoute (item) {
      return (item) => {
        if (item.hidden) return false
        if (this.permissions.indexOf('*') >= 0) return true
        if (!item.meta || !item.meta.perm) return true
        return this.permissions.indexOf(item.meta.perm) >= 0
      }
    }
  }
}
</script>

<style scoped lang="less">
.svg-icon {
  margin-right: 10px;
}
.hideSidebar .menu-indent{
  display: block;
}
a, a:focus, a:hover {
  cursor: pointer;
  color: inherit;
  text-decoration: none;
}
.menu-indent {
  .el-menu-item {
    padding-left: 50px !important;
    background-color: #3f4454 !important;
  }
  .el-menu-item:hover {
    background-color: #262832 !important;
  }
}
.menu-icon {
  width: 50px;
  height: 40px;
  line-height: 40px;
  text-align: center;
  vertical-align: middle;
}
</style>
