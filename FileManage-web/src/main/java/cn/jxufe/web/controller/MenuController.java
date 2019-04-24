package cn.jxufe.web.controller;


import cn.jxufe.beans.annotation.Log;
import cn.jxufe.beans.common.Tree;
import cn.jxufe.beans.model.Menu;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.MenuService;
import cn.jxufe.utils.FileUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private MenuService menuService;

    @Log("获取菜单信息")
    @RequestMapping("menu")
    @RequiresPermissions("menu:list")
    public String index() {
        return "system/menu/menu";
    }

    @RequestMapping("menu/menu")
    @ResponseBody
    public BaseResult getMenu(String userName) {
        try {
            List<Menu> menus = this.menuService.findUserMenus(userName);
            return BaseResult.buildSuccess(menus);
        } catch (Exception e) {
            logger.error("获取菜单失败", e);
            return BaseResult.buildFail("获取菜单失败！");
        }
    }

    @RequestMapping("menu/getMenu")
    @ResponseBody
    public BaseResult getMenu(Long menuId) {
        try {
            Menu menu = this.menuService.findById(menuId);
            return BaseResult.buildSuccess(menu);
        } catch (Exception e) {
            logger.error("获取菜单信息失败", e);
            return BaseResult.buildFail("获取信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("menu/menuButtonTree")
    @ResponseBody
    public BaseResult getMenuButtonTree() {
        try {
            Tree<Menu> tree = this.menuService.getMenuButtonTree();
            return BaseResult.buildSuccess(tree);
        } catch (Exception e) {
            logger.error("获取菜单列表失败", e);
            return BaseResult.buildFail("获取菜单列表失败！");
        }
    }

    @RequestMapping("menu/tree")
    @ResponseBody
    public BaseResult getMenuTree() {
        try {
            Tree<Menu> tree = this.menuService.getMenuTree();
            return BaseResult.buildSuccess(tree);
        } catch (Exception e) {
            logger.error("获取菜单树失败", e);
            return BaseResult.buildFail("获取菜单树失败！");
        }
    }

    @RequestMapping("menu/getUserMenu")
    @ResponseBody
    public BaseResult getUserMenu(String userName) {
        try {
            Tree<Menu> tree = this.menuService.getUserMenu(userName);
            return BaseResult.buildSuccess(tree);
        } catch (Exception e) {
            logger.error("获取用户菜单失败", e);
            return BaseResult.buildFail("获取用户菜单失败！");
        }
    }

    @RequestMapping("menu/list")
    @RequiresPermissions("menu:list")
    @ResponseBody
    public List<Menu> menuList(Menu menu) {
        try {
            return this.menuService.findAllMenus(menu);
        } catch (Exception e) {
            logger.error("获取菜单集合失败", e);
            return new ArrayList<>();
        }
    }

    @RequestMapping("menu/excel")
    @ResponseBody
    public BaseResult menuExcel(Menu menu) {
        try {
            List<Menu> list = this.menuService.findAllMenus(menu);
            return FileUtil.createExcelByPOIKit("菜单表", list, Menu.class);
        } catch (Exception e) {
            logger.error("带出菜单列表Excel失败", e);
            return BaseResult.buildFail("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("menu/csv")
    @ResponseBody
    public BaseResult menuCsv(Menu menu) {
        try {
            List<Menu> list = this.menuService.findAllMenus(menu);
            return FileUtil.createCsv("菜单表", list, Menu.class);
        } catch (Exception e) {
            logger.error("导出菜单列表Csv失败", e);
            return BaseResult.buildFail("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("menu/checkMenuName")
    @ResponseBody
    public boolean checkMenuName(String menuName, String type, String oldMenuName) {
        if (StringUtils.isNotBlank(oldMenuName) && menuName.equalsIgnoreCase(oldMenuName)) {
            return true;
        }
        Menu result = this.menuService.findByNameAndType(menuName, type);
        return result == null;
    }

    @Log("新增菜单/按钮")
    @RequiresPermissions("menu:add")
    @RequestMapping("menu/add")
    @ResponseBody
    public BaseResult addMenu(Menu menu) {
        String name;
        if (Menu.TYPE_MENU.equals(menu.getType())) {
            name = "菜单";
        } else {
            name = "按钮";
        }
        try {
            this.menuService.addMenu(menu);
            return BaseResult.buildSuccess("新增" + name + "成功！");
        } catch (Exception e) {
            logger.error("新增{}失败", name, e);
            return BaseResult.buildFail("新增" + name + "失败，请联系网站管理员！");
        }
    }

    @Log("删除菜单")
    @RequiresPermissions("menu:delete")
    @RequestMapping("menu/delete")
    @ResponseBody
    public BaseResult deleteMenus(String ids) {
        try {
            this.menuService.deleteMeuns(ids);
            return BaseResult.buildSuccess("删除成功！");
        } catch (Exception e) {
            logger.error("获取菜单失败", e);
            return BaseResult.buildFail("删除失败，请联系网站管理员！");
        }
    }

    @Log("修改菜单/按钮")
    @RequiresPermissions("menu:update")
    @RequestMapping("menu/update")
    @ResponseBody
    public BaseResult updateMenu(Menu menu) {
        String name;
        if (Menu.TYPE_MENU.equals(menu.getType()))
            name = "菜单";
        else
            name = "按钮";
        try {
            this.menuService.updateMenu(menu);
            return BaseResult.buildSuccess("修改" + name + "成功！");
        } catch (Exception e) {
            logger.error("修改{}失败", name, e);
            return BaseResult.buildFail("修改" + name + "失败，请联系网站管理员！");
        }
    }


    @Log("获取系统所有URL")
    @GetMapping("menu/urlList")
    @ResponseBody
    public List<Map<String, String>> getAllUrl() {
        return this.menuService.getAllUrl("1");
    }

}
