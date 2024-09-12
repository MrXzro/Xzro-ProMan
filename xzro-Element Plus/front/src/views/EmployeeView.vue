<template>
  <el-row :span="24">
    <el-col :span="24">
      <el-card>
        <el-button
          type="success"
          @click="addShowFun"
          style="margin-bottom: 10px"
          >添加</el-button
        >
        <el-table :data="selectEmployeeList" border style="width: 100%">
          <el-table-column prop="eid" label="ID" width="50px" />
          <el-table-column prop="eno" width="80" label="工号" />
          <el-table-column width="80" label="图片" >
            <template #default="scope">
              <el-avatar shape="square" size="" :src="'http://localhost:8080/upload/'+scope.row.eavatar" />
            </template>
          </el-table-column>
          <el-table-column prop="ename" width="80" label="姓名" />
          <el-table-column prop="eage" width="80" label="年龄" />
          <el-table-column prop="egender" width="80" label="性别" />
          <el-table-column prop="ejob" label="工种" />
          <el-table-column prop="eentrydate" label="入职时间" />
          <el-table-column prop="esalary" width="80" label="薪资" />
          <el-table-column label="状态" width="80">
            <template #default="scope">
              <el-tag v-if="scope.row.estate" type="primary">在职</el-tag>
              <el-tag v-if="!scope.row.estate" type="info">离职</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="department.dname" label="部门" />
          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                size="small"
                type="primary"
                @click="updateShowFun(scope.row.eid)"
                >编辑</el-button
              >
              <el-popconfirm
                title="你确定要删除该员工吗？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                width="200px"
                @confirm="deleteByEid(scope.row.eid)"
              >
                <template #reference>
                  <el-button size="small" type="danger">删除</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <el-row :span="24" justify="center">
          <el-pagination
            background
            layout="prev, pager, next"
            :page-count="allPage"
            :current-page="currentPage"
            @update:current-page="selectByPage"
            style="margin-top: 15px"
          />
        </el-row>
      </el-card>
    </el-col>
  </el-row>
  <!-- 添加员工的对话框开始 -->
  <el-dialog v-model="addDialogShow" title="添加部门" width="500">
    <el-form :model="addEmployee">
      <el-form-item label="工号" label-width="20%">
        <el-input v-model="addEmployee.eno" autocomplete="off" />
      </el-form-item>
      <el-form-item label="姓名" label-width="20%">
        <el-input v-model="addEmployee.ename" autocomplete="off" />
      </el-form-item>
      <el-form-item label="年龄" label-width="20%">
        <el-input v-model="addEmployee.eage" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" label-width="20%">
        <el-select v-model="addEmployee.egender" placeholder="请设置员工性别">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="工种" label-width="20%">
        <el-input v-model="addEmployee.ejob" autocomplete="off" />
      </el-form-item>

      <el-form-item label="薪资" label-width="20%">
        <el-input v-model="addEmployee.esalary" autocomplete="off" />
      </el-form-item>
      <el-form-item label="状态" label-width="20%">
        <el-select v-model="addEmployee.estate" placeholder="请设置员工状态">
          <el-option label="在职"  :value="1" />
          <el-option label="离职"  :value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="部门" label-width="20%">
        <el-select v-model="addEmployee.did" placeholder="请选择部门">
          <el-option
            v-for="(item, index) in departmentList"
            :label="item.dname"
            :key="index"
            :value="item.did"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="入职时间" label-width="20%">
        <el-date-picker
          v-model="addEmployee.eentrydate"
          type="date"
          placeholder="请选择入职日期"
          style="width: 100%"
          clearable
        />
      </el-form-item>
      <el-form-item label="头像" label-width="20%">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/admin/upload"
          name="pic"
          :value="addEmployee.eavatar"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img width="100px" v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="addDialogShow = false">取消</el-button>
        <el-button type="primary" @click="insert()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 添加员工的对话框结束 -->
  <!-- 修改员工的对话框开始 -->
  <el-dialog v-model="updateDialogShow" title="修改部门" width="500">
    <el-form :model="updateEmployee">
      <el-form-item label="工号" label-width="20%">
        <el-input v-model="updateEmployee.eno" autocomplete="off" />
      </el-form-item>
      <el-form-item label="姓名" label-width="20%">
        <el-input v-model="updateEmployee.ename" autocomplete="off" />
      </el-form-item>
      <el-form-item label="年龄" label-width="20%">
        <el-input v-model="updateEmployee.eage" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" label-width="20%">
        <el-select
          v-model="updateEmployee.egender"
          placeholder="请设置员工性别"
        >
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="工种" label-width="20%">
        <el-input v-model="updateEmployee.ejob" autocomplete="off" />
      </el-form-item>

      <el-form-item label="薪资" label-width="20%">
        <el-input v-model="updateEmployee.esalary" autocomplete="off" />
      </el-form-item>
      <el-form-item label="状态" label-width="20%">
        <el-select v-model="updateEmployee.estate" placeholder="请设置员工状态">
          <el-option label="在职"  :value="1" />
          <el-option label="离职"  :value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="部门" label-width="20%">
        <el-select v-model="updateEmployee.did" placeholder="请选择部门">
          <el-option
            v-for="(item, index) in departmentList"
            :label="item.dname"
            :key="index"
            :value="item.did"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="入职时间" label-width="20%">
        <el-date-picker
          v-model="updateEmployee.eentrydate"
          type="date"
          placeholder="请选择入职日期"
          style="width: 100%"
          clearable
        />
      </el-form-item>
      <el-form-item label="头像" label-width="20%">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/admin/upload"
          name="pic"
          :value="addEmployee.eavatar"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img width="100px" v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateDialogShow = false">取消</el-button>
        <el-button type="primary" @click="update()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 修改员工的对话框结束 -->
