<template lang="pug">
  section.app-main
    keep-alive
      router-view(v-if="$route.meta.keepAlive")
    router-view(v-if="!$route.meta.keepAlive")
</template>

<script>
export default {
  name: 'AppMain'
}
</script>

<style scoped lang="less">
.app-main {
  height: calc(100% - 50px);
}
</style>
