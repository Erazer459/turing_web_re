package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yantailor
 * on 2022/3/18 0:01 @Version 1.0
 */
@RestController
@Api(tags = "游客团队项目操作")
@RequestMapping("guest/project")
public class GuestProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("queryProject")
    @ApiOperation("团队项目查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page",value = "查询页数，可不填,做全查询"),
                    @ApiImplicitParam(name = "offset",value = "查询奖项偏移量,可不填，做全查询")
            })
    public R queryProject(@RequestParam(required = false) Integer page , @RequestParam(required = false) Integer offset){
        if(page == null || offset == null){
            return R.ok().data("Projects",projectService.queryProject());
        }
        return R.ok().data("Projects",projectService.queryProject(page, offset));
    }
    @GetMapping("queryProjectById")
    @ApiOperation("通过id查询团队项目详细信息")
    @ApiImplicitParam(name = "id",value = "团队项目ID")
    public R queryProjectById(@RequestParam(required = true) int id){
        return R.ok().data("Project",projectService.queryProjectById(id));
    }
}
