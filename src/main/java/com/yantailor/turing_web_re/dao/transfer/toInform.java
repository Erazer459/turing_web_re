package com.yantailor.turing_web_re.dao.transfer;

import com.yantailor.turing_web_re.entity.Inform;
import com.yantailor.turing_web_re.entity.dto.InformDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by yantailor
 * on 2022/2/13 20:41 @Version 1.0
 */
@Mapper
public interface toInform {
    toInform INSTANCE = Mappers.getMapper(toInform.class);

    Inform toInform(InformDto informDto);

    InformDto toInformDto(Inform inform);
}
