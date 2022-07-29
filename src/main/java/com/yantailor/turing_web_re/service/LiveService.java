package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.Live;
import com.yantailor.turing_web_re.entity.dto.LiveDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/21 18:01 @Version 1.0
 */
public interface LiveService extends IService<Live> {

    //添加活动事件
    void addLive(Live live , MultipartFile[] livePhotos);

    //更新活动事件
    boolean updateLive(Integer liveId , Live live , MultipartFile[] livePhotos);

    //删除活动事件
    boolean delLive(Integer liveId);

    //查询全部团队活动
    List<LiveDto> queryLive();

    //查询团队活动信息Page
    List<LiveDto> queryLive(Integer page , Integer offset);

    //根据Id查询活动
    LiveDto queryLiveById(Integer liveId);
}
