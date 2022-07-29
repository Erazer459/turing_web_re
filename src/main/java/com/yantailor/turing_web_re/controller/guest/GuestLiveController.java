package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.dto.LiveDto;
import com.yantailor.turing_web_re.service.LiveService;
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
 * on 2022/3/10 21:36 @Version 1.0
 */
@RestController
@Api(tags = "游客团队活动内容操作")
@RequestMapping("guest/live")
public class GuestLiveController {


    @Autowired
    LiveService liveService;

    @ApiOperation("团队活动内容查询")
    @GetMapping("liveQuery")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page",value = "查询页数，可不填,做全查询"),
                    @ApiImplicitParam(name = "offset",value = "查询奖项偏移量,可不填，做全查询")
            }
    )
    public R liveQuery(@RequestParam(required = false) Integer page , @RequestParam(required = false) Integer offset){
        if(page == null || offset == null){
            List<LiveDto> dtoList = liveService.queryLive();
            return R.ok().data("lives", dtoList);
        }
        List<LiveDto> dtoList = liveService.queryLive(page, offset);
        return R.ok().data("lives", dtoList);

    }

    @ApiOperation("团队活动内容根据ID获取")
    @GetMapping("liveQueryById")
    public R liveQueryById(@RequestParam Integer liveId){
        LiveDto dto = liveService.queryLiveById(liveId);
        return R.ok().data("live", dto);
    }

}
