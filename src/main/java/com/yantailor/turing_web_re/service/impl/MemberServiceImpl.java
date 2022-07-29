package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.bean.ErrorMap;
import com.yantailor.turing_web_re.dao.AwardDao;
import com.yantailor.turing_web_re.dao.AwardPhotoDao;
import com.yantailor.turing_web_re.dao.MemberDao;
import com.yantailor.turing_web_re.dao.transfer.toMember;
import com.yantailor.turing_web_re.entity.Award;
import com.yantailor.turing_web_re.entity.Member;
import com.yantailor.turing_web_re.entity.dto.MemberDto;
import com.yantailor.turing_web_re.entity.dto.MemberQueryDto;
import com.yantailor.turing_web_re.entity.dto.MemberUpdateDto;
import com.yantailor.turing_web_re.service.AwardService;
import com.yantailor.turing_web_re.service.MemberService;
import com.yantailor.turing_web_re.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/11 10:34 @Version 1.0
 */
@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<MemberDao , Member> implements MemberService {
    @Autowired
    MemberInitUtil memberInitUtil;

    @Value("${turingweb.desPath}")
    private String desPath;
    @Value("${turingweb.memberIconsFile}")
    private String memberIconsFile;

    @Autowired
    TransferUtil transferUtil;

    @Autowired
    MemberDao memberDao;

    @Autowired
    AwardDao awardDao;



    @Override
    public void addMember(MemberDto memberDto) {
        Member member = toMember.INSTANCE.toMember(memberDto);
        Member memberInit = memberInitUtil.memberInit(member);
        try {
            baseMapper.insert(memberInit);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorMap(ErrorCode.MEMBER_EXIST);

        }
    }

    @Override
    public boolean updateMember(MemberUpdateDto memberUpdateDto ,  MultipartFile memberIcon) {
        if(!baseMapper.exists(new QueryWrapper<Member>().eq("member_student_id", memberUpdateDto.getMemberStudentId()))){
            return false;
        }

        if(memberIcon != null){
            memberUpdateDto.setMemberIconUrl(desPath+"/"+memberIconsFile+"/"+memberIcon.getOriginalFilename());
            transferUtil.TransferFile(memberIcon, memberIconsFile);
        }

        Member member = toMember.INSTANCE.toMember(memberUpdateDto);
        member.setMemberRoleId(1);
        baseMapper.updateById(member);
        return true;
    }

    @Override
    public boolean delMember(String memberStudentId) {
        if(!baseMapper.exists(new QueryWrapper<Member>().eq("member_student_id", memberStudentId))){
            return false;
        }
        baseMapper.deleteById(memberStudentId);
        return true;
    }

    @Override
    public List<MemberQueryDto> queryMember() {
        ArrayList<MemberQueryDto> dtoList = new ArrayList<>();
        List<Member> members = baseMapper.selectList(null);
        Iterator<Member> iterator = members.iterator();
        while(iterator.hasNext()){
            MemberQueryDto memberQueryDto = toMember.INSTANCE.toMemberQueryDto(iterator.next());
            List<Integer> integers = memberDao.awardMemberTableRecordQuery(memberQueryDto.getMemberStudentId());
            Iterator<Integer> iterator1 = integers.iterator();
            ArrayList<Award> awardsList = new ArrayList<>();
            while (iterator1.hasNext()){
                Award award = awardDao.selectOne(new QueryWrapper<Award>().eq("award_id", iterator1.next()));
                awardsList.add(award);
            }
            memberQueryDto.setAwards(awardsList);
            dtoList.add(memberQueryDto);
        }
        return dtoList;
    }

    @Override
    public List<MemberQueryDto> queryMember(Integer page, Integer offset) {
        List<MemberQueryDto> dtoList = queryMember();
        return PageUtil.PageHandler(page, offset, dtoList);
    }

    @Override
    public List<MemberQueryDto> queryMemberByDirection(Integer page, Integer offset, String memberDirection) {
        List<MemberQueryDto> dtoList = queryMemberByDirection(memberDirection);
//        int fromIndex = page*offset;
//        int toIndex = page*offset + offset;
//        if(fromIndex > dtoList.size()){
//            return dtoList;
//        }
//
//        if(toIndex > dtoList.size()){
//            return dtoList.subList(fromIndex, dtoList.size());
//        }
//        return dtoList.subList(fromIndex,toIndex);
        return PageUtil.PageHandler(page, offset, dtoList);
    }

    @Override
    public List<MemberQueryDto> queryMemberByDirection(String memberDirection) {
        ArrayList<MemberQueryDto> dtoList = new ArrayList<>();
        List<Member> memberList = baseMapper.selectList(new QueryWrapper<Member>().eq("member_direction", memberDirection));
        Iterator<Member> iterator = memberList.iterator();
        while (iterator.hasNext()){
            MemberQueryDto memberQueryDto = toMember.INSTANCE.toMemberQueryDto(iterator.next());
            List<Integer> integers = memberDao.awardMemberTableRecordQuery(memberQueryDto.getMemberStudentId());
            Iterator<Integer> iterator1 = integers.iterator();
            ArrayList<Award> awardsList = new ArrayList<>();
            while (iterator1.hasNext()){
                Award award = awardDao.selectOne(new QueryWrapper<Award>().eq("award_id", iterator1.next()));
                awardsList.add(award);
            }
            memberQueryDto.setAwards(awardsList);
            dtoList.add(memberQueryDto);
        }
        return dtoList;
    }


    @Override
    public MemberDto queryMemberByMemberStudentId(String memberStudentId) {
        Member member = baseMapper.selectOne(new QueryWrapper<Member>().eq("member_student_id", memberStudentId));
        MemberDto memberDto = toMember.INSTANCE.toMemberDto(member);
        return memberDto;
    }
}
