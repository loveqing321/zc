<template lang="pug">
  .login-wrap
    .ms-login
      .ms-title
        | 51租车 - 管理后台
      el-form.ms-content(:model="ruleForm" ref="ruleForm" label-width="0px")
        el-form-item
          el-input(v-model="ruleForm.username" placeholder="用户名")
            el-button(slot="prepend" icon="el-icon-lx-people")
        el-form-item
          el-input(type="password" v-model="ruleForm.password" placeholder="密码")
            el-button(slot="prepend" icon="el-icon-lx-lock")
        el-row(type="flex")
          el-col(:span="12")
            el-form-item
              el-input(v-model="ruleForm.verifyCode" @keyup.enter.native="submitForm")
                el-button(slot="prepend" icon="el-icon-lx-lock")
          el-col(:span="12")
            el-form-item
              div(style="width:100%; height: 32px; padding-left:5px; box-sizing: border-box; padding-top: 1px;")
                img(:src="imgUrl" style="width: 100%; height: 100%;" @click="changeVerifyCode")
        .login-btn
          el-button(type="primary" @click="submitForm")
            | 登录
        .login-tips
            | {{errorMsg}}
</template>

<script>
import env from '@/libs/env'
import axios from 'axios'
import { getCsrfToken, getToken, setToken, setUserInfo } from '@/libs/util'

export default {
  data: () => {
    return {
      imgUrl: null,
      errorMsg: null,
      ruleForm: {
        username: '13333201150',
        password: 'admin',
        verifyCode: null
      }
    }
  },
  methods: {
    changeVerifyCode () {
      this.imgUrl = env.baseURL + '/verifyCode?d=' + new Date().getTime()
    },
    submitForm () {
      if (this.ruleForm.username === null || this.ruleForm.username === undefined || this.ruleForm.username.trim().length === 0) {
        this.errorMsg = '请输入用户名'
        return false
      }
      if (this.ruleForm.password === null || this.ruleForm.password === undefined || this.ruleForm.password.trim().length === 0) {
        this.errorMsg = '请输入密码'
        return false
      }
      if (this.ruleForm.verifyCode === null || this.ruleForm.verifyCode === undefined || this.ruleForm.verifyCode.trim().length === 0) {
        this.errorMsg = '请输入验证码'
        return false
      }
      const csrfToken = getCsrfToken()
      axios({
        url: env.baseURL + '/login',
        method: 'post',
        params: {
          username: this.ruleForm.username,
          password: this.ruleForm.password,
          verifyCode: this.ruleForm.verifyCode
        },
        withCredentials: true,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'X-XSRF-TOKEN': csrfToken
        }
      }).then((result) => {
        const { code, data, message } = result.data
        if (code !== 0) {
          this.errorMsg = message
          if (code === 106 || code === 107) {
            this.changeVerifyCode()
          }
        } else {
          // 设置token
          setToken(data.token)
          // 登录成功，保存用户信息
          setUserInfo(data.principal)
          this.$router.push({
            name: 'Home'
          })
        }
      }).catch((err) => {
        this.$message('error', err)
      })
    }
  },
  created () {
    // 没有token的话，初始化该页面的数据
    this.changeVerifyCode()
  },
  mounted () {
    // 判断是否已经有token，如果有的话，则直接跳转到home
    const token = getToken()
    if (token) {
      console.log('login', token)
      this.$router.push({
        name: 'Home'
      })
      return
    }
  }
}
</script>

<style scoped>
  .login-wrap{
    position: relative;
    width:100%;
    height:100%;
    background-image: url(../assets/img/login-bg.jpg);
    background-size: 100%;
  }
  .ms-title{
    width:100%;
    line-height: 50px;
    text-align: center;
    font-size:20px;
    color: #fff;
    border-bottom: 1px solid #ddd;
  }
  .ms-login{
    position: absolute;
    left:50%;
    top:50%;
    width:350px;
    margin:-190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255,255,255, 0.3);
    overflow: hidden;
  }
  .ms-content{
    padding: 30px 30px;
  }
  .login-btn{
    text-align: center;
  }
  .login-btn button{
    width:100%;
    height:36px;
    margin-bottom: 10px;
  }
  .login-tips{
    font-size: 12px;
    line-height:30px;
    color: darkred;
    text-align: center;
  }
</style>
