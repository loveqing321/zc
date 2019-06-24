<template lang="pug">
  .mp-row
    .mp-row--inner(
      :class="['justify-' + justify, 'align-' + align]"
      :style="rowStyle"
    )
      slot
</template>

<script>
export default {
  name: 'MpRow',
  props: {
    gutter: Number,
    justify: {
      type: String,
      default () {
        return 'start'
      }
    },
    align: {
      type: String,
      default () {
        return 'center'
      }
    }
  },
  data () {
    const rowStyle = {}
    if (this.gutter) {
      rowStyle.marginLeft = '-' + (this.gutter / 2) + 'px'
      rowStyle.marginRight = rowStyle.marginLeft
    }
    return {
      rowStyle: rowStyle
    }
  }
}
</script>

<style scoped lang="less">
.mp-row {
  overflow: hidden;
}
.mp-row--inner {
  position: relative;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  display: flex;
  flex-wrap: wrap;
}
.mp-row--inner::after {
  clear: both;
}
.mp-row--flex {
  &.justify-center {
    justify-content: center;
  }
  &.justify-end {
    justify-content: flex-end;
  }
  &.justify-start {
    justify-content: flex-start;
  }
  &.justify-space-between {
    justify-content: space-between;
  }
  &.justify-space-around {
    justify-content: space-around;
  }
  &.align-center {
    align-items: center;
  }
  &.align-top {
    align-items: flex-start;
  }
  &.align-bottom {
    align-items: flex-end;
  }
}
</style>
