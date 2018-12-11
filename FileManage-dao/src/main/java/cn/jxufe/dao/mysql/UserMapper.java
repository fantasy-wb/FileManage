package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.User;

import java.util.List;


public interface UserMapper {

    /**查询所有用户**/
    List<User> findAll();

    /**登陆**/
    User login(String loginName,String loginPassword);

    /** 1：删除成功；0：失败**/
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


}