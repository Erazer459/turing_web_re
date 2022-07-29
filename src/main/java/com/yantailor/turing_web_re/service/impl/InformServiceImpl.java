package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.bean.ErrorMap;
import com.yantailor.turing_web_re.dao.InformDao;
import com.yantailor.turing_web_re.dao.transfer.toInform;
import com.yantailor.turing_web_re.entity.Inform;
import com.yantailor.turing_web_re.entity.dto.AwardDto;
import com.yantailor.turing_web_re.entity.dto.InformDto;
import com.yantailor.turing_web_re.utils.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/13 22:10 @Version 1.0
 */
@Transactional
@Service
public class InformServiceImpl extends ServiceImpl<InformDao, Inform> implements com.yantailor.turing_web_re.service.InformService {
    public final static Logger LOGGER = LoggerFactory.getLogger(InformServiceImpl.class);


    @Override
    public void addInform(InformDto informDto) {
        Inform inform = toInform.INSTANCE.toInform(informDto);
        try {
            baseMapper.insert(inform);
        } catch (Exception e) {
            LOGGER.info("添加通知失败----,time:"+new Date());
            e.printStackTrace();
            throw new ErrorMap(ErrorCode.INFORM_INSERT_ERROR);
        }
    }

    @Override
    public void updateInform(int informId, InformDto informDto) {
              Inform inform = toInform.INSTANCE.toInform(informDto);
        try {
            baseMapper.update(inform,new UpdateWrapper<Inform>().eq("inform_id", informId));
        } catch (Exception e) {
            LOGGER.info("更新通知失败----,time:"+new Date());
            e.printStackTrace();
            throw new ErrorMap(ErrorCode.INFORM_UPDATE_ERROR);
        }

    }

    @Override
    public void delInform(int informId) {
        try {
            baseMapper.delete(new QueryWrapper<Inform>().eq("inform_id", informId));
        } catch (Exception e) {
            LOGGER.info("更新删除失败----,time:"+new Date());
            e.printStackTrace();
            throw new ErrorMap(ErrorCode.INFORM_DEL_ERROR);
        }
    }

    @Override
    public List<InformDto> queryInform_recently() {
        List<InformDto> dtoList = new LinkedList<>();
        List<Inform> informList = baseMapper.selectList(new QueryWrapper<Inform>().orderByDesc("inform_edit_time").last("LIMIT 5"));
        for (Inform inform : informList){
            InformDto informDto = toInform.INSTANCE.toInformDto(inform);
            dtoList.add(informDto);
        }
        return dtoList;
    }

    @Override
    public List<InformDto> queryInform_all() {
        return null;
    }

}
