<template lang="pug">
  div
    template(v-for='item in routes')
      el-submenu(
        v-if='validParentRoute(item, actions)'
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
        v-else-if='validRoute(item, actions)'
        :to="item.path"
        :key='item.path'
      )
        el-menu-item(:index="item.name")
          i.menu-icon(v-if='item.icon' :class='item.icon')
          | {{ (item.meta && item.meta.desc) || item.name }}
</template>

<script>
export default {
  name: 'SidebarItem',
  props: {
    routes: {
      type: Array,
      required: true
    }
  },
  computed: {
    actions () {
      return ['ALL']
    }
  },
  methods: {
    validParentRoute (item, actions) {
      if (!item.children || item.children.length === 0) {
        return false
      }
      return this.validRoute(item, actions)
    },
    validRoute (item, actions) {
      return !item.hidden && (actions.indexOf('ALL') >= 0 || actions.indexOf(item.meta && item.meta.actionId) >= 0)
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
  text-indent: 10px;
}
a, a:focus, a:hover {
  cursor: pointer;
  color: inherit;
  text-decoration: none;
}
.menu-indent .el-menu-item {
  padding-left: 50px !important;
}
.menu-icon {
  width: 50px;
  height: 40px;
  line-height: 40px;
  text-align: center;
  vertical-align: middle;
}
</style>
