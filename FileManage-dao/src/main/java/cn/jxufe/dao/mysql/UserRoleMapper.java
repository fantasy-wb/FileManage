package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.UserRole;

public interface UserRoleMapper {

    /**
     * 删除用户信息中间表
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);

    /**
     * 更改用户角色
     * @param userId
     * @return
     */
    int updateByUserId(Integer userId);

    /**
     * 得到当前用户角色关联对象
     * @param userId
     * @return
     */
    UserRole selectByUserId(Integer userId);

    int insert(UserRole record);

    int insertSelective(UserRole record);//状态码 1：成功 0：失败

    UserRole selectByPrimaryKey(Integer userRoleId);

    int deleteByPrimaryKey(Integer userRoleId);

    int updateByPrimaryKey(UserRole record);

    int updateByPrimaryKeySelective(UserRole record);
}