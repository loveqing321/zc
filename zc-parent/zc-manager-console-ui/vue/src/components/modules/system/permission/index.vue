<template lang="pug">
  main-content.zc-permission
    template(slot="toolbar")
      ul.zc-toolbar-ul
        li
          i(class="fa fa-cloud-download")
        li
          i(class="fa fa-cloud-download")
    template(slot="searchForm")
      el-form(ref="form" :inline="true" :model="searchForm")
        el-form-item
          el-input(v-model="searchForm.nameOrPerm" placeholder="权限名称 / 标签" style="width: 220px" clearable)
        el-form-item
          el-select(
            placeholder="权限分类"
            v-model="searchForm.category"
            :style="{display:'block', width: '150px'}"
            clearable
          )
            el-option(value="merchant_console_menu" label="菜单权限")
            el-option(value="merchant_console_data" label="数据权限")
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
        el-button(type="default" @click="add") 增加权限
    grid(ref="grid" :remote-method="getData")
      el-table-column(type="index" width="40")
      el-table-column(
        label="权限名称"
        show-overflow-tooltip
        width="140"
      )
        template(slot-scope="{row}")
          span(style="color: #409EFF;") {{row.name}}
      el-table-column(
        prop="category"
        label="权限分类"
        show-overflow-tooltip
        width="200"
      )
      el-table-column(
        prop="perm"
        label="权限标签"
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
      el-table-column(label="操作", show-overflow-tooltip, width="150")
        div.zc-operator-box(slot-scope="{row}")
          a(@click="modify(row)") 修改
          a(v-if="row.isDeleted === 0" @click="disablePermission(row)") 停用
          a(v-else @click="enablePermission(row)") 启用
          a(@click="del(row)") 删除
    permission-form(
      :visible.sync="visible"
      v-if="visible"
      :formData="formData"
    )
</template>
<script>
import mainContent from '@/components/common/main-content'
import grid from '@/components/common/grid'
import gridTitle from '@/components/common/grid-title'
import permissionForm from './permission-form'
import { queryPage, queryOne, del, save } from '@/api/system/permission'

export default {
  name: 'permission',
  components: {
    mainContent,
    grid,
    gridTitle,
    permissionForm
  },
  data () {
    return {
      searchForm: {},
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
    async disablePermission (row) {
      try {
        await this.$confirm('此操作将停用该权限, 是否继续?', '提示', {
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
    async enablePermission (row) {
      try {
        await this.$confirm('此操作将启用该权限, 是否继续?', '提示', {
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
.zc-permission {
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
