package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.Inform;
import com.yantailor.turing_web_re.entity.dto.InformDto;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/13 20:40 @Version 1.0
 */
public interface InformService extends IService<Inform> {

    //添加通知
    void addInform(InformDto informDto);

    //更新通知
    void updateInform(int informId , InformDto informDto);

    //删除通知
    void delInform(int informId);

    //显示最近的通知
    List<InformDto> queryInform_recently();

    //显示通知
    List<InformDto> queryInform_all();
}
