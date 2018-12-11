package cn.jxufe.beans.model;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {
    private Integer fileId;

    private String fileName;

    private String filePath;

    private Integer depId;

    private Boolean isImpot;

    private Integer createUserId;

    private Date createDate;

    private Integer modifyUserId;

    private Date modifyDate;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Boolean getIsImpot() {
        return isImpot;
    }

    public void setIsImpot(Boolean isImpot) {
        this.isImpot = isImpot;
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

    public File() {
    }

    public File(Integer fileId, String fileName, String filePath, Integer depId, Boolean isImpot, Integer createUserId, Date createDate, Integer modifyUserId, Date modifyDate) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.depId = depId;
        this.isImpot = isImpot;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.modifyUserId = modifyUserId;
        this.modifyDate = modifyDate;
    }
}