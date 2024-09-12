import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EmployeeView from '@/views/EmployeeView.vue'
import DepartmentView from '@/views/DepartmentView.vue'
import IndexView from '@/views/IndexView.vue'
import ProjectView from '@/views/ProjectView.vue'
import LoginView from '@/views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      redirect:'/index',
      children:[//子路由，访问子路由时父路由会一同渲染
        {
          path:'/emp',
          component:EmployeeView
        },{
          path:'/dept',
          component:DepartmentView
        },{
          path:'/index',
          component:IndexView
        },{
          path:"/project",
          component:ProjectView
        }
      ]
    },{
      path: '/login',
      name: 'login',
      component: LoginView,
    }
  ]
})
router.beforeEach((to, from) => {
  //如果当前访问的登录页
  if(to.path == '/login') {
      //放行
      return true
  } else { //如果当前访问的不是登录页
      //获取token
      let token = sessionStorage.getItem('token');
      if(token == null) { //如果token不存在去登录页
          return "/login"
      } else { //token存在放行
          return true
      }
  }
})
export default router
