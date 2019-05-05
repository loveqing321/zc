<template lang='pug'>
  .detail-panel-container
    .detail-panel
      el-container.panel-container
        el-main
          slot
        el-footer
          slot(name="footer")
</template>

<script>

export default {
  name: 'detail-panel',
  data () {
    return {
    }
  }
}
</script>

<style scoped lang="less">
.detail-panel-container {
  position: absolute;
  right: 0;
  z-index: 500;
  height: 100%;
  padding-top: 26px;
  box-sizing: border-box;
  transition: 1.3s;
  .detail-panel {
    height: 100%;
    background: #ffffff;
    border: 1px solid #ebeef5;
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
    .panel-container {
      height: 100%;
      padding: 10px 0;
    }
  }
}
</style>
