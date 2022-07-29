package com.yantailor.turing_web_re.utils;

import com.yantailor.turing_web_re.config.MemberStudentIdIsExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yantailor
 * on 2022/3/16 23:59 @Version 1.0
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MemberStudentIdIsExistValidator.class)
public @interface MemberStudentIdIsExist {
    String message() default "有学号不存在数据库中，请检查";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@link NotNull} annotations on the same element.
     *
     * @see javax.validation.constraints.NotNull
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        MemberStudentIdIsExist[] value();
    }
}
