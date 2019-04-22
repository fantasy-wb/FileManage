package cn.jxufe.dao.mysql;



import cn.jxufe.beans.model.User;
import cn.jxufe.beans.model.UserWithRole;
import cn.jxufe.dao.common.MyMapper;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}