package com.yantailor.turing_web_re.config;

import com.yantailor.turing_web_re.utils.MemberDirectionValid;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * Created by yantailor
 * on 2022/3/16 16:45 @Version 1.0
 */
@Component
public class MemberDirectionValidator implements ConstraintValidator<MemberDirectionValid,Object> {

    private String[] strValues;

    @Override
    public void initialize(MemberDirectionValid constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof String) {
            for (String s : strValues) {
                if (s.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
