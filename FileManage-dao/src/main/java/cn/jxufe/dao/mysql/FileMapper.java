package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.File;

import java.util.List;


public interface FileMapper {

    List<File> findAllFile();

    List<File> selectFileByDepartment(Integer depId);

    int deleteByPrimaryKey(Integer fileId);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Integer fileId);


    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}