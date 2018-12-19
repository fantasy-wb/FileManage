package cn.jxufe.iservice.iservice;

import cn.jxufe.beans.model.Department;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAllDepartment();

    String changeDepartmentInfo(Department department);

    String delateDepartment(Integer depId);

    String createNewDepartment(Department department);
}
