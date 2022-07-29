package com.yantailor.turing_web_re.bean;

import com.yantailor.turing_web_re.utils.ErrorCode;

import java.util.HashMap;

public class ResponseBean extends HashMap<String,Object> {

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public ResponseBean(){

    }

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseBean success(Object data){
        this.code = 200;
        this.msg = "success";
        this.data = data;
        return this;
    }

    public ResponseBean success(){
        this.setCode(200);
        this.setMsg("success");
        return this;
    }

    public ResponseBean fail(Object data){
        this.code = 400;
        this.msg = "fail";
        this.data = data;
        return this;
    }
    public ResponseBean fail(){
        this.setCode(400);
        this.setMsg("fail");
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseBean errorCode(ErrorCode errorCode){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(errorCode.getCode());
        responseBean.setMsg(errorCode.getMessage());
        return responseBean;
    }
}
