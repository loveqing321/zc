<template lang="pug">
  .addable-table
    el-table(
      ref="table"
      :data='value'
      border
      style='width: 100%;'
    )
      slot
      el-table-column(prop="operate", label="", width="60")
        template(slot-scope="scope")
          a.operator-action(style="color: #E6A23C;" @click.stop="delRow(scope)") 删除
</template>

<script>
export default {
  name: 'addable-table',
  props: {
    value: {
      type: Array,
      required: true
    }
  },
  methods: {
    delRow (scope) {
      this.value.splice(scope.$index, 1)
    }
  }
}
</script>

<style lang="less">
  .addable-table {
    .el-table {
      .el-table__header-wrapper {
        .el-table__header tr > th {
          background-color: transparent;
          padding: 2px 0;
        }
      }
      .el-table__body-wrapper {
        .el-table__row {
          input {
            font-size: 12px;
          }
        }
        .operator-action {
          cursor: pointer;
          text-decoration-line: underline;
          margin-right: 10px;
        }
      }
    }
  }
</style>
