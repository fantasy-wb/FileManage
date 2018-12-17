package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.User;

import java.util.List;

//报的是找不到permisssion对应返回值，但是我测试usermapper没用到permission
//usermapper的实现类在哪=-=我这只是测试冲数据库拿数据，还没到实现，UserMapper是接口，可以有对象嘛?
public interface UserMapper {

    /**查询所有用户**/
    List<User> findAll();

    /**登陆**/
    User login(String loginName,String loginPassword);

    /**员工查询界面**/
    List<User> selectByPrimaryKeyWithDepRole();

    /**根据login_name查询单个员工**/
    User selectByLoginName(String loginName);


    /**********基本增删改查************/
    /** 返回值 1：成功；0：失败**/
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


}