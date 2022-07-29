package com.yantailor.turing_web_re.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.turing_web_re.entity.dto.ResumeEntrepreneurshipDto;
import com.yantailor.turing_web_re.entity.dto.ResumeInnovateDto;

/**
 * Created by yantailor
 * on 2022/3/19 9:50 @Version 1.0
 */
public interface ResumeEntrepreneurshipService extends IService<ResumeEntrepreneurshipDto> {
    //添加简历信息
    void addOrUpdateResume(ResumeEntrepreneurshipDto resumeEntrepreneurshipDto);

    //输出excel简历信息
    void outPutExcel(String path);

    //根据学号查简历
    ResumeEntrepreneurshipDto queryResume(String studentId);
}
