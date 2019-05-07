package cn.jxufe.dao.mysql;

import cn.jxufe.beans.model.File;
import cn.jxufe.dao.common.MyMapper;

import java.util.List;

public interface FileMapper extends MyMapper<File> {
    List<File> findFileByParent(File file);
}