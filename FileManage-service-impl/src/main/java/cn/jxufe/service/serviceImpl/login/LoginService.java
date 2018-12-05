package cn.jxufe.service.serviceImpl.login;

import cn.jxufe.iservice.iservice.ILoginService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class LoginService implements ILoginService {
    @Override
    public String login(String userName, String password) {
        if("wang".equals(userName) && "123".equals(password)){
            return "SUCCESS";
        }
        return null;
    }
}
