package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by yantailor
 * on 2022/2/5 11:17 @Version 1.0
 */

@TableName("live")
public class Live {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer liveId;

    @NotNull(message = "活动内容不为空")
    @ApiModelProperty(value = "活动内容")
    private String liveContent;

    @NotNull(message = "活动标题不为空")
    @Length(max = 30 , message = "标题最多不超过{max}字")
    @ApiModelProperty(value = "活动标题")
    private String liveName;

    //前台传后台
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "活动日期不为空")
    @ApiModelProperty(value = "活动时间" ,example = "yyyy-MM-dd")
    private Date liveTime;

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    public String getLiveContent() {
        return liveContent;
    }

    public void setLiveContent(String liveContent) {
        this.liveContent = liveContent;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public Date getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(Date liveTime) {
        this.liveTime = liveTime;
    }

    @Override
    public String toString() {
        return "Live{" +
                "liveId=" + liveId +
                ", liveContent='" + liveContent + '\'' +
                ", liveName='" + liveName + '\'' +
                ", liveTime=" + liveTime +
                '}';
    }
}
