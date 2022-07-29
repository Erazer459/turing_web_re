package com.yantailor.turing_web_re.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by yantailor
 * on 2022/2/13 19:54 @Version 1.0
 */
public class InformDto implements Serializable {

    @ApiModelProperty(hidden = true)
    private Integer informId;

    @ApiModelProperty(value = "通知内容")
    @NotNull(message = "通知内容不为空")
    @Length(max = 1000 , message = "通知内容,最大字数为{max}")
    private String informContent;


    @ApiModelProperty(hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date informEditTime;

    @ApiModelProperty(value = "发布人署名")
    @NotNull(message = "发布人署名不为空")
    @Length(max = 5 , message = "哪有人名字那么长的XD,字数不得大于{max}")
    private String informPublisher;

    @ApiModelProperty(value = "通知标题")
    @NotNull(message = "通知标题不为空")
    @Length(max = 20 , message = "你见过有超过{max}字那么长的标题吗😈")
    private String informTitle;

    public Integer getInformId() {
        return informId;
    }

    public void setInformId(Integer informId) {
        this.informId = informId;
    }

    public String getInformContent() {
        return informContent;
    }

    public void setInformContent(String informContent) {
        this.informContent = informContent;
    }

    public Date getInformEditTime() {
        return informEditTime;
    }

    public void setInformEditTime(Date informEditTime) {
        this.informEditTime = informEditTime;
    }

    public String getInformPublisher() {
        return informPublisher;
    }

    public void setInformPublisher(String informPublisher) {
        this.informPublisher = informPublisher;
    }

    public String getInformTitle() {
        return informTitle;
    }

    public void setInformTitle(String informTitle) {
        this.informTitle = informTitle;
    }

    @Override
    public String toString() {
        return "InformDto{" +
                "informId=" + informId +
                ", informContent='" + informContent + '\'' +
                ", informEditTime=" + informEditTime +
                ", informPublisher='" + informPublisher + '\'' +
                ", informTitle='" + informTitle + '\'' +
                '}';
    }
}
