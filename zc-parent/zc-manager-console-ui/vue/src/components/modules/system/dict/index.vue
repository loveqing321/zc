<template lang="pug">
  main-content.zc-dict
    template(slot="toolbar")
      ul.zc-toolbar-ul
        li
          i(class="fa fa-cloud-download")
        li
          i(class="fa fa-cloud-download")
    template(slot="searchForm")
      el-form(ref="form" :inline="true" :model="searchForm")
        el-form-item
          el-input(v-model="searchForm.dictType" placeholder="字典类型" style="width: 180px" clearable)
        el-form-item
          el-input(v-model="searchForm.dictCode" placeholder="字典编码" style="width: 180px" clearable)
        el-form-item
          el-input(v-model="searchForm.dictValue" placeholder="字典值" style="width: 180px" clearable)
        el-form-item
          el-select(
            placeholder="状态"
            v-model="searchForm.isDeleted"
            :style="{display:'block', width: '120px'}"
            clearable
          )
            el-option(value="0" label="启用")
            el-option(value="1" label="停用")
        el-button(type="primary" @click="search" style="width: 70px") 搜索
        el-button(type="default" @click="add") 增加接口
    grid(ref="grid" :remote-method="getData")
      el-table-column(type="index" width="40")
      el-table-column(
        label="字典编码"
        show-overflow-tooltip
        width="120"
      )
        template(slot-scope="{row}")
          span(style="color: #409EFF;") {{row.dictCode}}
      el-table-column(
        prop="dictValue"
        label="字典值"
        width="150"
        show-overflow-tooltip
      )
      el-table-column(
        prop="dictType"
        label="字典类型"
        width="120"
        show-overflow-tooltip
      )
      el-table-column(
        prop="dictDesc"
        label="字典描述"
        show-overflow-tooltip
      )
      el-table-column(
        label="状态"
        width="100"
        show-overflow-tooltip
      )
        template(slot-scope="scope")
          el-tag(v-if="scope.row.isDeleted === 0" type="success" disable-transitions)
            | 启用
          el-tag(v-if="scope.row.isDeleted === 1" type="warning" disable-transitions)
            | 停用
      el-table-column(label="操作", show-overflow-tooltip, width="140")
        div.zc-operator-box(slot-scope="{row}")
          a(@click="modify(row)") 修改
          a(v-if="row.isDeleted === 0" @click="disableDict(row)") 停用
          a(v-else @click="enableDict(row)") 启用
          a(@click="del(row)") 删除
    dict-form(
      :visible.sync="visible"
      v-if="visible"
      :formData="formData"
    )
</template>
<script>
import mainContent from '@/components/common/main-content'
import grid from '@/components/common/grid'
import gridTitle from '@/components/common/grid-title'
import dictForm from './dict-form'
import { queryPage, queryOne, del, save } from '@/api/system/dict'

export default {
  name: 'dict',
  components: {
    mainContent,
    grid,
    gridTitle,
    dictForm
  },
  data () {
    return {
      searchForm: {

      },
      visible: false,
      formData: null
    }
  },
  methods: {
    search () {
      this.$refs.grid.loadData(this.searchForm)
    },
    getData (postData) {
      return queryPage(postData)
    },
    add () {
      this.formData = {}
      this.visible = true
    },
    async modify (row) {
      try {
        this.formData = await queryOne({ id: row.id })
        this.visible = true
      } catch (e) {
        // ignore
      }
    },
    async disableDict (row) {
      try {
        await this.$confirm('此操作将停用该字典值, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        })
        await save({ id: row.id, isDeleted: 1 })
        this.search()
      } catch (e) {
        // ignore
      }
    },
    async enableDict (row) {
      try {
        await this.$confirm('此操作将启用该字典值, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        })
        await save({ id: row.id, isDeleted: 0 })
        this.search()
      } catch (e) {
        // ignore
      }
    },
    async del (row) {
      try {
        await this.$confirm('此操作将删除该条目, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        })
        await del({ id: row.id })
        this.$message.info('删除成功')
        this.search()
      } catch (e) {
        // ignore
      }
    }
  },
  mounted () {
    this.search()
    this.$on('form-search', this.search)
  }
}
</script>

<style scoped lang="less">
.zc-dict {
  height: 100%;
  .zc-toolbar-ul {
    li {
      float: left;
      list-style-type: none;
      margin-left: 10px;
      i {
        color: #abc;
        cursor: pointer;
        font-size: 16px;
      }
    }
  }
  .zc-operator-box {
    a {
      cursor: pointer;
      text-decoration-line: underline;
      margin-right: 15px;
    }
    a:nth-child(4n+1) {
      color: #409EFF;
    }
    a:nth-child(4n+2) {
      color: #E6A23C;
    }
    a:nth-child(4n+3) {
      color: #67C23A;
    }
    a:nth-child(4n) {
      color: #F56C6C;
    }
  }
}
</style>
