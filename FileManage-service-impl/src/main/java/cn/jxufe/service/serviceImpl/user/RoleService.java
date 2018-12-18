package cn.jxufe.service.serviceImpl.user;

import cn.jxufe.beans.model.Role;
import cn.jxufe.dao.mysql.RoleMapper;
import cn.jxufe.dao.mysql.RolePermMapper;
import cn.jxufe.iservice.iservice.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RoleService implements IRoleService {
    private static final String SUCCESS = "success";
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RolePermMapper rolePermMapper;

    /**
     * 角色新增
     *
     * @param role
     * @return
     */
    @Override
    public String createNewRole(Role role) {
        try {
            role.setCreateDate(new Date());
            role.setCreateUserId(1);
            roleMapper.insertSelective(role);
            return SUCCESS;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public String deteteRole(Integer roleId) {
        try {
            if (null != rolePermMapper.selectByRoleId(roleId)) {
                roleMapper.deleteByPrimaryKey(roleId);
            } else {
                return null;
            }
            return SUCCESS;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 更改角色信息
     *
     * @param role
     * @return
     */
    @Override
    public String changeRoleInfo(Role role) {
        return null;
    }

    /**
     * 角色查找
     *
     * @param roleName
     * @return
     */
    @Override
    public Role selectByRoleName(String roleName) {
        return null;
    }
}
