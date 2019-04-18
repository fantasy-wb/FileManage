package cn.jxufe.web.controller.login;


import cn.jxufe.web.Utils.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class TestController {


    @Autowired
    private RedisService redisService;


    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String demoTest(@RequestParam("value")String value){
        try {
            redisService.set("name",value);
            return "插入成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }

    @ResponseBody
    @RequestMapping(value = "/getValue",method = RequestMethod.GET)
    public String demo(@RequestParam("key")String key){
        try {
            Object name = redisService.get("name");
            return name.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }

    public static void main(String[] args) {
        String token = "YWRtaW58NTM3MWQ0NGZ8bnF6dmEwODI2cTk5c3wxNTQwMTE3Nzg4NzU3";
        String decodeToken = new String(Base64Utils.decode(token.getBytes()));
        String[] splitToken = decodeToken.split("\\|");
        String sourceCode = splitToken[0] + splitToken[1];
        String encryptionCode = splitToken[2];
        String tmpSourceCode = "";
        for (int i = 0; i < encryptionCode.length(); i++) {
            int c = (int) encryptionCode.charAt(i);

            if (c >= 48 && c <= 57) {
                c = c - 48;
                c = (c + 5) % 10;
                c = c + 48;
            } else if (c >= 97 && c <= 122) {
                c = c - 97;
                c = (c + 13) % 26;
                c = c + 97;
            }
            tmpSourceCode = tmpSourceCode + (char) c;
        }
    }

}

