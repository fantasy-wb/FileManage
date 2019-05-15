package cn.jxufe.web.controller;

import cn.jxufe.beans.common.QueryRequest;
import cn.jxufe.beans.model.File;
import cn.jxufe.beans.model.User;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.DeptService;
import cn.jxufe.iservice.iservice.FileService;
import cn.jxufe.utils.FileTypeUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@Controller
public class FileController extends BaseController {
    @Reference
    private DeptService deptService;

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
    @RequestMapping(value = "file/delete")
    @RequiresPermissions("file:delete")
    public BaseResult deleteFileOrDir(String ids) {
        try {
            this.fileService.updateFileStatus(ids, false);
            return BaseResult.buildSuccess("文件删除成功！");
        } catch (Exception e) {
            return BaseResult.buildFail("文件删除失败！");
        }
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "file/list")
    //@RequiresPermissions("file:list")
    public Map<String, Object> getFileList(QueryRequest request, File file) {
        User user = super.getCurrentUser();
        if (file.getParentUrl().startsWith("&nbsp;&nbsp;个人文件")) {
            file.setParentUrl(file.getParentUrl().replace("&nbsp;&nbsp;个人文件", user.getUserId().toString()));
        } else if (file.getParentUrl().startsWith("&nbsp;&nbsp;共享资源")) {
            String deptName = deptService.selectByKey(user.getDeptId()).getDeptName();
            file.setParentUrl(file.getParentUrl().replace("&nbsp;&nbsp;共享资源", deptName));
        }
        return super.selectByPageNumSize(request, () -> this.fileService.findFileByParent(file));
    }


    @ResponseBody
    @RequestMapping(value = "file/download")
    @RequiresPermissions("file:download")
    public byte[] downloadFile(String fileUrl){
        return dfsClient.downFile(fileUrl);
    }


    @ResponseBody
    @Transactional
    @RequestMapping(value = "file/addFile")
    @RequiresPermissions("file:add")
    public BaseResult uploadFile(@RequestParam("file") MultipartFile multipartFile, File file) {
        try {
            if (file.getParentUrl().isEmpty()) {
                return BaseResult.buildFail("缺少必要的信息，请联系开发人员！");
            }

            User user = super.getCurrentUser();

            if (file.getParentUrl().startsWith("&nbsp;&nbsp;个人文件")) {
                file.setParentUrl(file.getParentUrl().replace("&nbsp;&nbsp;个人文件", user.getUserId().toString()));
            } else if (file.getParentUrl().startsWith("&nbsp;&nbsp;共享资源")) {
                String deptName = deptService.selectByKey(user.getDeptId()).getDeptName();
                file.setParentUrl(file.getParentUrl().replace("&nbsp;&nbsp;共享资源", deptName));
            }

            String fileType = FileTypeUtils.convertType(multipartFile.getContentType());
            file.setFileName(multipartFile.getOriginalFilename());
            file.setFileType(fileType);
            file.setIsActive(true);
            file.setIsMark(false);
            file.setCreateUserId(user.getUserId());
            file.setCreateDate(new Date());
            file.setModifyUserId(user.getUserId());
            file.setModifyDate(new Date());
            file.setFileSize(multipartFile.getSize());//储存单位：字节

            String fileUrl = dfsClient.uploadFile(multipartFile);
            file.setFileUrl(fileUrl);

            //dfsClient.deleteFile(fileUrl);
            this.fileService.save(file);
            return BaseResult.buildSuccess("文件上传成功！");
        } catch (Exception e) {
            return BaseResult.buildFail("文件上传失败！");
        }

    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "file/addDir")
    @RequiresPermissions("file:add")
    public BaseResult crateDir(File file) {

        try {
            if (file.getParentUrl().isEmpty() || file.getFileName().isEmpty()) {
                return BaseResult.buildFail("缺少必要的信息，请联系开发人员！");
            }

            User user = super.getCurrentUser();

            if (file.getParentUrl().startsWith("&nbsp;&nbsp;个人文件")) {
                file.setParentUrl(file.getParentUrl().replace("&nbsp;&nbsp;个人文件", user.getUserId().toString()));
            } else if (file.getParentUrl().startsWith("&nbsp;&nbsp;共享资源") && user.getDeptId() != null) {
                String deptName = deptService.selectByKey(user.getDeptId()).getDeptName();
                file.setParentUrl(file.getParentUrl().replace("&nbsp;&nbsp;共享资源", deptName));
            }

            file.setFileType("dir");
            file.setIsActive(true);
            file.setIsMark(false);
            file.setCreateUserId(user.getUserId());
            file.setCreateDate(new Date());
            file.setModifyUserId(user.getUserId());
            file.setModifyDate(new Date());


            this.fileService.save(file);
            return BaseResult.buildSuccess("新建文件夹成功！");
        } catch (Exception e) {
            return BaseResult.buildFail("新建文件夹失败！");
        }

    }

    @ResponseBody
    @RequestMapping("file/selectAll")
    @Transactional
    public Map<String, Object> selectAll(QueryRequest request, File file) {
        return super.selectByPageNumSize(request, () -> this.fileService.selectAll());
    }

    @ResponseBody
    @RequestMapping("file/test")
    public Map<String, Object> fileTest(QueryRequest request, HttpServletRequest httpServletRequest, File file) {

        return BaseResult.buildSuccess("新建文件夹成功！");//super.selectByPageNumSize(request, () -> this.fileService.selectAll());
    }

}
