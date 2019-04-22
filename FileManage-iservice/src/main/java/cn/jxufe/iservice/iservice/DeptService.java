package cn.jxufe.iservice.iservice;



import cn.jxufe.beans.common.Tree;
import cn.jxufe.beans.model.Dept;

import java.util.List;

public interface DeptService {
	List<Dept> selectAll();

	Dept selectByKey(Object key);

	int save(Dept entity);

	int delete(Object key);

	int batchDelete(List<String> list, String property, Class<Dept> clazz);

	int updateAll(Dept entity);

	int updateNotNull(Dept entity);

	List<Dept> selectByExample(Object example);

	Tree<Dept> getDeptTree();

	List<Dept> findAllDepts(Dept dept);

	Dept findByName(String deptName);

	Dept findById(Long deptId);
	
	void addDept(Dept dept);
	
	void updateDept(Dept dept);

	void deleteDepts(String deptIds);
}
