package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> getAllUser();

    void delUserByPrimaryKey(Integer id);

    void addUser(User user);

}
