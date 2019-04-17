package cn.jxufe.web.controller.other;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;




public class Justy {

    public static int maxProfit(int[] prices) {
        if (prices.length > 0) {
            int first = 0;
            int second = 0;
            int temp = 0;
            int min = prices[0];
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < min) {
                    min = prices[i];
                    if (temp != 0) {
                        if (temp > first) {
                            second = first;
                            first = temp;
                        } else if (temp > second)
                            second = temp;
                    }
                }

                if (prices[i] >= min) {
                    temp = prices[i] - min;
                }
            }
            return first + second;
        }
        return 0;
    }

    public void testHelloworld() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("登陆失败！！");
            //5、身份验证失败
        }
        //Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录
        //6、退出
        subject.logout();
    }


    public static void main(String[] args) {
        Justy justy = new Justy();
        justy.testHelloworld();
        //int arr[] = {3, 3, 5, 0, 0, 3, 1, 4};
        //System.out.println(Justy.maxProfit(arr));
    }
}
