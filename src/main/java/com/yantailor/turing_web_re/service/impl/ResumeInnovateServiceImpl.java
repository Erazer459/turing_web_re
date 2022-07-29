package com.yantailor.turing_web_re.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yantailor.turing_web_re.dao.ResumeInnovateDao;
import com.yantailor.turing_web_re.entity.dto.ResumeInnovateDto;
import com.yantailor.turing_web_re.service.ResumeInnovateService;
import com.yantailor.turing_web_re.utils.ExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yantailor
 * on 2022/3/19 10:13 @Version 1.0
 */
@Service
@Transactional
public class ResumeInnovateServiceImpl extends ServiceImpl<ResumeInnovateDao , ResumeInnovateDto> implements ResumeInnovateService {
    @Override
    public void addOrUpdateResume(ResumeInnovateDto resumeInnovateDto) {
        this.saveOrUpdate(resumeInnovateDto, new UpdateWrapper<ResumeInnovateDto>().eq("resume_student_id", resumeInnovateDto.getResumeStudentId()));
    }

    @Override
    public void outPutExcel(String path) {
        String[] header = {"姓名", "学号", "联系电话", "专业班级", "方向", "自我评价", "技能水平", "期望", "项目经验", "其他"};
        ExcelUtil.creatResumeInnovateExcel(path,header,"简历详情",baseMapper.selectList(null));
    }

    @Override
    public ResumeInnovateDto queryResume(String studentId) {
        ResumeInnovateDto resumeInnovateDto = baseMapper.selectOne(new QueryWrapper<ResumeInnovateDto>().eq("resume_student_id", studentId));
        return resumeInnovateDto;
    }
}
