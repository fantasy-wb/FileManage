package cn.jxufe.web.Utils.FastDFS;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 功能描述: 文件处理类
 *
 * @date 2018/10/12
 */
@Component
public class FastDFSClientWrapper {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSClientWrapper.class);

    public final static String HTTP_FILEURL = "http://94.191.85.22";

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FastDFSConfig fastDFSConfig;

    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile((InputStream) file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        logger.info("storePath:" + storePath);
        return getResAccessUrl(storePath);
    }

    /**
     * 封装文件完整URL地址
     *
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        //GlobalConstants.HTTP_PRODOCOL +
        String fileUrl = HTTP_FILEURL + ":" + fastDFSConfig.getStoragePort() + "/" + storePath.getFullPath();
        logger.info("fileUrl:" + fileUrl);
        return fileUrl;
    }

    /**
     * 功能描述: 删除文件
     *
     * @param fileUrl
     * @return void
     * @date 2018/10/12
     */
    public void deleteFile(String fileUrl) {

        logger.info("删除的文件的url:" + fileUrl);
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            logger.info("groupName:"+storePath.getGroup()+"------"+"文件路径path："+storePath.getPath());
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            logger.warn(e.getMessage());
        }
    }

    /**
     * 功能描述: 下载文件
     *
     * @param fileUrl
     * @return java.io.InputStream
     * @date 2018/10/12
     */
    public byte[] downFile(String fileUrl) {
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            byte[] fileByte = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
            //InputStream ins = new ByteArrayInputStream(fileByte);
            return fileByte;
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }
}