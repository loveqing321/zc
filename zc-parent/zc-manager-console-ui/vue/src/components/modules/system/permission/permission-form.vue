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
        el-form-item(label="名称", prop="name")
          el-input(v-model="formData.name")
        el-form-item(label="分类", prop="category")
          el-select(
            v-model="formData.category"
            :style="{display:'block', width: '100%'}"
            clearable
          )
            el-option(value="merchant_console_menu" label="菜单权限")
            el-option(value="merchant_console_data" label="数据权限")
        el-form-item(label="标签", prop="perm")
          el-input(v-model="formData.perm")
    template(slot="footer")
      el-button(@click="cancel") 取消
      el-button(type="primary" @click="confirm") 确定
</template>

<script>
import emitter from '@/mixins/emitter'
import { save } from '@/api/system/permission'

export default {
  name: 'permission-form',
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
        name: [
          { required: true, message: '请填写权限名称', trigger: 'blur' }
        ],
        category: [
          { required: true, message: '请选择权限分类', trigger: 'change' }
        ],
        perm: [
          { required: true, message: '请填写权限标签', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    title () {
      if (this.formData.id) {
        return '修改权限'
      } else {
        return '新增权限'
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
          this.dispatch('permission', 'form-search')
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
