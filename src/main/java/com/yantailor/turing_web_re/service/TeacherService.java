package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/18 9:09 @Version 1.0
 */
public interface TeacherService extends IService<Teacher> {
    //教师添加
    void addTeacher(Teacher teacher , MultipartFile teacherImg);

    //教师更新
    boolean updateTeacher(Long teacherId , Teacher teacher ,MultipartFile teacherImg);

    //教师删除
    boolean delTeacher(Integer teacherId);

    //教师查询
    List<Teacher> queryTeacher();

    //教师查询-分页查询
    List<Teacher> queryTeacher(Integer page , Integer offset);


}
