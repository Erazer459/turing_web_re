package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.Member;
import com.yantailor.turing_web_re.entity.dto.LiveDto;
import com.yantailor.turing_web_re.entity.dto.MemberDto;
import com.yantailor.turing_web_re.entity.dto.MemberQueryDto;
import com.yantailor.turing_web_re.service.MemberService;
import com.yantailor.turing_web_re.utils.MemberDirectionValid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/14 18:09 @Version 1.0
 */
@Api(tags = "游客团队成员操作")
@RestController
@RequestMapping("guest/member")
@Validated
public class GuestMemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("queryMember")
    @ApiOperation("查询团队成员信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page",value = "查询页数，可不填,做全查询"),
                    @ApiImplicitParam(name = "offset",value = "查询奖项偏移量,可不填，做全查询")
            }
    )
    public R queryMember(@RequestParam(required = false) Integer page , @RequestParam(required = false) Integer offset){
        if(page == null || offset == null){
            List<MemberQueryDto> dtoList = memberService.queryMember();
            return R.ok().data("Members", dtoList);
        }
        return  R.ok().data("Members", memberService.queryMember(page, offset));
    }

//    @GetMapping("queryMemberByDirection")
//    @ApiOperation("根据方向查询")
//    public R queryMemberByDirection(){
//        List<List<MemberQueryDto>> lists = memberService.queryMemberByDirection();
//        return R.ok().data("Members", lists);
//    }
    @GetMapping("queryMemberByDirection")
    @ApiOperation("查询团队成员信息根据方向")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page",value = "查询页数，可不填,做全查询"),
                    @ApiImplicitParam(name = "offset",value = "查询奖项偏移量,可不填，做全查询"),
                    @ApiImplicitParam(name = "direction", value = "技术方向",required = true, paramType = "query", dataType = "string"),
            }
    )
    public R queryMemberByDirection(@RequestParam(required = false) Integer page , @RequestParam(required = false) Integer offset,@MemberDirectionValid @RequestParam(name = "direction")String memberDirection ){
        if(page == null || offset == null){
            return R.ok().data("Members", memberService.queryMemberByDirection(memberDirection));
        }
        return R.ok().data("Members", memberService.queryMemberByDirection(page, offset, memberDirection));
    }

//    @GetMapping("ts")
//    @ApiOperation("ts")
//    public R test (@NotNull@RequestParam(required = false)String value){
//        return R.ok();
//    }

}
