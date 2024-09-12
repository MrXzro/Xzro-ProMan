<template>
  <div class="container">
    <el-card style="max-width: 480px">
      <el-form>
        <el-row :gutter="24" justify="center">
          <el-col style="margin-bottom: 20px" :span="6">
            <el-text class="mx-1" size="large"><b>用户登录</b></el-text>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="账号" :label-position="itemLabelPosition">
              <el-input v-model="username" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="密码" :label-position="itemLabelPosition">
              <el-input v-model="password" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :span="24" justify="center">
          <el-col :span="4">
            <el-button type="primary" @click="login">登录</el-button>
          </el-col>
        </el-row>
        </el-form>
    </el-card>
  </div>
</template>

<script setup>
import loginApi from "@/api/loginApi";
import { ref } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";
const username = ref("");
const password = ref("");

function login(){
  loginApi.login(username.value,password.value).then(resp=>{
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
        onClose:function(){
            sessionStorage.setItem("token", resp.data);
            router.push("/index")
        }
      });
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  })
}

</script>

<style scoped>
.container {
  position: relative;
  width: 500px;
  padding: 20px;
  padding-top: 0px;
  margin: auto;
  top: 300px;
}
</style>