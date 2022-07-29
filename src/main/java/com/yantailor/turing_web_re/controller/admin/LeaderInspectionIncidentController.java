package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.LeaderInspectionIncident;
import com.yantailor.turing_web_re.service.LeaderInspectionIncidentService;
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
 * on 2022/2/16 20:54 @Version 1.0
 */
@RestController
@RequestMapping("/admin/leaderInspectionIncident")
@Api(tags = "领导视察内容操作")
public class LeaderInspectionIncidentController {

    @Autowired
    LeaderInspectionIncidentService leaderInspectionIncidentService;

    @PostMapping("add")
    @ApiOperation("领导视察内容添加")
    @ApiImplicitParam(value = "领导视察照片" , name = "leaderInspectionIncidentPhoto" ,dataType = "__file")
    public R LeaderInspectionIncidentAdd(@Valid LeaderInspectionIncident leaderInspectionIncident , BindingResult bindingResult, MultipartFile leaderInspectionIncidentPhoto){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck!=null){
            return resultCheck;
        }
        leaderInspectionIncidentService.addLeaderInspectionIncident(leaderInspectionIncident,leaderInspectionIncidentPhoto);
        return R.ok();
    }


    @PutMapping("update")
    @ApiOperation("领导视察内容更新")
    @ApiImplicitParam(value = "领导视察照片" , name = "leaderInspectionIncidentPhoto" ,dataType = "__file")
    public R LeaderInspectionIncidentUpdate(@RequestParam Integer incidentId , @Valid LeaderInspectionIncident leaderInspectionIncident , BindingResult bindingResult, MultipartFile leaderInspectionIncidentPhoto){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck!=null){
            return resultCheck;
        }
        boolean isExists = leaderInspectionIncidentService.updateLeaderInspectionIncident(incidentId, leaderInspectionIncident, leaderInspectionIncidentPhoto);
        if(!isExists){
            return R.error().message("不存在此incidentId");
        }
        return R.ok();
    }

    @DeleteMapping("del")
    @ApiOperation("领导视察内容删除")
    public R LeaderInspectionIncidentDel(@RequestParam Integer incidentId){
        boolean isExists = leaderInspectionIncidentService.delLeaderInspectionIncident(incidentId);
        if(!isExists){
            return R.error().message("不存在此incidentId");
        }
        return R.ok();
    }

}
