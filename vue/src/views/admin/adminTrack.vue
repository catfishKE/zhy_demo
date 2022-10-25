<template>
  <div style="padding:10px ">

    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type = "primary" @click="add">新增</el-button>
      <el-button type = "primary">导入</el-button>
      <el-button type = "primary">导出</el-button>
    </div>

    <!--    搜索区-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="输入关键字" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>


    <el-table :data="tableData" border style="width: 100%" stripe>

      <el-table-column prop="id" label="id" sortable/>
      <el-table-column prop="track" label="轨迹" />
      <el-table-column prop="time" label="时间" />

      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">
          <el-button type="text" size="mini" @click="handleEdit(scope.row)">编辑</el-button>

          <el-popconfirm title="确定删除?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pagesize"
          :page-sizes="[5, 10, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />


      <el-dialog
          v-model="dialogVisible" title="Tips" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="轨迹">
            <el-input v-model="form.track" style="width: 80%" />
          </el-form-item>
          <el-form-item label="时间">
            <el-input v-model="form.time" style="width: 80%" />
          </el-form-item>

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </span>
        </template>
      </el-dialog>


    </div>
  </div>
</template>

<script>



import request from "@/utils/request";

export default {
  name: 'Track',
  components: {

  },
  data(){
    return{
      search:'',
      currentPage:1,
      total:0,
      pagesize:10,
      dialogVisible:false,
      form:{},

      tableData:[ ]
    }
  },
  created() {
    this.load()
  },

  methods:{
    load(){
      request.get("/track", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        //console.log(res);
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    add(){
      this.dialogVisible = true
      this.form = {}
    },
    save(){
      if (this.form.id) {
        request.put("/track", this.form).then(res => {
          if (res.code == '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg
            });
          }
        })
      } else {
        request.post("/track", this.form).then(res => {
          if (res.code == '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg
            });
          }
        })
      }

      this.load();  //刷新页面 和关弹窗
      this.dialogVisible = false;
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleSizeChange(pageSize){ //改变当前页码触发
      this.pageSize = pageSize; //我使用了双向绑定
      this.load()
    },
    handleCurrentChange(pageNum){  //改变当前每页个数触发
      this.currentPage =pageNum; //同上
      this.load()
    },
    handleDelete(id) {
      console.log(id);
      request.delete("/track/" + id).then(res => {
        if (res.code == '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg
          });
        }
      })
      this.load();
    }
  }
}
</script>
