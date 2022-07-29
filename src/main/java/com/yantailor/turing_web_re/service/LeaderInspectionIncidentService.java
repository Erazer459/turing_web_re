package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.LeaderInspectionIncident;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/15 23:51 @Version 1.0
 */
public interface LeaderInspectionIncidentService extends IService<LeaderInspectionIncident> {

    //添加领导视察事件
    void addLeaderInspectionIncident(LeaderInspectionIncident leaderInspectionIncident , MultipartFile multipartFile);

    //更新
    boolean updateLeaderInspectionIncident(Integer incidentId , LeaderInspectionIncident leaderInspectionIncident , MultipartFile multipartFile);

    //删除
    boolean delLeaderInspectionIncident(Integer incidentId);

    //查询全部事件
    List<LeaderInspectionIncident> queryAllLeaderInspectionIncident();

    //根据Id查事件
    LeaderInspectionIncident queryLeaderInspectionIncident(Integer incidentId);
}
