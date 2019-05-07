package cn.jxufe.iservice.iservice;


import cn.jxufe.beans.common.QueryRequest;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.model.UserWithRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "UserService")
public interface UserService extends IService<User> {

    UserWithRole findById(Long userId);

    User findByName(String userName);

    @Cacheable(key = "#p0.toString() + (#p1 != null ? #p1.toString() : '')")
    List<User> findUserWithDept(User user, QueryRequest request);

    @CacheEvict(key = "#", allEntries = true)
    void registUser(User user);

    void updateTheme(String theme, String userName);

    @CacheEvict(allEntries = true)
    void addUser(User user, Long[] roles);

    @CacheEvict(key = "#p0", allEntries = true)
    void updateUser(User user, Long[] roles);

    @CacheEvict(key = "#p0", allEntries = true)
    void deleteUsers(String userIds);

    void updateLoginTime(String userName);

    void updatePassword(String password);

    User findUserProfile(User user);

    void updateUserProfile(User user);
}
