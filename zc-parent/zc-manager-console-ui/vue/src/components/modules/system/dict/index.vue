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
          el-input(v-model="searchForm.name" placeholder="接口编码 / 名称" style="width: 220px")
        el-button(type="primary" @click="search" style="width: 70px") 搜索
        el-button(type="default" @click="add") 增加接口
    grid(ref="grid" :remote-method="getData")
      el-table-column(type="index" width="40")
      el-table-column(
        label="名称"
        show-overflow-tooltip
        width="250"
      )
        template(slot-scope="{row}")
          span(style="color: #409EFF;") {{row.name}}
      el-table-column(
        prop="code"
        label="编码"
        show-overflow-tooltip
      )
      el-table-column(label="操作", show-overflow-tooltip, width="120")
        div.zc-operator-box(slot-scope="{row}")
          a(@click="modify(row)") 修改
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
import { queryPage, queryOne, del } from '@/api/system/dict'

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
