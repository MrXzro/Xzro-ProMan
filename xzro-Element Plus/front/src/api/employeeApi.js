import service from ".";
const employeeApi = {
    selectByPage(currentPage){
        return service.get(`/admin/employee/selectByPage/${currentPage}`);
    },
    selectByEid(eid){
        return service.get(`/admin/employee/selectByEid/${eid}`)
    },
    deleteByEid(eid){
        return service.post("/admin/employee/delete",{"eid":eid})
    },
    insert(data){
        return service.post("/admin/employee/insert",data)
    },
    update(data){
        return service.post("/admin/employee/update",data)
    }

}
export default employeeApi