<template lang="pug">
  .mp-col(:class="classList" :style="style")
    slot
</template>

<script>
export default {
  name: 'MpCol',
  props: {
    span: {
      type: Number,
      default: 24
    },
    offset: Number,
    pull: Number,
    push: Number
  },
  data () {
    const classList = []
    let style = {}
    const gutter = this.gutter()
    if (gutter !== 0) {
      style.paddingLeft = (gutter / 2) + 'px'
      style.paddingRight = (gutter / 2) + 'px'
    }
    ['span', 'offset', 'pull', 'push'].forEach(prop => {
      if (this[prop] || this[prop] === 0) {
        classList.push(`mp-col-${prop}-${this[prop]}`)
      }
    })
    return {
      style
    }
  },
  computed: {
    classList () {
      const classList = []
      if (this.span || this.span === 0) {
        classList.push(`mp-col-span-${this.span}`)
      }
      if (this.offset || this.offset === 0) {
        classList.push(`mp-col-offset-${this.offset}`)
      }
      if (this.pull || this.pull === 0) {
        classList.push(`mp-col-pull-${this.pull}`)
      }
      if (this.push || this.push === 0) {
        classList.push(`mp-col-push-${this.push}`)
      }
      return classList
    }
  },
  methods: {
    gutter () {
      let parent = this.$parent
      while (parent && parent.$options.name !== 'MpRow') {
        parent = parent.$parent
      }
      return parent ? parent.gutter : 0
    }
  }
}
</script>

