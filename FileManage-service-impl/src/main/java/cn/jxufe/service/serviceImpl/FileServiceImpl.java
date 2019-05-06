package cn.jxufe.service.serviceImpl;

import cn.jxufe.dao.mysql.FileMapper;
import cn.jxufe.iservice.iservice.IFileService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    FileMapper fileMapper;


    @Override
    @Transactional
    public void addDirectory(Integer targetFile, String newFile) {
        System.out.println("-----------------------------++++++++++++++++++++++++++++++++++11111");
        System.out.println("-----------------------------++++++++++++++++++++++++++++++++++11111");
        System.out.println("-----------------------------++++++++++++++++++++++++++++++++++11111");
        System.out.println("-----------------------------++++++++++++++++++++++++++++++++++11111");
    }

    @Override
    public void addFile() {

    }
}
