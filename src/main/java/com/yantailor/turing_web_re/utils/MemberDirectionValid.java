package com.yantailor.turing_web_re.utils;

/**
 * Created by yantailor
 * on 2022/3/16 16:41 @Version 1.0
 */

import com.yantailor.turing_web_re.config.MemberDirectionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MemberDirectionValidator.class})
public @interface MemberDirectionValid {
    // 默认错误消息
    String message() default "必须为指定值,前端，后台，自然语言处理，计算机视觉,UI";

    String[] strValues() default {"前端","后台","自然语言处理","计算机视觉","UI"};

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        MemberDirectionValid[] value();
    }
}
