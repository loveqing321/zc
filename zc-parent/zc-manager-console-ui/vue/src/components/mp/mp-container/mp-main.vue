<template lang="pug">
  main.mp-main
    slot
</template>

<script>
export default {
  name: 'MpMain',
  data () {
    return {
    }
  }
}
</script>

<style scoped lang="less">
@import '../variables.less';
.mp-main {
  width: 100%;
  overflow: scroll;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  flex: 1;
  background-color: @mainBackground;
  position: relative;
}
</style>
