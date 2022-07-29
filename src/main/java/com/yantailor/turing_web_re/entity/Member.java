package com.yantailor.turing_web_re.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yantailor
 * on 2022/2/5 11:21 @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("member")
public class Member {
    @TableId
    private String memberStudentId;

    private String memberName;

    private String memberMajor;

    private String memberIntroduction;

    private String memberIconUrl;

    private String memberAfterGraduatedDestination;

    private String memberDirection;

    private String memberTelephone;

    private String memberEmail;

    private boolean memberActivateState;

    private String memberPassword;

    private int memberRoleId;

    private String memberNickName;

    private String memberDepartment;


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

    public boolean isMemberActivateState() {
        return memberActivateState;
    }

    public void setMemberActivateState(boolean memberActivateState) {
        this.memberActivateState = memberActivateState;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public int getMemberRoleId() {
        return memberRoleId;
    }

    public void setMemberRoleId(int memberRoleId) {
        this.memberRoleId = memberRoleId;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberDepartment() {
        return memberDepartment;
    }

    public void setMemberDepartment(String memberDepartment) {
        this.memberDepartment = memberDepartment;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberStudentId='" + memberStudentId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberMajor='" + memberMajor + '\'' +
                ", memberIntroduction='" + memberIntroduction + '\'' +
                ", memberIconUrl='" + memberIconUrl + '\'' +
                ", memberAfterGraduatedDestination='" + memberAfterGraduatedDestination + '\'' +
                ", memberDirection='" + memberDirection + '\'' +
                ", memberTelephone='" + memberTelephone + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberActivateState=" + memberActivateState +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberRoleId=" + memberRoleId +
                ", memberNickName='" + memberNickName + '\'' +
                ", memberDepartment='" + memberDepartment + '\'' +
                '}';
    }
}
