//package cn.jxufe.web.controller;
//
//import cn.jxufe.beans.annotation.Log;
//import cn.jxufe.beans.model.UserOnline;
//import cn.jxufe.beans.result.BaseResult;
//import cn.jxufe.iservice.iservice.SessionService;
//import com.alibaba.dubbo.config.annotation.Reference;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Controller
//public class SessionController {
//
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Reference
//    SessionService sessionService;
//
//    @Log("获取在线用户信息")
//    @RequestMapping("session")
//    @RequiresPermissions("session:list")
//    public String online() {
//        return "system/monitor/online";
//    }
//
//    @ResponseBody
//    @RequestMapping("session/list")
//    @RequiresPermissions("session:list")
//    public Map<String, Object> list() {
//        List<UserOnline> list = sessionService.list();
//        Map<String, Object> rspData = new HashMap<>();
//        rspData.put("rows", list);
//        rspData.put("total", list.size());
//        return rspData;
//    }
//
//    @ResponseBody
//    @RequiresPermissions("user:kickout")
//    @RequestMapping("session/forceLogout")
//    public BaseResult forceLogout(String id) {
//        try {
//            sessionService.forceLogout(id);
//            return BaseResult.buildSuccess();
//        } catch (Exception e) {
//            log.error("踢出用户失败", e);
//            return BaseResult.buildFail("踢出用户失败");
//        }
//
//    }
//}
