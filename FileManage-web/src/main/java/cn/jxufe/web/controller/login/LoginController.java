package cn.jxufe.web.controller.login;


import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.ListResult;
import cn.jxufe.iservice.iservice.ILoginService;
import cn.jxufe.iservice.iservice.IUserService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);



    @Reference
    private ILoginService loginService;

    @Reference
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(String userName, String password,  HttpServletRequest request){

        if(!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)){
            //logger.info("-------------->"+loginService.login(userName,password));
            if(null != loginService.login(userName, password)){
                return "登陆成功！";
            }
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public ListResult<User> getAllUser(int currentPage, int pageSize, HttpServletRequest request){
        PageInfo<User> pageInfo = userService.getAllUser(currentPage,pageSize);

        return ListResult.buildSuccess(pageInfo);
    }
}
