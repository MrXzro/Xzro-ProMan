package com.xzro.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzro.bean.RespBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: LoginInterceptor
 * Package: com.xzro.interceptor
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/25 15:54
 * @Version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        HttpSession session = request.getSession();
        Object admin = session.getAttribute("username");
        if (admin != null) {
            return true;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), RespBean.error("您还未登录，无法访问"));
        return false;
    }
}
