package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Permission;

import java.util.List;


public interface PermissionMapper {

    List<Permission> findAllPermission();

    int deleteByPrimaryKey(Integer permId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}