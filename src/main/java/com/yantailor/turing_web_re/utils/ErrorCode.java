package com.yantailor.turing_web_re.utils;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Created by yantailor
 * on 2021/11/25 16:57 @Version 1.0
 */
@NoArgsConstructor
public enum ErrorCode implements IErrorCode {
    EMAIL_NUMBER_EXIST(37,"邮箱号码已存在"),

    EMAIL_ACTIVATION_FAIL(38,"邮箱激活失败"),

    ACCOUNT_NOT_EXIST(39,"账号不存在，请注册账号"),

    MESSAGE_CAN_NOT_BE_NOT(40,"邮箱账号或不能为空"),

    PASSWORD_NOT_CORRECT(41,"密码不正确"),

    ACCOUNT_STATUS_EXCEPTION(42,"账号状态异常，已被注销或者封禁"),

    TOKEN_PAST_DUE(43,"token已过期"),

    DATA_ERROR(44,"accessToken或refreshToken不存在，登出出错"),

    TOKEN_ERROR(45,"accessToken已加入黑名单，token作废请重新登录获取新token"),

    MEMBER_NOT_EXIST(46,"学号不存在数据库，请查看学号是否正确"),

    INFORM_INSERT_ERROR(47,"通知添加出问题"),
    INFORM_UPDATE_ERROR(48,"通知更新出问题"),
    INFORM_DEL_ERROR(49,"通知删除出问题"),

    MEMBER_EXIST(50,"该学号已存在请检查"),

    USERNAME_NOT_EXIST(51,"用户名不存在"),

    TOKEN_ILLEGAL(52,"token非法"),

    TOKEN_INVALID(53,"此token已失效，请重新登录(可能是客户端异地登录)"),

    /**
     * 500:服务端异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器冒烟了...要不等它降降温后再来访问?");

    private int code;
    private String message;

    ErrorCode(int code , String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
