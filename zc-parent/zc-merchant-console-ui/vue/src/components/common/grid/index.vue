<template lang='pug'>
  .flex-box
    .flex-box
      el-table(
        ref="table"
        v-loading='searching'
        :data='data'
        border
        stripe
        highlight-current-row
        height='100%'
        style='width: 100%;margin-bottom: 12px;'
        @selection-change='handleSelectionChange'
        @row-click="handleRowClick"
      )
        slot
      el-pagination(
        v-if='displayPagination'
        :current-page='currentPage'
        :page-sizes='pageSizes'
        :page-size='size'
        :layout='layout'
        :total='total'
        @size-change='handleSizeChange'
        @current-change='handleCurrentChange'
      )
</template>

<script>
import { deepCopy } from '@/libs/util'

export default {
  props: {
    remoteMethod: {
      type: Function,
      required: true
    },
    displayPagination: {
      type: Boolean,
      default: true
    },
    pageSize: {
      type: Number,
      default: 20
    },
    page: {
      type: String,
      default: 'pageNo'
    },
    pageSizes: {
      type: Array,
      default: () => [15, 20, 30, 40, 50]
    },
    layout: {
      type: String,
      default: 'total, sizes, prev, pager, next, jumper'
    }
  },
  data () {
    return {
      postData: {},
      searching: false,
      data: [],
      currentPage: 1,
      size: 20,
      total: 0
    }
  },
  methods: {
    loadData (params) {
      this.currentPage = 1
      this._loadData(params)
    },
    loadDataWithCurrentPage (params) {
      this._loadData(params)
    },
    async _loadData (params) {
      if (params) {
        this.postData = deepCopy(params)
      }
      this.postData = this.postData || {}
      this.postData[this.page] = this.currentPage
      this.postData.pageSize = this.size
      this.searching = true
      try {
        const response = await this.remoteMethod(this.postData)
        if (response) {
          const { result, total } = response
          this.total = total
          this.data = result
        }
      } catch (e) {
        this.$message.error(e.message)
      }
      this.searching = false
    },
    async reload () {
      return this._loadData(this.postData)
    },
    handleSizeChange (size) {
      this.size = size
      this._loadData()
    },
    handleCurrentChange (page) {
      this.currentPage = page
      this._loadData()
    },
    handleSelectionChange (val) {
      this.$emit('selection', [...val])
    },
    handleRowClick (row) {
      this.$emit('row-click', row)
    },
    toggleRowExpansion (row) {
      this.$refs.table.toggleRowExpansion(row)
    }
  }
}
</script>

<style scoped lang="less">
.flex-box {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}
</style>
