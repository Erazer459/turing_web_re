package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.Teacher;
import com.yantailor.turing_web_re.service.TeacherService;
import com.yantailor.turing_web_re.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Created by yantailor
 * on 2022/3/18 9:08 @Version 1.0
 */
@RestController
@Api(tags = "教师内容操作")
@RequestMapping("/admin/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("add")
    @ApiOperation("教师信息添加")
    public R teacherAdd(@Valid Teacher teacher , BindingResult bindingResult, @RequestPart(name = "teacherIcon") MultipartFile teacherIcon ){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck!=null){
            return resultCheck;
        }
        teacherService.addTeacher(teacher,teacherIcon);
        return R.ok();
    }

    @PostMapping("update")
    @ApiOperation("教师信息更新")
    public R teacherUpdate(@RequestParam Long teacherId , @Valid Teacher teacher , BindingResult bindingResult, @RequestPart(name = "teacherIcon" ,required = false) MultipartFile teacherIcon ){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck!=null){
            return resultCheck;
        }
        boolean isExist = teacherService.updateTeacher(teacherId, teacher, teacherIcon);
        if(!isExist){
            return R.error().message("教师ID不存在");
        }
        return R.ok();
    }

    @DeleteMapping("del")
    @ApiOperation("教师信息删除")
    public R teacherDel(@RequestParam Integer teacherId){
        boolean isExist = teacherService.delTeacher(teacherId);
        if(!isExist){
            return R.error().message("教师ID不存在");
        }
        return R.ok();
    }



}
