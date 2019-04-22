package cn.jxufe.web.controller;


import cn.jxufe.beans.annotation.Log;
import cn.jxufe.beans.common.QueryRequest;
import cn.jxufe.beans.model.Role;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.RoleService;
import cn.jxufe.utils.FileUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class RoleController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Reference
    private RoleService roleService;

    @Log("获取角色信息")
    @RequestMapping("role")
    @RequiresPermissions("role:list")
    public String index() {
        return "system/role/role";
    }

    @RequestMapping("role/list")
    @RequiresPermissions("role:list")
    @ResponseBody
    public Map<String, Object> roleList(QueryRequest request, Role role) {
        return super.selectByPageNumSize(request, () -> this.roleService.findAllRole(role));
    }

    @RequestMapping("role/excel")
    @ResponseBody
    public BaseResult roleExcel(Role role) {
        try {
            List<Role> list = this.roleService.findAllRole(role);
            return FileUtil.createExcelByPOIKit("角色表", list, Role.class);
        } catch (Exception e) {
            log.error("导出角色信息Excel失败", e);
            return BaseResult.buildFail("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("role/csv")
    @ResponseBody
    public BaseResult roleCsv(Role role) {
        try {
            List<Role> list = this.roleService.findAllRole(role);
            return FileUtil.createCsv("角色表", list, Role.class);
        } catch (Exception e) {
            log.error("导出角色信息Csv失败", e);
            return BaseResult.buildFail("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("role/getRole")
    @ResponseBody
    public BaseResult getRole(Long roleId) {
        try {
            Role role = this.roleService.findRoleWithMenus(roleId);
            return BaseResult.buildSuccess(role);
        } catch (Exception e) {
            log.error("获取角色信息失败", e);
            return BaseResult.buildFail("获取角色信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("role/checkRoleName")
    @ResponseBody
    public boolean checkRoleName(String roleName, String oldRoleName) {
        if (StringUtils.isNotBlank(oldRoleName) && roleName.equalsIgnoreCase(oldRoleName)) {
            return true;
        }
        Role result = this.roleService.findByName(roleName);
        return result == null;
    }

    @Log("新增角色")
    @RequiresPermissions("role:add")
    @RequestMapping("role/add")
    @ResponseBody
    public BaseResult addRole(Role role, Long[] menuId) {
        try {
            this.roleService.addRole(role, menuId);
            return BaseResult.buildSuccess("新增角色成功！");
        } catch (Exception e) {
            log.error("新增角色失败", e);
            return BaseResult.buildFail("新增角色失败，请联系网站管理员！");
        }
    }

    @Log("删除角色")
    @RequiresPermissions("role:delete")
    @RequestMapping("role/delete")
    @ResponseBody
    public BaseResult deleteRoles(String ids) {
        try {
            this.roleService.deleteRoles(ids);
            return BaseResult.buildSuccess("删除角色成功！");
        } catch (Exception e) {
            log.error("删除角色失败", e);
            return BaseResult.buildFail("删除角色失败，请联系网站管理员！");
        }
    }

    @Log("修改角色")
    @RequiresPermissions("role:update")
    @RequestMapping("role/update")
    @ResponseBody
    public BaseResult updateRole(Role role, Long[] menuId) {
        try {
            this.roleService.updateRole(role, menuId);
            return BaseResult.buildSuccess("修改角色成功！");
        } catch (Exception e) {
            log.error("修改角色失败", e);
            return BaseResult.buildFail("修改角色失败，请联系网站管理员！");
        }
    }
}
