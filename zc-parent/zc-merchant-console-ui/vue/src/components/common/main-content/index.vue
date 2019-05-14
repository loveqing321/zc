<template lang="pug">
  el-container.zc-main-content
    el-header(height="40px")
      .zc-content-header
        .zc-breadcrumb
          el-breadcrumb(separator="/")
            el-breadcrumb-item(v-for="(item, index) in breadcrumbList" :key="index")
              span.zc-breadcrumb-text {{item}}
        .zc-toolbar
          slot(name="toolbar")
    el-main(style="display: flex; flex-direction: column; position: relative")
      .zc-search-form
        slot(name="searchForm")
      slot
</template>

<script>
  export default {
    name: 'main-content',
    computed: {
      breadcrumbList () {
        let breadcrumbList = [].concat(this.$route.meta.breadcrumb || [])
        breadcrumbList = breadcrumbList.map(item => {
          // 找到指定名称的路由
          const { route } = this.$router.resolve(item)
          return route.meta.desc
        })
        if (this.$route.meta.desc) {
          breadcrumbList.push(this.$route.meta.desc)
        }
        return breadcrumbList
      }
    }
  }
</script>

<style scoped lang="less">
  .zc-main-content {
    height: 100%;
    .zc-content-header {
      height: 100%;
      border-bottom: 1px solid #eee;
      display: flex;
      justify-content: space-between;
      align-items: center;
      .zc-breadcrumb {
        .zc-breadcrumb-text {
          font-size: 13px;
          font-weight: 700;
        }
      }
      .zc-toolbar {
        height: 100%;
        display: flex;
        align-items: center;
      }
    }
    .zc-search-form {
      display: flex;
      align-items: center;
      flex-wrap: wrap;
      padding-top: 15px;
    }
  }
</style>
