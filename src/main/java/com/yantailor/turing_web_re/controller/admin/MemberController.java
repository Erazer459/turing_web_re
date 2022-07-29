package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.dto.MemberDto;
import com.yantailor.turing_web_re.entity.dto.MemberUpdateDto;
import com.yantailor.turing_web_re.service.MemberService;
import com.yantailor.turing_web_re.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Created by yantailor
 * on 2022/3/11 11:16 @Version 1.0
 */
@Api(tags = "团队成员操作")
@RestController
@RequestMapping("/admin/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("add")
    @ApiOperation("团队成员添加")
    public R memberAdd(@Valid MemberDto memberDto , BindingResult bindingResult){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck!=null){
            return resultCheck;
        }
        memberService.addMember(memberDto);
        return R.ok();
    }

    @PutMapping("update")
    @ApiOperation("团队成员更新")
    public R memberUpdate(@Valid MemberUpdateDto memberUpdateDto , BindingResult bindingResult, @RequestPart(name = "memberIcon",required = false) MultipartFile memberIcon){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck!=null){
            return resultCheck;
        }
        memberService.updateMember(memberUpdateDto,memberIcon);
        return R.ok();
    }

    @DeleteMapping("del")
    @ApiOperation("团队成员删除")
    public R memberDel(@RequestParam String memberStudentId){
        boolean isExist = memberService.delMember(memberStudentId);
        if(!isExist){
            return R.error().message("学号不存在,删除失败，请检查");
        }
        return R.ok();
    }

    @GetMapping("queryMemberByMemberStudentId")
    @ApiOperation("查询团队成员信息根据学号")
    public R queryMemberByMemberStudentId(@RequestParam String MemberStudentId){
        MemberDto memberDto = memberService.queryMemberByMemberStudentId(MemberStudentId);
        if(memberDto == null){
            return R.error().message("没有这个学号的同学");
        }
        return R.ok().data("Member",memberDto);
    }
}
