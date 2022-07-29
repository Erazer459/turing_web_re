package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.Teacher;
import com.yantailor.turing_web_re.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/18 20:40 @Version 1.0
 */
@RestController
@Api(tags = "游客教师内容操作")
@RequestMapping("guest/teacher")
public class GuestTeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("queryTeacher")
    @ApiOperation("教师内容查询")
    public R queryTeacher(){
        List<Teacher> teachers = teacherService.queryTeacher();
        return R.ok().data("Teachers", teachers);
    }

}
