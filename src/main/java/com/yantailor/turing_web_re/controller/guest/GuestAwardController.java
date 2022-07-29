package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.dto.AwardDto;
import com.yantailor.turing_web_re.service.AwardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/11 16:20 @Version 1.0
 */
@RestController
@RequestMapping("guest/award")
@Api("奖项操作")
public class GuestAwardController {

    @Autowired
    AwardService awardService;

    @GetMapping("awardQuery")
    @ApiOperation("奖项查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page",value = "查询页数，可不填,做全查询"),
                    @ApiImplicitParam(name = "offset",value = "查询奖项偏移量,可不填，做全查询")
            }
    )
    public R awardQuery(@RequestParam(required = false) Integer page ,@RequestParam(required = false) Integer offset){
        List<AwardDto> awardDto;
        if(page == null || offset == null ) {
             awardDto = awardService.queryAward();
            return R.ok().data("awards", awardDto);
        }
        awardDto = awardService.queryAward(page, offset);
        return R.ok().data("awards", awardDto);
    }

    @GetMapping("awardCount")
    @ApiOperation("奖项总数")
    public R awardCount(){
        Long countAward = awardService.countAward();
        return R.ok().data("countAward", countAward);
    }
}
