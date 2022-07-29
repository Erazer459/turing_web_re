package com.yantailor.turing_web_re.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yantailor.turing_web_re.entity.AwardPhoto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by yantailor
 * on 2021/9/27 10:25 @Version 1.0
 */
@Mapper
@Component
public interface AwardPhotoDao extends BaseMapper<AwardPhoto> {
}
