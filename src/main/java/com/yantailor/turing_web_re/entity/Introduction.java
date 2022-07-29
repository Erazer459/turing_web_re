package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yantailor
 * on 2022/2/4 19:53 @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@TableName("introduction")
public class Introduction implements Serializable {

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date introductionEditTime;

    @ApiModelProperty(value = "团队简介内容")
    @NotNull(message = "简介为空")
    private String introductionInfo;

    @ApiModelProperty(hidden = true)
    private String introductionVideoUrl;

    @ApiModelProperty(value = "简介年份 例：2021" , example = "2021")
    @NotNull(message = "这个不能为空啊，填年份捏铁铁")
    private Integer introductionId;

    public Date getIntroductionEditTime() {
        return introductionEditTime;
    }

    public void setIntroductionEditTime(Date introductionEditTime) {
        this.introductionEditTime = introductionEditTime;
    }

    public String getIntroductionInfo() {
        return introductionInfo;
    }

    public void setIntroductionInfo(String introductionInfo) {
        this.introductionInfo = introductionInfo;
    }

    public String getIntroductionVideoUrl() {
        return introductionVideoUrl;
    }

    public void setIntroductionVideoUrl(String introductionVideoUrl) {
        this.introductionVideoUrl = introductionVideoUrl;
    }

    public Integer getIntroductionId() {
        return introductionId;
    }

    public void setIntroductionId(Integer introductionId) {
        this.introductionId = introductionId;
    }

    @Override
    public String toString() {
        return "Introduction{" +
                "introductionEditTime=" + introductionEditTime +
                ", introductionInfo='" + introductionInfo + '\'' +
                ", introductionVideoUrl='" + introductionVideoUrl + '\'' +
                ", introductionId=" + introductionId +
                '}';
    }
}
