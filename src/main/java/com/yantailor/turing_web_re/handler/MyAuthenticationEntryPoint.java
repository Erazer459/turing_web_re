package com.yantailor.turing_web_re.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yantailor
 * on 2022/3/24 11:00 @Version 1.0
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
//        if(authException instanceof AccountExpiredException){
////            map.put("code", 403);
////            map.put("msg", "用户已注销");
////            map.put("success", false);
////            String json = JSON.toJSONString(map);
////            response.setContentType("text/json;charset=utf-8");
////            response.getWriter().write(json);
////            return;
////        }
////        if(authException instanceof UsernameNotFoundException){
////            map.put("code", 400);
////            map.put("msg", "用户用户名不存在");
////            map.put("success", false);
////            String json = JSON.toJSONString(map);
////            response.setContentType("text/json;charset=utf-8");
////            response.getWriter().write(json);
////            return;
////        }
        map.put("code", 403);
        map.put("msg", authException.getMessage());
        map.put("success", false);
        String json = JSON.toJSONString(map);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);

    }
}
