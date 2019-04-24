//package cn.jxufe.service.serviceImpl.user;
//
//
//import cn.jxufe.beans.common.Tree;
//import cn.jxufe.beans.common.TreeUtils;
//import cn.jxufe.beans.model.Dept;
//import cn.jxufe.dao.mysql.DeptMapper;
//import cn.jxufe.iservice.iservice.DeptService;
//import cn.jxufe.service.serviceImpl.BaseService;
//import com.alibaba.dubbo.config.annotation.Service;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import tk.mybatis.mapper.common.Mapper;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@Service
////@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
//public class DeptServiceImpl implements DeptService {
//
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	private DeptMapper deptMapper;
//
//	@Autowired
//	protected Mapper<Dept> mapper;
//
//	public Mapper<Dept> getMapper() {
//		return mapper;
//	}
//
//	@Override
//	public List<Dept> selectAll() {
//		return mapper.selectAll();
//	}
//
//	@Override
//	public Dept selectByKey(Object key) {
//		return mapper.selectByPrimaryKey(key);
//	}
//
//	@Override
//	@Transactional
//	public int save(Dept entity) {
//		return mapper.insert(entity);
//	}
//
//	@Override
//	@Transactional
//	public int delete(Object key) {
//		return mapper.deleteByPrimaryKey(key);
//	}
//
//	@Override
//	@Transactional
//	public int batchDelete(List<String> list, String property, Class<Dept> clazz) {
//		Example example = new Example(clazz);
//		example.createCriteria().andIn(property, list);
//		return this.mapper.deleteByExample(example);
//	}
//
//	@Override
//	@Transactional
//	public int updateAll(Dept entity) {
//		return mapper.updateByPrimaryKey(entity);
//	}
//
//	@Override
//	@Transactional
//	public int updateNotNull(Dept entity) {
//		return mapper.updateByPrimaryKeySelective(entity);
//	}
//
//	@Override
//	public List<Dept> selectByExample(Object example) {
//		return mapper.selectByExample(example);
//	}
//
//	@Override
//	public Tree<Dept> getDeptTree() {
//		List<Tree<Dept>> trees = new ArrayList<>();
//		List<Dept> depts = this.findAllDepts(new Dept());
//		depts.forEach(dept -> {
//			Tree<Dept> tree = new Tree<>();
//			tree.setId(dept.getDeptId().toString());
//			tree.setParentId(dept.getParentId().toString());
//			tree.setText(dept.getDeptName());
//			trees.add(tree);
//		});
//		return TreeUtils.build(trees);
//	}
//
//	@Override
//	public List<Dept> findAllDepts(Dept dept) {
//		try {
//			Example example = new Example(Dept.class);
//			if (StringUtils.isNotBlank(dept.getDeptName())) {
//				example.createCriteria().andCondition("dept_name=", dept.getDeptName());
//			}
//			example.setOrderByClause("dept_id");
//			return this.selectByExample(example);
//		} catch (Exception e) {
//			log.error("获取部门列表失败", e);
//			return new ArrayList<>();
//		}
//
//	}
//
//	@Override
//	public Dept findByName(String deptName) {
//		Example example = new Example(Dept.class);
//		example.createCriteria().andCondition("lower(dept_name) =", deptName.toLowerCase());
//		List<Dept> list = this.selectByExample(example);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Override
//	@Transactional
//	public void addDept(Dept dept) {
//		Long parentId = dept.getParentId();
//		if (parentId == null)
//			dept.setParentId(0L);
//		dept.setCreateTime(new Date());
//		this.save(dept);
//	}
//
//	@Override
//	@Transactional
//	public void deleteDepts(String deptIds) {
//		List<String> list = Arrays.asList(deptIds.split(","));
//		this.batchDelete(list, "deptId", Dept.class);
//		this.deptMapper.changeToTop(list);
//	}
//
//	@Override
//	public Dept findById(Long deptId) {
//		return this.selectByKey(deptId);
//	}
//
//	@Override
//	@Transactional
//	public void updateDept(Dept dept) {
//		this.updateNotNull(dept);
//	}
//
//}
