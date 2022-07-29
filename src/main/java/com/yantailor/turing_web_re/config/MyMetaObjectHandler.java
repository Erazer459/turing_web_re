package com.yantailor.turing_web_re.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Created by yantailor
 * on 2022/2/4 19:48 @Version 1.0
 *
 * mbp 时间自动生成bean
 */

@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {



    @Override
    public void insertFill(MetaObject metaObject) {
        //通知编辑时间
        this.setFieldValByName("informEditTime",new Date(),metaObject);
        //团队介绍编辑时间
        this.setFieldValByName("introductionEditTime", new Date(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("informEditTime", new Date(),metaObject);
        this.setFieldValByName("introductionEditTime", new Date(),metaObject);
    }
}
