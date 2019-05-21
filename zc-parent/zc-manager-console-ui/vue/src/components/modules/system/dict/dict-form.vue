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
        el-form-item(label="字典编码", prop="dictCode")
          el-input(v-model="formData.dictCode")
        el-form-item(label="字典值", prop="dictValue")
          el-input(v-model="formData.dictValue")
        el-form-item(label="字典类型", prop="dictType")
          el-input(v-model="formData.dictType")
        el-form-item(label="字典描述", prop="dictDesc")
          el-input(v-model="formData.dictDesc")
    template(slot="footer")
      el-button(@click="cancel") 取消
      el-button(type="primary" @click="confirm") 确定
</template>

<script>
import emitter from '@/mixins/emitter'
import { save } from '@/api/system/dict'

export default {
  name: 'dict-form',
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
        dictType: [
          { required: true, message: '请填写字典类型', trigger: 'blur' }
        ],
        dictCode: [
          { required: true, message: '请填写字典编码', trigger: 'blur' }
        ],
        dictValue: [
          { required: true, message: '请填写字典值', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    title () {
      if (this.formData.id) {
        return '修改字典信息'
      } else {
        return '新增字典信息'
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
          this.dispatch('dict', 'form-search')
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
