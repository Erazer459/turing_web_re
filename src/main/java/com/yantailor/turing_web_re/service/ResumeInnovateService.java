package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.dto.ResumeEntrepreneurshipDto;
import com.yantailor.turing_web_re.entity.dto.ResumeInnovateDto;

/**
 * Created by yantailor
 * on 2022/3/19 10:11 @Version 1.0
 */
public interface ResumeInnovateService extends IService<ResumeInnovateDto> {
    //添加简历信息
    void addOrUpdateResume(ResumeInnovateDto resumeInnovateDto);

    //输出excel简历信息
    void outPutExcel(String path);

    //根据学号查简历
    ResumeInnovateDto queryResume(String studentId);
}
