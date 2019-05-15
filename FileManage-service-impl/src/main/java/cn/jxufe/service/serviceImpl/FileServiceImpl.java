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

    public int updateFileStatus(String ids,boolean status) {
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                File file = fileMapper.selectByPrimaryKey(Integer.parseInt(id));
                file.setIsActive(status);
                fileMapper.updateByPrimaryKey(file);
            }
            return 0;
        }catch (Exception e){
            return 1;
        }
    }

    @Override
    public List<File> selectAll() {
        return fileMapper.selectAll();
    }



}
