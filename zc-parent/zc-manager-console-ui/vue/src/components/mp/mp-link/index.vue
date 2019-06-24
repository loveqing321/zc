<template lang="pug">
  a.mp-link(:class="typeClasses")
    span.mp-link--inner
      slot
</template>

<script>
export default {
  name: 'MpLink',
  props: {
    type: {
      type: String,
      default: 'default'
    },
    underline: Boolean,
    disabled: Boolean,
    href: String
  },
  computed: {
    typeClasses () {
      let classes = []
      classes.push('mp-link--' + this.type)
      this.underline && classes.push('is-underline')
      this.disabled && classes.push('is-disabled')
      return classes
    }
  }
}
</script>

<style scoped lang="less">
@import '../variables.less';
.mp-link {
  display: inline-flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  vertical-align: center;
  position: relative;
  text-decoration: none;
  outline: none;
  cursor: pointer;
  padding: 0;
  font-size: @baseFontSize;
  font-weight: 500;
}
</style>
