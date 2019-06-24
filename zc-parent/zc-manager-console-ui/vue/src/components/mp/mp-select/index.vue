<template lang="pug">
  .mp-select(ref="mpSelect" @click="onClickInput")
    mp-input(v-model="value1" :placeholder="placeholder" readonly)

</template>

<script>
import mpInput from '../mp-input/index'

export default {
  name: 'MpSelect',
  components: {
    mpInput
  },
  props: {
    value: {
      type: String,
      required: true
    },
    placeholder: {
      type: String
    },
    maxHeight: {
      type: Number,
      default () {
        return 50
      }
    }
  },
  data () {
    return {
      value1: this.value,
      screenHeight: document.documentElement.clientHeight
    }
  },
  watch: {
    value1 (val) {
      this.$emit('input', val)
    }
  },
  methods: {
    // 点击输入框
    onClickInput () {
      console.log('click')
      // 获取底部的距离
      console.log(this.$refs.mpSelect.offsetTop)
      console.log(this.$refs.mpSelect.clientHeight)
      console.log(this.screenHeight)
    }
  },
  mounted () {
    window.onresize = () => {
      this.screenHeight = document.documentElement.clientHeight
    }
  }
}
</script>

<style scoped lang="less">
.mp-select {
  position: relative;
}
</style>
