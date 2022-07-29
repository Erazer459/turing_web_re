package com.yantailor.turing_web_re.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yantailor.turing_web_re.entity.Award;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by yantailor
 * on 2022/2/4 17:42 @Version 1.0
 */
@Mapper
@Repository
public interface AwardDao extends BaseMapper<Award> {
}
