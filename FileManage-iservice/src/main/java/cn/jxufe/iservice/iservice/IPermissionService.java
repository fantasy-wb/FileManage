package cn.jxufe.iservice.iservice;

import cn.jxufe.beans.model.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAllPermission();

    String createNewPermission(Permission permission);

    String pudatePermission(Permission permission);

    Permission selectPermissionById(Integer permId);
}
