package com.yantailor.turing_web_re.bean;

import com.yantailor.turing_web_re.utils.ErrorCode;

/**
 * Created by yantailor
 * on 2022/2/10 20:44 @Version 1.0
 */
public class ErrorMap extends RuntimeException {
    private Integer code;
    private String message;

    public ErrorMap(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
