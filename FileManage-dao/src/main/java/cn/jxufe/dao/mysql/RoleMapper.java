package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Role;

import java.util.List;


public interface RoleMapper {


    /**********根据部门名获得所有角色**********/
    List<Role> selectByprimaryKeyWithPermissions(String roleName);





    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);
    /*******判断部门是否存在并决定是否添加Role*********/
    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    /**********更改角色所属部门***********/
    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}