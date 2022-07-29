package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.Project;
import com.yantailor.turing_web_re.entity.dto.ProjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/17 0:16 @Version 1.0
 */
public interface ProjectService extends IService<Project>{
    //添加项目
    void addProject(ProjectDto projectDto , MultipartFile projectGif , MultipartFile[] projectPhotos);

    //更新项目
    boolean updateProject(Integer projectId , ProjectDto projectDto , MultipartFile projectGif , MultipartFile[] projectPhotos);

    //删除项目
    boolean delProject(Integer projectId);

    //查询项目
    List<ProjectDto> queryProject();

    //查询项目-分页查询
    List<ProjectDto> queryProject(Integer page , Integer offset);

    //查询项目-根据projectId
    ProjectDto queryProjectById(Integer projectId);


}
