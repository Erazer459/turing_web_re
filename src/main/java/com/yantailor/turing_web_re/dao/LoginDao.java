package com.yantailor.turing_web_re.dao;


import com.yantailor.turing_web_re.entity.login.Role;
import com.yantailor.turing_web_re.entity.login.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/22 18:40 @Version 1.0
 */

@Mapper
@Repository
public interface LoginDao {
    @Select("select member_email,member_password from member where member_email = #{memberEmail}")
    User getUser(String memberEmail);

    @Select("SELECT role.role_id , role.role_name , role.role_description  FROM role JOIN member ON member.`member_role_id` = role.`role_id` WHERE member_email= #{memberEmail}")
    List<Role> getRole(String memberEmail);

}
