package com.xzro.controller;/**
 * ClassName: InfoController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/19 18:48
 * @Version 1.0
 */

import com.xzro.bean.RespBean;
import com.xzro.service.AdminService;
import com.xzro.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/*
* 获取已登录用户的信息并返回
* */
@CrossOrigin

@RestController
@RequestMapping("/admin/info")
public class InfoController{
    @Autowired
    private AdminService adminLogin;
    //查询前五
    @PostMapping("/selectTop5DnameAndCount")
    protected RespBean selectTop5DnameAndCount(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Map<String,Object>> list =  adminLogin.selectTop5DnameAndCount();
        return RespBean.ok("", list);

    }
    @PostMapping ("/userinfo")
    protected RespBean userinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取到Session
        HttpSession session = request.getSession();
        //获取Session中存放的已登录用户的信息
        Object username = session.getAttribute("username");
        //返回
        return RespBean.ok("用户信息查询成功",username);
    }

}
