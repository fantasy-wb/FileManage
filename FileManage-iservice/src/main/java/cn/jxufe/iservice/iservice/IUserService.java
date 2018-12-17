package cn.jxufe.iservice.iservice;

import cn.jxufe.beans.model.User;
import com.github.pagehelper.PageInfo;


public interface IUserService {

    PageInfo<User> selectByPrimaryKeyWithDepRole(int currentPage, int pageSize);

    User selectByLoginName(String loginName);

    String createNewUser(User user);

    String delateUser(Integer userId);

    String changeUserInfo(User user);
}
