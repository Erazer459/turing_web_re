package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.javafx.scene.traversal.Direction;
import com.yantailor.turing_web_re.entity.Member;
import com.yantailor.turing_web_re.entity.dto.MemberDto;
import com.yantailor.turing_web_re.entity.dto.MemberQueryDto;
import com.yantailor.turing_web_re.entity.dto.MemberUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/11 10:34 @Version 1.0
 */
public interface MemberService extends IService<Member> {
    //添加团队成员
    void addMember(MemberDto memberDto);

    //更新团队成员
    boolean updateMember(MemberUpdateDto memberUpdateDto, MultipartFile memberIcon);

    //删除团队成员
    boolean delMember(String memberStudentId);

    //查看团队成员
    List<MemberQueryDto> queryMember();

    //查看团队成员-分页查询
    List<MemberQueryDto> queryMember(Integer page, Integer offset);

    //查看团队成员根据年级
//    List<MemberQueryDto> queryMemberByGrade();

    //根据方向查询
    List<MemberQueryDto> queryMemberByDirection(Integer page , Integer offset , String memberDirection);

    //根据方向查询-分页查询
    List<MemberQueryDto> queryMemberByDirection(String memberDirection);

    //查看团队成员
    MemberDto queryMemberByMemberStudentId(String memberStudentId);



}
