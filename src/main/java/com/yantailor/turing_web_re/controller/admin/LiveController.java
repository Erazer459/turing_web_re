package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.Live;
import com.yantailor.turing_web_re.service.LiveService;
import com.yantailor.turing_web_re.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Created by yantailor
 * on 2022/2/21 18:14 @Version 1.0
 */
@Api(tags = "团队活动内容操作")
@RestController
@RequestMapping("/admin/live")
public class LiveController {

    @Autowired
    LiveService liveService;

    @PostMapping("add")
    @ApiOperation("团队活动内容添加")
//    @ApiImplicitParam(name = "livePhotos" , value = "团队活动的照片",dataType = "__file[]")
    public R liveAdd(@Valid Live live , BindingResult bindingResult, @RequestPart(name = "livePhotos" ,required = false)MultipartFile[] livePhotos){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck != null){
            return resultCheck;
        }
        liveService.addLive(live, livePhotos);
        return R.ok();
    }

    @PutMapping("update")
    @ApiOperation("团队活动内容更新")
    public R liveUpdate(@RequestParam Integer liveId , @Valid Live live , BindingResult bindingResult, @RequestPart(name = "livePhotos" ,required = false)MultipartFile[] livePhotos){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck != null){
            return resultCheck;
        }
        boolean isExists = liveService.updateLive(liveId, live, livePhotos);
        if(!isExists){
            return R.error().message("此liveId不存在");
        }
        return R.ok();
    }

    @DeleteMapping("del")
    @ApiOperation("团队活动内容删除")
    public R liveDel(@RequestParam Integer liveId){
        boolean isExist = liveService.delLive(liveId);
        if(!isExist){
           return R.error().message("不存在此liveId");
        }
        return R.ok();
    }

}
