<template lang="pug">
  mp-scroll.grid-example
    mp-panel.grid-container
      mp-title.grid-title(text="栅格系统" size="large" color="#185858" bordered)
      .grid-content
        mp-panel(style="margin: 0" bordered shadowed)
          mp-row
            mp-col(:span="24")
              .grid-box
                span
                  | span = 24
          mp-row(:gutter="6" style="margin-top: 5px")
            mp-col(:span="12")
              .grid-box
                span
                  | span = 12
            mp-col(:span="12")
              .grid-box
                span
                  | span = 12
          mp-row(:gutter="6" style="margin-top: 5px")
            mp-col(:span="8" :offset="4")
              .grid-box
                span
                  | span = 8, offset = 4
            mp-col(:span="4" :offset="8")
              .grid-box
                span
                  | span = 4, offset = 8
          mp-row(:gutter="6" style="margin-top: 5px")
            mp-col(:span="8" :pull="4")
              .grid-box
                span
                  | span = 8, pull = 4
            mp-col(:span="4" :pull="4")
              .grid-box
                span
                  | span = 4, pull = 4
        .example-desc
          mp-title.grid-title(text="参数说明" size="large" color="#185858" bordered)


</template>

<script>
export default {
  name: 'GridExample'
}
</script>

<style scoped lang="less">
.grid-example {
  .grid-container {
    display: flex;
    flex-direction: column;
    .grid-title {
      margin-bottom: 10px;
    }
    .grid-content {
      flex: 1;
      height: 0;
      .grid-box {
        height: 40px;
        text-align: center;
        background-color: #DCDFE6;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        border: 1px solid #E4E7ED;
      }
      .example-desc {
        margin: 20px 0 0;
      }
    }
  }
}
</style>
