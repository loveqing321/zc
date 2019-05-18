<template lang="pug">
  el-dialog(
    :visible="visible",
    :title="title",
    width="650px",
    @close="cancel"
    top="5vh"
    :close-on-click-modal="false"
  )
    el-container
      el-form(
        ref="form",
        :rules="rules",
        :model="formData",
        label-position="right",
        label-width="100px",
        :style={flex:1}
        label-suffix=":"
      )
        el-form-item(label="角色名称", prop="roleName")
          el-input(v-model="formData.roleName")
    template(slot="footer")
      el-button(@click="cancel") 取消
      el-button(type="primary" @click="confirm") 确定
</template>

<script>
import emitter from '@/mixins/emitter'
import { save } from '@/api/system/role'

export default {
  name: 'role-form',
  mixins: [
    emitter
  ],
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    formData: {
      type: Object,
      default () {
        return {}
      }
    }
  },
  data () {
    return {
      rules: {
        roleName: [
          { required: true, message: '请填写角色名称', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    title () {
      if (this.formData.id) {
        return '修改角色'
      } else {
        return '新增角色'
      }
    }
  },
  methods: {
    cancel () {
      this.$emit('update:visible', false)
    },
    async confirm () {
      try {
        const val = await this.$refs.form.validate()
        if (val) {
          await save(this.formData)
          this.$emit('update:visible', false)
          this.dispatch('role', 'form-search')
        }
      } catch (e) {
        // ignore
      }
    }
  }
}
</script>

<style scoped lang="less">
</style>
