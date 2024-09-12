import service from ".";

const departmentApi = {
    //添加
    insert(department){
        //发送axios请求到服务器
        return service.post("/admin/department/insert",department)
    },
    //根据ID删除
    delete(data){
        return service.post("/admin/department/delete",data)
    },
    //修改
    update(department) {
        return service.post("/admin/department/update",department)
    },
    //查询所有
    selectAll() {
        return service.get("/admin/department/selectAll")
    },
    //根据ID查询
    selectByDid(did){
        return service.get(`/admin/department/selectByDid/${did}`)
    }
}
export default departmentApi