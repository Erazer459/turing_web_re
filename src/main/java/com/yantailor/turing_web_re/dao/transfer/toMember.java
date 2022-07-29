package com.yantailor.turing_web_re.dao.transfer;

import com.yantailor.turing_web_re.entity.Member;
import com.yantailor.turing_web_re.entity.dto.MemberDto;
import com.yantailor.turing_web_re.entity.dto.MemberQueryDto;
import com.yantailor.turing_web_re.entity.dto.MemberUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by yantailor
 * on 2022/3/11 10:31 @Version 1.0
 */
@Mapper
public interface toMember {
    toMember INSTANCE = Mappers.getMapper(toMember.class);

    Member toMember(MemberDto memberDto);

    MemberDto toMemberDto(Member member);

    Member toMember(MemberUpdateDto memberUpdateDto);

    MemberQueryDto toMemberQueryDto(Member member);
}
