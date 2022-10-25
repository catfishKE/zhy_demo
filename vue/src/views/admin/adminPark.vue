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
        :row-class-name="tableRowClassName"
    >
<!--      <el-table-column prop="id" label="ID" sortable/>-->
      <el-table-column prop="name" label="园区名称" />
      <el-table-column prop="address" label="地址" />
      <el-table-column prop="state" label="状态" />
      <el-table-column prop="maps" label="园区地图" />

      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button  @click="handleEdit(scope.row)">编辑</el-button>
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

    <el-dialog v-model="dialogVisible" title="提示" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="园区名称">
          <el-input v-model="form.parkname" style="width: 80%" />
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" style="width: 80%" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.state" class="ml-4">
            <el-radio label="封控" size="large" style="color: red;">封控</el-radio>
            <el-radio label="正常" size="large" style="color: green">正常</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="地图">
          <el-input type="textarea" v-model="form.maps" style="width: 80%" />
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
  name: 'adminPark',
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
      request.get("/park",{
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
        request.put("/park",this.form).then(res => {
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
        request.post("/park",this.form).then(res => {
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
      request.delete("/park/" + id).then(res => {
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