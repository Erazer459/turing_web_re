package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.Introduction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/15 15:23 @Version 1.0
 */
public interface IntroductionService extends IService<Introduction> {

    //添加简介
    boolean addIntroduction(com.yantailor.turing_web_re.entity.Introduction introduction , MultipartFile introductionVideo);

    //简介更新
    boolean updateIntroduction(Introduction introduction , MultipartFile introductionVideo);

    //简介查询
    List<Introduction> queryAllIntroduction();

    //简介查询查最新那个
    Introduction queryLastingOneIntroduction();
}
