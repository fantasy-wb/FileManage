package cn.jxufe.web.controller;

import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.IFileService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController {

    @Reference
    private IFileService fileService;


    @GetMapping(value = "/addDirectory")
    public BaseResult addDirectory(Integer targetFile, String newFile, HttpServletRequest request){
        User curUser = (User)request.getSession().getAttribute("curUser");
        try {
            fileService.addDirectory(targetFile, newFile);
            return BaseResult.buildSuccess("新建文件夹成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResult.buildFail("新建文件夹失败！");
    }


}
