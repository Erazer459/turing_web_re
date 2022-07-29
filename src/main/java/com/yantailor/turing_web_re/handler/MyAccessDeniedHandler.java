package com.yantailor.turing_web_re.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yantailor
 * on 2022/3/22 23:39 @Version 1.0
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("states", 403);
        hashMap.put("exception",accessDeniedException.getMessage());
        String s = new ObjectMapper().writeValueAsString(hashMap);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().println(s);

    }
}
