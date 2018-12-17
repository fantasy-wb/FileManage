package cn.jxufe.service.serviceImpl.user;

import cn.jxufe.beans.model.Role;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.model.UserRole;
import cn.jxufe.dao.mysql.RoleMapper;
import cn.jxufe.dao.mysql.UserMapper;
import cn.jxufe.dao.mysql.UserRoleMapper;
import cn.jxufe.iservice.iservice.IUserService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;
    /**
     * 用户信息分页查询
     * @param currentPage 当前页
     * @param pageSize 单页大小
     * @return
     */
    @Override
    public PageInfo<User> selectByPrimaryKeyWithDepRole(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<User> userList =  userMapper.selectByPrimaryKeyWithDepRole();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    /**
     * 获取用户详细信息
     * @param loginName 用户名
     * @return
     */
    @Override
    public User selectByLoginName(String loginName) {
        return userMapper.selectByLoginName(loginName);
    }

    /**
     * 创建新用户，注意此用户有Role属性
     * @param user
     * @return
     */
    @Override
    public String createNewUser(User user) {
        /** 1：注意此处要保证数据库事务，需要两表全建才成功
         * 待我研究一波
         * 2：此处createUserId应为当前登陆用户Id
         */
        UserRole userRole = new UserRole(null,user.getUserId(),user.getRole().getRoleId(),1,new Date(),null,null);
        userMapper.insert(user);
        userRoleMapper.insertSelective(userRole);
        return null;
    }

    @Override
    public String delateUser(Integer userId) {
        userRoleMapper.deleteByUserId(userId);
        userMapper.deleteByPrimaryKey(userId);
        return null;
    }

    @Override
    public String changeUserInfo(User user) {
        return null;
    }
}
