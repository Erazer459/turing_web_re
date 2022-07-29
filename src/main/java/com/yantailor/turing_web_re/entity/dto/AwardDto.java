package com.yantailor.turing_web_re.entity.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/5 11:54 @Version 1.0
 */

public class AwardDto implements Serializable {

    //传入时藏住 不需要 , 传出需要
    @ApiModelProperty(hidden = true)
    private int awardId;

    @ApiModelProperty(value = "奖项名称")
    @NotNull(message = "奖项名称不能为空")
    private String awardName;

    //前台传后台
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "获奖时间 格式yyyy-MM-dd")
    @NotNull(message = "获奖日期不能为空 格式yyyy-MM-dd")
    private Date awardTime;

    @ApiModelProperty(value = "获奖成员学号 格式 202111611431")
    @JsonIgnore
    @NotNull(message = "获奖成员学号不能为空，你总要有一个人获奖，对吧 0_0 ")
    private LinkedList<String> memberStudentId;

    @NotNull(message = "作品名称不能为空")
    @ApiModelProperty(value = "作品名称")
    private String awardWorks;

    //传入时藏住 不需要 , 传出需要
    @ApiModelProperty(hidden = true)
    private List<String> memberName;

    //传入时藏住 不需要 , 传出需要
    @ApiModelProperty(hidden = true)
    private List<String> awardPhotoUrl;

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public Date getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
    }

    public LinkedList<String> getMemberStudentId() {
        return memberStudentId;
    }

    public void setMemberStudentId(LinkedList<String> memberStudentId) {
        this.memberStudentId = memberStudentId;
    }

    public String getAwardWorks() {
        return awardWorks;
    }

    public void setAwardWorks(String awardWorks) {
        this.awardWorks = awardWorks;
    }

    public List<String> getMemberName() {
        return memberName;
    }

    public void setMemberName(List<String> memberName) {
        this.memberName = memberName;
    }

    public List<String> getAwardPhotoUrl() {
        return awardPhotoUrl;
    }

    public void setAwardPhotoUrl(List<String> awardPhotoUrl) {
        this.awardPhotoUrl = awardPhotoUrl;
    }

    @Override
    public String toString() {
        return "AwardDto{" +
                "awardId=" + awardId +
                ", awardName='" + awardName + '\'' +
                ", awardTime=" + awardTime +
                ", memberStudentId=" + memberStudentId +
                ", awardWorks='" + awardWorks + '\'' +
                ", memberName=" + memberName +
                ", awardPhotoUrl=" + awardPhotoUrl +
                '}';
    }
}
