package cn.jxufe.service.serviceImpl.login;

import cn.jxufe.dao.mysql.UserDao;
import cn.jxufe.iservice.iservice.ILoginService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public String login(String userName, String password) {

        if("wang".equals(userName) && "123".equals(password)){
            return "SUCCESS";
        }
        return null;
    }

}
