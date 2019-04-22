package cn.jxufe.iservice.iservice;


import cn.jxufe.beans.model.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
