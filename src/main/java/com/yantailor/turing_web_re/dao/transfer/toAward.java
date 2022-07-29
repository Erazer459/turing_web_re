package com.yantailor.turing_web_re.dao.transfer;

import com.yantailor.turing_web_re.entity.Award;
import com.yantailor.turing_web_re.entity.dto.AwardDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by yantailor
 * on 2022/2/9 15:53 @Version 1.0
 */
@Mapper(componentModel="spring")
public interface toAward {
    toAward INSTANCE = Mappers.getMapper(toAward.class);

    Award toAward(AwardDto awardDto);

    AwardDto toAwardDto(Award award);
}
