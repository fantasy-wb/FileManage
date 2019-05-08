package cn.jxufe.beans.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_file")
public class File implements Serializable {

    private static final long serialVersionUID = 5524099171346214187L;
    /**
     * 文件唯一标志符ID
     */
    @Id
    @Column(name = "FILE_ID")
    private Integer fileId;

    /**
     * 文件/文件夹名
     */
    @Column(name = "FILE_NAME")
    private String fileName;

    /**
     * directory类型:文件夹
     */
    @Column(name = "FILE_TYPE")
    private String fileType;

    /**
     * 文件大小
     */
    @Column(name = "FILE_SIZE")
    private Double fileSize;

    /**
     * 文件实际存放路径
     */
    @Column(name = "FILE_URL")
    private String fileUrl;

    /**
     * 1:标记文件
     */
    @Column(name = "IS_MARK")
    private Boolean isMark;

    /**
     * 文件是否有效，有效：1，无效：0
     */
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    /**
     * 上级文件夹路径
     */
    @Column(name = "PARENT_URL")
    private String parentUrl;

    /**
     * 所属部门
     */
    @Column(name = "DEPT_ID")
    private Integer deptId;

    /**
     * null：共享文件
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 上传用户
     */
    @Column(name = "CREATE_USER_ID")
    private Integer createUserId;

    /**
     * 上传日期
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 修改人员
     */
    @Column(name = "MODIFY_USER_ID")
    private Integer modifyUserId;

    /**
     * 修改日期
     */
    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    /**
     * 获取文件唯一标志符ID
     *
     * @return FILE_ID - 文件唯一标志符ID
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * 设置文件唯一标志符ID
     *
     * @param fileId 文件唯一标志符ID
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取文件/文件夹名
     *
     * @return FILE_NAME - 文件/文件夹名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件/文件夹名
     *
     * @param fileName 文件/文件夹名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 获取directory类型:文件夹
     *
     * @return FILE_TYPE - directory类型:文件夹
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置directory类型:文件夹
     *
     * @param fileType directory类型:文件夹
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * 获取文件大小
     *
     * @return FILE_SIZE - 文件大小
     */
    public Double getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     *
     * @param fileSize 文件大小
     */
    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 获取文件实际存放路径
     *
     * @return FILE_URL - 文件实际存放路径
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 设置文件实际存放路径
     *
     * @param fileUrl 文件实际存放路径
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    /**
     * 获取1:标记文件
     *
     * @return IS_MARK - 1:标记文件
     */
    public Boolean getIsMark() {
        return isMark;
    }

    /**
     * 设置1:标记文件
     *
     * @param isMark 1:标记文件
     */
    public void setIsMark(Boolean isMark) {
        this.isMark = isMark;
    }

    /**
     * 获取文件是否有效，有效：1，无效：0
     *
     * @return IS_ACTIVE - 文件是否有效，有效：1，无效：0
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置文件是否有效，有效：1，无效：0
     *
     * @param isActive 文件是否有效，有效：1，无效：0
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取上级文件夹路径
     *
     * @return PARENT_URL - 上级文件夹路径
     */
    public String getParentUrl() {
        return parentUrl;
    }

    /**
     * 设置上级文件夹路径
     *
     * @param parentUrl 上级文件夹路径
     */
    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl == null ? null : parentUrl.trim();
    }

    /**
     * 获取所属部门
     *
     * @return DEPT_ID - 所属部门
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * 设置所属部门
     *
     * @param deptId 所属部门
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取null：共享文件
     *
     * @return USER_ID - null：共享文件
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置null：共享文件
     *
     * @param userId null：共享文件
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取上传用户
     *
     * @return CREATE_USER_ID - 上传用户
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置上传用户
     *
     * @param createUserId 上传用户
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取上传日期
     *
     * @return CREATE_DATE - 上传日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置上传日期
     *
     * @param createDate 上传日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改人员
     *
     * @return MODIFY_USER_ID - 修改人员
     */
    public Integer getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 设置修改人员
     *
     * @param modifyUserId 修改人员
     */
    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 获取修改日期
     *
     * @return MODIFY_DATE - 修改日期
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改日期
     *
     * @param modifyDate 修改日期
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}