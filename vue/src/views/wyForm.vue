<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0;">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load()">查询</el-button>
    </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%"
        :row-class-name="tableRowClassName"
    >
      <el-table-column prop="id" label="订单编号" sortable/>
      <el-table-column prop="time" label="时间" />
      <el-table-column label="外卖员">
        <template #default="scope">
          <el-button
              link
              type="primary"
              @click.prevent="showDriver(scope.$index)"
          >
           {{ scope.row.nickname }}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="轨迹">
        <template #default="scope">
          <el-button
              link
              type="primary"
              @click.prevent="showDriver(scope.$index)"
          >
            {{ scope.row.nickname }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20, 50, 100]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>

  </div>
</template>

<script>
// @ is an alias to /src
import { ref } from 'vue'
import request from "@/utils/request";
const input = ref('')

export default {
  name: 'wyForm',
  components: {

  },
  data(){
    return{
      recordsForm:{},
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
  },
}
</script>
