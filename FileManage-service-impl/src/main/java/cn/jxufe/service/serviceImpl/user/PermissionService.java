package cn.jxufe.service.serviceImpl.user;

import cn.jxufe.beans.model.Permission;
import cn.jxufe.dao.mysql.PermissionMapper;
import cn.jxufe.iservice.iservice.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.List;

public class PermissionService implements IPermissionService {
    private static final String SUCCESS = "success";

    private static final Integer currentUser = 1;

    private PlatformTransactionManager transactionManager;

    @Autowired
    PermissionMapper permissionMapper;


    @Override
    public List<Permission> findAllPermission() {
        return permissionMapper.findAllPermission();
    }

    @Override
    public String createNewPermission(Permission permission) {


        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            permission.setCreateDate(new Date());
            permission.setCreateUserId(currentUser);
            permissionMapper.insertSelective(permission);
            transactionManager.commit(transactionStatus);
            return SUCCESS;
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            return null;
        }
    }

    @Override
    public String updatePermission(Permission permission) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            permission.setModifyDate(new Date());
            permission.setModifyUserId(currentUser);
            transactionManager.commit(transactionStatus);
            return SUCCESS;
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            return null;
        }
    }

    @Override
    public Permission selectPermissionById(Integer permId) {
        return null;
    }
}
