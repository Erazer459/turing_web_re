package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by yantailor
 * on 2022/2/4 17:33 @Version 1.0
 */

@TableName("award")
public class Award {

    //mbp id自增
    @TableId(type = IdType.AUTO)
    private int awardId;

    private String awardName;

    private Date awardTime;

    private String awardWorks;

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

    public String getAwardWorks() {
        return awardWorks;
    }

    public void setAwardWorks(String awardWorks) {
        this.awardWorks = awardWorks;
    }
}
