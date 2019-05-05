<template lang="pug">
  .dynamic-table
    table.dynamic-table-header(cellspacing="0" cellpading="0" border="0")
      thead(style="color: #909399; font-weight: 500;")
        tr
          th.header-column(
            v-for="h in header"
            colspan="1"
            rowspan="1"
          )
            .header-cell(:style="{width: cellWidth + 'px'}" :title="h")
              | {{h}}
    .dynamic-table-body-wrapper(:style="{width: width + 'px'}")
      table.dynamic-table-body(cellspacing="0" cellpading="0" border="0")
        tbody
          tr(
            v-for="(line, index) in lines"
            :key="index"
          )
            td.body-column(
              v-for="(v, index) in line"
              colspan="1"
              rowspan="1"
            )
              .body-cell(:style="{width: cellWidth + 'px'}" :title="v")
                | {{v}}
</template>

<script>
export default {
  name: 'dynamic-table',
  props: {
    value: {
      type: Array,
      required: true,
      default: () => []
    }
  },
  data () {
    return {
      header: [],
      lines: [],
      cellWidth: 120
    }
  },
  watch: {
    value () {
      // 因为数据的格式为 List<Map<String, Object>> 格式，从数据中解析出头
      const header = []
      if (this.value.length > 0) {
        for (let i in this.value[0]) {
          header.push(i)
        }
      }
      const lines = []
      if (this.value.length > 0) {
        let line
        for (let i = 0; i < this.value.length; i++) {
          line = []
          for (let j = 0; j < header.length; j++) {
            line.push(this.value[i][header[j]])
          }
          lines.push(line)
        }
      }
      this.header = header
      this.lines = lines
    }
  },
  computed: {
    width () {
      return this.header.length * this.cellWidth + this.header.length
    }
  }
}
</script>

<style scoped lang="less">
  .dynamic-table {
    width: 100%;
    height: 100%;
    overflow-x: auto;
    border: 1px solid #ebeef5;
    .dynamic-table-header {
      table-layout: fixed;
      border-collapse: separate;
      .header-column {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        background-color: #f0f3f7;
        border-right: 1px solid #ebeef5;
        border-bottom: 1px solid #ebeef5;
        overflow: hidden;
        padding: 8px 0;
        vertical-align: middle;
        position: relative;
        text-align: left;
        .header-cell {
          white-space: nowrap;
          overflow: hidden;
          word-break: break-all;
          text-overflow: ellipsis;
          box-sizing: border-box;
          line-height: 23px;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    }
    .dynamic-table-body-wrapper {
      height: calc(100% - 40px);
      overflow-y: auto;
      .dynamic-table-body {
        table-layout: fixed;
        border-collapse: separate;
        .body-column {
          -webkit-box-sizing: border-box;
          -moz-box-sizing: border-box;
          box-sizing: border-box;
          display: table-cell;
          border-right: 1px solid #ebeef5;
          border-bottom: 1px solid #ebeef5;
          overflow: hidden;
          padding: 8px 0;
          vertical-align: middle;
          position: relative;
          text-align: left;
          .body-cell {
            white-space: nowrap;
            overflow: hidden;
            word-break: break-all;
            text-overflow: ellipsis;
            box-sizing: border-box;
            line-height: 23px;
            padding-left: 5px;
            padding-right: 5px;
          }
        }
      }
    }
  }
</style>
