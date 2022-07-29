package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.ErrorMap;
import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.login.LoginUser;
import com.yantailor.turing_web_re.entity.login.User;
import com.yantailor.turing_web_re.utils.ErrorCode;
import com.yantailor.turing_web_re.utils.JwtUtil;
import com.yantailor.turing_web_re.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Created by yantailor
 * on 2022/3/25 9:38 @Version 1.0
 */
@RestController
@RequestMapping("/guest/InAndOut")
@Api(tags = "登录接口")
public class LoginController {

    // 过期时间两小时
    private static final long EXPIRE_TIME = 60*60*2;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisUtils redisUtils;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login(@Valid LoginUser loginUser){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getMemberEmail(),loginUser.getMemberPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //如果验证没通过，给出对应提示
        if(Objects.isNull(authenticate)){
            throw new ErrorMap(ErrorCode.PASSWORD_NOT_CORRECT);
        }

        User user = (User) authenticate.getPrincipal();

        String token = JwtUtil.sign(user.getUsername(), user.getPassword(), user.getAuthorities());

        redisUtils.set("username:"+user.getUsername(), token ,EXPIRE_TIME);

        return R.ok().data("token", token).message("登陆成功");
    }

    @ApiOperation("用户注销")
    @PostMapping("/logout")
    public R logout(HttpServletRequest request){
        String token = request.getHeader("token");
        String email = JwtUtil.getEmail(token);
        redisUtils.del("username:"+email);
        return R.ok().message("注销成功:"+email);
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }

}
