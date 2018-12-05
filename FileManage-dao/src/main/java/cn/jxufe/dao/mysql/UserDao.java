package cn.jxufe.dao.mysql;

import cn.jxufe.beans.User.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> getAllUser();
}
