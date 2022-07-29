package com.yantailor.turing_web_re.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yantailor.turing_web_re.config.MemberStudentIdIsExistValidator;
import com.yantailor.turing_web_re.utils.MemberStudentIdIsExist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/16 23:46 @Version 1.0
 */
public class ProjectDto {
    //项目id不传出
//    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @TableId(type = IdType.AUTO)
    private Integer projectId;

    @ApiModelProperty(value = "项目内容")
    @NotNull(message = "项目内容不为空")
    @Length(max = 500 , message = "项目内容描述不超过{max}字")
    private String projectContent;

    @ApiModelProperty(value = "项目名称")
    @NotNull(message = "项目名称不为空")
    @Length(max = 20 , message = "项目名称不超过{max}字")
    private String projectName;

    @ApiModelProperty(value = "项目GIF演示图" , hidden = true)
    private String projectGifUrl;

    @ApiModelProperty(hidden = true)
    private List<String> projectPhotosUrl;

    @ApiModelProperty(value = "项目人员学号")
    @NotNull(message = "项目人员不为空")
    @MemberStudentIdIsExist()
    @JsonIgnore
    private List<String> projectMemberStudentId;

    @ApiModelProperty(hidden = true)
    private List<String> projectMemberName;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGifUrl() {
        return projectGifUrl;
    }

    public void setProjectGifUrl(String projectGifUrl) {
        this.projectGifUrl = projectGifUrl;
    }

    public List<String> getProjectPhotosUrl() {
        return projectPhotosUrl;
    }

    public void setProjectPhotosUrl(List<String> projectPhotosUrl) {
        this.projectPhotosUrl = projectPhotosUrl;
    }

    public List<String> getProjectMemberStudentId() {
        return projectMemberStudentId;
    }

    public void setProjectMemberStudentId(List<String> projectMemberStudentId) {
        this.projectMemberStudentId = projectMemberStudentId;
    }

    public List<String> getProjectMemberName() {
        return projectMemberName;
    }

    public void setProjectMemberName(List<String> projectMemberName) {
        this.projectMemberName = projectMemberName;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "projectId='" + projectId + '\'' +
                ", projectContent='" + projectContent + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectGifUrl='" + projectGifUrl + '\'' +
                ", projectPhotosUrl=" + projectPhotosUrl +
                ", projectMemberName=" + projectMemberName +
                '}';
    }
}
