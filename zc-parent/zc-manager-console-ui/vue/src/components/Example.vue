<template lang="pug">
  mp-container(ref="container" asideClass="my-aside")
    template(slot="header")
      span(style="font-size: 18px; font-weight: 400;")
        | MP 组件库
    template(slot="aside")
      route-aside(:routes="$router.options.routes[2].children")
    mp-scroll
      router-view
</template>

<script>
import routeAside from './common/aside/route-aside'
import { mapState } from 'vuex'

export default {
  components: {
    routeAside
  },
  data () {
    return {
    }
  },
  computed: {
    ...mapState('app', [
      'asideStatus'
    ])
  },
  watch: {
    asideStatus (val) {
      this.$refs.container.collapseAside(!val)
    }
  },
  methods: {
    kk (event) {
    }
  },
  created () {
    console.log(this.$router)
  },
  mounted () {
  }
}
</script>

<style lang="less">
.my-aside {
  background-color: #2f323f;
}
</style>
