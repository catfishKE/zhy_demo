<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0;">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20% " clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load()">查询</el-button>
    </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%"
        :row-class-name="tableRowClassName">
      <el-table-column prop="id" label="id" sortable/>
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="idNumber" label="身份证号"  />
      <el-table-column prop="platform" label="所属平台" />
      <el-table-column prop="phoneNumber" label="电话号码" />

      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button style="margin-left: 12px" @click="$router.push('/admin/more')" type="primary" >详情</el-button>
          <!--<el-button  @click="handleEdit(scope.row)">编辑</el-button>-->
          <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>

    <el-dialog v-model="dialogVisible" title="新增信息" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="所属平台">
          <el-radio-group v-model="form.platform" class="ml-4">
            <el-radio style="color: #baba0e" label="美团" size="large">美团</el-radio>
            <el-radio style="color: blue" label="饿了么" size="large">饿了么</el-radio>
            <el-radio label="其他" size="large">其他</el-radio>
          </el-radio-group>
        </el-form-item><el-form-item label="姓名">
        <el-input v-model="form.username" style="width: 80%" />
      </el-form-item><el-form-item label="身份证号">
        <el-input v-model="form.idNum" style="width: 80%" />
      </el-form-item><el-form-item label="电话号码">
        <el-input v-model="form.telNum" style="width: 80%" />
      </el-form-item><el-form-item label="核酸记录">
        <el-input v-model="form.nucleic" style="width: 80%" />
      </el-form-item><el-form-item label="信用记录">
        <el-input v-model="form.belief" style="width: 80%" />
      </el-form-item><el-form-item label="工龄">
        <el-input v-model="form.workAge" style="width: 80%" />
      </el-form-item><el-form-item label="定位">
        <!--暂时以输入来代替-->
        <el-input v-model="form.point" style="width: 80%" />
      </el-form-item><el-form-item label="入园时间">
        <el-input v-model="form.inTime" style="width: 80%" />
      </el-form-item><el-form-item label="离开时间">
        <el-input v-model="form.outTime" style="width: 80%" />
      </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
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
      tableData:[],
      id:null
    }
  },
  created() {

    this.load()
  },
  methods:{
    load(){
      request.get("/takeAwayMembers",{
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
        request.put("/takeAwayMembers",this.form).then(res => {
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
        request.post("/takeAwayMembers",this.form).then(res => {
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
      request.delete("/takeAwayMembers/" + id).then(res => {
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