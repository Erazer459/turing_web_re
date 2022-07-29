package com.yantailor.turing_web_re.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yantailor.turing_web_re.entity.LivePhoto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by yantailor
 * on 2022/2/21 20:56 @Version 1.0
 */
@Mapper
@Repository
public interface LivePhotoDao extends BaseMapper<LivePhoto> {
}
