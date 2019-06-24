<template lang="pug">
  ul.mp-menu
    slot
</template>

<script>
import emitter from '../mp-mixins/emitter'
export default {
  name: 'MpMenu',
  mixins: [
    emitter
  ],
  props: {
    fontColor: String,
    fontSize: Number,
    gutter: {
      type: Number,
      default: 5
    },
    levelPadding: {
      type: Number,
      default: 10
    },
    hoverBgColor: String,
    hoverColor: String,
    activeColor: String
  },
  methods: {
    closeAllSubmenu () {
      this.broadcast('MpSubmenu', 'close')
    },
    // 清除选中项
    clearActiveItem () {
      this.broadcast('MpMenuItem', 'clearActive')
    }
  }
}
</script>

<style lang="less">
.mp-menu {
  list-style: none;
  position: relative;
  margin: 0;
  padding: 0;
  background-color: transparent;
}
.mp-menu-enter-active, .mp-menu-leave-active {
  transition: all .3s cubic-bezier(.55,0,.1,1);
}
.mp-menu-enter, .mp-menu-leave-to {
  opacity: 0.2;
  height: 0;
}
</style>
