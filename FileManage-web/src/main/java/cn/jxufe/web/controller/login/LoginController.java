package cn.jxufe.web.controller.login;


import cn.jxufe.beans.User.User;
import cn.jxufe.iservice.iservice.ILoginService;
import cn.jxufe.iservice.iservice.IUserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/user")
@Controller
public class LoginController {

    //private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Reference
    private ILoginService loginService;

    @Reference
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
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
    public List<User> getAllUser(HttpServletRequest request){

        return userService.getAllUser();
    }
}
