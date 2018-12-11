package cn.jxufe.beans.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {


    private Integer userId;

    private String loginName;

    private String loginPassword;

    private String realName;

    private Integer sex;

    private String email;

    private Boolean status;

    private Date createDate;

    private Integer createUserId;

    private Date lastLoginDate;

    private Integer modifyUserId;

    private Date modifyDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public User(){}

    public User(Integer userId, String loginName, String loginPassword, String realName, Integer sex, String email, Boolean status, Date createDate, Integer createUserId, Date lastLoginDate, Integer modifyUserId, Date modifyDate) {
        this.userId = userId;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.realName = realName;
        this.sex = sex;
        this.email = email;
        this.status = status;
        this.createDate = createDate;
        this.createUserId = createUserId;
        this.lastLoginDate = lastLoginDate;
        this.modifyUserId = modifyUserId;
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", createUserId=" + createUserId +
                ", lastLoginDate=" + lastLoginDate +
                ", modifyUserId=" + modifyUserId +
                ", modifyDate=" + modifyDate +
                '}';
    }
}