<template>
  <div style="width: 100%; height: 100vh; background-color: mediumslateblue; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 50px">欢迎登录</div>
      <el-form :model="form" size="normal" ref="form" :rules="rules">
        <el-form-item prop="username">
          <el-input
              v-model="form.username"
              style="width: 50vh;margin-bottom: 5px"
              size="large"
              placeholder="用户名"
              :prefix-icon="UserFilled"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              v-model="form.password"
              style="width: 50vh;margin-bottom: 5px"
              size="large"
              placeholder="密码"
              show-password
              :prefix-icon="Lock"/>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" size="large" type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import { UserFilled,Lock } from '@element-plus/icons-vue'
import request from "@/utils/request";


export default {
  name: "Login",
  data(){
    return{
      form:{},
      rules:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
      }
    }
  },
  methods:{
    login(){
      request.post("/user/login",this.form).then(res => {
        if (res.code == '0') {
          this.$message({
            type: "success",
            message: "登录成功"
          })
          //登录成功后页面跳转,跳转到主页
          this.$router.push("/")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  },
  //icon
  setup(){
    return{
      UserFilled,Lock
    }
  }
}
</script>

<style scoped>

</style>