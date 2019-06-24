<template lang="pug">
  .mp-footer(:style="style")
    slot
</template>

<script>
export default {
  name: 'MpFooter',
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
.mp-footer {
  width: 100%;
  height: @footerHeight;
  line-height: @footerHeight;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  background-color: @footerBackground;
  color: @footerColor;
  padding: 0 20px;
}
</style>
