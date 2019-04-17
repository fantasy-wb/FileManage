package cn.jxufe.beans.model;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable {
    private Integer permId;

    private String permName;

    private String permUrl;

    private Integer createUserId;

    private Date createDate;

    private Integer modifyUserId;

    private Date modifyDate;

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName == null ? null : permName.trim();
    }

    public String getPermUrl() {
        return permUrl;
    }

    public void setPermUrl(String permUrl) {
        this.permUrl = permUrl;
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

    public Permission(){}

    public Permission(Integer permId, String permName, String permUrl, Integer createUserId, Date createDate, Integer modifyUserId, Date modifyDate) {
        this.permId = permId;
        this.permName = permName;
        this.permUrl = permUrl;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.modifyUserId = modifyUserId;
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permId=" + permId +
                ", permName='" + permName + '\'' +
                ", permUrl='" + permUrl + '\'' +
                ", createUserId=" + createUserId +
                ", createDate=" + createDate +
                ", modifyUserId=" + modifyUserId +
                ", modifyDate=" + modifyDate +
                '}';
    }
}