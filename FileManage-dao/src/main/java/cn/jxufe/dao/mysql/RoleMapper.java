package cn.jxufe.dao.mysql;



import cn.jxufe.beans.model.Role;
import cn.jxufe.beans.model.RoleWithMenu;
import cn.jxufe.dao.common.MyMapper;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}