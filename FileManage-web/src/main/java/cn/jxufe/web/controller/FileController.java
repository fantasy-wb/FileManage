package cn.jxufe.web.controller;

import cn.jxufe.beans.common.QueryRequest;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.FileService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController extends BaseController{

    @Reference
    private FileService fileService;

    @RequestMapping("personalfile")
    @RequiresPermissions("file:personal")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/file/file";
    }

    @RequestMapping(value = "file/addDir")
    @RequiresPermissions("file:addDir")
    public BaseResult addDirectory(Integer targetFile, String newFile, HttpServletRequest request){
//        User curUser = (User)request.getSession().getAttribute("curUser");
//        try {
//            //fileService.addDir(targetFile, newFile);
//            return BaseResult.buildSuccess("新建文件夹成功！");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return BaseResult.buildFail("新建文件夹失败！");
    }

    //这样分页不会有问题？
    @ResponseBody
    @RequestMapping("file/test")
    public BaseResult fileTest(QueryRequest request,String parentUrl,User user, HttpServletRequest httpServletRequest){
        //User curUser = (User)request.getSession().getAttribute("curUser");
        boolean i = true;
        return BaseResult.buildFail("新建文件夹失败！");
    }

}
