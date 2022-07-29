package com.yantailor.turing_web_re.controller.admin;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.dao.transfer.toInform;
import com.yantailor.turing_web_re.entity.Inform;
import com.yantailor.turing_web_re.entity.dto.InformDto;
import com.yantailor.turing_web_re.service.InformService;
import com.yantailor.turing_web_re.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yantailor
 * on 2022/2/13 22:14 @Version 1.0
 */
@RestController
@RequestMapping("/admin/inform")
@Api(tags = "通知操作")
public class InformController {

    @Autowired
    InformService informService;

    @PostMapping("add")
    @ApiOperation("添加通知")
    public R informAdd(@Valid InformDto informDto , BindingResult bindingResult){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck != null){
            return resultCheck;
        }
        informService.addInform(informDto);
        return R.ok();
    }

    @PutMapping("update")
    @ApiOperation("更新通知")
    public R informUpdate(@RequestParam Integer informId , @Valid InformDto informDto , BindingResult bindingResult){
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck != null){
            return resultCheck;
        }
        informService.updateInform(informId, informDto);
        return R.ok();
    }

    @DeleteMapping("del")
    @ApiOperation("删除通知")
    public R informDel(@RequestParam Integer informId){
        informService.delInform(informId);
        return R.ok();
    }


}
