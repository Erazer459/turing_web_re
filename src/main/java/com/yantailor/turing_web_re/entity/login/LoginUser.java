package com.yantailor.turing_web_re.entity.login;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by yantailor
 * on 2022/3/25 9:34 @Version 1.0
 */

public class LoginUser {

    @ApiModelProperty(value = "账号，注册邮箱")
    @NotNull(message = "账号不为空")
    private String memberEmail;

    @ApiModelProperty(value = "密码，默认为学号")
    @NotNull(message = "密码不为空")
    private String memberPassword;

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "memberEmail='" + memberEmail + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                '}';
    }
}
