package com.yantailor.turing_web_re.controller.guest;

import com.yantailor.turing_web_re.bean.R;
import com.yantailor.turing_web_re.entity.LeaderInspectionIncident;
import com.yantailor.turing_web_re.service.LeaderInspectionIncidentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/20 10:42 @Version 1.0
 */
@RestController
@Api(tags = "游客领导视察操作")
@RequestMapping("guest/leaderInspectionIncident")
public class GuestLeaderInspectionIncidentController {
    @Autowired
    LeaderInspectionIncidentService leaderInspectionIncidentService;

    @ApiOperation("领导视察全查询")
    @GetMapping("LeaderInspectionIncidentQueryAll")
    public R LeaderInspectionIncidentQueryAll(){
        List<LeaderInspectionIncident> leaderInspectionIncidents = leaderInspectionIncidentService.queryAllLeaderInspectionIncident();
        return R.ok().data("leaderInspectionIncidents",leaderInspectionIncidents);
    }

    @ApiOperation("领导视察ID查询")
    @GetMapping("LeaderInspectionIncidentQuery")
    public R LeaderInspectionIncidentQuery(@RequestParam Integer incidentId){
        LeaderInspectionIncident leaderInspectionIncident = leaderInspectionIncidentService.queryLeaderInspectionIncident(incidentId);
        return R.ok().data("leaderInspectionIncident",leaderInspectionIncident);
    }

}
