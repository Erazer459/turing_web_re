package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by yantailor
 * on 2022/2/4 17:40 @Version 1.0
 */
@NoArgsConstructor
@TableName("award_photo")
public class AwardPhoto {

    private static volatile AwardPhoto INSTANCE;

    public static AwardPhoto getInstance(){
        if(INSTANCE == null){
            synchronized (AwardPhoto.class){
                if(INSTANCE == null){
                    INSTANCE = new AwardPhoto();
                }
            }
        }
        return INSTANCE;
    }

    private int awardId;


    private String awardPhotoUrl;

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public String getAwardPhotoUrl() {
        return awardPhotoUrl;
    }

    public void setAwardPhotoUrl(String awardPhotoUrl) {
        this.awardPhotoUrl = awardPhotoUrl;
    }
}
