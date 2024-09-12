<template>
  <el-row :span="24">
    <el-col :span="24">
      <el-card>
        <el-button type="success" style="margin-bottom: 10px">添加</el-button>
        <el-table :data="ProjectList" border style="width: 100%">
          <el-table-column prop="pid" label="ID" width="50px" />
          <el-table-column prop="pname" label="项目名" />
          <el-table-column prop="pstart" width="150px" label="项目开始时间" />
          <el-table-column prop="pend" width="150px" label="项目结束时间" />
          <el-table-column label="项目进度">
            <template #default="scope">
              <el-progress :percentage="scope.row.pprogress" />
            </template>
          </el-table-column>
          <el-table-column prop="pdescription" label="项目描述" />
          <el-table-column label="操作">
            <template #default="scope">
              <el-popover placement="right" :width="400" trigger="hover">
                <template #reference>
                  <el-button
                    size="small"
                    @click="showSetEmployeeDialog(scope.row.pid)"
                    type="success"
                    >分配员工</el-button
                  >
                </template>
                <el-table :data="scope.row.emps">
                  <el-table-column property="eid" label="工号" />
                  <el-table-column property="ename" label="姓名" />
                  <el-table-column
                    property="department.dname"
                    label="部门"
                  />
                </el-table>
              </el-popover>
              <el-button size="small" type="primary">编辑</el-button>
              <el-popconfirm
                title="你确定要删除该部门吗？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                width="200px"
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
  <!-- 分配员工的对话框开始 -->
  <el-dialog v-model="setEmployeeDialogShow" title="添加部门" width="600px">
    <!-- data数据源 -->
    <el-transfer v-model="selectEids" :data="allEmployee" />
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="setEmployeeDialogShow = false">取消</el-button>
        <el-button type="primary" @click="insertPidAndEid()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 分配员工的对话框结束 -->
</template>
  <script setup>
import departmentApi from "@/api/departmentApi";
import { reactive, ref } from "vue";
import projectApi from "@/api/projectApi";
import { ElMessage } from "element-plus";
//存放所有项目的数组
const ProjectList = ref([]);
//是否展示分配员工的对话框
const setEmployeeDialogShow = ref(false);
//所有在职员工的信息key label
const allEmployee = ref([]);
//被选中的员工的eid
const selectEids = ref([]);
//需要分配员工的项目ID
const selectPid = ref(0);


//分配员工
function insertPidAndEid() {
  projectApi.insertPidAndEid(selectPid.value, selectEids.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      setEmployeeDialogShow.value = false;
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
//查询所有员工的信息并显示分配所有员工的对话框
function showSetEmployeeDialog(pid) {
  //查询所有员工的信息
  projectApi.allEmployee(pid).then((resp) => {
    console.log(resp);
    selectPid.value = pid;
    allEmployee.value = resp.data.allEmployee;
    selectEids.value = resp.data.selectEids;
    setEmployeeDialogShow.value = true;
  });
}
//查询所有的项目
function selectAll() {
  projectApi.selectAll().then((resp) => {
    ProjectList.value = resp.data;
  });
}
selectAll();
</script>
  <style coped>
</style>