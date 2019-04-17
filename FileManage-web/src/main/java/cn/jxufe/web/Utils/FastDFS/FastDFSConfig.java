package cn.jxufe.web.Utils.FastDFS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class FastDFSConfig {


    @Value("${fdfs.res-host}")
    private String resHost;

    @Value("${fdfs.storage-port}")
    private String storagePort;

    public String getResHost() {
        return resHost;
    }

    public void setResHost(String resHost) {
        this.resHost = resHost;
    }

    public String getStoragePort() {
        return storagePort;
    }

    public void setStoragePort(String storagePort) {
        this.storagePort = storagePort;
    }

}