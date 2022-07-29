package com.yantailor.turing_web_re.bean;

import com.yantailor.turing_web_re.filter.JWTAuthenticationTokenFilter;
import com.yantailor.turing_web_re.handler.MyAccessDeniedHandler;
import com.yantailor.turing_web_re.handler.MyAuthenticationEntryPoint;
import com.yantailor.turing_web_re.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by yantailor
 * on 2022/3/25 9:45 @Version 1.0
 */
@Configuration
public class webSecurityConfigurer extends WebSecurityConfigurerAdapter {

    //让swagger-ui通过
    private static final String[] AUTH_LIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };


    private final LoginServiceImpl loadUserByUsername;

    @Autowired
    public webSecurityConfigurer(LoginServiceImpl loadUserByUsername) {
        this.loadUserByUsername = loadUserByUsername;
    }

    @Autowired
    JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("自定义AuthenticationManager"+auth);//DaoAuthenticationProvider
        auth.userDetailsService(loadUserByUsername);

        //swagger-ui manager注入
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //swagger-ui通过配置
        http.authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll();

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers("/guest/**").permitAll()


                .antMatchers("/admin/*/update").hasAnyRole("user","admin")
                .antMatchers("/admin/*/del").hasRole("admin")
                .antMatchers("/admin/*/add").hasRole("admin")


                .antMatchers("/guest/InAndOut/login").anonymous()
                .antMatchers("/guest/InAndOut/logout").authenticated()
                .anyRequest().authenticated()
                .and()

                .exceptionHandling()
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                .accessDeniedHandler(new MyAccessDeniedHandler());

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);




    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码加密，必须为 @Bean ，否则报错
     *     作用：实例化密码加密规则，该规则首先会校验数据库中存储的密码是否符合其规则（经过 BCryptPasswordEncoder 加密的密码
     * 的字符串符合一定的规则）：
     *     1.若不符合，直接报错；
     *     2.若符合，则会将前端传递的密码经过 BCryptPasswordEncoder 加密，再和数据库中的密码进行比对，一样则通过
     *     所以，这里要求，我们存入进数据库的密码不能是明文，而必须是经过 BCryptPasswordEncoder 加密后，才能存入数据库
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
