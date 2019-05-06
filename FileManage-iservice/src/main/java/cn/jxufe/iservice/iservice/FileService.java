package cn.jxufe.iservice.iservice;

import cn.jxufe.beans.model.File;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;
//@CacheConfig(cacheNames = "FileService")
public interface FileService extends IService<File>{

    void addFile(String fileURL,String parentURL,boolean isDir);

//    void delFileByFileId(Integer fileId);
//
//    void updateFile(Integer fileID,String fileName,String fileURL,boolean isDir);
//
//    List<File> findFilesByParentURL(String parentURL);
//
//    List<File> findFilesByFileName(String fileName);

}
