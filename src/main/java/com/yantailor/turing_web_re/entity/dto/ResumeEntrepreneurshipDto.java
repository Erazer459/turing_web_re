package com.yantailor.turing_web_re.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by yantailor
 * on 2021/10/4 10:44 @Version 1.0
 */

@TableName(value = "resume_entrepreneurship")
@JsonIgnoreProperties("resumeId")
public class ResumeEntrepreneurshipDto {

    @TableId(type = IdType.AUTO)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer resumeId;

    @NotBlank(message = "简历名字不能为空")
    @ApiModelProperty("名字")
    private String resumeName;

    @ApiModelProperty("学号")
    @Size(min=12, max=12, message="学号必须为12位!")
    private String resumeStudentId;

    @Pattern(regexp = "^1[3-9]\\d{9}$"
            ,message = "电话号码格式不正确,请检查一下捏")
    @ApiModelProperty("电话号码")
    private String resumeTelephone;

    @ApiModelProperty("专业班级，例：电子1194")
    @NotBlank(message = "必须填写专业班级")
    private String resumeMajor;

    @ApiModelProperty(value = "面试方向：创业组",example = "创业组")
    @NotBlank(message = "必须填写面试方向!")
    private String resumeDirect;

    @ApiModelProperty("自我评价")
    @NotBlank(message = "必须填写自我评价!")
    private String resumeEvaluation;

    @ApiModelProperty("掌握技能")
    @NotBlank(message = "掌握技能若无,则填暂无!")
    private String resumeSkills;

    @ApiModelProperty("项目经验若无,则填暂无!")
    @NotBlank(message = "项目经验若无,则填暂无!")
    @TableField(value = "resume_experience")
    private String resumeExp;

    @ApiModelProperty("必须填写未来期望")
    @NotBlank(message = "必须填写未来期望!")
    private String resumeExpect;

    @ApiModelProperty("其他,其它优势若无, 则填暂无!")
    @NotBlank(message = "其它优势若无, 则填暂无!")
    private String resumeOther;

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getResumeStudentId() {
        return resumeStudentId;
    }

    public void setResumeStudentId(String resumeStudentId) {
        this.resumeStudentId = resumeStudentId;
    }

    public String getResumeTelephone() {
        return resumeTelephone;
    }

    public void setResumeTelephone(String resumeTelephone) {
        this.resumeTelephone = resumeTelephone;
    }

    public String getResumeMajor() {
        return resumeMajor;
    }

    public void setResumeMajor(String resumeMajor) {
        this.resumeMajor = resumeMajor;
    }

    public String getResumeDirect() {
        return resumeDirect;
    }

    public void setResumeDirect(String resumeDirect) {
        this.resumeDirect = resumeDirect;
    }

    public String getResumeEvaluation() {
        return resumeEvaluation;
    }

    public void setResumeEvaluation(String resumeEvaluation) {
        this.resumeEvaluation = resumeEvaluation;
    }

    public String getResumeSkills() {
        return resumeSkills;
    }

    public void setResumeSkills(String resumeSkills) {
        this.resumeSkills = resumeSkills;
    }

    public String getResumeExp() {
        return resumeExp;
    }

    public void setResumeExp(String resumeExp) {
        this.resumeExp = resumeExp;
    }

    public String getResumeExpect() {
        return resumeExpect;
    }

    public void setResumeExpect(String resumeExpect) {
        this.resumeExpect = resumeExpect;
    }

    public String getResumeOther() {
        return resumeOther;
    }

    public void setResumeOther(String resumeOther) {
        this.resumeOther = resumeOther;
    }

    @Override
    public String toString() {
        return "ResumeEntrepreneurshipDto{" +
                "resumeId=" + resumeId +
                ", resumeName='" + resumeName + '\'' +
                ", resumeStudentId='" + resumeStudentId + '\'' +
                ", resumeTelephone='" + resumeTelephone + '\'' +
                ", resumeMajor='" + resumeMajor + '\'' +
                ", resumeDirect='" + resumeDirect + '\'' +
                ", resumeEvaluation='" + resumeEvaluation + '\'' +
                ", resumeSkills='" + resumeSkills + '\'' +
                ", resumeExp='" + resumeExp + '\'' +
                ", resumeExpect='" + resumeExpect + '\'' +
                ", resumeOther='" + resumeOther + '\'' +
                '}';
    }
}
