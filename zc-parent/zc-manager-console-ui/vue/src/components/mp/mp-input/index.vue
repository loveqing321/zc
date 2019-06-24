<template lang="pug">
  div(
    :class="[type === 'textarea' ? 'mp-textarea' : 'mp-input', {'mp-input-suffix': clearable}]"
    @mouseenter="hovering = true"
    @mouseleave="hovering = false"
  )
    template(v-if="type !== 'textarea'")
      input(
        ref="input"
        class="mp-input--inner"
        :value="currentValue"
        :type="type"
        :placeholder="placeholder"
        :readonly="readonly"
        :disabled="disabled"
        v-bind="$attrs"
        @input="handleInput"
        @focus="handleFocus"
        @blur="handleBlur"
        @change="handleChange"
      )
      span(v-if="showClear" class="mp-input--suffix")
        span(class="mp-input--suffix-inner")
          i(class="mp-input--icon mp-input--clear el-icon-circle-close" @click="clear")
    input(
      v-else
      ref="textarea"
      class="mp-textarea__inner"
      :value="currentValue"
      :placeholder="placeholder"
      :readonly="readonly"
      :disabled="disabled"
      v-bind="$attrs"
      @input="handleInput"
      @focus="handleFocus"
      @blur="handleBlur"
      @change="handleChange"
    )
</template>

<script>
export default {
  name: 'MpInput',
  props: {
    type: {
      type: String,
      default () {
        return 'text'
      }
    },
    placeholder: {
      type: String
    },
    clearable: {
      type: Boolean,
      default () {
        return false
      }
    },
    readonly: {
      type: Boolean,
      default () {
        return false
      }
    },
    disabled: {
      type: Boolean,
      default () {
        return false
      }
    },
    value: {
      type: String
    }
  },
  data () {
    return {
      currentValue: this.value === undefined || this.value === null ? '' : this.value,
      hovering: false,
      focused: false
    }
  },
  computed: {
    showClear () {
      // 只读/不启用 则不显示
      if (this.readonly || this.disabled || this.type === 'textarea') {
        return false
      }
      if (this.clearable && this.currentValue !== '' && (this.hovering || this.focused)) {
        return true
      }
      return false
    }
  },
  watch: {
    value (val) {
      this.currentValue = val
    }
  },
  methods: {
    focus () {
      (this.$refs.input || this.$refs.textarea).focus()
    },
    blur () {
      (this.$refs.input || this.$refs.textarea).blur()
    },
    handleInput (event) {
      const value = event.target.value
      this.setCurrentValue(value)
      this.$emit('input', value)
    },
    handleFocus (event) {
      this.focused = true
      this.$emit('focus', event)
    },
    handleBlur (event) {
      this.focused = false
      this.$emit('blur', event)
    },
    handleChange (event) {
      this.$emit('change', event.target.value)
    },
    setCurrentValue (val) {
      this.currentValue = val
    },
    clear () {
      this.setCurrentValue('')
      this.$emit('input', '')
      this.$emit('change', '')
      this.$emit('clear')
      this.focus()
    }
  }
}
</script>

<style scoped lang="less">
@import '../variables.less';

.mp-input {
  position: relative;
  font-size: 14px;
  display: inline-block;
  width: 100%;
  .mp-input--inner {
    background-color: #ffffff;
    border: 1px solid @primaryBorderColor;
    box-sizing: border-box;
    border-radius: 2px;
    display: inline-block;
    color: @regularFontColor;
    padding: @inputPadding;
    font-size: inherit;
    width: 100%;
    transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
    height: @inputHeight;
    line-height: @inputHeight;
  }
  .mp-input--inner:hover {
    border-color: @inputHoverColor;
  }
  .mp-input--inner:focus {
    outline: none;
    border-color: @primaryColor;
  }
  .mp-input--inner::placeholder {
    color: @inputAppendColor
  }
  .mp-input--prefix, .mp-input--suffix {
    position: absolute;
    top: 0;
    text-align: center;
    transition: all .3s;
    height: 100%;
    color: @inputAppendColor;
  }
  .mp-input--suffix {
    right: 5px;
    transition: all .3s;
    .mp-input--suffix-inner {
      height: 100%;
      line-height: 100%;

      .mp-input--icon {
        height: 100%;
        width: 25px;
        text-align: center;
        transition: all .3s;
        line-height: @inputHeight;
      }
      .mp-input--icon:after {
        content: '';
        height: 100%;
        width: 0;
        display: inline-block;
        vertical-align: middle;
      }
      .mp-input--clear {
        color: #c0c4cc;
        font-size: 14px;
        line-height: 16px;
        cursor: pointer;
        transition: color .2s cubic-bezier(.645,.045,.355,1)
      }
      .mp-input--clear:hover {
        color: @secondaryFontColor;
      }
    }
  }
}
.mp-input-suffix {
  .mp-input--inner {
    padding-right: 30px;
  }
}
</style>
