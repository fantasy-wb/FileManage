package cn.jxufe.service.serviceImpl;

//import cn.jxufe.beans.model.File;
import cn.jxufe.beans.model.File;
import cn.jxufe.iservice.iservice.FileService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
//我觉得这边extend
public class FileServiceImpl  implements FileService {
    @Override
    public void addFile(String fileURL, String parentURL, boolean isDir) {
        System.out.println("------------------888888888888888888888888888888888888888888999999999999999------------");
    }



    @Override
    public List<File> selectAll() {
        return null;
    }

    @Override
    public File selectByKey(Object key) {
        return null;
    }

    @Override
    public int save(File entity) {
        return 0;
    }

    @Override
    public int delete(Object key) {
        return 0;
    }

    @Override
    public int batchDelete(List<String> list, String property, Class<File> clazz) {
        return 0;
    }

    @Override
    public int updateAll(File entity) {
        return 0;
    }

    @Override
    public int updateNotNull(File entity) {
        return 0;
    }

    @Override
    public List<File> selectByExample(Object example) {
        return null;
    }
//    @Override
//    public void delFileByFileId(Integer fileId) {
//
//    }
//
//    @Override
//    public void updateFile(Integer fileID, String fileName, String fileURL, boolean isDir) {
//
//    }
//
//    @Override
//    public List<File> findFilesByParentURL(String parentURL) {
//        return null;
//    }
//
//    @Override
//    public List<File> findFilesByFileName(String fileName) {
//        return null;
//    }
}
