package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.dto.InformDto;
import com.yantailor.turing_web_re.service.InformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/14 11:37 @Version 1.0
 */
@RestController
@Api(tags = "游客通知操作")
@RequestMapping("guest/inform")
public class GuestInformController {

    @Autowired
    InformService informService;

    @GetMapping("informQuery")
    @ApiOperation("最近通知查询")
    public R informQuery(){
        List<InformDto> informDtos = informService.queryInform_recently();
        return R.ok().data("informs", informDtos);
    }

}
