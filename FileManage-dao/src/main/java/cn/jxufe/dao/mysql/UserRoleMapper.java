package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.UserRole;

public interface UserRoleMapper {

    /**
     * 删除用户信息中间表
     * @param UserId
     * @return
     */
    int deleteByUserId(Integer UserId);

    int deleteByPrimaryKey(Integer userRoleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);//状态码 1：成功 0：失败

    UserRole selectByPrimaryKey(Integer userRoleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

}