package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.dao.LeaderInspectionIncidentDao;
import com.yantailor.turing_web_re.entity.LeaderInspectionIncident;
import com.yantailor.turing_web_re.service.LeaderInspectionIncidentService;
import com.yantailor.turing_web_re.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/2/15 23:51 @Version 1.0
 */
@Service
@Transactional
public class LeaderInspectionIncidentServiceImpl extends ServiceImpl<LeaderInspectionIncidentDao , LeaderInspectionIncident>
    implements LeaderInspectionIncidentService
    {

        @Value("${turingweb.desPath}")
        private String desPath;
        @Value("${turingweb.leaderInspectionPhotoStoreFile}")
        private String leaderInspectionPhotoFile;
        @Autowired
        TransferUtil transferUtil;

        @Override
        public void addLeaderInspectionIncident(LeaderInspectionIncident leaderInspectionIncident, MultipartFile multipartFile) {
            if(multipartFile != null){
                transferUtil.TransferFile(multipartFile, leaderInspectionPhotoFile);
                leaderInspectionIncident.setIncidentPhotoUrl(desPath+"/"+leaderInspectionPhotoFile+"/"+multipartFile.getOriginalFilename());
            }
            baseMapper.insert(leaderInspectionIncident);
        }

        @Override
        public boolean updateLeaderInspectionIncident(Integer incidentId , LeaderInspectionIncident leaderInspectionIncident, MultipartFile multipartFile) {
            boolean isExists = baseMapper.exists(new QueryWrapper<LeaderInspectionIncident>().eq("incident_id", incidentId));
            if(!isExists){
                return false;
            }
            if(multipartFile != null){
                leaderInspectionIncident.setIncidentPhotoUrl(desPath+"/"+leaderInspectionPhotoFile+"/"+multipartFile.getOriginalFilename());
                transferUtil.RemoveFile(baseMapper.selectOne(new QueryWrapper<LeaderInspectionIncident>().eq("incident_id", incidentId)).getIncidentPhotoUrl());
                transferUtil.TransferFile(multipartFile, leaderInspectionPhotoFile);
            }
            leaderInspectionIncident.setIncidentId(incidentId);
            baseMapper.updateById(leaderInspectionIncident);
            return true;
        }

        @Override
        public boolean delLeaderInspectionIncident(Integer incidentId) {
            boolean isExists = baseMapper.exists(new QueryWrapper<LeaderInspectionIncident>().eq("incident_id", incidentId));
            if(!isExists){
                return false;
            }
            String url = baseMapper.selectOne(new QueryWrapper<LeaderInspectionIncident>().eq("incident_id", incidentId)).getIncidentPhotoUrl();
            if(!url.equals("no exist")) {
                transferUtil.RemoveFile(url);
            }
            baseMapper.deleteById(incidentId);
            return true;
        }

        @Override
        public List<LeaderInspectionIncident> queryAllLeaderInspectionIncident() {
            List<LeaderInspectionIncident> leaderInspectionIncidents = baseMapper.selectList(null);
            return leaderInspectionIncidents;
        }

        @Override
        public LeaderInspectionIncident queryLeaderInspectionIncident(Integer incidentId) {
            LeaderInspectionIncident leaderInspectionIncident = baseMapper.selectOne(new QueryWrapper<LeaderInspectionIncident>().eq("incident_id", incidentId));
            return leaderInspectionIncident;
        }
    }
