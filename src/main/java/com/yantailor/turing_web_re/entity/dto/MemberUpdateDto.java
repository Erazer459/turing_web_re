package com.yantailor.turing_web_re.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by yantailor
 * on 2022/3/11 16:24 @Version 1.0
 */

public class MemberUpdateDto {
    @ApiModelProperty(value = "学号" )
    @Length(max = 12 , min = 12 , message = "学号格式不正确，应为12位数")
    @NotNull(message = "学号不能为空")
    private String memberStudentId;

    @ApiModelProperty(value = "姓名" ,example = "胡图图")
    @Length(max = 5 , message = "哪来那么长的名字")
    @NotNull(message = "姓名不能为空")
    private String memberName;

    @ApiModelProperty(value = "专业" , example = "电子1194")
    @Length(max = 20)
    @NotNull(message = "专业不为空")
    private String memberMajor;

    @ApiModelProperty(value = "学院" , example = "电子与信息工程学院")
    @Length(max = 20)
    @NotNull(message = "学院不为空")
    private String memberDepartment;

    @ApiModelProperty(value = "自我介绍")
    @Length(max = 500)
    private String memberIntroduction = "暂无";

    @ApiModelProperty(value = "团队成员头像",hidden = true)
    private String memberIconUrl;

    @ApiModelProperty(value = "毕业去向,没毕业就填暂无")
    @NotNull(message = "毕业去向不为空，没毕业就填暂无")
    private String memberAfterGraduatedDestination;

    @ApiModelProperty(value = "学习方向" , example = "单：后台 ， 多：前端&后台   用&来添加下一个")
    @NotNull(message = "方向不为空")
    private String memberDirection;

    @ApiModelProperty(value = "电话号码")
    @NotNull(message = "电话号码不为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$"
            ,message = "电话号码格式不正确,请检查一下捏")
    private String memberTelephone;

    @ApiModelProperty(value = "邮箱")
    @NotNull(message = "邮箱不为空")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",message = "邮箱格式不正确")
    private String memberEmail;

    @ApiModelProperty(value = "网络冲浪名")
    @Length(max = 20)
    private String memberNickName;

    public String getMemberStudentId() {
        return memberStudentId;
    }

    public void setMemberStudentId(String memberStudentId) {
        this.memberStudentId = memberStudentId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMajor() {
        return memberMajor;
    }

    public void setMemberMajor(String memberMajor) {
        this.memberMajor = memberMajor;
    }

    public String getMemberDepartment() {
        return memberDepartment;
    }

    public void setMemberDepartment(String memberDepartment) {
        this.memberDepartment = memberDepartment;
    }

    public String getMemberIntroduction() {
        return memberIntroduction;
    }

    public void setMemberIntroduction(String memberIntroduction) {
        this.memberIntroduction = memberIntroduction;
    }

    public String getMemberIconUrl() {
        return memberIconUrl;
    }

    public void setMemberIconUrl(String memberIconUrl) {
        this.memberIconUrl = memberIconUrl;
    }

    public String getMemberAfterGraduatedDestination() {
        return memberAfterGraduatedDestination;
    }

    public void setMemberAfterGraduatedDestination(String memberAfterGraduatedDestination) {
        this.memberAfterGraduatedDestination = memberAfterGraduatedDestination;
    }

    public String getMemberDirection() {
        return memberDirection;
    }

    public void setMemberDirection(String memberDirection) {
        this.memberDirection = memberDirection;
    }

    public String getMemberTelephone() {
        return memberTelephone;
    }

    public void setMemberTelephone(String memberTelephone) {
        this.memberTelephone = memberTelephone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    @Override
    public String toString() {
        return "MemberUpdateDto{" +
                "memberStudentId='" + memberStudentId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberMajor='" + memberMajor + '\'' +
                ", memberDepartment='" + memberDepartment + '\'' +
                ", memberIntroduction='" + memberIntroduction + '\'' +
                ", memberIconUrl='" + memberIconUrl + '\'' +
                ", memberAfterGraduatedDestination='" + memberAfterGraduatedDestination + '\'' +
                ", memberDirection='" + memberDirection + '\'' +
                ", memberTelephone='" + memberTelephone + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberNickName='" + memberNickName + '\'' +
                '}';
    }
}
