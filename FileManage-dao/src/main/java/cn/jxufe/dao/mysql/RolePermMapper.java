package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Permission;
import cn.jxufe.beans.model.RolePerm;

import java.util.List;


public interface RolePermMapper {
    int deleteByPrimaryKey(Integer rolePermId);

    int insert(RolePerm record);

    int insertSelective(RolePerm record);

    RolePerm selectByPrimaryKey(Integer rolePermId);

    int updateByPrimaryKeySelective(RolePerm record);

    int updateByPrimaryKey(RolePerm record);

    List<Permission> selectByPrimaryKeyWithPermission(Integer roleId);
}