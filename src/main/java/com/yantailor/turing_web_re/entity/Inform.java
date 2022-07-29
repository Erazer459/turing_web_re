package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 * Created by yantailor
 * on 2022/2/4 17:58 @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("inform")
public class Inform {

    @TableId(type = IdType.AUTO)
    private int informId;

    private String informContent;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    //前台传后台
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date informEditTime;

    private String informPublisher;

    private String informTitle;

    public int getInformId() {
        return informId;
    }

    public void setInformId(int informId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inform inform = (Inform) o;
        return informId == inform.informId &&
                Objects.equals(informContent, inform.informContent) &&
                Objects.equals(informEditTime, inform.informEditTime) &&
                Objects.equals(informPublisher, inform.informPublisher) &&
                Objects.equals(informTitle, inform.informTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(informId, informContent, informEditTime, informPublisher, informTitle);
    }

    @Override
    public String toString() {
        return "Inform{" +
                "informId=" + informId +
                ", informContent='" + informContent + '\'' +
                ", informEditTime=" + informEditTime +
                ", informPublisher='" + informPublisher + '\'' +
                ", informTitle='" + informTitle + '\'' +
                '}';
    }
}
