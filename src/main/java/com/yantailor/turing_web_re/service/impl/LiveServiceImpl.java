package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.dao.LiveDao;
import com.yantailor.turing_web_re.dao.LivePhotoDao;
import com.yantailor.turing_web_re.dao.transfer.toLive;
import com.yantailor.turing_web_re.entity.Live;
import com.yantailor.turing_web_re.entity.LivePhoto;
import com.yantailor.turing_web_re.entity.dto.LiveDto;
import com.yantailor.turing_web_re.service.LiveService;
import com.yantailor.turing_web_re.utils.PageUtil;
import com.yantailor.turing_web_re.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/21 18:01 @Version 1.0
 */
@Service
@Transactional
public class LiveServiceImpl extends ServiceImpl<LiveDao , Live> implements LiveService {

    @Autowired
    LivePhotoDao livePhotoDao;
    @Value("${turingweb.desPath}")
    private String desPath;
    @Value("${turingweb.livePhotosStoreFile}")
    private String livePhotosStoreFile;
    @Autowired
    TransferUtil transferUtil;

    @Override
    public void addLive(Live live, MultipartFile[] livePhotos) {
        baseMapper.insert(live);
        if(livePhotos != null){
            LivePhoto livePhoto = LivePhoto.getInstance();
            for(int i = 0 ; i < livePhotos.length ; i++){
                livePhoto.setLiveId(live.getLiveId());
                livePhoto.setLivePhotoUrl(desPath+"/"+livePhotosStoreFile+"/"+livePhotos[i].getOriginalFilename());
                livePhotoDao.insert(livePhoto);

            }
            transferUtil.TransferFiles(livePhotos, livePhotosStoreFile);
        }

    }

    @Override
    public boolean updateLive(Integer liveId, Live live, MultipartFile[] livePhotos) {
        if(!baseMapper.exists(new QueryWrapper<Live>().eq("live_id", liveId))){
            return false;
        }
        live.setLiveId(liveId);
        baseMapper.updateById(live);
        if(livePhotos !=null){
            //传入前先删除原来的文件
            List<Object> urlList = livePhotoDao.selectObjs(new QueryWrapper<LivePhoto>().eq("live_id", liveId).select("live_photo_url"));
            transferUtil.RemoveFiles((List<String>)(List)urlList);
            livePhotoDao.delete(new QueryWrapper<LivePhoto>().eq("live_id", liveId));

            //传入文件
            LivePhoto photo = LivePhoto.getInstance();
            for(int i = 0 ; i < livePhotos.length ; i++){
                photo.setLiveId(liveId);
                photo.setLivePhotoUrl(desPath+"/"+livePhotosStoreFile+"/"+livePhotos[i].getOriginalFilename());
                livePhotoDao.insert(photo);
            }
            transferUtil.TransferFiles(livePhotos, livePhotosStoreFile);
        }
        return true;
    }

    @Override
    public boolean delLive(Integer liveId) {
        boolean exists = baseMapper.exists(new QueryWrapper<Live>().eq("live_id", liveId));
        if(!exists){
            return false;
        }
        List<Object> urlList = livePhotoDao.selectObjs(new QueryWrapper<LivePhoto>().eq("live_id", liveId).select("live_photo_url"));
        if(urlList != null){
                transferUtil.RemoveFiles((List<String>)(List)urlList);
                livePhotoDao.delete(new QueryWrapper<LivePhoto>().eq("live_id", liveId));
        }
        baseMapper.deleteById(liveId);

        return true;
    }

    @Override
    public List<LiveDto> queryLive() {
        List<LiveDto> dtoList = new ArrayList<>();
        List<Live> liveList = baseMapper.selectList(null);
        for(Live live : liveList){
            LiveDto liveDto = toLive.INSTANCE.toLiveDto(live);
            liveDto.setLivePhotoUrl((List<String>)(List) livePhotoDao.selectObjs(new QueryWrapper<LivePhoto>().eq("live_id", liveDto.getLiveId()).select("live_photo_url")));
            dtoList.add(liveDto);
        }
        return dtoList;
    }

    @Override
    public List<LiveDto> queryLive(Integer page, Integer offset) {
        List<LiveDto> dtoList = queryLive();
        return PageUtil.PageHandler(page, offset, dtoList);
    }

    @Override
    public LiveDto queryLiveById(Integer liveId) {
        Live live = baseMapper.selectById(liveId);
        LiveDto liveDto = toLive.INSTANCE.toLiveDto(live);
        return liveDto;
    }


}
