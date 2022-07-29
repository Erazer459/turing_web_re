package com.yantailor.turing_web_re.filter;

import com.yantailor.turing_web_re.bean.ErrorMap;
import com.yantailor.turing_web_re.utils.ErrorCode;
import com.yantailor.turing_web_re.utils.JwtUtil;
import com.yantailor.turing_web_re.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.stereotype.Component;
        import org.springframework.util.StringUtils;
        import org.springframework.web.filter.OncePerRequestFilter;

        import javax.servlet.FilterChain;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.HashSet;

/**
 * Created by yantailor
 * on 2022/3/25 9:47 @Version 1.0
 */
@Component
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        if(JwtUtil.isTokenExpired(token)){
            throw new ErrorMap(ErrorCode.TOKEN_PAST_DUE);
        }
        String email = null;
        try {
            email = JwtUtil.getEmail(token);
        } catch (Exception e) {
            throw new ErrorMap(ErrorCode.TOKEN_ILLEGAL);
        }
        try {
            String tokenInRedis =(String) redisUtils.get("username:" + email);
            if(!tokenInRedis.equals(token)){
                throw new ErrorMap(ErrorCode.TOKEN_INVALID);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new ErrorMap(ErrorCode.TOKEN_INVALID);
        }

        String role = JwtUtil.getRole(token);
        String substring = role.substring(1, role.length() - 1);
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(substring);
        authorities.add(simpleGrantedAuthority);


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(null, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
