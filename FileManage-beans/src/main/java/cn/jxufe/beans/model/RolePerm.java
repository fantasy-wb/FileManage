package cn.jxufe.beans.model;

import java.io.Serializable;
import java.util.Date;

public class RolePerm implements Serializable {
    private Integer rolePermId;

    private Integer roleId;

    private Integer permId;

    private Integer createUserId;

    private Date createDate;

    private Integer modifyUserId;

    private Date modifyDate;

    public Integer getRolePermId() {
        return rolePermId;
    }

    public void setRolePermId(Integer rolePermId) {
        this.rolePermId = rolePermId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
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

    public RolePerm(){}

    public RolePerm(Integer rolePermId, Integer roleId, Integer permId, Integer createUserId, Date createDate, Integer modifyUserId, Date modifyDate) {
        this.rolePermId = rolePermId;
        this.roleId = roleId;
        this.permId = permId;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.modifyUserId = modifyUserId;
        this.modifyDate = modifyDate;
    }
}