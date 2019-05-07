package cn.jxufe.web.controller;

import cn.jxufe.beans.common.QueryRequest;
import cn.jxufe.beans.model.File;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.FileService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "file/addDir")
    @RequiresPermissions("file:addDir")
    public BaseResult addDirectory(Integer targetFile, String newFile, HttpServletRequest request){
        return BaseResult.buildFail("新建文件夹失败！");
    }

    @RequestMapping("file/list")
    @ResponseBody
    public Map<String, Object> selectAll(QueryRequest request, File file){
        file.setUserId(167);
        List<File> files = this.fileService.findFileByParent(file);
        return super.selectByPageNumSize(request, () -> this.fileService.selectAll());
    }

    @ResponseBody
    @RequestMapping("file/test")
    public BaseResult fileTest(QueryRequest request,String parentUrl,User user, HttpServletRequest httpServletRequest){

        return BaseResult.buildFail("新建文件夹失败！");
    }

}
