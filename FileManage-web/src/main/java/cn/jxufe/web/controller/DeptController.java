package cn.jxufe.web.controller;


import cn.jxufe.beans.annotation.Log;
import cn.jxufe.beans.common.Tree;
import cn.jxufe.beans.model.Dept;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.DeptService;
import cn.jxufe.utils.FileUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeptController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Reference
    private DeptService deptService;

    @Log("获取部门信息")
    @RequestMapping("dept")
    @RequiresPermissions("dept:list")
    public String index() {
        return "system/dept/dept";
    }

    @RequestMapping("dept/tree")
    @ResponseBody
    public BaseResult getDeptTree() {
        try {
            Tree<Dept> tree = this.deptService.getDeptTree();
            return BaseResult.buildSuccess(tree);
        } catch (Exception e) {
            log.error("获取部门树失败", e);
            return BaseResult.buildFail("获取部门树失败！");
        }
    }

    @RequestMapping("dept/getDept")
    @ResponseBody
    public BaseResult getDept(Long deptId) {
        try {
            Dept dept = this.deptService.findById(deptId);
            return BaseResult.buildSuccess(dept);
        } catch (Exception e) {
            log.error("获取部门信息失败", e);
            return BaseResult.buildFail("获取部门信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("dept/list")
    @RequiresPermissions("dept:list")
    @ResponseBody
    public List<Dept> deptList(Dept dept) {
        try {
            return this.deptService.findAllDepts(dept);
        } catch (Exception e) {
            log.error("获取部门列表失败", e);
            return new ArrayList<>();
        }
    }

    @RequestMapping("dept/excel")
    @ResponseBody
    public BaseResult deptExcel(Dept dept) {
        try {
            List<Dept> list = this.deptService.findAllDepts(dept);
            return FileUtil.createExcelByPOIKit("部门表", list, Dept.class);
        } catch (Exception e) {
            log.error("导出部门信息Excel失败", e);
            return BaseResult.buildFail("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("dept/csv")
    @ResponseBody
    public BaseResult deptCsv(Dept dept) {
        try {
            List<Dept> list = this.deptService.findAllDepts(dept);
            return FileUtil.createCsv("部门表", list, Dept.class);
        } catch (Exception e) {
            log.error("获取部门信息Csv失败", e);
            return BaseResult.buildFail("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("dept/checkDeptName")
    @ResponseBody
    public boolean checkDeptName(String deptName, String oldDeptName) {
        if (StringUtils.isNotBlank(oldDeptName) && deptName.equalsIgnoreCase(oldDeptName)) {
            return true;
        }
        Dept result = this.deptService.findByName(deptName);
        return result == null;
    }

    @Log("新增部门 ")
    @RequiresPermissions("dept:add")
    @RequestMapping("dept/add")
    @ResponseBody
    public BaseResult addRole(Dept dept) {
        try {
            this.deptService.addDept(dept);
            return BaseResult.buildSuccess("新增部门成功！");
        } catch (Exception e) {
            log.error("新增部门失败", e);
            return BaseResult.buildFail("新增部门失败，请联系网站管理员！");
        }
    }

    @Log("删除部门")
    @RequiresPermissions("dept:delete")
    @RequestMapping("dept/delete")
    @ResponseBody
    public BaseResult deleteDepts(String ids) {
        try {
            this.deptService.deleteDepts(ids);
            return BaseResult.buildSuccess("删除部门成功！");
        } catch (Exception e) {
            log.error("删除部门失败", e);
            return BaseResult.buildFail("删除部门失败，请联系网站管理员！");
        }
    }

    @Log("修改部门 ")
    @RequiresPermissions("dept:update")
    @RequestMapping("dept/update")
    @ResponseBody
    public BaseResult updateRole(Dept dept) {
        try {
            this.deptService.updateDept(dept);
            return BaseResult.buildSuccess("修改部门成功！");
        } catch (Exception e) {
            log.error("修改部门失败", e);
            return BaseResult.buildFail("修改部门失败，请联系网站管理员！");
        }
    }
}
