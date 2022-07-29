package com.yantailor.turing_web_re.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.yantailor.turing_web_re.entity.Project;
        import org.apache.ibatis.annotations.Delete;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Select;

        import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/17 9:41 @Version 1.0
 */
@Mapper
public interface ProjectDao extends BaseMapper<Project> {
    @Insert("insert into project_member values(#{memberStudentId},#{projectId})")
    void projectMemberTableRecord(String memberStudentId , Integer projectId);

    @Delete("delete from project_member where project_id = #{projectId}")
    void projectMemberTableRecordDel(Integer projectId);

    @Insert("insert into project_photo value(#{projectId},#{projectPhoto})")
    void projectPhotoTableRecord(Integer projectId , String projectPhoto);

    @Delete("delete from project_photo where project_id = #{projectId}")
    void projectPhotoTableRecordDel(Integer projectId);

    @Select("select project_photo from project_photo where project_id = #{projectId}")
    List<String> projectPhotoTableRecordQuery(Integer projectId);

    @Select("select member_student_id from project_member where project_id = #{projectId}")
    List<String> projectMemberTableRecordQuery(Integer projectId);
}
