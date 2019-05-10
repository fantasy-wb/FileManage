package cn.jxufe.web.Utils.FastDFS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Controller
@RequestMapping("/fastDFS")
public class UploadController {

    @Autowired
    private FastDFSClientWrapper dfsClient;

    @GetMapping("/")
    public String index() {
        return "system/upload/file";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String fdfsUpload(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "fail";
        }

        try {
            String fileUrl = dfsClient.uploadFile(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + fileUrl + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping("/download")
    public void uploadStatus(HttpServletResponse response, @RequestParam(value = "fileUrl") String fileUrl) {

        OutputStream outputStream = null;

        try {
            byte[] content = dfsClient.downFile(fileUrl);//得到文件的字节数组
            // 对文件名进行编码处理中文问题
            String fileName = new String("小明.jpg".getBytes("UTF-8"),"iso-8859-1");
            response.reset(); // 重点突出
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");// 不同类型的文件对应不同的MIME类型
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);

            outputStream = response.getOutputStream();
            outputStream.write(content); // 输出数据

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 特别重要
            // 1. 进行关闭是为了释放资源
            // 2. 进行关闭会自动执行flush方法清空缓冲区内容
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public String deleteFile(@RequestParam(value = "fileUrl") String fileUrl) {
        dfsClient.deleteFile(fileUrl);
        return "Success";
    }
}