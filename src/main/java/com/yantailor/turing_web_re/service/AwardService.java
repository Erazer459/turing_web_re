package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.Award;
import com.yantailor.turing_web_re.entity.dto.AwardDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/4 17:51 @Version 1.0
 */

public interface AwardService extends IService<Award> {

    //添加奖项
    void addAward(AwardDto awardDto, MultipartFile[] awardPhotos);

    //删除
    void delAward(int awardId);

    //更新奖项
    void updateAward(int awardId , AwardDto awardDto,MultipartFile[] awardPhotos);

    //查询全奖项
    List<AwardDto> queryAward();

    //查询奖项Page
    List<AwardDto> queryAward(int page , int offset);

    //奖项总条
    Long countAward();

}
