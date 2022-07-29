package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.dto.AwardDto;
import com.yantailor.turing_web_re.service.AwardService;
import com.yantailor.turing_web_re.utils.TransferUtil;

import com.yantailor.turing_web_re.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by yantailor
 * on 2022/2/9 19:49 @Version 1.0
 */
@RestController
@RequestMapping("/admin/award")
@Api(tags = "奖项操作")
public class AwardController {

    public static final Logger logger = LoggerFactory.getLogger(TransferUtil.class);

    @Autowired
    AwardService awardService;


    @PostMapping("add")
    @ApiOperation("添加奖项")
    public R awardAdd (
            @Valid AwardDto awardDto,
            BindingResult bindingResult,
            @RequestPart(name = "awardPhotos" ,required = false) MultipartFile[] awardPhotos
            ){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck !=null){
            return resultCheck;
        }

//        if(bindingResult.hasErrors()){
//            R r = R.error();
//            int errorCounter = 1;
//            for(ObjectError error : bindingResult.getAllErrors()){
//                r.data("error"+errorCounter, error.getDefaultMessage());
//                errorCounter++;
//            }
//            return r;
//        }

        awardService.addAward(awardDto, awardPhotos);

        logger.info("奖项上传成功++++,"+awardDto.getAwardName()+",time:"+new Date());
        return R.ok();
    }

    @PutMapping("update")
    @ApiOperation("奖项更新")
    public R awardAdd( @RequestParam(name = "awardId") int awardId ,
                       @Valid AwardDto awardDto,
                       BindingResult bindingResult,
                       @RequestPart(name = "awardPhotos" ,required = false) MultipartFile[] awardPhotos){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck !=null){
            return resultCheck;
        }
        awardService.updateAward(awardId , awardDto, awardPhotos);

        logger.info("奖项更新成功++++,"+awardDto.getAwardName()+",time:"+new Date());
        return R.ok();
    }

    @DeleteMapping("del")
    @ApiOperation("奖项删除")
    public R awardDel(int awardId){
        awardService.delAward(awardId);
        logger.info("奖项删除成功++++,awardId:"+awardId+",time:"+new Date());
        return R.ok();
    }


}
