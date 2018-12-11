package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.Department;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {


    int deleteByPrimaryKey(Integer depId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer depId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}