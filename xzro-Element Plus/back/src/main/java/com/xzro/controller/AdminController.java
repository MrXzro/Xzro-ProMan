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
import cn.hutool.crypto.SecureUtil;
import com.xzro.bean.Admin;
import com.xzro.bean.RespBean;
import com.xzro.service.AdminService;
import com.xzro.service.impl.AdminServiceImpl;
import com.xzro.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/adminController")
public class AdminController extends HttpServlet {
    @Autowired
    private AdminService adminService;
    //登录
    @PostMapping("/login")
    public RespBean login(@RequestBody Map<String,Object> userData,  HttpSession session) throws IOException {
        System.out.println(userData);
                Admin login = adminService.login((String) userData.get("username"));
                String md5Pwd = SecureUtil.md5(SecureUtil.md5((String) userData.get("password")));

                if (md5Pwd.equals(login.getPassword())) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("username", login.getUsername());
                    String token = JwtUtils.generateJwt(map);
                    //将用户名装入session
                    session.setAttribute("username",login);
                    return RespBean.ok("登陆成功",token);
                }else {
                    return RespBean.error("账号或密码错误");
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
