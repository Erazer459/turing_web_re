package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yantailor
 * on 2022/2/5 11:48 @Version 1.0
 */

@TableName("project")
public class Project {
    @TableId(type = IdType.AUTO)
    private Integer projectId;

    private String projectContent;

    private String projectName;

    private String projectGifUrl;

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

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectContent='" + projectContent + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectGifUrl='" + projectGifUrl + '\'' +
                '}';
    }
}
