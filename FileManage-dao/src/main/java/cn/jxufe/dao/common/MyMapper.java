package cn.jxufe.dao.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

//不能被扫描到


public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
	
}