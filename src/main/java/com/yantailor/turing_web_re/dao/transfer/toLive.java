package com.yantailor.turing_web_re.dao.transfer;

import com.yantailor.turing_web_re.entity.Live;
import com.yantailor.turing_web_re.entity.dto.LiveDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by yantailor
 * on 2022/3/10 22:23 @Version 1.0
 */
@Mapper
public interface toLive {
    toLive INSTANCE = Mappers.getMapper(toLive.class);

    LiveDto toLiveDto(Live live);
}
