<template lang="pug">
  .mp-aside
    slot
</template>

<script>
export default {
  name: 'MpAside'
}
</script>

<style scoped lang="less">
@import '../variables.less';
.mp-aside {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  position: relative;
}

</style>
