package com.yantailor.turing_web_re.entity.dto;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/10 22:20 @Version 1.0
 */
public class LiveDto {
    private Integer liveId ;

    private String liveContent;

    private String liveName;

    private List<String> livePhotoUrl;

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

    public List<String> getLivePhotoUrl() {
        return livePhotoUrl;
    }

    public void setLivePhotoUrl(List<String> livePhotoUrl) {
        this.livePhotoUrl = livePhotoUrl;
    }
}
