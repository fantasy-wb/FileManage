package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Role;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.model.UserRole;

public interface UserRoleMapper {

    int deleteByPrimaryKey(Integer userRoleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);//状态码 1：成功 0：失败

    UserRole selectByPrimaryKey(Integer userRoleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    Role selectRoleByCurrentUser(User user);




}