package cn.jxufe.beans.model;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable {
    private Integer depId;

    private String depName;

    private Integer createUserId;

    private Date createDate;

    private Integer modifyUserId;

    private Date modifyDate;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Department() {
    }

    public Department(Integer depId, String depName, Integer createUserId, Date createDate, Integer modifyUserId, Date modifyDate) {
        this.depId = depId;
        this.depName = depName;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.modifyUserId = modifyUserId;
        this.modifyDate = modifyDate;
    }
}