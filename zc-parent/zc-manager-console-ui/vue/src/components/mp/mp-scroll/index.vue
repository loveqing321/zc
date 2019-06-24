<template lang="pug">
  .mp-scroll
    .mp-scroll--inner
      slot
</template>

<script>
export default {
  name: 'mp-scroll'
}
</script>

<style scoped lang="less">
@import '../variables.less';
.mp-scroll {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
  .mp-scroll--inner {
    width: calc(~"100% + " @scrollBarWidth);
    height: calc(~"100% + " @scrollBarWidth);
    overflow: scroll;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    padding: 0 @scrollBarWidth @scrollBarWidth 0;
  }
}
</style>
