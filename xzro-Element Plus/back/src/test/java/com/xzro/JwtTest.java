package com.xzro;

import com.xzro.utils.JwtUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: JwtTest
 * Package: com.xzro
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/2 11:19
 * @Version 1.0
 */
public class JwtTest {
    @Test
    public void test1(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("age", 20);

        String jwtStr = JwtUtils.generateJwt(map);
        System.out.println(jwtStr);
    }
    @Test
    public void test2(){
        Map<String, Object> map = JwtUtils.parseJwtToMap("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjp7ImFnZSI6MjAsInVzZXJuYW1lIjoiYWRtaW4ifSwiZXhwIjoxNzI1MjQ5MzI4fQ.mRNDWmDYg15FmulRCqJAR4FuyUxy-4P9R-g_rwqJ7tk");
        System.out.println(map.get("username"));
        System.out.println(map.get("age"));
    }
}
