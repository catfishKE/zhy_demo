<template>
  <div class="switch-class">
    <div class="switch-wrapper">
      <h2>{{title}}</h2>
      <el-divider></el-divider>
      <div class="status-class">
        <el-form ref="form" :model="form" label-width="100px">
          <el-form-item label="目前状态：">
            封控
            <el-switch
                v-model="form.value"
                active-color="#13ce66"
                inactive-color="#ff4949"
                disabled>
            </el-switch>
            开放
          </el-form-item>
          <el-form-item label="更新状态：">
            <el-radio-group v-model="form.radio">
              <el-radio :label="2">开放</el-radio>
              <el-radio :label="1">封控</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </div>
      <el-button type="primary" @click="updateHandle">更新</el-button>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'
export default {
  name: "wyUpdate",
  data() {
    return {
      title: '园区名称',
      form: {
        value: true,
        radio: 2
      }
    }
  },
  methods: {
    updateHandle() {
      this.getInit(this.form.radio);
    },
    getInit(flag) {
      let url = '/school/getById';
      let params = {
        id: 1
      }
      if (flag) {
        url = '/school/update';
        params.state = flag;
      }
      request({
        url: url,
        method: 'post',
        data: params
      }).then(res => {
        if (res.code == 200) {
          this.title = res.data && res.data.schoolName;
          this.form = Object.assign({}, {
            value: res.data.state == 2,
            radio: Number(res.data.state)
          });
        }
      })
    }
  },
  mounted() {
    this.getInit();
  }
}
</script>

<style scoped>
.switch-class {
  width: 80%;
  margin: 0 auto;
}
.switch-wrapper {
  padding: 20px;
  margin-top: 80px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.status-class {
  width: 300px;
  margin: 0 auto;
  padding-bottom: 40px;
}
.status-class > p {
  padding-bottom: 20px;
}
</style>
