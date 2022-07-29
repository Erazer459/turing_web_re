package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.dao.ResumeEntrepreneurshipDao;
import com.yantailor.turing_web_re.entity.dto.ResumeEntrepreneurshipDto;
import com.yantailor.turing_web_re.service.ResumeEntrepreneurshipService;
import com.yantailor.turing_web_re.utils.ExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yantailor
 * on 2022/3/19 9:56 @Version 1.0
 */
@Service
@Transactional
public class ResumeEntrepreneurshipServiceImpl extends ServiceImpl<ResumeEntrepreneurshipDao , ResumeEntrepreneurshipDto>  implements ResumeEntrepreneurshipService {

    @Override
    public void addOrUpdateResume(ResumeEntrepreneurshipDto resumeEntrepreneurshipDto) {
        this.saveOrUpdate(resumeEntrepreneurshipDto, new UpdateWrapper<ResumeEntrepreneurshipDto>().eq("resume_student_id", resumeEntrepreneurshipDto.getResumeStudentId()));
    }

    @Override
    public void outPutExcel(String path) {
        String[] header = {"姓名", "学号", "联系电话", "专业班级", "方向", "自我评价", "技能水平", "期望", "项目经验", "其他"};
        ExcelUtil.createEntrepreneurshipExcel(path,header,"简历详情",baseMapper.selectList(null));
    }

    @Override
    public ResumeEntrepreneurshipDto queryResume(String studentId) {
        ResumeEntrepreneurshipDto resumeEntrepreneurshipDto = baseMapper.selectOne(new QueryWrapper<ResumeEntrepreneurshipDto>().eq("resume_student_id", studentId));
        return resumeEntrepreneurshipDto;
    }
}
