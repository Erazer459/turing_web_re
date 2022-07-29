package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by yantailor
 * on 2021/10/2 15:41 @Version 1.0
 */

@TableName(value = "teacher")
public class Teacher {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long teacherId;

    @ApiModelProperty(value = "教师名字")
    @Length(max = 5,message = "教师名字不超过{max}个字")
    private String teacherName;

    @ApiModelProperty(value = "教师邮箱")
    @Length(max = 30,message = "邮箱不符合,过长")
    @NotNull(message = "邮箱不为空")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",message = "邮箱格式不正确")
    private String teacherEmail;

    @ApiModelProperty(value = "教师户籍地,可不填" ,example = "广东")
    private String teacherBorn;

    @ApiModelProperty(value = "教师职位")
    @NotNull(message = "教师职位填写不为空")
    private String teacherPosition;

    @ApiModelProperty(value = "教师职称")
    @NotNull(message = "教师职称填写不为空")
    private String teacherJob;

    @ApiModelProperty(value = "教师毕业院校")
    @NotNull(message = "教师毕业院校填写不为空")
    private String teacherGraduation;

    @ApiModelProperty(value = "教师主要研究方向")
    @NotNull(message = "教师主要研究方向填写不为空")
    private String teacherResearch;

    @ApiModelProperty(value = "教师科技研究方向")
    @NotNull(message = "教师科技研究方向填写不为空")
    private String teacherScientificResearch;

    @ApiModelProperty(value = "教师获奖情况")
    @NotNull(message = "教师获奖情况填写不为空")
    private String teacherAwardIntroduction;

    @ApiModelProperty(value = "教师图片",hidden = true)
    private String teacherImg;


    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherBorn() {
        return teacherBorn;
    }

    public void setTeacherBorn(String teacherBorn) {
        this.teacherBorn = teacherBorn;
    }

    public String getTeacherPosition() {
        return teacherPosition;
    }

    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition;
    }

    public String getTeacherJob() {
        return teacherJob;
    }

    public void setTeacherJob(String teacherJob) {
        this.teacherJob = teacherJob;
    }

    public String getTeacherGraduation() {
        return teacherGraduation;
    }

    public void setTeacherGraduation(String teacherGraduation) {
        this.teacherGraduation = teacherGraduation;
    }

    public String getTeacherResearch() {
        return teacherResearch;
    }

    public void setTeacherResearch(String teacherResearch) {
        this.teacherResearch = teacherResearch;
    }

    public String getTeacherScientificResearch() {
        return teacherScientificResearch;
    }

    public void setTeacherScientificResearch(String teacherScientificResearch) {
        this.teacherScientificResearch = teacherScientificResearch;
    }

    public String getTeacherAwardIntroduction() {
        return teacherAwardIntroduction;
    }

    public void setTeacherAwardIntroduction(String teacherAwardIntroduction) {
        this.teacherAwardIntroduction = teacherAwardIntroduction;
    }

    public String getTeacherImg() {
        return teacherImg;
    }

    public void setTeacherImg(String teacherImg) {
        this.teacherImg = teacherImg;
    }



    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherBorn='" + teacherBorn + '\'' +
                ", teacherPosition='" + teacherPosition + '\'' +
                ", teacherJob='" + teacherJob + '\'' +
                ", teacherGraduation='" + teacherGraduation + '\'' +
                ", teacherResearch='" + teacherResearch + '\'' +
                ", teacherScientificResearch='" + teacherScientificResearch + '\'' +
                ", teacherAwardIntroduction='" + teacherAwardIntroduction + '\'' +
                ", teacherImg='" + teacherImg + '\'' +
                '}';
    }
}
