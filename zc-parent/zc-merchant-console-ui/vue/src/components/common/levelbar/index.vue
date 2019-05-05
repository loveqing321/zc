<template lang="pug">
  el-breadcrumb.app-levelbar(separator='/')
    el-breadcrumb-item(v-for='item in levelList', :key='item.desc + item.href')
      a(:href='item.href') {{ item.desc }}
</template>

<script>
export default {
  name: 'Levelbar',
  data () {
    return {
      levelList: null
    }
  },
  methods: {
    getBreadcrumb () {
      const breadcrumb = [].concat(this.$route.meta.breadcrumb || [])
      this.levelList = breadcrumb.map(item => {
        // 找到指定名称的路由
        const { route, href } = this.$router.resolve(Object.assign({}, item), { params: this.$route.params })
        return {
          desc: route.meta.desc || route.name,
          href
        }
      })
      this.levelList.push({ desc: this.$route.meta.desc })
    }
  }
}
</script>

<style scoped>

</style>
