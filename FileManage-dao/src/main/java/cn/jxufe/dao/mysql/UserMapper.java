package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.User;

import java.util.List;

public interface UserMapper {

    /**
     * 查找所以账户信息
     * @return
     */
    List<User> findAll();

    /**
     * 登陆校验
     * @param loginName 登陆账户
     * @param loginPassword 登陆密码
     * @return 登陆用户对象
     */
    User login(String loginName,String loginPassword);

    /**
     * 员工查询界面
     * @return 员工集合
     */
    List<User> selectByPrimaryKeyWithDepRole();

    /**
     * 根据login_name查询单个员工
     * @return 查询对象
     */
    User selectByLoginName(String loginName);


    /**
     * 基本增删改查
     * @return 返回值 1：成功；0：失败
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 基本增删改查
     * @return 返回值 1：成功；0：失败
     */
    int insert(User record);

    /**
     * 基本增删改查
     * @return 返回值 1：成功；0：失败
     */
    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    /**
     * 基本增删改查
     * @return 返回值 1：成功；0：失败
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 基本增删改查
     * @return 返回值 1：成功；0：失败
     */
    int updateByPrimaryKey(User record);


}