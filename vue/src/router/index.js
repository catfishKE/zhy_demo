import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'
import wyLayout from "@/layout/wyLayout";
import adminLayout from "@/layout/adminLayout";


const routes = [
  //  物业端
  {
    path: '/wy',
    name: 'wy',
    component: wyLayout,
    redirect:"/wy/login",
    children: [
      {
        path: 'form',
        name: 'Form',
        component: () => import ("@/views/wyForm"),
      },
      {
        path: 'update',
        name: 'Update',
        component: () => import ("@/views/wyUpdate"),
      },
    ]
  },
  {
    path: '/wy/login',
    name: 'wyLogin',
    component: () => import ("@/views/wyLogin")
  },

    //admin
  {
    path: '/admin',
    name: 'admin',
    component: adminLayout,
    children: [
      {
        //外卖员表单
        path: 'takeAwayMembers',
        name: 'TakeAwayMembers',
        component: () => import ("@/views/admin/adminTakeAwayMembers"),
      },
      {
        //外卖员详情
        path: 'more',
        name: 'more',
        component: () => import ("@/views/admin/adminMoreMsg"),
      },
      {
        //园区表单
        path: 'park',
        name: 'park',
        component: () => import ("@/views/admin/adminPark"),
      },
      {
        //轨迹表单
        path: 'track',
        name: 'Track',
        component: () => import ("@/views/admin/adminTrack"),
      }
    ]
  },
  //admin登录
  {
    path: '/admin/login',
    name: 'adminLogin',
    component: () => import ("@/views/admin/adminLogin")
  },


  //  demo
  {
    path: '/demo',
    name: 'Layout',
    component: Layout,
    redirect:"/demo/home",
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import ("@/views/demoHome"),
      }
    ]
  },
  {
    path: '/demo/login',
    name: 'login',
    component: () => import("@/views/demoLogin")
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
