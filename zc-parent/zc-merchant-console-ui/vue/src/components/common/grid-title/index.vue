<template lang="pug">
  .grid-title.clearfix
    span.title
      slot(name='title') {{ name }}
    .tool
      slot(name='tool')
</template>

<script>
export default {
  name: 'grid-title',
  props: {
    name: {
      type: String,
      default: ''
    }
  }
}
</script>

<style scoped lang="less">
.grid-title {
  border-bottom: 2px solid #459ad0;
  padding-bottom: 2px;
  margin-bottom: 8px;

  >span.title {
    font-size: 16px;
    font-weight: 600;
    &::before {
      width: 4px;
      height: 18px;
      background-color: #459ad0;
      display: inline-block;
      content: "";
      vertical-align: text-bottom;
      margin-right: 6px;
    }
  }
  >.tool {
    float: right;
  }
}
</style>
