<template lang="pug">
  el-dialog.scene-form(
    :visible="visible",
    title="上传进度"
    width="300px",
    :show-close="false"
    top="5vh"
    :close-on-click-modal="false"
  )
    el-progress(
      :text-inside="true"
      :stroke-width="18"
      :percentage="percentage"
      status="success"
    )
</template>

<script>
export default {
  name: 'upload-progress',
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    percentage: {
      type: Number,
      default () {
        return 0
      }
    }
  },
  methods: {
    cancel () {
      this.$emit('update:visible', false)
    }
  }
}
</script>

<style scoped lang="less">
</style>