<style lang="less">
@import '../variables.less';
.mp-col {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
.mp-col-pull-0, .mp-col-pull-1, .mp-col-pull-2, .mp-col-pull-3, .mp-col-pull-4, .mp-col-pull-5, .mp-col-pull-6, .mp-col-pull-7, .mp-col-pull-8, .mp-col-pull-9, .mp-col-pull-10, .mp-col-pull-11, .mp-col-pull-12, .mp-col-pull-13, .mp-col-pull-14, .mp-col-pull-15, .mp-col-pull-16, .mp-col-pull-17, .mp-col-pull-18, .mp-col-pull-19, .mp-col-pull-20, .mp-col-pull-21, .mp-col-pull-22, .mp-col-pull-23, .mp-col-pull-24, .mp-col-push-0, .mp-col-push-1, .mp-col-push-2, .mp-col-push-3, .mp-col-push-4, .mp-col-push-5, .mp-col-push-6, .mp-col-push-7, .mp-col-push-8, .mp-col-push-9, .mp-col-push-10, .mp-col-push-11, .mp-col-push-12, .mp-col-push-13, .mp-col-push-14, .mp-col-push-15, .mp-col-push-16, .mp-col-push-17, .mp-col-push-18, .mp-col-push-19, .mp-col-push-20, .mp-col-push-21, .mp-col-push-22, .mp-col-push-23, .mp-col-push-24 {
  position: relative;
}
.mp-col-span-0 {
  display: none;
  width: 0;
}
.mp-col-span-1 {
  width: @grid1;
}
.mp-col-span-2 {
  width: @grid2;
}
.mp-col-span-3 {
  width: @grid3;
}
.mp-col-span-4 {
  width: @grid4;
}
.mp-col-span-5 {
  width: @grid5;
}
.mp-col-span-6 {
  width: @grid6;
}
.mp-col-span-7 {
  width: @grid7;
}
.mp-col-span-8 {
  width: @grid8;
}
.mp-col-span-9 {
  width: @grid9;
}
.mp-col-span-10 {
  width: @grid10;
}
.mp-col-span-11 {
  width: @grid11;
}
.mp-col-span-12 {
  width: @grid12;
}
.mp-col-span-13 {
  width: @grid13;
}
.mp-col-span-14 {
  width: @grid14;
}
.mp-col-span-15 {
  width: @grid15;
}
.mp-col-span-16 {
  width: @grid16;
}
.mp-col-span-17 {
  width: @grid17;
}
.mp-col-span-18 {
  width: @grid18;
}
.mp-col-span-19 {
  width: @grid19;
}
.mp-col-span-20 {
  width: @grid20;
}
.mp-col-span-21 {
  width: @grid21;
}
.mp-col-span-22 {
  width: @grid22;
}
.mp-col-span-23 {
  width: @grid23;
}
.mp-col-span-24 {
  width: @grid24;
}
.mp-col-offset-0 {
  margin-left: 0;
}
.mp-col-offset-1 {
  margin-left: @grid1;
}
.mp-col-offset-2 {
  margin-left: @grid2;
}
.mp-col-offset-3 {
  margin-left: @grid3;
}
.mp-col-offset-4 {
  margin-left: @grid4;
}
.mp-col-offset-5 {
  margin-left: @grid5;
}
.mp-col-offset-6 {
  margin-left: @grid6;
}
.mp-col-offset-7 {
  margin-left: @grid7;
}
.mp-col-offset-8 {
  margin-left: @grid8;
}
.mp-col-offset-9 {
  margin-left: @grid9;
}
.mp-col-offset-10 {
  margin-left: @grid10;
}
.mp-col-offset-11 {
  margin-left: @grid11;
}
.mp-col-offset-12 {
  margin-left: @grid12;
}
.mp-col-offset-13 {
  margin-left: @grid13;
}
.mp-col-offset-14 {
  margin-left: @grid14;
}
.mp-col-offset-15 {
  margin-left: @grid15;
}
.mp-col-offset-16 {
  margin-left: @grid16;
}
.mp-col-offset-17 {
  margin-left: @grid17;
}
.mp-col-offset-18 {
  margin-left: @grid18;
}
.mp-col-offset-19 {
  margin-left: @grid19;
}
.mp-col-offset-20 {
  margin-left: @grid20;
}
.mp-col-offset-21 {
  margin-left: @grid21;
}
.mp-col-offset-22 {
  margin-left: @grid22;
}
.mp-col-offset-23 {
  margin-left: @grid23;
}
.mp-col-offset-24 {
  margin-left: @grid24;
}
.mp-col-pull-0 {
  left: 0;
}
.mp-col-pull-1 {
  left: @grid1;
}
.mp-col-pull-2 {
  left: @grid2;
}
.mp-col-pull-3 {
  left: @grid3;
}
.mp-col-pull-4 {
  left: @grid4;
}
.mp-col-pull-5 {
  left: @grid5;
}
.mp-col-pull-6 {
  left: @grid6;
}
.mp-col-pull-7 {
  left: @grid7;
}
.mp-col-pull-8 {
  left: @grid8;
}
.mp-col-pull-9 {
  left: @grid9;
}
.mp-col-pull-10 {
  left: @grid10;
}
.mp-col-pull-11 {
  left: @grid11;
}
.mp-col-pull-12 {
  left: @grid12;
}
.mp-col-pull-13 {
  left: @grid13;
}
.mp-col-pull-14 {
  left: @grid14;
}
.mp-col-pull-15 {
  left: @grid15;
}
.mp-col-pull-16 {
  left: @grid16;
}
.mp-col-pull-17 {
  left: @grid17;
}
.mp-col-pull-18 {
  left: @grid18;
}
.mp-col-pull-19 {
  left: @grid19;
}
.mp-col-pull-20 {
  left: @grid20;
}
.mp-col-pull-21 {
  left: @grid21;
}
.mp-col-pull-22 {
  left: @grid22;
}
.mp-col-pull-23 {
  left: @grid23;
}
.mp-col-pull-24 {
  left: @grid24;
}
.mp-col-push-0 {
  right: 0;
}
.mp-col-push-1 {
  right: @grid1;
}
.mp-col-push-2 {
  right: @grid2;
}
.mp-col-push-3 {
  right: @grid3;
}
.mp-col-push-4 {
  right: @grid4;
}
.mp-col-push-5 {
  right: @grid5;
}
.mp-col-push-6 {
  right: @grid6;
}
.mp-col-push-7 {
  right: @grid7;
}
.mp-col-push-8 {
  right: @grid8;
}
.mp-col-push-9 {
  right: @grid9;
}
.mp-col-push-10 {
  right: @grid10;
}
.mp-col-push-11 {
  right: @grid11;
}
.mp-col-push-12 {
  right: @grid12;
}
.mp-col-push-13 {
  right: @grid13;
}
.mp-col-push-14 {
  right: @grid14;
}
.mp-col-push-15 {
  right: @grid15;
}
.mp-col-push-16 {
  right: @grid16;
}
.mp-col-push-17 {
  right: @grid17;
}
.mp-col-push-18 {
  right: @grid18;
}
.mp-col-push-19 {
  right: @grid19;
}
.mp-col-push-20 {
  right: @grid20;
}
.mp-col-push-21 {
  right: @grid21;
}
.mp-col-push-22 {
  right: @grid22;
}
.mp-col-push-23 {
  right: @grid23;
}
.mp-col-push-24 {
  right: @grid24;
}

</style>
