package cn.jxufe.service.serviceImpl.user;

import cn.jxufe.beans.model.Department;

import cn.jxufe.dao.mysql.DepartmentMapper;
import cn.jxufe.dao.mysql.RoleMapper;
import cn.jxufe.iservice.iservice.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import java.util.Date;
import java.util.List;

public class DepartmentService implements IDepartmentService {

    private static final String SUCCESS = "success";

    private static final Integer currentUer = 1;

    private PlatformTransactionManager transactionManager;
    /*********数据库事务**********/
//    private PlatformTransactionManager transactionManager;
//
//    TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
//    TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
//        try {
//
//        transactionManager.commit(transactionStatus);
//        return SUCCESS;
//    } catch (Exception e) {
//        transactionManager.rollback(transactionStatus);
//        return null;
//    }
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Department> findAllDepartment() {
        return departmentMapper.findAllDepartment();
    }

    @Override
    public String changeDepartmentInfo(Department department) {
        try {
            department.setModifyDate(new Date());
            department.setModifyUserId(currentUer);
            departmentMapper.updateByPrimaryKeySelective(department);
            return SUCCESS;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String delateDepartment(Integer depId) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            if (null != roleMapper.selectByDeptId(depId)) {
                return null;
            } else {
                departmentMapper.deleteByPrimaryKey(depId);
                transactionManager.commit(transactionStatus);
                return SUCCESS;
            }
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            return null;
        }
    }

    @Override
    public String createNewDepartment(Department department) {
        try {
            department.setCreateDate(new Date());
            department.setCreateUserId(currentUer);
            departmentMapper.insertSelective(department);
            return SUCCESS;
        }catch (Exception e){
            return null;
        }
    }
}
