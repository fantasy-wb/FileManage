package cn.jxufe.beans.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_file")
public class File {
    @Column(name = "file_id")
    private Integer fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private Float fileSize;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "is_directory")
    private Boolean isDirectory;

    @Column(name = "is_star")
    private Boolean isStar;

    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "parent_url")
    private String parentUrl;

    @Column(name = "create_user_id")
    private Integer createUserId;

    @Column(name = "modify_user_id")
    private Integer modifyUserId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * @return file_id
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * @param fileId
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * @return file_name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * @return file_size
     */
    public Float getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     */
    public void setFileSize(Float fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return file_url
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param fileUrl
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    /**
     * @return is_directory
     */
    public Boolean getIsDirectory() {
        return isDirectory;
    }

    /**
     * @param isDirectory
     */
    public void setIsDirectory(Boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    /**
     * @return is_star
     */
    public Boolean getIsStar() {
        return isStar;
    }

    /**
     * @param isStar
     */
    public void setIsStar(Boolean isStar) {
        this.isStar = isStar;
    }

    /**
     * @return dept_id
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return parent_url
     */
    public String getParentUrl() {
        return parentUrl;
    }

    /**
     * @param parentUrl
     */
    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl == null ? null : parentUrl.trim();
    }

    /**
     * @return create_user_id
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * @return modify_user_id
     */
    public Integer getModifyUserId() {
        return modifyUserId;
    }

    /**
     * @param modifyUserId
     */
    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return modify_date
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}