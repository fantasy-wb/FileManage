package cn.jxufe.service.serviceImpl;

//import cn.jxufe.beans.model.File;
import cn.jxufe.beans.model.File;
import cn.jxufe.dao.mysql.FileMapper;
import cn.jxufe.iservice.iservice.FileService;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileServiceImpl extends BaseService<File> implements FileService {

    private static int SUCCESS = 1;

    private static int FAIL = 0;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileMapper fileMapper;

    @Autowired
    FileService fileService;

    @Override
    public List<File> findFileByParent(File file) {
        return fileMapper.findFileByParent(file);
    }

    @Override
    public List<File> findFileHistoryList(File file) {
        return fileMapper.findFileHistoryList(file);
    }


    @Override
    public int updateFileStatus(String ids,boolean status) {
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                File file = fileMapper.selectByPrimaryKey(Integer.parseInt(id));
                file.setIsActive(status);
                fileMapper.updateByPrimaryKey(file);
            }
            return SUCCESS;
        }catch (Exception e){
            return FAIL;
        }
    }

    @Override
    public int changeFileVersion(File file) {
//        try {
            fileMapper.unableFileStatus(file);
            return SUCCESS;
//        }catch (Exception e){
//            return FAIL;
//        }
    }

    @Override
    public File checkFileExist(File file) {
        return fileMapper.checkFileExist(file);
    }

    @Override
    public List<File> selectAll() {
        return fileMapper.selectAll();
    }



}
