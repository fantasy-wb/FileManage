package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.RolePerm;

public interface RolePermMapper {

    RolePerm selectByRoleId(Integer roleId);

    int deteteByRoleId(Integer roleId);

    int deleteByPrimaryKey(Integer rolePermId);

    int insert(RolePerm record);

    int insertSelective(RolePerm record);

    RolePerm selectByPrimaryKey(Integer rolePermId);

    int updateByPrimaryKeySelective(RolePerm record);

    int updateByPrimaryKey(RolePerm record);

}