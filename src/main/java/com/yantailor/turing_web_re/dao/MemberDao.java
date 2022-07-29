package com.yantailor.turing_web_re.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yantailor.turing_web_re.entity.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/10 16:22 @Version 1.0
 */
@Mapper
@Component
public interface MemberDao extends BaseMapper<Member> {
    @Insert("Insert into member_award values(#{memberStudentId} ,#{awardId} )")
    void memberAwardTableRecord(String memberStudentId , int awardId);

    @Delete("delete from member_award where award_id = #{awardId}")
    void memberAwardTableRecordDel(int awardId);

    @Select("select member_student_id from member_award where award_id = #{awardId}")
    List<String> memberAwardTableRecordQuery(int awardId);

    @Select("select award_id from member_award where member_student_id = #{member_student_id}")
    List<Integer> awardMemberTableRecordQuery(String memberStudentId);

}
