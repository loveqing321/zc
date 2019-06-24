<template lang="pug">
  li.mp-menu-item(
    @mouseenter="onMouseEnter"
    @mouseleave="onMouseLeave"
    @click="onClickMenuItem"
    :data-level="level"
    :style="[style, activeStyle]"
  )
    i.mp-menu-item--prefix(v-if="icon" :class="icon" :style="prefixStyle")
    span {{title}}
    span.mp-menu-item--append(v-if="$slots.append")
      mp-fade-transition
        span(v-if="hovering")
          slot(name="append")
</template>

<script>
import mpFadeTransition from '../mp-transition/mp-fade-transition'
import mixins from './mixins'
import emitter from '../mp-mixins/emitter'
export default {
  name: 'MpMenuItem',
  mixins: [
    mixins,
    emitter
  ],
  components: {
    mpFadeTransition
  },
  data () {
    return {
      isActive: false
    }
  },
  computed: {
    activeStyle () {
      let activeStyle = {}
      if (this.isActive && this.activeColor) {
        activeStyle.color = this.activeColor
      }
      return activeStyle
    }
  },
  methods: {
    clearActive () {
      this.isActive = false
    },
    onClickMenuItem () {
      this.root.clearActiveItem()
      this.isActive = true
    }
  },
  created () {
    this.$on('clearActive', this.clearActive)
  }
}
</script>

<style lang="less">
@import '../variables.less';
.mp-menu-item {
  height: @defaultMenuItemHeight;
  line-height: @defaultMenuItemHeight;
  font-size: @baseFontSize;
  color: @primaryFontColor;
  padding: 0 10px;
  list-style: none;
  cursor: pointer;
  position: relative;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  white-space: nowrap;
  transition: border-color .3s, background-color .3s, color .3s;
  &.is-active {
    color: @primaryColor;
  }
  &.is-disabled {
    background: none !important;
    cursor: not-allowed;
    opacity: 0.25;
  }
  &:hover, &:focus {
    outline: none;
    background-color: @defaultActiveColor;
  }
  * {
    vertical-align: center;
  }
  .mp-menu-item--prefix {
    margin-right: 5px;
  }
  .mp-menu-item--append {
    font-size: @extraSmallFontSize;
    color: @secondaryFontColor;
    position: absolute;
    top: 0;
    right: 20px;
    height: 100%;
    display: inline-flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    transition: transform .3s;
    * {
      margin-left: 5px;
    }
  }
}
</style>
