<template lang="pug">
  li.mp-submenu(:class="{'is-opened': isOpened}")
    .mp-submenu--title(
      key="title"
      @mouseenter="onMouseEnter"
      @mouseleave="onMouseLeave"
      @click="onClickSubTitle"
      :data-level="level"
      :style="style"
    )
      i.mp-submenu--prefix(v-if="showCollapsePrefix" class="mp-submenu--collapse el-icon-arrow-down"  :style="prefixStyle")
      i.mp-submenu--prefix(v-if="icon" :class="icon" :style="prefixStyle")
      span {{title}}
      span.mp-submenu--append(v-if="$slots.append")
        i(v-if="showCollapseSuffix" class="mp-submenu--collapse el-icon-arrow-down")
        mp-fade-transition
          span(v-if="hovering")
            slot(name="append")
    mp-collapse-transition
      mp-menu(v-if="$slots.default && isOpened" key="menu")
        slot
</template>

<script>
import mpMenu from './index'
import mpCollapseTransition from '../mp-transition/mp-collapse-transition'
import mpFadeTransition from '../mp-transition/mp-fade-transition'
import mixins from './mixins'
import emitter from '../mp-mixins/emitter'
export default {
  name: 'MpSubmenu',
  mixins: [
    mixins,
    emitter
  ],
  components: {
    mpMenu,
    mpCollapseTransition,
    mpFadeTransition
  },
  props: {
    showCollapsible: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      isOpened: false
    }
  },
  computed: {
    // 是否在前面显示collapse
    showCollapsePrefix () {
      if (this.icon) {
        return false
      }
      return this.showCollapsible
    },
    // 是否在后面显示collapse
    showCollapseSuffix () {
      return this.icon && this.showCollapsible
    }
  },
  methods: {
    onClickSubTitle () {
      this.isOpened = !this.isOpened
      this.$emit('open', this.isOpened)
    },
    close () {
      this.isOpened = false
      this.$emit('close', this.isOpened)
    },
    open () {
      this.isOpened = true
      this.$emit('open', this.isOpened)
    },
    isClosed () {
      return !this.isOpened
    }
  },
  created () {
    this.$on('close', this.close)
  }
}
</script>

<style lang="less">
@import '../variables.less';
.mp-submenu {
  list-style: none;
  margin: 0;
  padding: 0;
  .mp-submenu--title {
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
    &:hover, &:focus {
      outline: none;
      background-color: @defaultActiveColor;
    }
    * {
      vertical-align: center;
    }
    .mp-submenu--prefix {
      margin-right: 5px;
    }
    .mp-submenu--append {
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
  .mp-submenu--collapse {
    transition: transform .3s;
  }
  &.is-opened {
    .mp-submenu--collapse {
      -webkit-transform: rotateZ(180deg);
      -moz-transform: rotateZ(180deg);
      -ms-transform: rotateZ(180deg);
      -o-transform: rotateZ(180deg);
      transform: rotateZ(180deg);
    }
  }
}
.mp-submenu-enter, .list-complete-leave-to {
  opacity: 0;
  height: 0;
}
.mp-submenu-enter-active, .mp-submenu-leave-active {
  transition: all .3s ease;
}
</style>
