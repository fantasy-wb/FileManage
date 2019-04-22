package cn.jxufe.service.serviceImpl.user;

import cn.jxufe.dao.mysql.FileMapper;
import cn.jxufe.iservice.iservice.IFileService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileService implements IFileService {

    @Autowired
    private FileMapper fileMapper;


    @Override
    //@Transactional
    public void addDirectory(Integer targetFile, String newFile) {

    }

    @Override
    public void addFile() {

    }
}
