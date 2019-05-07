package cn.jxufe.web.controller.login;


import cn.jxufe.beans.annotation.Log;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.BaseResult;

import cn.jxufe.iservice.iservice.UserService;
import cn.jxufe.utils.MD5Utils;
import cn.jxufe.utils.vcode.Captcha;
import cn.jxufe.utils.vcode.GifCaptcha;
import cn.jxufe.web.properties.MyProperties;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.apache.shiro.SecurityUtils.getSubject;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Reference
    private UserService userService;

    private static final String CODE_KEY = "fantasy";

    @Autowired
    private MyProperties myProperties;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public BaseResult login(String username, String password, String code, Boolean rememberMe) {
        //*******方便登陆调试********//
        username="xiong";
        password="1234";
        rememberMe=true;

        if (!StringUtils.isNotBlank(code)) {
            return BaseResult.buildFail("验证码不能为空！");
        }
        Session session = getSubject().getSession();
        String sessionCode = (String) session.getAttribute(CODE_KEY);
        if (!code.equalsIgnoreCase(sessionCode)) {
            //return BaseResult.buildFail("验证码错误！");
        }
        // 密码 MD5 加密
        password = MD5Utils.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            Subject subject = getSubject();
            if (subject != null)
                subject.logout();
            //shiro登录验证
            getSubject().login(token);

            userService.updateLoginTime(username);
            return BaseResult.buildSuccess();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return BaseResult.buildFail(e.getMessage());
        } catch (AuthenticationException e) {
            return BaseResult.buildFail("认证失败！");
        }
    }

    @GetMapping(value = "gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");

            Captcha captcha = new GifCaptcha(
                    myProperties.getValidateCode().getWidth(),
                    myProperties.getValidateCode().getHeight(),
                    myProperties.getValidateCode().getLength());
            HttpSession session = request.getSession(true);
            captcha.out(response.getOutputStream());
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            logger.error("图形验证码生成失败", e);
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("/403")
    public String forbid() {
        return "403";
    }

    @Log("访问系统")
    @RequestMapping("/index")
    public String index(Model model) {
        // 登录成后，即可通过 Subject 获取登录的用户信息
        User user = (User) getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }
}
