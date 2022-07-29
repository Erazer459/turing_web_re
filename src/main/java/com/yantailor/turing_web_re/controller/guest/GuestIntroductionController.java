package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.Introduction;
import com.yantailor.turing_web_re.service.IntroductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/15 21:57 @Version 1.0
 */
@RestController
@Api(tags = "游客团队简介操作")
@RequestMapping("guest/introduction")
public class GuestIntroductionController {

    @Autowired
    IntroductionService introductionService;

    @GetMapping("introductionQueryAll")
    @ApiOperation("团队简介查询全部")
    public R introductionQuery(){
        List<Introduction> introductions = introductionService.queryAllIntroduction();
        return R.ok().data("introductions", introductions);
    }

    @GetMapping("introductionQueryLastingOne")
    @ApiOperation("最新的团队简介")
    public R introductionQueryLastingOne(){
        Introduction introduction = introductionService.queryLastingOneIntroduction();
        return R.ok().data("introduction", introduction);
    }



}
