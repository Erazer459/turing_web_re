package com.yantailor.turing_web_re.utils;

import com.yantailor.turing_web_re.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by yantailor
 * on 2022/3/11 11:00 @Version 1.0
 */
@Component
public class MemberInitUtil {

    @Value("${turingweb.desPath}")
    private String desPath;
    @Value("${turingweb.defaultIconUrl}")
    private String defaultIconUrl;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member memberInit(Member member){
        member.setMemberIntroduction("暂无");
        member.setMemberIconUrl(desPath+defaultIconUrl);
        member.setMemberActivateState(false);
        member.setMemberPassword(bCryptPasswordEncoder.encode(member.getMemberStudentId()));
        member.setMemberNickName("图灵"+new Random().nextInt(1000));
        member.setMemberRoleId(1);
        return member;
    }
}
