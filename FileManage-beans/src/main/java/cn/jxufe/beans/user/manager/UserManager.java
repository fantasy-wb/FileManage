package cn.jxufe.beans.user.manager;

import cn.jxufe.beans.model.Permission;
import cn.jxufe.beans.model.Role;
import cn.jxufe.beans.model.User;
import cn.jxufe.utils.MathUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class UserManager {

    /**
     * 密码加密保存
     * @param user
     * @return
     */
    public static User md5Pswd(User user){
        user.setLoginPassword(md5Pswd(user.getLoginName(),user.getLoginPassword()));
        return user;
    }

    /**
     * MD5加密
     * @param loginName
     * @param loginPassword
     * @return
     */
    public static String md5Pswd(String loginName,String loginPassword){
        loginPassword = String.format("%s#%s",loginName,loginPassword);
        loginPassword = MathUtil.getMD5(loginPassword);
        return loginPassword;
    }

    /**
     * 把查询出来的roles 转换成bootstarp 的 tree数据
     * @param roles
     * @return
     */
    public static List<Map<String,Object>> toTreeData(List<Role> roles){
        List<Map<String,Object>> resultData = new LinkedList<Map<String,Object>>();
        for (Role u : roles) {
            //角色列表
            Map<String,Object> map = new LinkedHashMap<String, Object>();
            map.put("text", u.getRoleName());//名称
            map.put("href", "javascript:void(0)");//链接
            List<Permission> ps = u.getPermission();
            map.put("tags",  new Integer[]{ps.size()});//显示子数据条数
            if(null != ps && ps.size() > 0){
                List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
                //权限列表
                for (Permission up : ps) {
                    Map<String,Object> mapx = new LinkedHashMap<String, Object>();
                    mapx.put("text", up.getPermName());//权限名称
                    mapx.put("href", up.getPermUrl());//权限url
                    //mapx.put("tags", "0");//没有下一级
                    list.add(mapx);
                }
                map.put("nodes", list);
            }
            resultData.add(map);
        }
        return resultData;

    }


}
