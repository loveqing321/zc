<template lang="pug">
  main-content.zc-role
    template(slot="toolbar")
      ul.zc-toolbar-ul
        li
          i(class="fa fa-cloud-download")
        li
          i(class="fa fa-cloud-download")
    template(slot="searchForm")
      el-form(ref="form" :inline="true" :model="searchForm")
        el-form-item
          el-input(v-model="searchForm.roleName" placeholder="角色名" style="width: 180px" clearable)
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
        el-button(type="default" @click="add") 增加角色
        el-button(type="default" @click="assignPermission") 分配权限
    grid(ref="grid" :remote-method="getData" @selection="handleSelectionChange")
      el-table-column(type="selection" width="55")
      el-table-column(
        label="角色名"
        show-overflow-tooltip
      )
        template(slot-scope="{row}")
          span(style="color: #409EFF;") {{row.roleName}}
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
          a(v-if="row.isDeleted === 0" @click="disableRole(row)") 停用
          a(v-else @click="enableRole(row)") 启用
          a(@click="del(row)") 删除
    role-form(
      :visible.sync="visible"
      v-if="visible"
      :formData="formData"
    )
    assign-permission(
      :visible.sync="assignPermissionVisible"
      v-if="assignPermissionVisible"
      :roleId="selectedRoleId"
    )
</template>
<script>
import mainContent from '@/components/common/main-content'
import grid from '@/components/common/grid'
import gridTitle from '@/components/common/grid-title'
import roleForm from './role-form'
import assignPermission from './assign-permission'
import { queryPage, queryOne, save, del } from '@/api/system/role'

export default {
  name: 'role',
  components: {
    mainContent,
    grid,
    gridTitle,
    roleForm,
    assignPermission
  },
  data () {
    return {
      searchForm: {},
      visible: false,
      formData: null,
      selectedRows: [],
      assignPermissionVisible: false
    }
  },
  computed: {
    selectedRoleId () {
      if (this.selectedRows && this.selectedRows.length > 0) {
        return this.selectedRows[0].id
      } else {
        return -1
      }
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
    async disableRole (row) {
      try {
        await this.$confirm('此操作将停用该角色, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        })
        await save({ id: row.id, isDeleted: 1 })
        this.$message.info('删除成功')
        this.search()
      } catch (e) {
        // ignore
      }
    },
    async enableRole (row) {
      try {
        await this.$confirm('此操作将启用该角色, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        })
        await save({ id: row.id, isDeleted: 0 })
        this.$message.info('删除成功')
        this.search()
      } catch (e) {
        // ignore
      }
    },
    async del (row) {
      try {
        await this.$confirm('此操作将删除该角色, 是否继续?', '提示', {
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
    },
    handleSelectionChange (val) {
      this.selectedRows = val
    },
    assignPermission () {
      if (!this.selectedRows || this.selectedRows.length === 0) {
        this.$message.warning('请选择要操作的角色')
        return
      } else if (this.selectedRows.length > 1) {
        this.$message.warning('请选择一个角色进行分配权限')
        return
      }
      this.assignPermissionVisible = true
    }
  },
  mounted () {
    this.search()
    this.$on('form-search', this.search)
  }
}
</script>

<style scoped lang="less">
.zc-role {
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
