package com.xzro.controller;/**
 * ClassName: AdminController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/17 15:09
 * @Version 1.0
 */

import cn.hutool.captcha.LineCaptcha;
import com.xzro.bean.Admin;
import com.xzro.bean.RespBean;
import com.xzro.service.AdminService;
import com.xzro.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/adminController")
public class AdminController extends HttpServlet {
    @Autowired
    private AdminService adminService;
    //登录
    @PostMapping("/login")
    public RespBean login(String username, String password, String captchaInput, HttpSession session) throws IOException {
        String captcha = (String)session.getAttribute("captcha");
            if (captcha.equalsIgnoreCase(captchaInput)) {
                Admin login = adminService.login(username, password);
                if (login != null) {
                    //将用户名装入session
                    session.setAttribute("username",login);
                    return RespBean.ok("登陆成功");
                }else {
                    return RespBean.error("账号或密码错误");
                }
            }else {
                return RespBean.error("验证码错误");
            }
    }
    //注销
    @GetMapping("/logout")
    public RespBean logout(HttpSession session) throws IOException {
        session.invalidate();
        return RespBean.ok("退出成功");
    }
    //验证码
    @GetMapping("/captcha")
    protected void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成验证码的对象
        LineCaptcha lineCaptcha = new LineCaptcha(127, 38, 4, 20);
        //获取Session
        HttpSession session = request.getSession();
        //保存验证码的文本到session中
        session.setAttribute("captcha",lineCaptcha.getCode());
        //返回验证码的图片给浏览器
        lineCaptcha.write(response.getOutputStream());

    }


}
