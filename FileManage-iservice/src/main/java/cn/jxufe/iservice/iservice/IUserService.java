package cn.jxufe.iservice.iservice;

import cn.jxufe.beans.model.User;
import com.github.pagehelper.PageInfo;


public interface IUserService {

    public PageInfo<User> getAllUser(int currentPage, int pageSize);
}
