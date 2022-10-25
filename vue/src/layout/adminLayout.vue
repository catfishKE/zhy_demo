<template>
  <div>
  <adminHeader />
  <!--  主体-->
  <div style="display: flex">
    <!--侧边栏-->
    <adminAside />
    <!--内容区域-->
    <router-view style ="flex: 1"/>
  </div>
  </div>
</template>

<script>
import adminHeader from "@/components/adminHeader";
import adminAside from "@/components/adminAside";
export default {
  name: "adminLayout",
  components: {
    adminHeader,
    adminAside
  }
}
</script>

<style scoped>

</style>