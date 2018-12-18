package cn.jxufe.service.serviceImpl.user;

import cn.jxufe.beans.model.Role;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.model.UserRole;
import cn.jxufe.beans.user.manager.UserManager;
import cn.jxufe.dao.mysql.RoleMapper;
import cn.jxufe.dao.mysql.UserMapper;
import cn.jxufe.dao.mysql.UserRoleMapper;
import cn.jxufe.iservice.iservice.IUserService;
import cn.jxufe.utils.MathUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    private static final String SUCCESS = "success";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    private PlatformTransactionManager transactionManager;

    /**
     * 用户信息分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    单页大小
     * @return
     */
    @Override
    public PageInfo<User> selectByPrimaryKeyWithDepRole(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<User> userList = userMapper.selectByPrimaryKeyWithDepRole();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    /**
     * 获取用户详细信息
     *
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
        /**
         * 注：此处createUserId应为当前登陆用户Id
         */
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            /*****将user中password加密后保存*****/
            user = UserManager.md5Pswd(user);
            UserRole userRole = new UserRole(null, user.getUserId(), user.getRole().getRoleId(), 1, new Date(), null, null);
            userMapper.insert(user);
            userRoleMapper.insertSelective(userRole);
            transactionManager.commit(transactionStatus);
            return SUCCESS;
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            return null;
        }
    }

    /**
     * 删除一个用户
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public String delateUser(Integer userId) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            userRoleMapper.deleteByUserId(userId);
            userMapper.deleteByPrimaryKey(userId);
            transactionManager.commit(transactionStatus);
            return SUCCESS;
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            return null;
        }
    }

    /**
     * 更改一个用户
     * @param user
     * @return
     */
    @Override
    public String changeUserInfo(User user) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            UserRole userRole = userRoleMapper.selectByUserId(user.getUserId());
            Role role = user.getRole();
            if ((role != null) && (role.getRoleId() != userRole.getRoleId())) {
                userRole.setModifyDate(new Date());
                userRole.setModifyUserId(1);
                userRole.setRoleId(role.getRoleId());
                userRoleMapper.updateByPrimaryKeySelective(userRole);
            }
            user.setModifyDate(new Date());
            user.setModifyUserId(1);
            userMapper.updateByPrimaryKey(user);
            transactionManager.commit(transactionStatus);
            return SUCCESS;
        }catch (Exception e){
            transactionManager.rollback(transactionStatus);
            return null;
        }
    }
}