</template>
<script setup>
import employeeApi from "@/api/employeeApi";
import departmentApi from "@/api/departmentApi";
import { ElMessage } from "element-plus";
import { ref } from "vue";
import service from "@/api";
function test(a) {
  console.log(a);
}
//获取的员工列表
const selectEmployeeList = ref([]);
//获取的总页数
const allPage = ref(0);
//当前页面
const currentPage = ref(1);
//添加员工对象
const addEmployee = ref({
  eid: null,
  eno: "",
  ename: "",
  eage: null,
  egender: "",
  ejob: "",
  eentrydate: "",
  esalary: null,
  estate: null,
  did: null,
  eavatar:"",
  department: {
    did: null,
    dname: "",
    dlocation: "",
  },
});
//添加对话框显示变量
const addDialogShow = ref(false);
//部门列表
const departmentList = ref([]);
//修改员工对话框显示变量
const updateDialogShow = ref(false);
//修改员工对象
const updateEmployee = ref({
  eid: null,
  eno: "",
  ename: "",
  eage: null,
  egender: "",
  ejob: "",
  eentrydate: "",
  esalary: null,
  estate: null,
  did: null,
  eavatar: null,
  department: {
    did: null,
    dname: "",
    dlocation: "",
  },
});
//头像地址
const imageUrl = ref("");

//上传成功的钩子
function handleAvatarSuccess(response, uploadFile) {
  ElMessage({
    message: response.msg,
    type: "success",
    duration: 1200,
  });
  imageUrl.value = "http://localhost:8080/upload/" + response.data;
  addEmployee.value.eavatar = response.data;
  updateEmployee.value.eavatar = response.data;
}

//判断是否可以上传的方法
function beforeAvatarUpload(rawFile) {
  if (rawFile.type !== "image/jpeg") {
    ElMessage.error("头像必须是JPG格式！");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("头像大小超出2MB！");
    return false;
  }
  return true;
}

//修改员工方法
function update() {
  employeeApi.update(updateEmployee.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      updateDialogShow.value = false;
      selectByPage(currentPage.value);
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//显示修改对话框方法
function updateShowFun(eid) {
  //显示部门列表
  selectAll();
  //数据回显
  employeeApi.selectByEid(eid).then((resp) => {
    console.log(resp);
    updateEmployee.value = resp.data;
    imageUrl.value = 'http://localhost:8080/upload/'+resp.data.eavatar
  });
  updateDialogShow.value = true;
}
//添加员工方法
function insert() {
  employeeApi.insert(addEmployee.value).then((resp) => {
    //判断-弹出消息-刷新表格
    if (resp.code == 10000) {
      //弹出消息
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      //刷新表格
      selectByPage(allPage.value);
      //隐藏对话框
      addDialogShow.value = false;
      //清除对话框信息
      addEmployee.value = {
        eid: null,
        eno: "",
        ename: "",
        eage: null,
        egender: "",
        ejob: "",
        eentrydate: "",
        esalary: null,
        estate: null,
        did: null,
        department: {
          did: null,
          dname: "",
          dlocation: "",
        },
      };
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//显示添加对话框方法
function addShowFun() {
  //显示部门列表
  selectAll();
  addDialogShow.value = true;
}
//查询所有部门
function selectAll() {
  departmentApi
    .selectAll() //发送axios请求到服务器
    .then((resp) => {
      //成功的回调
      departmentList.value = resp.data;
    });
}

//删除员工
function deleteByEid(eid) {
  employeeApi.deleteByEid(eid).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      selectByPage(currentPage.value);
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//根据页码查询
function selectByPage(current) {
  employeeApi.selectByPage(current).then((resp) => {
    selectEmployeeList.value = resp.data.list;
    allPage.value = resp.data.pages;
    currentPage.value = current;
    console.log(resp);
  });
}
selectByPage(1);
</script>
<style coped>
.avatar-uploader, .avatar {
				width: 178px;
				height: 178px;
				display: block;
				border: 1px dotted #dcdfe6;
				border-radius: 5px;
			}

			.el-icon.avatar-uploader-icon {
				font-size: 28px;
				color: #8c939d;
				width: 178px;
				height: 178px;
				text-align: center;
			}
</style>