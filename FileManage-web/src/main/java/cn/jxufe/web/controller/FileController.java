package cn.jxufe.web.controller;

import cn.jxufe.beans.common.QueryRequest;
import cn.jxufe.beans.model.File;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.FileService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class FileController extends BaseController{

    @Reference
    private FileService fileService;

    @RequestMapping("personalfile")
    //@RequiresPermissions("file:personal")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/file/file";
    }

    @RequestMapping(value = "file/addFile")
    @RequiresPermissions("file:addFile")
    @Transactional
    public BaseResult addDirectory(QueryRequest request, File file){
        try{
            this.fileService.save(file);
            return BaseResult.buildFail("新建文件夹成功！");
        }
        catch (Exception e){
            return BaseResult.buildFail("新建文件夹失败！");
        }

    }

    @ResponseBody
    @RequestMapping("file/selectAll")
    public Map<String, Object> selectAll(QueryRequest request, File file){
        return super.selectByPageNumSize(request, () -> this.fileService.selectAll());
    }

    @ResponseBody
    @RequestMapping("file/test")
    public Map<String, Object> fileTest(QueryRequest request, File file){
        return super.selectByPageNumSize(request, () -> this.fileService.selectAll());
    }

}
