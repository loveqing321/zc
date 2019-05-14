<template lang="pug">
  main-content.zc-message
    template(slot="toolbar")
      ul.zc-toolbar-ul
        li
          i(class="fa fa-cloud-download")
        li
          i(class="fa fa-cloud-download")
    template(slot="searchForm")
      el-form(ref="form" :inline="true" :model="searchForm")
        el-form-item(prop="name")
          el-input(v-model="searchForm.name" placeholder="接口编码 / 名称" style="width: 220px")
        el-form-item(prop="usage")
          el-select(
            placeholder="接口用途"
            v-model="searchForm.usage"
            :style="{display:'block', width: '150px'}"
            clearable
            filterable
          )
            el-option(
              v-for="use in allUsages"
              :value="use"
              :label="use"
              :key="use"
            )
        el-button(type="primary" @click="search" style="width: 70px") 搜索
        el-button(type="default" @click="openAddInterfaceForm") 添加接口
    grid(ref="grid" :remote-method="getData")
      el-table-column(
        label="名称"
        show-overflow-tooltip
        width="250"
      )
        template(slot-scope="{row}")
          a(style="color: #409EFF; cursor: pointer" @click.stop="openUpdateInterfaceForm(row)") {{row.name}}
      el-table-column(
        prop="code"
        label="编码"
        show-overflow-tooltip
      )
</template>

<script>
import mainContent from '@/components/common/main-content'
import grid from '@/components/common/grid'
import gridTitle from '@/components/common/grid-title'

export default {
  name: 'message',
  components: {
    mainContent,
    grid,
    gridTitle
  },
  data () {
    return {
      searchForm: {},
      visible: false,
      selectedMessage: null
    }
  },
  methods: {
    search () {
      this.$refs.grid.loadData(this.searchForm)
    },
    searchWithCurrentPage () {
      this.$refs.grid.loadDataWithCurrentPage(this.searchForm)
    },
    getData (postData) {
      // return queryByPage(postData)
    }
  }
}
</script>

<style scoped lang="less">
.zc-message {
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
}
</style>
