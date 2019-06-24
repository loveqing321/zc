export default {
  props: {
    height: Number,
    icon: String,
    title: String
  },
  data () {
    return {
      root: null,
      level: 1,
      hovering: false,
      fontColor: 0,
      fontSize: 0,
      levelPadding: 10,
      gutter: 5,
      hoverBgColor: null,
      hoverColor: null,
      activeColor: null
    }
  },
  computed: {
    style () {
      let style = {}
      const padding = this.level * this.levelPadding
      style.paddingLeft = padding + 'px'
      if (this.height) {
        style.height = this.height + 'px'
        style.lineHeight = this.height + 'px'
      }
      if (this.fontSize) {
        style.fontSize = this.fontSize + 'px'
      }
      if (this.fontColor) {
        style.color = this.fontColor
      }
      if (this.hoverBgColor && this.hovering) {
        style.backgroundColor = this.hoverBgColor
      }
      if (this.hoverColor && this.hovering) {
        style.color = this.hoverColor
      }
      return style
    },
    prefixStyle () {
      let prefixStyle = {}
      if (this.gutter) {
        prefixStyle.marginRight = this.gutter + 'px'
      }
      return prefixStyle
    }
  },
  methods: {
    onMouseEnter (event) {
      this.hovering = true
      this.$emit('mouseenter', event)
    },
    onMouseLeave (event) {
      this.hovering = false
      this.$emit('mouseleave', event)
    },
    // 计算层级
    calculateMenuProps () {
      let lvl = 0
      let parent = this.$parent
      while (parent) {
        if (parent.$options.name === 'MpMenu') {
          lvl++
          this.fontColor = parent.fontColor
          this.fontSize = parent.fontSize
          this.hoverBgColor = parent.hoverBgColor
          this.hoverColor = parent.hoverColor
          this.activeColor = parent.activeColor
          if (parent.levelPadding) {
            this.levelPadding = parent.levelPadding
          }
          if (parent.gutter) {
            this.gutter = parent.gutter
          }
          this.root = parent
        }
        parent = parent.$parent
      }
      this.level = lvl
    }
  },
  created () {
    this.calculateMenuProps()
  }
}
