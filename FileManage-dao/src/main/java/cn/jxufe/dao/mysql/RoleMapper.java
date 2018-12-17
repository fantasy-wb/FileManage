package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Role;

import java.util.List;


public interface RoleMapper {


    /**
     * 角色权限查询
     * @param roleName 角色名
     * @return 角色集合
     */
    List<Role> selectByprimaryKeyWithPermissions(String roleName);

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);
    /**
     * 角色新增
     * @return 返回值 1：成功；0：失败
     */
    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    /**
     * 更改角色信息
     * @return 返回值 1：成功；0：失败
     */
    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}