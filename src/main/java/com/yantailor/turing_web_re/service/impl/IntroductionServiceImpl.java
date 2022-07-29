package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.dao.IntroductionDao;
import com.yantailor.turing_web_re.entity.Introduction;
import com.yantailor.turing_web_re.service.IntroductionService;
import com.yantailor.turing_web_re.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/15 15:23 @Version 1.0
 */
@Transactional
@Service
public class IntroductionServiceImpl extends ServiceImpl<IntroductionDao , Introduction> implements IntroductionService {

    @Value("${turingweb.desPath}")
    private String desPath;
    @Value("${turingweb.introductionVideoStoreFile}")
    private String introductionVideoFile;

    @Autowired
    TransferUtil transferUtil;

    @Override
    public boolean addIntroduction(Introduction introduction , MultipartFile introductionVideo) {
        introduction.setIntroductionVideoUrl(null);
        if(introductionVideo != null) {
            introduction.setIntroductionVideoUrl(desPath + "/" + introductionVideoFile + "/" + introductionVideo.getOriginalFilename());
        }
        if(baseMapper.exists(new QueryWrapper<Introduction>().eq("introduction_id", introduction.getIntroductionId()))){
            return false;
        }
        baseMapper.insert(introduction);
        if(introductionVideo != null){
            transferUtil.TransferFile(introductionVideo, introductionVideoFile);
        }
        return true;
    }

    @Override
    public boolean updateIntroduction(Introduction introduction , MultipartFile introductionVideo) {
        introduction.setIntroductionVideoUrl(null);
        if(introductionVideo != null){
            introduction.setIntroductionVideoUrl(desPath+"/"+introductionVideoFile+"/"+introductionVideo.getOriginalFilename());
        }
        if(!baseMapper.exists(new QueryWrapper<Introduction>().eq("introduction_id", introduction.getIntroductionId()))){
            return false;
        }
        if(introductionVideo != null){
            String videoUrl = baseMapper.selectOne(new QueryWrapper<Introduction>().eq("introduction_id", introduction.getIntroductionId())).getIntroductionVideoUrl();
            transferUtil.RemoveFile(videoUrl);

            transferUtil.TransferFile(introductionVideo, introductionVideoFile);
        }
        baseMapper.update(introduction, new UpdateWrapper<Introduction>().eq("introduction_id", introduction.getIntroductionId()));

        return true;
    }

    @Override
    public List<Introduction> queryAllIntroduction() {
        List<Introduction> introductions = baseMapper.selectList(null);
        return introductions;
    }

    @Override
    public Introduction queryLastingOneIntroduction() {
        return baseMapper.selectOne(new QueryWrapper<Introduction>().orderByDesc("introduction_id").last("LIMIT 1"));
    }


}
