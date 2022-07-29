package com.yantailor.turing_web_re.config;

import com.yantailor.turing_web_re.bean.ErrorMap;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * Created by yantailor
 * on 2022/2/10 20:48 @Version 1.0
 */
@Component
@ControllerAdvice
public class ExceptionHander {

//    #单文件上传最大容量
//    spring.servlet.multipart.max-file-size=2MB
//#总上传文件小
//    spring.servlet.multipart.max-request-size=5MB

    @Value("${spring.servlet.multipart.max-file-size}")
    String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size}")
    String maxRequestSize;

    @ExceptionHandler(ErrorMap.class)
    public ResponseEntity errorCodeHandler(ErrorMap errorMap){
//        R status = R.errorCode(errorMap.getCode(),errorMap.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(errorMap.getMessage());
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity fileTransferError(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body("文件内容过大，单文件不能超过"+maxFileSize+"单次总大小不能超过"+maxRequestSize);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity memberDirection(Exception e){
        e.printStackTrace();
        if(e instanceof ConstraintViolationException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("必须为指定值,前端，后台，自然语言处理，计算机视觉");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("验证出了问题");
    }
}
