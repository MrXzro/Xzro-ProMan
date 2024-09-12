import service from ".";

const loginApi = {
    login(username,password){
        return service.post("/adminController/login",{username,password})
    }
}

export default loginApi