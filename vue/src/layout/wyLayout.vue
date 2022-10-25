<template>
  <div>
    <!--  顶部长条-->
    <div style=" height: 100px; line-height: 50px;display:flex">

      <!--    第一块 放图片-->
      <div>
        <img style="height: 80px;
             margin-top: 10px;
             margin-left: 30px;
             object-fit: contain"
             :src="require('@/assets/pics/logo.png')" />
      </div>

      <!--    第二块   跳转页面--部分功能-->
      <div style=" flex: 1;border-bottom: 1px solid #ccc;" >
        <!--      这两个先设置为跳转到home页面-->
        <div style="margin-left: 250px;margin-top: 30px;width: 400px;height: 100px;float:left">
          <el-link :underline="false" @click="$router.push('/wy/form')">园区流量控制</el-link>
        </div>

        <div style="margin-left: 750px;margin-top: 30px;width: 400px;height: 100px">
          <el-link :underline="false" @click="$router.push('/wy/update')" >园区情况更新 </el-link>
        </div>
      </div>

      <!--    第三块  更多功能--下拉菜单-->
      <div style=" width: 180px;border-bottom: 1px solid #ccc;">
        <el-dropdown>
          <el-button type="primary" size="large" style="margin-right: 40px;margin-top: 30px">
            更多功能
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
          </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>更多功能 1</el-dropdown-item>
              <el-dropdown-item>更多功能 2</el-dropdown-item>
              <el-dropdown-item>更多功能 3</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>

    </div>
    <!--      内容区域-->
    <router-view style="margin:5vh 10vh; flex: 1"/>
  </div>


</template>

<script>
export default {
  name: "wyLayout"
};


</script>

<style scoped>

</style>