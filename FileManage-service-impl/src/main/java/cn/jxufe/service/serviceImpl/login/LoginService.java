package cn.jxufe.service.serviceImpl.login;

import cn.jxufe.beans.user.manager.UserManager;
import cn.jxufe.dao.mysql.UserMapper;
import cn.jxufe.iservice.iservice.ILoginService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登陆
     * @param loginName
     * @param loginPassword
     * @return
     */
    @Override
    public String login(String loginName, String loginPassword) {
        /***********校验密码+创建cooke************/
        loginPassword = UserManager.md5Pswd(loginName,loginPassword);
        if(userMapper.login(loginName,loginPassword)!=null){
            return "SUCCESS";
        }
        return null;
    }

}
