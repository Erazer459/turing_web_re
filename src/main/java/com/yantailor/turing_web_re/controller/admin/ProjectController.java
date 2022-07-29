package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.dto.ProjectDto;
import com.yantailor.turing_web_re.service.ProjectService;
import com.yantailor.turing_web_re.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Created by yantailor
 * on 2022/3/16 23:49 @Version 1.0
 */
@Api(tags = "团队项目操作")
@RestController
@RequestMapping("/admin/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("add")
    @ApiOperation("团队项目添加")
    public R projectAdd(@Valid ProjectDto projectDto , BindingResult bindingResult , @RequestPart(name = "projectGif" ,required = false) MultipartFile projectGif ,
                        @RequestPart(name = "projectPhotos" ,required = false)MultipartFile[] projectPhotos){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck !=null){
            return resultCheck;
        }
        projectService.addProject(projectDto, projectGif, projectPhotos);
        return R.ok();
    }

    @PutMapping("update")
    @ApiOperation("团队项目更新")
    public R projectUpdate(@RequestParam(name = "projectId") Integer projectId ,
                           @Valid ProjectDto projectDto ,
                           BindingResult bindingResult ,
                           @RequestPart(name = "projectGif" ,required = false) MultipartFile projectGif ,
                           @RequestPart(name = "projectPhotos" ,required = false)MultipartFile[] projectPhotos){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck !=null){
            return resultCheck;
        }
        boolean isExist = projectService.updateProject(projectId, projectDto, projectGif, projectPhotos);
        if(!isExist){
            return R.error().message("此项目ID不存在");
        }
        return R.ok();

    }

    @DeleteMapping("del")
    @ApiOperation("团队项目删除")
    public R projectDel(@RequestParam(name = "projectId") Integer projectId){
        boolean isExist = projectService.delProject(projectId);
        if(!isExist){
            return R.error().message("此项目ID不存在");
        }
        return R.ok();
    }

    @GetMapping("projectQueryById")
    @ApiOperation("团队项目查询根据id")
    public R projectQueryById(@RequestParam(name = "projectId") Integer projectId){
        ProjectDto projectDto = projectService.queryProjectById(projectId);
        if(projectDto==null){
            return R.error().message("此项目id不存在");
        }
        return R.ok().data("project", projectDto);
    }

}
