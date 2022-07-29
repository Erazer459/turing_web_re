package com.yantailor.turing_web_re.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yantailor.turing_web_re.entity.Award;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/15 15:40 @Version 1.0
 */
public class MemberQueryDto {

    //不需要传出
    @JsonIgnore
    private String memberStudentId;

    private String memberName;


    private String memberMajor;


    private String memberDepartment;


    private String memberIntroduction;

    private String memberIconUrl;


    private String memberAfterGraduatedDestination;

    private String memberDirection;

    private List<Award> awards;

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

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    public String getMemberStudentId() {
        return memberStudentId;
    }

    public void setMemberStudentId(String memberStudentId) {
        this.memberStudentId = memberStudentId;
    }

    @Override
    public String toString() {
        return "MemberQueryDto{" +
                "memberName='" + memberName + '\'' +
                ", memberMajor='" + memberMajor + '\'' +
                ", memberDepartment='" + memberDepartment + '\'' +
                ", memberIntroduction='" + memberIntroduction + '\'' +
                ", memberIconUrl='" + memberIconUrl + '\'' +
                ", memberAfterGraduatedDestination='" + memberAfterGraduatedDestination + '\'' +
                ", memberDirection='" + memberDirection + '\'' +
                ", awards=" + awards +
                '}';
    }
}
