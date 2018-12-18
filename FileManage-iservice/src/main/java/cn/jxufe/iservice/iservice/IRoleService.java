package cn.jxufe.iservice.iservice;

import cn.jxufe.beans.model.Role;

public interface IRoleService {

    String createNewRole(Role role);

    String deteteRole(Integer roleId);

    String changeRoleInfo(Role role);

    Role selectByRoleName(String roleName);
}
