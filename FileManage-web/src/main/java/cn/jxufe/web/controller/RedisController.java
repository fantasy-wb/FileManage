package cn.jxufe.web.controller;


import cn.jxufe.beans.RedisInfo;
import cn.jxufe.beans.annotation.Log;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.iservice.iservice.RedisService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("redis")
public class RedisController {

    private static final String INTEGER_PREFIX = "(integer) ";

    @Reference
    private RedisService redisService;

    @RequestMapping("info")
    @RequiresPermissions("redis:list")
    public String getRedisInfo(Model model) {
        List<RedisInfo> infoList = this.redisService.getRedisInfo();
        model.addAttribute("infoList", infoList);
        return "system/redis/info";
    }

    @RequestMapping("terminal")
    @RequiresPermissions("redis:terminal")
    public String redisTerminal(Model model) {
        String osName = System.getProperty("os.name");
        model.addAttribute("osName", osName);
        return "system/redis/terminal";
    }

    @RequestMapping("keysSize")
    @ResponseBody
    public String getKeysSize() {
        return JSON.toJSONString(redisService.getKeysSize());
    }

    @RequestMapping("memoryInfo")
    @ResponseBody
    public String getMemoryInfo() {
        return JSON.toJSONString(redisService.getMemoryInfo());
    }

    @Log("执行Redis keys命令")
    @RequestMapping("keys")
    @ResponseBody
    public BaseResult keys(String arg) {
        try {
            Set<String> set = this.redisService.getKeys(arg);
            return BaseResult.buildSuccess(set);
        } catch (Exception e) {
            return BaseResult.buildFail(e.getMessage());
        }
    }

    @Log("执行Redis get命令")
    @RequestMapping("get")
    @ResponseBody
    public BaseResult get(String arg) {
        try {
            String result = this.redisService.get(arg);
            return BaseResult.buildSuccess(result == null ? "" : result);
        } catch (Exception e) {
            return BaseResult.buildFail(e.getMessage());
        }
    }

    @Log("执行Redis set命令")
    @RequestMapping("set")
    @ResponseBody
    public BaseResult set(String arg) {
        try {
            String[] args = arg.split(",");
            if (args.length == 1)
                return BaseResult.buildFail("(error) ERR wrong number of arguments for 'set' command");
            else if (args.length != 2)
                return BaseResult.buildFail("(error) ERR syntax error");
            String result = this.redisService.set(args[0], args[1]);
            return BaseResult.buildSuccess(result);
        } catch (Exception e) {
            return BaseResult.buildFail(e.getMessage());
        }
    }

    @Log("执行Redis del命令")
    @RequestMapping("del")
    @ResponseBody
    public BaseResult del(String arg) {
        try {
            String[] args = arg.split(",");
            Long result = this.redisService.del(args);
            return BaseResult.buildSuccess(INTEGER_PREFIX + result);
        } catch (Exception e) {
            return BaseResult.buildFail(e.getMessage());
        }
    }

    @Log("执行Redis exists命令")
    @RequestMapping("exists")
    @ResponseBody
    public BaseResult exists(String arg) {
        try {
            int count = 0;
            String[] arr = arg.split(",");
            for (String key : arr) {
                if (this.redisService.exists(key))
                    count++;
            }
            return BaseResult.buildSuccess(INTEGER_PREFIX + count);
        } catch (Exception e) {
            return BaseResult.buildFail(e.getMessage());
        }
    }

    @Log("执行Redis pttl命令")
    @RequestMapping("pttl")
    @ResponseBody
    public BaseResult pttl(String arg) {
        try {
            return BaseResult.buildSuccess(INTEGER_PREFIX + this.redisService.pttl(arg));
        } catch (Exception e) {
            return BaseResult.buildFail(e.getMessage());
        }
    }

    @Log("执行Redis pexpire命令")
    @RequestMapping("pexpire")
    @ResponseBody
    public BaseResult pexpire(String arg) {
        try {
            String[] arr = arg.split(",");
            if (arr.length != 2 || !isValidLong(arr[1])) {
                return BaseResult.buildFail("(error) ERR wrong number of arguments for 'pexpire' command");
            }
            return BaseResult.buildSuccess(INTEGER_PREFIX + this.redisService.pexpire(arr[0], Long.valueOf(arr[1])));
        } catch (Exception e) {
            return BaseResult.buildFail(e.getMessage());
        }
    }

    private static boolean isValidLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
