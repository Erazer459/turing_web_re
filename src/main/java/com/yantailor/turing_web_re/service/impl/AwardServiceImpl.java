package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.bean.ErrorMap;
import com.yantailor.turing_web_re.dao.AwardDao;
import com.yantailor.turing_web_re.dao.AwardPhotoDao;
import com.yantailor.turing_web_re.dao.MemberDao;
import com.yantailor.turing_web_re.dao.transfer.toAward;
import com.yantailor.turing_web_re.entity.Award;
import com.yantailor.turing_web_re.entity.AwardPhoto;
import com.yantailor.turing_web_re.entity.Member;
import com.yantailor.turing_web_re.entity.dto.AwardDto;
import com.yantailor.turing_web_re.service.AwardService;

import com.yantailor.turing_web_re.utils.ErrorCode;
import com.yantailor.turing_web_re.utils.PageUtil;
import com.yantailor.turing_web_re.utils.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/5 12:07 @Version 1.0
 */

@Service
@Transactional
public class AwardServiceImpl extends ServiceImpl<AwardDao, Award> implements AwardService {

    public final static Logger LOGGER = LoggerFactory.getLogger(AwardServiceImpl.class);

    @Value("${turingweb.desPath}")
    private String desPath;
    @Value("${turingweb.awardStoreFile}")
    private String awardStoreFile;

    @Autowired
    AwardPhotoDao awardPhotoDao;

    @Autowired
    TransferUtil transferUtil;

    @Autowired
    MemberDao memberDao;

    @Override
    public void addAward(AwardDto awardDto, MultipartFile[] awardPhotos) {
        Award award = toAward.INSTANCE.toAward(awardDto);
        //存储进表
        this.save(award);

        //成员-奖项挂钩
        for (int i = 0 ; i < awardDto.getMemberStudentId().size() ; i++){
            try {
                memberDao.memberAwardTableRecord(awardDto.getMemberStudentId().get(i), award.getAwardId());
            } catch (Exception e) {
                LOGGER.info("此学号:"+awardDto.getMemberStudentId().get(i)+"在数据库不存在");
                e.printStackTrace();
                throw new ErrorMap(ErrorCode.MEMBER_NOT_EXIST);
            }
        }

        //查看传的图片是否为空,不空则传入
        if(awardPhotos != null){
            AwardPhoto photo = AwardPhoto.getInstance();
            for (int i = 0 ; i < awardPhotos.length ; i++){
                photo.setAwardId(award.getAwardId());
                photo.setAwardPhotoUrl(desPath+"/"+awardStoreFile+"/"+awardPhotos[i].getOriginalFilename());
                awardPhotoDao.insert(photo);

                //文件传输
                transferUtil.TransferFiles(awardPhotos, awardStoreFile);
            }
        }



    }

    @Override
    public void delAward(int awardId) {
        try {
            //成员-奖项挂钩删除
            memberDao.memberAwardTableRecordDel(awardId);

            //奖项图片也删除
            awardPhotoDao.delete(new QueryWrapper<AwardPhoto>().eq("award_id", awardId));

            baseMapper.delete(new QueryWrapper<Award>().eq("award_id", awardId));
        } catch (Exception e) {
            LOGGER.info("奖项删除失败----,awardId:"+awardId+",time:"+new Date());
            e.printStackTrace();
        }
    }

    @Override
    public void updateAward(int awardId , AwardDto awardDto, MultipartFile[] awardPhotos) {
        try {
            Award award = toAward.INSTANCE.toAward(awardDto);
            baseMapper.update(award, new UpdateWrapper<Award>()
                    .eq("award_id", awardId));
            //查看传的图片是否为空,不空则传入
            if(awardPhotos != null){

                //传入之前先删除原有的文件
                QueryWrapper<AwardPhoto> awardPhotoQueryWrapper = new QueryWrapper<>();
                awardPhotoQueryWrapper.eq("award_id", awardId);
                awardPhotoQueryWrapper.select("award_photo_url");
                List<Object> urlList =awardPhotoDao.selectObjs(awardPhotoQueryWrapper);
                transferUtil.RemoveFiles((List<String>)(List) urlList);
                awardPhotoQueryWrapper = new QueryWrapper<>();
                awardPhotoQueryWrapper.eq("award_id", awardId);
                awardPhotoDao.delete(awardPhotoQueryWrapper);

                //传入文件
                AwardPhoto photo = AwardPhoto.getInstance();
                for (int i = 0 ; i < awardPhotos.length ; i++){
                    photo.setAwardId(awardId);
                    photo.setAwardPhotoUrl(desPath+"/"+awardStoreFile+"/"+awardPhotos[i].getOriginalFilename());
                    awardPhotoDao.insert(photo);
                }
                //文件传输
                transferUtil.TransferFiles(awardPhotos, awardStoreFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<AwardDto> queryAward() {
        List<AwardDto> dtoList = new LinkedList<>();
        List<Award> awardList = baseMapper.selectList(null);
        toAward instance = toAward.INSTANCE;
        for(int i = 0 ; i < awardList.size() ; i++){
            AwardDto awardDto = instance.toAwardDto(awardList.get(i));
            List<Object> memberNameList = null;
            List<String> memberStudentIdList = memberDao.memberAwardTableRecordQuery(awardList.get(i).getAwardId());
            for(int j = 0 ; j < memberStudentIdList.size() ; j++){
                memberNameList = memberDao.selectObjs
                        (new QueryWrapper<Member>().
                                eq("member_student_id", memberStudentIdList.get(j)).
                                select("member_name"));
            }
            awardDto.setMemberName((List<String>)(List)memberNameList);
            awardDto.setAwardPhotoUrl((List<String>)(List) awardPhotoDao.selectObjs(new QueryWrapper<AwardPhoto>().eq("award_id", awardList.get(i).getAwardId()).select("award_photo_url")));
            dtoList.add(awardDto);
        }
        return dtoList;
    }

    @Override
    public List<AwardDto> queryAward(int page , int offset) {
        List<AwardDto> dtoList = queryAward();
        return PageUtil.PageHandler(page, offset, dtoList);


    }

    @Override
    public Long countAward() {
        return baseMapper.selectCount(null);

    }


}
