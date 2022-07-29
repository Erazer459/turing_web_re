package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.Introduction;
import com.yantailor.turing_web_re.service.IntroductionService;
import com.yantailor.turing_web_re.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.Validation;

/**
 * Created by yantailor
 * on 2022/2/15 15:15 @Version 1.0
 */
@RestController
@Api(tags = "团队简介操作")
@RequestMapping("/admin/introduction")
public class IntroductionController {

    @Autowired
    IntroductionService introductionService;

    @ApiImplicitParam(name = "introductionVideo" , value = "团队介绍的视频" , dataType = "__file")
    @PostMapping("add")
    public R introdutionAdd(@Valid Introduction introduction ,
                            BindingResult bindingResult,
                            @RequestPart(required = false , value = "introductionVideo")MultipartFile introductionVideo){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck != null){
            return resultCheck;
        }

        boolean b = introductionService.addIntroduction(introduction, introductionVideo);
        if(!b){
            return R.error().message("此年份信息已存在，请执行更新操作");
        }
        return R.ok();

    }

    @ApiImplicitParam(name = "introductionVideo" , value = "团队介绍的视频" , dataType = "__file")
    @PostMapping("update")
    public R introdutionUpdate(@Valid Introduction introduction ,
                            BindingResult bindingResult,
                            @RequestPart(required = false , value = "introductionVideo")MultipartFile introductionVideo){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck != null){
            return resultCheck;
        }

        boolean b = introductionService.updateIntroduction(introduction, introductionVideo);
        if(!b){
            return R.error().message("此年份信息不存在，请执行插入操作或者找对年份");
        }
        return R.ok();

    }


}
