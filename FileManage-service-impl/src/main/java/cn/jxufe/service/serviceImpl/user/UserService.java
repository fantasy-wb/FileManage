package cn.jxufe.service.serviceImpl.user;

import cn.jxufe.beans.User.User;
import cn.jxufe.dao.mysql.UserDao;
import cn.jxufe.iservice.iservice.IUserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
