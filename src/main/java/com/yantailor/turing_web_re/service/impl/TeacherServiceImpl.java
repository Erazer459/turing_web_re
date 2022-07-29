package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.dao.TeacherDao;
import com.yantailor.turing_web_re.entity.Teacher;
import com.yantailor.turing_web_re.service.TeacherService;
import com.yantailor.turing_web_re.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/18 9:14 @Version 1.0
 */
@Service
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherDao , Teacher> implements TeacherService {
    @Value("${turingweb.desPath}")
    private String desPath;

    @Value("${turingweb.teacherIconSourceFile}")
    private String teacherIconSourceFile;

    private String teacherIconOld;

    @Autowired
    TransferUtil transferUtil;
    @Override
    public void addTeacher(Teacher teacher, MultipartFile teacherImg) {
        teacher.setTeacherImg(desPath+"/"+teacherIconSourceFile+"/"+teacherImg.getOriginalFilename());
        baseMapper.insert(teacher);
        transferUtil.TransferFile(teacherImg, teacherIconSourceFile);
    }

    @Override
    public boolean updateTeacher(Long teacherId, Teacher teacher , MultipartFile teacherImg) {

            if(!baseMapper.exists(new QueryWrapper<Teacher>().eq("teacher_id", teacherId))){
                return false;
                }
            if(teacherImg != null){
                Teacher teacher1 = baseMapper.selectById(teacherId);
                teacherIconOld = teacher1.getTeacherImg();
                teacher.setTeacherImg(desPath+"/"+teacherIconSourceFile+"/"+teacherImg.getOriginalFilename());
            }
            teacher.setTeacherId(teacherId);
            baseMapper.updateById(teacher);

            if(teacherImg != null) {
                transferUtil.RemoveFile(teacherIconOld);
                transferUtil.TransferFile(teacherImg, teacherIconSourceFile);
            }
            return true;

    }

    @Override
    public boolean delTeacher(Integer teacherId) {
        if(!baseMapper.exists(new QueryWrapper<Teacher>().eq("teacher_id", teacherId))){
            return false;
        }
        Teacher teacher1 = baseMapper.selectById(teacherId);
        teacherIconOld = teacher1.getTeacherImg();
        baseMapper.deleteById(teacherId);
        transferUtil.RemoveFile(teacherIconOld);
        return true;
    }

    @Override
    public List<Teacher> queryTeacher() {
        List<Teacher> teacherList = baseMapper.selectList(null);
        return teacherList;
    }

    @Override
    public List<Teacher> queryTeacher(Integer page, Integer offset) {
        return null;
    }
}
