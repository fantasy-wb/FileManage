package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Department;

import java.util.List;



public interface DepartmentMapper {

    /**
     * 查询得到所有部门下所有Role
     * @return 部门集合
     */
    List<Department> findAllDepartment();

    /********Department类的增删改查********/
    int deleteByPrimaryKey(Integer depId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer depId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}