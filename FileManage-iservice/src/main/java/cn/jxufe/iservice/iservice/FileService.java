package cn.jxufe.iservice.iservice;

import cn.jxufe.beans.model.File;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;
@CacheConfig(cacheNames = "FileService")
public interface FileService extends IService<File>{

    List<File> findFileByParent(File file);

    int updateFileStatus(String ids,boolean status);
}
