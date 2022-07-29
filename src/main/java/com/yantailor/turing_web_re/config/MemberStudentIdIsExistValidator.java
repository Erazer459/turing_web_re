package com.yantailor.turing_web_re.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yantailor.turing_web_re.dao.MemberDao;
import com.yantailor.turing_web_re.entity.Member;
import com.yantailor.turing_web_re.utils.MemberStudentIdIsExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/17 0:01 @Version 1.0
 */
@Component
public class MemberStudentIdIsExistValidator implements ConstraintValidator<MemberStudentIdIsExist,Object> {

    @Autowired
    MemberDao memberDao;

    private List<String> memberlist;

    public static String errorStudentId;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        memberlist=(List<String>) value;
        for(String id : memberlist){
            boolean exists = memberDao.exists(new QueryWrapper<Member>().eq("member_student_id", id));
            if(!exists){
                errorStudentId = id;
                return false;
            }
        }
        return true;
    }
}
