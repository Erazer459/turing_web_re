package com.yantailor.turing_web_re.utils;

import com.yantailor.turing_web_re.bean.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yantailor
 * on 2022/2/12 18:18 @Version 1.0
 */
public class ValidationUtil {
//    private static Validator validator;
//    static {
//        validator = Validation.buildDefaultValidatorFactory().getValidator();
//
//    }
//    public List<String> valid(Object o){
//        Set<ConstraintViolation<Object>> set = validator.validate(o);
//        List<String> list = set.stream().map(v -> "属性：" + v.getPropertyPath() + "属性的值"
//                + v.getInvalidValue() + ",校验不通过的消息:" + v.getMessage())
//                .collect(Collectors.toList());
//        return list;
//    }
//
    public static R bindingResultCheck(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            R r = R.error();
            int errorCounter = 1;
            for(ObjectError error : bindingResult.getAllErrors()){
                r.data("error"+errorCounter, error.getDefaultMessage());
                errorCounter++;
            }
            return r;
        }
        return null;
    }
}
