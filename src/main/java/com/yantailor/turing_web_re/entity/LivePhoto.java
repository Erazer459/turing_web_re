package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yantailor
 * on 2022/2/5 11:20 @Version 1.0
 */

@TableName("live_photo")
public class LivePhoto {
    private static volatile LivePhoto INSTANCE;

    public static LivePhoto getInstance(){
        if(INSTANCE == null){
            synchronized (LivePhoto.class){
                if(INSTANCE == null){
                    INSTANCE = new LivePhoto();
                }
            }
        }
        return INSTANCE;
    }

    private Integer liveId;

    private String livePhotoUrl;

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    public String getLivePhotoUrl() {
        return livePhotoUrl;
    }

    public void setLivePhotoUrl(String livePhotoUrl) {
        this.livePhotoUrl = livePhotoUrl;
    }

    @Override
    public String toString() {
        return "LivePhoto{" +
                "liveId=" + liveId +
                ", livePhotoUrl='" + livePhotoUrl + '\'' +
                '}';
    }
}
