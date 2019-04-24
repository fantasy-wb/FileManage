package cn.jxufe.web.controller;


import cn.jxufe.beans.annotation.Log;
import cn.jxufe.beans.common.QueryRequest;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.UserService;
import cn.jxufe.utils.FileUtil;
import cn.jxufe.utils.MD5Utils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Reference
    private UserService userService;

    private static final String ON = "on";

    @RequestMapping("user")
    @RequiresPermissions("user:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/user/user";
    }

    @RequestMapping("user/checkUserName")
    @ResponseBody
    public boolean checkUserName(String username, String oldusername) {
        if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
            return true;
        }
        User result = this.userService.findByName(username);
        return result == null;
    }

    @RequestMapping("user/getUser")
    @ResponseBody
    public BaseResult getUser(Long userId) {
        try {
            User user = this.userService.findById(userId);
            return BaseResult.buildSuccess(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return BaseResult.buildFail("获取用户失败，请联系网站管理员！");
        }
    }

    @Log("获取用户信息")
    @RequestMapping("user/list")
    @RequiresPermissions("user:list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, User user) {
        return super.selectByPageNumSize(request, () -> this.userService.findUserWithDept(user, request));
    }

    @RequestMapping("user/excel")
    @ResponseBody
    public BaseResult userExcel(User user) {
        try {
            List<User> list = this.userService.findUserWithDept(user, null);
            return FileUtil.createExcelByPOIKit("用户表", list, User.class);
        } catch (Exception e) {
            log.error("导出用户信息Excel失败", e);
            return BaseResult.buildFail("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/csv")
    @ResponseBody
    public BaseResult userCsv(User user) {
        try {
            List<User> list = this.userService.findUserWithDept(user, null);
            return FileUtil.createCsv("用户表", list, User.class);
        } catch (Exception e) {
            log.error("导出用户信息Csv失败", e);
            return BaseResult.buildFail("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/regist")
    @ResponseBody
    public BaseResult regist(User user) {
        try {
            User result = this.userService.findByName(user.getUsername());
            if (result != null) {
                return BaseResult.buildFail("该用户名已被使用！");
            }
            this.userService.registUser(user);
            return BaseResult.buildSuccess();
        } catch (Exception e) {
            log.error("注册失败", e);
            return BaseResult.buildFail("注册失败，请联系网站管理员！");
        }
    }

    @Log("更换主题")
    @RequestMapping("user/theme")
    @ResponseBody
    public BaseResult updateTheme(User user) {
        try {
            this.userService.updateTheme(user.getTheme(), user.getUsername());
            return BaseResult.buildSuccess();
        } catch (Exception e) {
            log.error("修改主题失败", e);
            return BaseResult.buildFail("修改主题失败");
        }
    }

    @Log("新增用户")
    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    @ResponseBody
    public BaseResult addUser(User user, Long[] roles) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(User.STATUS_VALID);
            else
                user.setStatus(User.STATUS_LOCK);
            this.userService.addUser(user, roles);
            return BaseResult.buildSuccess("新增用户成功！");
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return BaseResult.buildFail("新增用户失败，请联系网站管理员！");
        }
    }

    @Log("修改用户")
    @RequiresPermissions("user:update")
    @RequestMapping("user/update")
    @ResponseBody
    public BaseResult updateUser(User user, Long[] rolesSelect) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(User.STATUS_VALID);
            else
                user.setStatus(User.STATUS_LOCK);
            this.userService.updateUser(user, rolesSelect);
            return BaseResult.buildSuccess("修改用户成功！");
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return BaseResult.buildFail("修改用户失败，请联系网站管理员！");
        }
    }

    @Log("删除用户")
    @RequiresPermissions("user:delete")
    @RequestMapping("user/delete")
    @ResponseBody
    public BaseResult deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
            return BaseResult.buildSuccess("删除用户成功！");
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return BaseResult.buildFail("删除用户失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        User user = getCurrentUser();
        String encrypt = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        return user.getPassword().equals(encrypt);
    }

    @RequestMapping("user/updatePassword")
    @ResponseBody
    public BaseResult updatePassword(String newPassword) {
        try {
            this.userService.updatePassword(newPassword);
            return BaseResult.buildSuccess("更改密码成功！");
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return BaseResult.buildFail("更改密码失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/profile")
    public String profileIndex(Model model) {
        User user = super.getCurrentUser();
        user = this.userService.findUserProfile(user);
        String ssex = user.getSsex();
        if (User.SEX_MALE.equals(ssex)) {
            user.setSsex("性别：男");
        } else if (User.SEX_FEMALE.equals(ssex)) {
            user.setSsex("性别：女");
        } else {
            user.setSsex("性别：保密");
        }
        model.addAttribute("user", user);
        return "system/user/profile";
    }

    @RequestMapping("user/getUserProfile")
    @ResponseBody
    public BaseResult getUserProfile(Long userId) {
        try {
            User user = new User();
            user.setUserId(userId);
            return BaseResult.buildSuccess(this.userService.findUserProfile(user));
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return BaseResult.buildFail("获取用户信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/updateUserProfile")
    @ResponseBody
    public BaseResult updateUserProfile(User user) {
        try {
            this.userService.updateUserProfile(user);
            return BaseResult.buildSuccess("更新个人信息成功！");
        } catch (Exception e) {
            log.error("更新用户信息失败", e);
            return BaseResult.buildFail("更新用户信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/changeAvatar")
    @ResponseBody
    public BaseResult changeAvatar(String imgName) {
        try {
            String[] img = imgName.split("/");
            String realImgName = img[img.length - 1];
            User user = getCurrentUser();
            user.setAvatar(realImgName);
            this.userService.updateNotNull(user);
            return BaseResult.buildSuccess("更新头像成功！");
        } catch (Exception e) {
            log.error("更换头像失败", e);
            return BaseResult.buildFail("更新头像失败，请联系网站管理员！");
        }
    }
}
