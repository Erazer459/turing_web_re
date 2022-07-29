package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.service.ResumeEntrepreneurshipService;
import com.yantailor.turing_web_re.service.ResumeInnovateService;
import com.yantailor.turing_web_re.utils.ExcelUtil;
import com.yantailor.turing_web_re.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yantailor
 * on 2022/3/19 10:53 @Version 1.0
 */
@Api(tags = "简历操作")
@RestController
@RequestMapping("/admin/resume")
public class ResumeController {
    @Autowired
    ResumeInnovateService resumeInnovateService;
    @Autowired
    ResumeEntrepreneurshipService resumeEntrepreneurshipService;

    @Value("${turingweb.resumeStoreFile}")
    private String resumeStoreFile;

    @Value("${turingweb.desPath}")
    private String desPath;

    @Autowired
    RedisUtils redisUtils;

    private String valueDescription = null;

    private ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

    public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @PostMapping("outPutInnovateExcel")
    @ApiOperation("创新组简历导出")
    public R outPutInnovateExcel(){
        resumeInnovateService.outPutExcel(desPath+"/"+resumeStoreFile+"/resumeInnovate.xls");
        resumeEntrepreneurshipService.outPutExcel(desPath+"/"+resumeStoreFile+"/resumeEntrepreneurship.xls");
        return R.ok().data("创新组Excel生成地址",desPath+"/"+resumeStoreFile+"/resumeInnovate.xls")
                     .data("创业组Excel生成地址", desPath+"/"+resumeStoreFile+"/resumeEntrepreneurship.xls");
    }

    @ApiOperation("面试简历模板下载地址")
    @GetMapping("/getMobanUrl")
    public R getMobanUrl(){
        return new R().data("创业组模板", desPath+"/"+resumeStoreFile+"/chuangye.docx")
                      .data("创新组模板", desPath+"/"+resumeStoreFile+"chuangxin.docx");
    }

    @ApiOperation("设置简历开放时间")
    @PostMapping("setResumeOpenTime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime" , value = "开放时间 yyyy-MM-dd HH:mm(格式)" , dataType = "String"),
            @ApiImplicitParam(name = "endTime" , value = "关闭时间 yyyy-MM-dd HH:mm(格式)" , dataType = "String")
    })
    public R setResumeOpenTime(String startTime , String endTime) throws Exception{
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            if(startDate.getTime() < new Date().getTime() || endDate.getTime()< new Date().getTime()){
                return R.error().message("不能设置已过的时间");
            }
            if(endDate.getTime() < startDate.getTime()){
                return R.error().message("起始时间不能大于结束时间");
            }
            valueDescription = sdf.format(startDate)+"-->"+sdf.format(endDate);
            Long delay = (endDate.getTime() - startDate.getTime())/1000;

            //开始定时任务，到时间开启
            taskScheduler.setPoolSize(1);
            taskScheduler.initialize();
            taskScheduler.schedule(() -> {
                redisUtils.set("resumeKey", valueDescription, delay);
            }, startDate);



            return R.ok().message("设置时间成功").data("开放时间", valueDescription);

    }

//    @ApiOperation("设置简历功能开")
//    @PostMapping("ResumeOn")

    @ApiOperation("设置简历功能关")
    @PostMapping("resumeOpenOff")
    public R setResumeOpenTime(){
        redisUtils.del("resumeKey");
        return R.ok().message("设置简历功能关");
    }

    @ApiOperation("查看简历填写开放状态")
    @GetMapping("getResumeState")
    public R getResumeState(){
        if(redisUtils.hasKey("resumeKey")){
            //已开启，已生效
            return R.ok().message("简历填写已开启,开放时间:"+valueDescription);
        }

        //未开启
        return R.ok().message("简历填写暂未开启,定时任务或许已启动");
    }

}
