<template lang="pug">
  el-dialog(
    :visible="visible",
    title="分配权限",
    width="650px",
    @close="cancel"
    top="5vh"
    :close-on-click-modal="false"
  )
    el-container
      div.assign-permission-box(style="width: 100%; height: 100%; display: flex; flex-direction: column; align-items: center;")
        el-transfer(
          v-model="assignedPermissions"
          :data="allPermissions"
          :filter-method="filterPermission"
          filter-placeholder="请输入权限名称"
          :props="{ key: 'id', label: 'name' }"
          :titles="['所有权限', '已选权限']"
          filterable
         )
    template(slot="footer")
      el-button(@click="cancel") 取消
      el-button(type="primary" @click="confirm") 确定
</template>

<script>
import emitter from '@/mixins/emitter'
import { queryAssignedPermissionIds, assignPermissions } from '@/api/system/role'
import { queryAll } from '@/api/system/permission'

export default {
  name: 'assign-permission',
  mixins: [
    emitter
  ],
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    roleId: {
      type: Number,
      required: true
    }
  },
  data () {
    return {
      assignedPermissions: [],
      allPermissions: []
    }
  },
  methods: {
    cancel () {
      this.$emit('update:visible', false)
    },
    async confirm () {
      try {
        await assignPermissions({ roleId: this.roleId, permissionIds: this.assignedPermissions })
        this.$emit('update:visible', false)
      } catch (e) {
        this.$message.error(e.message || '分配角色失败')
      }
    },
    filterPermission (query, item) {
      return item.name.indexOf(query) > -1
    }
  },
  async mounted () {
    // 加载所有的角色信息，根据角色信息分隔成未选中列表和选中列表
    try {
      this.assignedPermissions = []
      this.allPermissions = []
      const allPermissions = await queryAll()
      const assigned = await queryAssignedPermissionIds({ roleId: this.roleId })
      allPermissions.forEach(permission => {
        this.allPermissions.push(permission)
        if (assigned.indexOf(permission.id) > -1) {
          this.assignedPermissions.push(permission.id)
        }
      })
    } catch (e) {
      this.$message.error(e.message || '加载权限信息失败！')
    }
  }
}
</script>

<style lang="less">
.assign-permission-box {
  .el-transfer {
    .el-transfer-panel__body {
      height: 350px !important;
    }
  }
}
</style>
