//package cn.jxufe.service.serviceImpl.user;
//
//import cn.jxufe.beans.model.User;
//import cn.jxufe.dao.mysql.UserDao;
//import cn.jxufe.iservice.iservice.IUserService;
//import com.alibaba.dubbo.config.annotation.Service;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//@Service
//public class UserService implements IUserService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public PageInfo<User> getAllUser(int currentPage, int pageSize) {
//        PageHelper.startPage(currentPage, pageSize);
//        List<User> userList =  userDao.getAllUser();
//        PageInfo<User> pageInfo = new PageInfo<>(userList);
//        return pageInfo;
//    }
//
//    @Override
//    public User selectByUserId(Integer userId) {
//        return userDao.selectByUserId(userId);
//    }
//
//
//
//}
