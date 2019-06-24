<template lang="pug">
  section.mp-container(:class="{'is-vertical': isVertical, 'is-collapsed': isCollapsed}")
    template(v-if="isVertical")
      mp-header(v-if="$slots.header" ref="header" :bgColor="headerBgColor" :color="headerColor")
        slot(name="header")
      section.mp-main-container(v-if="isVertical")
        .mp-aside--wrap(v-if="$slots.aside")
          mp-aside(:class="asideClass")
            slot(name="aside")
        .mp-container--wrap(:class="{ 'with-aside': $slots.aside }")
          mp-main(:class="mainClass")
            slot
          mp-footer(v-if="$slots.footer" :class="footerClass")
            slot(name="footer")
    section.mp-main-container(v-else)
      .mp-aside--wrap(v-if="$slots.aside")
        mp-aside(:class="asideClass")
          slot(name="aside")
      .mp-container--wrap(:class="{ 'with-aside': $slots.aside }")
        mp-main(:class="mainClass")
          slot
        mp-footer(v-if="$slots.footer" :class="footerClass")
          slot(name="footer")
</template>

<script>
import mpHeader from './mp-header'
import mpAside from './mp-aside'
import mpMain from './mp-main'
import mpFooter from './mp-footer'
export default {
  name: 'MpContainer',
  components: {
    mpHeader,
    mpAside,
    mpMain,
    mpFooter
  },
  props: {
    headerBgColor: String,
    headerColor: String,
    footerBgColor: String,
    footerColor: String,
    headerClass: String,
    asideClass: String,
    mainClass: String,
    footerClass: String
  },
  data () {
    // 包含header或者footer的话，则使用垂直布局
    let isVertical = this.$slots.header || 0
    return {
      isVertical,
      isCollapsed: false
    }
  },
  methods: {
    // 折叠Aside
    collapseAside (flag) {
      this.isCollapsed = flag
    }
  }
}
</script>

<style lang="less">
@import '../variables.less';
.mp-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  flex: 1;
  box-sizing: border-box;
  .mp-main-container {
    position: relative;
    flex: 1;
    height: 0;
    width: 100%;
    .mp-aside--wrap {
      height: 100%;
      width: @asideWidth;
      position: absolute;
      -webkit-box-sizing: border-box;
      -moz-box-sizing: border-box;
      box-sizing: border-box;
      top: 0;
      bottom: 0;
      left: 0;
      z-index: 2001;
      overflow: hidden;
      transition: all 0.28s ease-out;
      .mp-aside {
        transition: all 0.28s ease-out;
      }
    }
    .mp-container--wrap {
      height: 100%;
      display: flex;
      flex-direction: column;
      transition: all 0.28s ease-out;
      &.with-aside {
        margin-left: @asideWidth;
      }
    }
  }
  &.is-collapsed {
    .mp-aside--wrap {
      transform: translate(@asideMiniWidth - @asideWidth, 0);
    }
    .mp-aside {
      transform: translate(@asideWidth - @asideMiniWidth, 0);
    }
    .mp-container--wrap {
      &.with-aside {
        margin-left: @asideMiniWidth;
      }
    }
  }
}
.is-vertical {
  flex-direction: column;
}
</style>
