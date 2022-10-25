<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px) "
        default-active="user"
        :default-openeds="[1]"
        class="el-menu-vertical-demo"
    >
      <el-sub-menu index="1">
        <template #title>
          <span>系统管理</span>
        </template>
        <el-menu-item index="user" :route="{path: '/'}">用户管理</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="data" :route="{path: '/'}">数据管理</el-menu-item>
      <el-menu-item index="2">
        <el-icon><icon-menu /></el-icon>
        <span>Navigator Two</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>

import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'


export default {
  name: "Aside",
  components:{
    Document,
    IconMenu,
    Location,
    Setting,
  }
}
</script>

<style scoped>

</style>