<template lang="pug">
  .mp-title
    .mp-title--wrap
      span(:class="{'is-bordered': bordered}")
        span.mp-title--border
      template(v-if="$slots.default")
        slot
      template(v-else)
        span(:class="'mp-title--text-' + size" :style="textColor")
          | {{text}}
    .mp-title--toolbar(v-if="$slots.toolbar")
      template(slot="toolbar")
</template>

<script>
import mpPanel from '../mp-panel/index'
export default {
  name: 'MpTitle',
  props: {
    text: String,
    // 大小分为 extra-large, large, medium, base, small, extra-small
    size: {
      type: String,
      default: 'medium'
    },
    color: String,
    bordered: Boolean
  },
  components: {
    mpPanel
  },
  computed: {
    textColor () {
      let style = {}
      if (this.color) {
        style.color = this.color
      }
      return style
    }
  }
}
</script>

<style scoped lang="less">
@import '../variables.less';
.mp-title {
  padding: 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  .mp-title--wrap {
    font-weight: 500;
    display: flex;
    flex-direction: row;
    align-items: center;
    .is-bordered {
      display: inline-block;
      -webkit-box-sizing: border-box;
      -moz-box-sizing: border-box;
      box-sizing: border-box;
      height: 100%;
      padding: 2px 0;
      width: 15px;
      .mp-title--border {
        border-left: 4px solid @primaryColor;
      }
    }
    .mp-title--text-extra-large {
      font-size: @extraLargeFontSize;
    }
    .mp-title--text-large {
      font-size: @largeFontSize;
    }
    .mp-title--text-medium {
      font-size: @mediumFontSize;
    }
    .mp-title--text-base {
      font-size: @baseFontSize;
    }
    .mp-title--text-small {
      font-size: @smallFontSize;
    }
    .mp-title--text-extra-small {
      font-size: @extraSmallFontSize;
    }
  }
  .mp-title--toolbar {
    flex: 1;
    overflow: hidden;
  }
}
</style>
