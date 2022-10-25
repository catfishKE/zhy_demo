<!--物业登录-->
<template>
  <div style="width: 100% ;height: 100vh;background-color: lightgrey;overflow: hidden">
      <div style="width: 400px;margin: 100px auto">
        <div style="color:black;font-size: 30px;text-align: center ;padding: 30px 0 ">欢迎登录智慧园系统</div>
          <el-form :model="form" ref="form" size="normal" :rules="rules">
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
              <el-button style="width: 100% " type="primary" @click = "login">登 录</el-button>
            </el-form-item>
<!--            这个按键router到管理员的登录页面 -->
            <div style="float:left" >
              <el-link :underline="false" @click="$router.push('/home')">以管理员身份登录</el-link>
            </div>
<!--            可能整个弹窗给管理员发邮件 先让跳转到home-->
            <div style="margin-left: 200px" >
              <el-link :underline="false" @click="$router.push('/home')">问题解决不了？请联系管理员</el-link>
            </div>
          </el-form>

      </div>
  </div>
</template>

<style scoped>
</style>

<script>
  import request from "@/utils/request";
  import { UserFilled,Lock } from '@element-plus/icons-vue'

  export default {
    name:"Login",
    data(){
      return{
        form:{},
        rules :{
          username: [
            { required: true, message: '输入用户名', trigger: 'blur' },
          ],
          password: [
            { required: true, message: '输入密码', trigger: 'blur' },
          ],
        },
      }
    },

  methods: {

    login() {
      this.$refs['form'].validate( (valid)=> {
        if (valid){
          request.post("/user/login",this.form).then(res => {
            if (res.code === '0') {
              this.$message({type: "success", message: "登录成功"})
              this.$router.push("/wy") //登录跳转
            } else{
              this.$message({type: "error", message:"用户名或密码错误"})
            }
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