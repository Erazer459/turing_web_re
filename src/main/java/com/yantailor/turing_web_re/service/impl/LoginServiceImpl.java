package com.yantailor.turing_web_re.service.impl;

import com.yantailor.turing_web_re.bean.ErrorMap;
import com.yantailor.turing_web_re.dao.LoginDao;
import com.yantailor.turing_web_re.entity.login.User;
import com.yantailor.turing_web_re.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

/**
 * Created by yantailor
 * on 2022/3/25 9:48 @Version 1.0
 */
@Repository
public class LoginServiceImpl implements UserDetailsService {
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    LoginDao loginDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = loginDao.getUser(username);
        if(ObjectUtils.isEmpty(user)){
            throw new ErrorMap(ErrorCode.USERNAME_NOT_EXIST);
        }
        user.setRoles(loginDao.getRole(username));
        return user;
    }
}
