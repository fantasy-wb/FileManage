package cn.jxufe.web.controller;

import cn.jxufe.beans.common.QueryRequest;
import cn.jxufe.beans.model.File;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.FileService;
import cn.jxufe.web.Utils.FastDFS.FastDFSClientWrapper;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class FileController extends BaseController{

    private static String headUrl;

    @Reference
    private FileService fileService;

    @Autowired
    private FastDFSClientWrapper dfsClient;

    @RequestMapping("personalfile")
    //@RequiresPermissions("file:personal")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/file/file";
    }

    @RequestMapping("deptfile")
    //@RequiresPermissions("file:personal")
    public String index2(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/file/file";
    }


    @ResponseBody
    @Transactional
    @RequestMapping(value = "file/delFileODir")
    @RequiresPermissions("file:delete")
    public BaseResult deleteFileOrDir(List<Integer> ids){
        try{




            this.fileService.delete(1);
            return BaseResult.buildSuccess("新建文件夹成功！");
        }
        catch (Exception e){
            return BaseResult.buildFail("新建文件夹失败！");
        }

    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "file/list")
    @RequiresPermissions("file:addFile")
    public BaseResult getFileList( MultipartFile multipartFile, File file){

            return BaseResult.buildFail("新建文件夹失败！");
    }



    @ResponseBody
    @Transactional
    @RequestMapping(value = "file/addFile")
    @RequiresPermissions("file:addFile")
    public BaseResult uploadFile(@RequestParam("file") MultipartFile multipartFile, File file){
        try{
            if(file.getParentUrl().isEmpty()||file.getFileType().isEmpty()){
                return BaseResult.buildFail("缺少必要的信息，请联系开发人员！");
            }
            String fileUrl = dfsClient.uploadFile(multipartFile);



            this.fileService.save(file);
            return BaseResult.buildSuccess("新建文件夹成功！");
        }
        catch (Exception e){
            return BaseResult.buildFail("新建文件夹失败！");
        }

    }


    @ResponseBody
    @RequestMapping("file/selectAll")
    @Transactional
    public Map<String, Object> selectAll(QueryRequest request, File file){
        return super.selectByPageNumSize(request, () -> this.fileService.selectAll());
    }

    @ResponseBody
    @RequestMapping("file/test")
    public Map<String, Object> fileTest(QueryRequest request,  @RequestParam("file") MultipartFile multipartFile, File file){

        return super.selectByPageNumSize(request, () -> this.fileService.selectAll());
    }

}
