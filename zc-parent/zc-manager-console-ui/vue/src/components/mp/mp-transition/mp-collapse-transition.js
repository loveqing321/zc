import { addClass, removeClass } from '../mp-util/index'

export default {
  name: 'MpCollapseTransition',
  functional: true,
  render (h, { props, children }) {
    const transitionClass = (props.direction === 'horizontal' ? 'horizontal-collapse-transition' : 'collapse-transition')

    const data = {
      props: {
        mode: 'out-in'
      },
      on: {
        // 开始进入之前
        beforeEnter (el) {
          addClass(el, transitionClass)
          if (!el.dataset) el.dataset = {}
          // 保存之前的位置信息
          el.dataset.oldPaddingTop = el.style.paddingTop
          el.dataset.oldPaddingBottom = el.style.paddingBottom

          // 调整元素的大小
          el.style.height = '0'
          el.style.paddingTop = 0
          el.style.paddingBottom = 0
        },

        enter (el) {
          // 进入之后
          el.dataset.oldOverflow = el.style.overflow
          if (el.scrollHeight !== 0) {
            el.style.height = el.scrollHeight + 'px'
            el.style.paddingTop = el.dataset.oldPaddingTop
            el.style.paddingBottom = el.dataset.paddingBottom
          } else {
            el.style.height = ''
            el.style.paddingTop = el.dataset.oldPaddingTop
            el.style.paddingBottom = el.dataset.paddingBottom
          }
          el.style.overflow = 'hidden'
        },

        afterEnter (el) {
          // 进入结束
          removeClass(el, transitionClass)
          el.style.height = ''
          el.style.overflow = el.dataset.oldOverflow
        },

        beforeLeave (el) {
          if (!el.dataset) el.dataset = {}
          el.dataset.oldPaddingTop = el.style.paddingTop
          el.dataset.oldPaddingBottom = el.style.paddingBottom
          el.dataset.oldOverflow = el.style.overflow

          el.style.height = el.scrollHeight + 'px'
          el.style.overflow = 'hidden'
        },

        leave (el) {
          if (el.scrollHeight !== 0) {
            addClass(el, transitionClass)
            el.style.height = 0
            el.style.paddingTop = 0
            el.style.paddingBottom = 0
          }
        },

        afterLeave (el) {
          removeClass(el, transitionClass)
          el.style.height = ''
          el.style.overflow = el.dataset.oldOverflow
          el.style.paddingTop = el.dataset.oldPaddingTop
          el.style.paddingBottom = el.dataset.oldPaddingBottom
        }
      }
    }
    return h('transition', data, children)
  }
}
