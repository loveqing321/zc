<template lang="pug">
  .mp-route-menu
    template(v-for='item in routes')
      mp-submenu(
        v-if='validParentRoute(item)'
        :key='item.path'
        :icon="item.icon"
        :title="item.meta.desc"
      )
        mp-route-menu(
          :routes='item.children'
          :key='item.name'
        )
      router-link.mp-route--link(
        v-else-if='validRoute(item)'
        :to="item.path"
        :key='item.path'
      )
        mp-menu-item(
          :icon="item.icon"
          :title = "(item.meta && item.meta.desc) || item.name"
        )
</template>

<script>
export default {
  name: 'MpRouteMenu',
  props: {
    routes: {
      type: Array,
      required: true
    },
    permissions: {
      type: Array,
      default () {
        return []
      }
    }
  },
  computed: {
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
    validRoute () {
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
.mp-route--link {
  text-decoration: none;
}
</style>
