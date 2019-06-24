<template lang="pug">
  .mp-header(:style="style")
    slot
</template>

<script>
export default {
  name: 'MpHeader',
  props: {
    bgColor: String,
    color: String
  },
  data () {
    let style = {}
    if (this.bgColor) {
      style.backgroundColor = this.bgColor
    }
    if (this.color) {
      style.color = this.color
    }
    return {
      style
    }
  }
}
</script>

<style scoped lang="less">
@import '../variables.less';
.mp-header {
  height: @headerHeight;
  line-height: @headerHeight;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  background-color: @headerBackground;
  color: @headerColor;
  padding: 0 20px;
}
</style>
