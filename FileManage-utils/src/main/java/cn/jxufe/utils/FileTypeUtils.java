package cn.jxufe.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class FileTypeUtils {

    private static Logger log = LoggerFactory.getLogger(FileTypeUtils.class);

    private static HashMap<String,String> typeMap = new HashMap<String, String>(){
        {
            put("text/html", "code");
            put("text/plain", "txt");
            put("text/xml", "code");
            put("image/gif", "gif");
            put("image/jpeg", "jpeg");
            put("image/png", "png");
            put("application/xhtml+xml", "code");
            put("application/xml", "code");
            put("application/atom+xml", "code");
            put("application/json", "code");
            put("application/pdf", "pdf");
            put("audio/mp3", "mp3");
            put("video/mp4", "mp4");
            put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "word");
            put("application/x-zip-compressed", "zip");
            put("application/msword", "word");
            put("application/octet-stream", "zip");
            put("application/vnd.openxmlformats-officedocument.presentationml.presentation", "ppt");
            put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "excle");//rar
            put("application/x-www-form-urlencoded","default");

        }
    };

    public FileTypeUtils() {
    }

    public static String convertType(String fileContentType){
        if(typeMap.containsKey(fileContentType)){
            return typeMap.get(fileContentType);
        }
        return "default";
    }

}
