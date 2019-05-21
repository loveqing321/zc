<template lang="pug">
  el-dialog(
    :visible="visible",
    title="分配角色",
    width="650px",
    @close="cancel"
    top="5vh"
    :close-on-click-modal="false"
  )
    el-container
      div.assign-role-box(style="width: 100%; height: 100%; display: flex; flex-direction: column; align-items: center;")
        el-transfer(
          v-model="assignedRoles"
          :data="allRoles"
          :filter-method="filterRoles"
          filter-placeholder="请输入角色名称"
          :props="{ key: 'id', label: 'roleName' }"
          :titles="['所有角色', '已选角色']"
          filterable
         )
    template(slot="footer")
      el-button(@click="cancel") 取消
      el-button(type="primary" @click="confirm") 确定
</template>

<script>
import emitter from '@/mixins/emitter'
import { queryAssignedRoleIds, assignRoles } from '@/api/system/user'
import { queryAll } from '@/api/system/role'

export default {
  name: 'assign-roles',
  mixins: [
    emitter
  ],
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    userId: {
      type: Number,
      required: true
    }
  },
  data () {
    return {
      assignedRoles: [],
      allRoles: []
    }
  },
  methods: {
    cancel () {
      this.$emit('update:visible', false)
    },
    async confirm () {
      try {
        await assignRoles({ userId: this.userId, roleIds: this.assignedRoles })
        this.$emit('update:visible', false)
      } catch (e) {
        this.$message.error(e.message || '分配角色失败')
      }
    },
    filterRoles (query, item) {
      return item.roleName.indexOf(query) > -1
    }
  },
  async mounted () {
    // 加载所有的角色信息，根据角色信息分隔成未选中列表和选中列表
    try {
      this.assignedRoles = []
      this.allRoles = []
      const allRoles = await queryAll()
      const assigned = await queryAssignedRoleIds({ userId: this.userId })
      allRoles.forEach(role => {
        this.allRoles.push(role)
        if (assigned.indexOf(role.id) > -1) {
          this.assignedRoles.push(role.id)
        }
      })
    } catch (e) {
      this.$message.error('加载角色信息失败！')
    }
  }
}
</script>

<style lang="less">
.assign-role-box {
  .el-transfer {
    .el-transfer-panel__body {
      height: 350px !important;
    }
  }
}
</style>
