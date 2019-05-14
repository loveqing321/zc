<template lang="pug">
  main-content.zc-user
    template(slot="toolbar")
      ul.zc-toolbar-ul
        li
          i(class="fa fa-cloud-download")
        li
          i(class="fa fa-cloud-download")
    template(slot="searchForm")
      el-form(ref="form" :inline="true" :model="searchForm")
        el-form-item
          el-input(v-model="searchForm.searchText" placeholder="用户名 / 账号 / 电话" style="width: 220px" clearable)
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
        el-button(type="default" @click="add") 添加用户
    grid(ref="grid" :remote-method="getData")
      el-table-column(type="index" width="40")
      el-table-column(
        prop="cname"
        label="姓名"
        width="120"
        show-overflow-tooltip
      )
      el-table-column(
        prop="username"
        label="账号"
        width="150"
        show-overflow-tooltip
      )
      el-table-column(
        prop="telephone"
        label="电话号码"
        width="150"
        show-overflow-tooltip
      )
      el-table-column(
        prop="post"
        label="职位"
        width="100"
        show-overflow-tooltip
      )
      el-table-column(
        prop="department"
        label="部门"
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
      el-table-column(prop="operate", label="操作", show-overflow-tooltip, width="120")
        div.zc-operator-box(slot-scope="{row}")
          a(@click="modify(row)") 修改
          a(v-if="row.isDeleted === 0" @click="disableUser(row)") 停用
          a(v-else @click="enableUser(row)") 启用
    user-form(
      :visible.sync="visible"
      v-if="visible"
      :formData="formData"
    )
</template>
<script>
import mainContent from '@/components/common/main-content'
import grid from '@/components/common/grid'
import gridTitle from '@/components/common/grid-title'
import userForm from './user-form'
import { queryPage, queryOne, del, save } from '@/api/system/user'

export default {
  name: 'user',
  components: {
    mainContent,
    grid,
    gridTitle,
    userForm
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
        this.formData = await queryOne({ username: row.username })
        this.visible = true
      } catch (e) {
        // ignore
      }
    },
    async disableUser (row) {
      try {
        await this.$confirm('此操作将停用该用户, 是否继续?', '提示', {
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
    async enableUser (row) {
      try {
        await this.$confirm('此操作将启用该用户, 是否继续?', '提示', {
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
    }
  },
  mounted () {
    this.search()
    this.$on('form-search', this.search)
  }
}
</script>

<style scoped lang="less">
.zc-user {
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
