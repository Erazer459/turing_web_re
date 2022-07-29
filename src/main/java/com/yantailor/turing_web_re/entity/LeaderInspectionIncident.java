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
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yantailor
 * on 2022/2/5 11:10 @Version 1.0
 */

@TableName("leader_inspection_incident")
public class LeaderInspectionIncident implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private int incidentId;

    @NotNull(message = "事件内容不能为空")
    @ApiModelProperty(value = "事件内容")
    private String incidentContent;

    //前台传后台
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "视察时间 格式yyyy-MM-dd")
    private Date incidentDate;

    @ApiModelProperty(hidden = true)
    private String incidentPhotoUrl;

    @NotNull(message = "事件标题不能为空")
    @Length(max = 100,message = "标题字数不能超过{max}字")
    private String incidentTitle;

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentContent() {
        return incidentContent;
    }

    public void setIncidentContent(String incidentContent) {
        this.incidentContent = incidentContent;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentPhotoUrl() {
        return incidentPhotoUrl;
    }

    public void setIncidentPhotoUrl(String incidentPhotoUrl) {
        this.incidentPhotoUrl = incidentPhotoUrl;
    }

    public String getIncidentTitle() {
        return incidentTitle;
    }

    public void setIncidentTitle(String incidentTitle) {
        this.incidentTitle = incidentTitle;
    }

    @Override
    public String toString() {
        return "LeaderInspectionIncident{" +
                "incidentId=" + incidentId +
                ", incidentContent='" + incidentContent + '\'' +
                ", incidentDate=" + incidentDate +
                ", incidentPhotoUrl='" + incidentPhotoUrl + '\'' +
                ", incidentTitle='" + incidentTitle + '\'' +
                '}';
    }
}
