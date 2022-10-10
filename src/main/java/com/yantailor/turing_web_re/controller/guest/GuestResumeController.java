package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.dto.ResumeEntrepreneurshipDto;
import com.yantailor.turing_web_re.entity.dto.ResumeInnovateDto;
import com.yantailor.turing_web_re.service.ResumeEntrepreneurshipService;
import com.yantailor.turing_web_re.service.ResumeInnovateService;
import com.yantailor.turing_web_re.utils.FileDownloadUtil;
import com.yantailor.turing_web_re.utils.RedisUtils;
import com.yantailor.turing_web_re.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Optimistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by yantailor
 * on 2022/3/19 9:44 @Version 1.0
 */
@RestController
@RequestMapping("guest/resume")
@Api(tags = "简历填写")
public class GuestResumeController {

    @Autowired
    ResumeEntrepreneurshipService resumeEntrepreneurshipService;

    @Autowired
    ResumeInnovateService resumeInnovateService;
    @Value("${turingweb.resumeStoreFile}")
    private String resumeStoreFile;

    @Value("${turingweb.desPath}")
    private String desPath;
    @Autowired
    RedisUtils redisUtils;

    @PostMapping("resumeInnovateAdd")
    @ApiOperation("创新组内容填写")
    public R resumeInnovateAdd(@Valid ResumeInnovateDto resumeInnovateDto , BindingResult bindingResult){
        if(!redisUtils.hasKey("resumeKey")){
            return R.error().message("简历尚未到开放时间");
        }
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck!=null){
            return resultCheck;
        }
        resumeInnovateService.addOrUpdateResume(resumeInnovateDto);
        return R.ok();
    }

    @PostMapping("resumeEntrepreneurshipAdd")
    @ApiOperation("创业组内容填写")
    public R resumeInnovateAdd(@Valid ResumeEntrepreneurshipDto resumeEntrepreneurshipDto , BindingResult bindingResult){
        if(!redisUtils.hasKey("resumeKey")){
            return R.error().message("简历尚未到开放时间");
        }
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck!=null){
            return resultCheck;
        }
        resumeEntrepreneurshipService.addOrUpdateResume(resumeEntrepreneurshipDto);
        return R.ok();
    }

    @GetMapping("ResumeQueryById")
    @ApiOperation("根据学号查询简历内容")
    public R ResumeQueryById(@RequestParam String StudentId){
        ResumeInnovateDto resumeInnovateDto = resumeInnovateService.queryResume(StudentId);
        if(resumeInnovateDto == null){
            ResumeEntrepreneurshipDto resumeEntrepreneurshipDto = resumeEntrepreneurshipService.queryResume(StudentId);
            if(resumeEntrepreneurshipDto == null){
                return R.error().message("学号->"+StudentId+" 尚未录入");
            }
            return R.ok().data("resume",resumeEntrepreneurshipDto);
        }
        return R.ok().data("resume", resumeInnovateDto);
    }
    @ApiOperation("面试简历模板下载地址")
    @GetMapping("getMobanUrl")
    public R getMobanUrl(){
        return new R().data("创业组模板", desPath+"/"+resumeStoreFile+"/chuangye.docx")
                .data("创新组模板", desPath+"/"+resumeStoreFile+"/chuangxin.docx");
    }



}
