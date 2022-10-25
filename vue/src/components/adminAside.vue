<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        router="true"
        default-active="/admin/takeAwayMembers"
        class="el-menu-vertical-demo">
      <el-menu-item index="/admin/takeAwayMembers" :route="{path:'/admin/takeAwayMembers'}">外卖员信息</el-menu-item>
      <el-menu-item index="/admin/park" :route="{path:'/admin/park'}">园区信息管理</el-menu-item>
      <el-menu-item index="/admin/track" :route="{path:'/admin/track'}">园区流量情况</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "adminAside"
}
</script>

<style scoped>

</style>