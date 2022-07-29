package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.dao.MemberDao;
import com.yantailor.turing_web_re.dao.ProjectDao;
import com.yantailor.turing_web_re.dao.transfer.toProject;
import com.yantailor.turing_web_re.entity.Member;
import com.yantailor.turing_web_re.entity.Project;
import com.yantailor.turing_web_re.entity.dto.ProjectDto;
import com.yantailor.turing_web_re.service.ProjectService;
import com.yantailor.turing_web_re.utils.PageUtil;
import com.yantailor.turing_web_re.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/17 9:40 @Version 1.0
 */
@Service
@Transactional
public class ProjectServiceImpl extends ServiceImpl<ProjectDao , Project> implements ProjectService {
    @Autowired
    TransferUtil transferUtil;
    @Value("${turingweb.projectSourceFile}")
    private String projectSourceFile;

    @Value("${turingweb.desPath}")
    private String desPath;

    private String gifUrl;
    private List<String> photosList;

    @Autowired
    MemberDao memberDao;

    @Override
    public void addProject(ProjectDto projectDto, MultipartFile projectGif, MultipartFile[] projectPhotos) {
        if(projectGif != null){
            transferUtil.TransferFile(projectGif, projectSourceFile);
            projectDto.setProjectGifUrl(desPath+"/"+projectSourceFile+"/"+projectGif.getOriginalFilename());
        }
        if(projectPhotos != null){
            transferUtil.TransferFiles(projectPhotos, projectSourceFile);
        }
        Project project = toProject.INSTANCE.toProject(projectDto);
        baseMapper.insert(project);

        //project_member表的处理
        for(String memberStudentId : projectDto.getProjectMemberStudentId()){
            baseMapper.projectMemberTableRecord(memberStudentId, project.getProjectId());
        }
        //project_photo表的处理
        for(MultipartFile projectPhoto : projectPhotos){
            baseMapper.projectPhotoTableRecord(project.getProjectId(), desPath+"/"+projectSourceFile+"/"+projectPhoto.getOriginalFilename());
        }

    }

    @Override
    public boolean updateProject(Integer projectId, ProjectDto projectDto, MultipartFile projectGif, MultipartFile[] projectPhotos) {
        if(!baseMapper.exists(new QueryWrapper<Project>().eq("project_id", projectId))){
            return false;
        }
        Project projectOld = baseMapper.selectById(projectId);
        if(projectGif != null){
            //添加新的
            transferUtil.TransferFile(projectGif, projectSourceFile);
            projectDto.setProjectGifUrl(desPath+"/"+projectSourceFile+"/"+projectGif.getOriginalFilename());
            //删除旧的
            transferUtil.RemoveFile(projectOld.getProjectGifUrl());
        }
        if(projectPhotos != null){
            //添加新的
            transferUtil.TransferFiles(projectPhotos, projectSourceFile);
            //删除旧的
            transferUtil.RemoveFiles(baseMapper.projectPhotoTableRecordQuery(projectId));
            baseMapper.projectPhotoTableRecordDel(projectId);
        }
        projectDto.setProjectId(projectId);
        baseMapper.updateById(toProject.INSTANCE.toProject(projectDto));

        //project_member表的处理
        baseMapper.projectMemberTableRecordDel(projectId);
        for(String memberStudentId : projectDto.getProjectMemberStudentId()){
            baseMapper.projectMemberTableRecord(memberStudentId, projectId);
        }
        //project_photo表的处理
        for(MultipartFile projectPhoto : projectPhotos){
            baseMapper.projectPhotoTableRecord(projectId, desPath+"/"+projectSourceFile+"/"+projectPhoto.getOriginalFilename());
        }

        return true;
    }

    @Override
    public boolean delProject(Integer projectId) {
        if(!baseMapper.exists(new QueryWrapper<Project>().eq("project_id", projectId))){
            return false;
        }
        try {
            //文件删除-io操作
            Project project = baseMapper.selectById(projectId);
            gifUrl = project.getProjectGifUrl();
            photosList = baseMapper.projectPhotoTableRecordQuery(projectId);
            //表操作
            baseMapper.projectPhotoTableRecordDel(projectId);
            baseMapper.projectMemberTableRecordDel(projectId);
            baseMapper.deleteById(projectId);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transferUtil.RemoveFile(gifUrl);
            transferUtil.RemoveFiles(photosList);
        }


        return false;
    }

    @Override
    public List<ProjectDto> queryProject() {
        ArrayList<ProjectDto> projectDtoList = new ArrayList<>();
//        ArrayList<String> memberNameList = new ArrayList<>();
        List<Project> projects = baseMapper.selectList(null);
        for(Project project : projects){
            ArrayList<String> memberNameList = new ArrayList<>();
            List<String> memberStudentIdList = baseMapper.projectMemberTableRecordQuery(project.getProjectId());
            for (String id : memberStudentIdList){
                Member member = memberDao.selectOne(new QueryWrapper<Member>().eq("member_student_id", id).select("member_name"));
                memberNameList.add(member.getMemberName());
            }
            ProjectDto projectDto = toProject.INSTANCE.toProjectDto(project);
            projectDto.setProjectPhotosUrl(baseMapper.projectPhotoTableRecordQuery(project.getProjectId()));
            projectDto.setProjectMemberName(memberNameList);
            projectDtoList.add(projectDto);


        }
        return projectDtoList;
    }

    @Override
    public List<ProjectDto> queryProject(Integer page, Integer offset) {
        List<ProjectDto> dtoList = queryProject();
        return PageUtil.PageHandler(page, offset, dtoList);
    }

    @Override
    public ProjectDto queryProjectById(Integer projectId) {
        if(!baseMapper.exists(new QueryWrapper<Project>().eq("project_id", projectId))){
            return null;
        }
        ArrayList<String> memberNameList = new ArrayList<>();
        List<String> memberStudentIdList = baseMapper.projectMemberTableRecordQuery(projectId);
        for (String id : memberStudentIdList){
            Member member = memberDao.selectOne(new QueryWrapper<Member>().eq("member_student_id", id).select("member_name"));
            memberNameList.add(member.getMemberName());
        }
        List<String> photoList = baseMapper.projectPhotoTableRecordQuery(projectId);

        Project project = baseMapper.selectById(projectId);

        ProjectDto projectDto = toProject.INSTANCE.toProjectDto(project);

        projectDto.setProjectMemberName(memberNameList);
        projectDto.setProjectPhotosUrl(photoList);

        return projectDto;
    }


}
