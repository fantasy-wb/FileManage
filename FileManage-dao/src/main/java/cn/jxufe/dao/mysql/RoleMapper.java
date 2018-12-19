package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Role;

import java.util.List;


public interface RoleMapper {


    /**
     * 所有角色权限查询
     *
     * @param
     * @return 角色集合
     */
    List<Role> selectByprimaryKeyWithPermissions();

    /**
     * 查找部门下所有角色
     * @param deptId
     * @return
     */
    List<Role> selectByDeptId(Integer deptId);

    /**
     * 角色删除
     *
     * @param roleId
     * @return 返回值 1：成功；0：数据不存在
     */
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    /**
     * 角色新增
     *
     * @return 返回值 1：成功；
     */
    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    /**
     * 更改角色信息
     *
     * @return 返回值 1：成功；
     */
    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}