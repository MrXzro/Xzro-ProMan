<template>
  <div class="common-layout">
    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside>
          <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="2"
            text-color="#fff"
            @select="toPage"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon>
                  <location />
                </el-icon>
                <span>企业管理</span>
              </template>
              <el-menu-item index="/emp">员工管理</el-menu-item>
              <el-menu-item index="/dept">部门管理</el-menu-item>
              <el-menu-item index="/project">项目管理</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main>
          <RouterView />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup>
import { RouterView, RouterLink } from "vue-router";
import router from '@/router'


function toPage(indexPath) {
  //编程式导航
  router.push(indexPath)
}
</script>

