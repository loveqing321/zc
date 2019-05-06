<template lang="pug">
  section.app-main(:class="{hideSidebar: !sidebarStatus}")
    .sidebar-wrapper
      sidebar
    .router-wrapper
      keep-alive
        router-view(v-if="$route.meta.keepAlive")
      router-view(v-if="!$route.meta.keepAlive")
</template>

<script>
import Sidebar from '../sidebar'
import { mapState } from 'vuex'

export default {
  name: 'AppMain',
  components: {
    Sidebar
  },
  computed: {
    ...mapState('app', [
      'sidebarStatus'
    ])
  }
}
</script>

<style scoped lang="less">
.app-main {
  height: calc(100% - 50px);
  position: relative;
  &.hideSidebar {
    .sidebar-wrapper {
      transform: translate(-150px, 0);
      .sidebar-container {
        transform: translate(150px, 0);
      }
    }
    .router-wrapper {
      margin-left: 40px;
    }
  }
  .sidebar-wrapper {
    width: 200px;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    z-index: 2001;
    overflow: hidden;
    transition: all 0.28s ease-out;
  }
  .router-wrapper {
    transition: all 0.28s ease-out;
    margin-left: 200px;
    height: 100%;
    overflow-y: scroll;
  }
}
</style>
