<template lang="pug">
  .mp-panel(:class="[{'mp-border': bordered, 'round': bordered, 'secondary-shadow': shadowed}]")
    slot
</template>

<script>
export default {
  name: 'MpPanel',
  props: {
    bordered: {
      type: Boolean,
      default: false
    },
    shadowed: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style scoped lang="less">
@import '../variables.less';
.mp-panel {
  position: relative;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  padding: 10px;
  margin: 10px;
}
</style>
