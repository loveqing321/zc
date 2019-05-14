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
        el-form-item(label="账号", prop="username")
          el-input(v-model="formData.username")
        el-form-item(label="姓名", prop="cname")
          el-input(v-model="formData.cname")
        el-form-item(label="租户", prop="merchantId")
          el-input(v-model="formData.merchantId")
        el-form-item(label="电话", prop="telephone")
          el-input(v-model="formData.telephone")
        el-form-item(label="部门", prop="department")
          el-input(v-model="formData.department")
        el-form-item(label="职位", prop="post")
          el-input(v-model="formData.post")
        el-form-item(label="账号超时", prop="accountExpired")
          el-radio-group(v-model="formData.accountExpired")
            el-radio(:label="0") 正常
            el-radio(:label="1") 超时
        el-form-item(label="账号锁定", prop="accountLocked")
          el-radio-group(v-model="formData.accountLocked")
            el-radio(:label="0") 正常
            el-radio(:label="1") 锁定
        el-form-item(label="密码超时", prop="credentialsExpired")
          el-radio-group(v-model="formData.credentialsExpired")
            el-radio(:label="0") 正常
            el-radio(:label="1") 超时
    template(slot="footer")
      el-button(@click="cancel") 取消
      el-button(type="primary" @click="confirm") 确定
</template>

<script>
import emitter from '@/mixins/emitter'
import { save } from '@/api/system/user'

export default {
  name: 'user-form',
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
        username: [
          { required: true, message: '请填写账号', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    title () {
      if (this.formData.id) {
        return '修改用户'
      } else {
        return '新增用户'
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
          this.dispatch('user', 'form-search')
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
