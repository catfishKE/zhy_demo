<template>
  <div style="padding: 10px">
    <!--    头像-->
    <div class="demo-type" style="margin-left: 50%" >
        <el-avatar> user </el-avatar>
      </div>
    <!--内容主体-->
    <div style="padding: 10px">
    <el-descriptions style="font-size: 30px; text-align: center; padding: 20px" title="用户信息" column="2" >
      <el-descriptions-item prop="id" label="ID：" sortable/>
      <el-descriptions-item prop="platform" label="所属平台：" />
      <el-descriptions-item prop="username" label="姓名：" />
      <el-descriptions-item prop="idNum" label="身份证号：" />
      <el-descriptions-item prop="telNum" label="电话号码：" />
      <el-descriptions-item prop="nucleic" label="核酸记录：" />
      <el-descriptions-item prop="belief" label="信用记录：" />
      <el-descriptions-item prop="workAge" label="工龄：" />
      <el-descriptions-item prop="point" label="定位：" />
      <el-descriptions-item prop="way" label="轨迹：" />
      <el-descriptions-item prop="inTime'" label="进入时间：" />
      <el-descriptions-item prop="outTime" label="离开时间：" />
    </el-descriptions>

        <el-button style="margin-left: 45%" type="primary padding: 20px" size="large"
                   @click="handleEdit(scope.row)">编辑</el-button>
        <el-button type="danger padding: 20px" size="large"
                   @confirm="handleDelete(scope.row.id)">删除</el-button>
      </div>
    </div>
    <el-dialog v-model="dialogVisible" title="提示" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" style="width: 80%" />
        </el-form-item>
        <el-form-item label="工龄">
          <el-input v-model="form.workAge" style="width: 80%" />
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex" class="ml-4">
            <el-radio label="男" size="large">男</el-radio>
            <el-radio label="女" size="large">女</el-radio>
            <el-radio label="未知" size="large">未知</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" v-model="form.address" style="width: 80%" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
      </template>
    </el-dialog>

</template>

<script>
// @ is an alias to /src
// 输入框
import { ref } from 'vue'
import request from "@/utils/request";
const input = ref('')
export default {
  name: 'HomeView',
  components: {
  },

  data(){
    return{
      form:{},
      dialogVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 10,
      tableData:[]
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/user",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add(){
      this.dialogVisible = true
      this.form = {}
    },
    save(){
      if(this.form.id){
        request.put("/user",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            this.$message.success("更新成功")
          }else{
            this.$message.error(res.msg)
          }
          this.load()
          this.dialogVisible = false
        })
      } else{
        request.post("/user",this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() //刷新表格数据
          this.dialogVisible = false//关闭弹窗
        })
      }
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true;
    },
    handleDelete(id){//删除
      console.log(id)
      request.delete("/user/" + id).then(res => {
        if(res.code === '0'){
          this.$message.success("删除成功")
        }else{
          this.$message.error(res.msg)
        }
        this.load() //删除后，刷新表格数据
      })
    },
    handleSizeChange(){ //改变当前每页个数
      this.load()
    },
    handleCurrentChange(){ //改变当前页码触发
      this.load()
    },
  },
}
</script>