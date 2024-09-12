import service from ".";

const projectApi = {
    //查询所有
    selectAll() {
        return service.get("/admin/project")
    },
    //获取所有在职员工
    allEmployee(pid){
        return service.get(`/admin/project/allEmployee/${pid}`)
    },
    insertPidAndEid(pid,eids){
        return service.post("/admin/project/pidAndEid",{pid,eids})
    }
    
}
export default projectApi