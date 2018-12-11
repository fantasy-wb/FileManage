package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Permission;


public interface PermissionMapper {


    int deleteByPrimaryKey(Integer permId);

    int insert(Permission record);

    int insertSelective(Permission record);


    Permission selectByPrimaryKey(Integer permId);


    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}