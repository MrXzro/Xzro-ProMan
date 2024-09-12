<template>
  <el-row :span="24">
    <el-col :span="12">
      <el-card>
        <el-button
          type="success"
          @click="addDialogShow = true"
          style="margin-bottom: 10px"
          >添加</el-button
        >
        <el-table :data="departmentList" border style="width: 100%">
          <el-table-column prop="did" label="ID" width="50px" />
          <el-table-column prop="dname" label="部门名称" />
          <el-table-column prop="dlocation" label="部门位置" />
          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                size="small"
                type="primary"
                @click="selectByDid(scope.row.did)"
                >编辑</el-button
              >
              <el-popconfirm
                title="你确定要删除该部门吗？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                width="200px"
                @confirm="deleteByDid(scope.row.did)"
              >
                <template #reference>
                  <el-button size="small" type="danger">删除</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
  </el-row>
  <!-- 添加部门的对话框开始 -->
  <el-dialog v-model="addDialogShow" title="添加部门" width="500">
    <el-form :model="departmentAdd">
      <el-form-item label="部门名称" label-width="20%">
        <el-input v-model="departmentAdd.dname" autocomplete="off" />
      </el-form-item>
      <el-form-item label="部门位置" label-width="20%">
        <el-input v-model="departmentAdd.dlocation" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="addDialogShow = false">取消</el-button>
        <el-button type="primary" @click="insert()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 添加部门的对话框结束 -->
  <!-- 修改部门的对话框开始 -->
  <el-dialog v-model="updateDialogShow" title="修改部门" width="500">
    <el-form :model="departmentUpdate">
      <el-form-item label="部门名称" label-width="20%">
        <el-input v-model="departmentUpdate.dname" autocomplete="off" />
      </el-form-item>
      <el-form-item label="部门位置" label-width="20%">
        <el-input v-model="departmentUpdate.dlocation" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateDialogShow = false">取消</el-button>
        <el-button type="primary" @click="update()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 修改部门的对话框结束 -->
</template>
<script setup>
import departmentApi from "@/api/departmentApi";
import { reactive, ref } from "vue";
import { ElMessage } from "element-plus";
const departmentList = ref([]);
//添加对话框是否显示状态
const addDialogShow = ref(false);
//修改对话框是否显示状态
const updateDialogShow = ref(false);
//被添加部门的信息
const departmentAdd = ref({
  dname: "",
  dlocation: "",
});
//被修改部门的信息
const departmentUpdate = ref({
  did: 0,
  dname: "",
  dlocation: "",
});
//定义修改的方法
function update() {
  departmentApi.update(departmentUpdate.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      updateDialogShow.value = false;
      selectAll();
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//定义方法根据部门id查询部门信息
function selectByDid(did) {
  departmentApi.selectByDid(did).then((resp) => {
    departmentUpdate.value = resp.data;
    //显示修改的对话框
    updateDialogShow.value = true;
  });
}
//定义方法完成部门的添加
function insert() {
  console.log(departmentAdd.value);
  departmentApi.insert(departmentAdd.value).then((resp) => {
    if (resp.code == 10000) {
      addDialogShow.value = false;
      departmentAdd.value = {
        dname: "",
        dlocation: "",
      };
      //弹出消息
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      //刷新表格
      selectAll();
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//定义方法获取所有部门的信息
function selectAll() {
  departmentApi
    .selectAll() //发送axios请求到服务器
    .then((resp) => {
      //成功的回调
      departmentList.value = resp.data;
    });
}
//定义方法根据部门id删除部门
function deleteByDid(did) {
  let data = {
    did,
    isAll: false,
  };
  console.log(data);

  departmentApi.delete(data).then((resp) => {
    //判断-弹出消息-刷新表格
    if (resp.code == 10000) {
      //弹出消息
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      //刷新表格
      selectAll();
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}

//调用方法获取部门的信息
selectAll();
</script>
<style coped>
</style>