//package cn.jxufe.web.interceptor;
//
//import cn.jxufe.beans.enums.Errcode;
//import cn.jxufe.beans.result.BaseResult;
//import cn.jxufe.iservice.iservice.IUserService;
//import cn.jxufe.utils.HttpRequestUtils;
//import cn.jxufe.utils.HttpResponseUtils;
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.alibaba.fastjson.JSONObject;
//import com.cmos.advertcenter.beans.ChopRoleCapable;
//import com.cmos.advertcenter.beans.ChopUserEntity;
//import com.cmos.advertcenter.beans.Errcode;
//import com.cmos.advertcenter.iservice.IUserService;
//import com.cmos.advertcenter.web.business.result.BaseResult;
//import com.cmos.advertcenter.web.utils.FrontUtil;
//import com.cmos.common.bean.JsonFormatException;
//import com.google.common.collect.Lists;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.mframework.common.LoginInfo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Base64Utils;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//@Component
//public class LoginInterceptor extends HandlerInterceptorAdapter {
//
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
//
//    private static final String DEFAULT_ADMIN = "admin";
//    private static final String DEVELOPMENT_ENVIRONMENT = "development";
//    private static final String INFO_INSIDE = "info_inside";
//
//
//    @Value("${loginUrl}")
//    private String loginUrl;
//
//    @Value("${webEnv}")
//    private String env;
//
//    @Reference(group = "FileManage")
//    private IUserService userService;
//
//    @Value("${excludeSSoUrls}")
//    private String excludeUrls;
//    private List<String> excludeUrlList = new ArrayList();
//
//
//    @PostConstruct
//    public void afterPropertiesSet() {
//        if (StringUtils.isNotEmpty(excludeUrls)) {
//            String[] excludeUrlArray = StringUtils.split(excludeUrls, ",");
//            excludeUrlList = Arrays.asList(excludeUrlArray);
//        }
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        for (String url : excludeUrlList) {
//            if (requestURI.startsWith(url)) {
//                return true;
//            }
//        }
//        if (requestURI.startsWith(contextPath + "/api")) {
//            request.getContextPath();
//            HttpSession session = request.getSession();
////            LoginInfo loginInfo = (LoginInfo) session.getAttribute(SESSIONKEY_M_LOGININFO);
//            HttpRequestUtils httpRequestUtils = new HttpRequestUtils(request);
//
//
//            LoginInfo user;
////            if (StringUtils.isNotBlank(env) && StringUtils.equals(DEVELOPMENT_ENVIRONMENT, env)) {
////                // 开发环境用默认用户名
////                user = this.getUserByName(DEFAULT_ADMIN);
////                request.setAttribute(FrontUtil.SESSIONKEY_M_LOGININFO, user);
////                request.getSession().setAttribute(FrontUtil.SESSIONKEY_M_LOGININFO, user);
////                return true;
////            }
//
////            String ssoToken = request.getParameter("ssoToken");
//            // YWRtaW58NTM3MWQ0NGZ8bnF6dmEwODI2cTk5c3wxNTQwMTE3Nzg4NzU3
//            String ssoToken = request.getParameter("ssoToken");
//            if (StringUtils.isBlank(ssoToken)) {
//                ssoToken = httpRequestUtils.getCookieValue(INFO_INSIDE, "");
//            }
//            LOGGER.info("request-url-->" + request.getRequestURL() + ":::cookie->" + ssoToken);
//            if (StringUtils.isBlank(ssoToken)) {
//                LOGGER.error("单点登录出错，cookie为空");
//                this.writeErrorResponse(response);
//                return false;
//            }
//
//
//            user = this.parseSSOToken(ssoToken);
//
//            if (null == user) {
//                //response.sendRedirect(loginUrl);
//                this.writeErrorResponse(response);
//                return false;
//            }
//            request.setAttribute(FrontUtil.SESSIONKEY_M_LOGININFO, user);
//            request.getSession().setAttribute(FrontUtil.SESSIONKEY_M_LOGININFO, user);
//            if (request.getRequestURL().indexOf("logout") == -1) {
//                this.setCookieExpires(request, response, ssoToken);
//            } else {
//                String domain = HttpRequestUtils.getDomain(request);
//                HttpResponseUtils.deleteCookie(response, INFO_INSIDE, domain, "/");
//                this.writeLogoutResponse(response);
//                return false;
//            }
//        }
//        return super.preHandle(request, response, handler);
//    }
//
//    /**
//     * 解析ssoToken
//     *
//     * @param ssoToken
//     * @return
//     */
//    private LoginInfo parseSSOToken(String ssoToken) {
//        try {
//            String tmpToken = ssoToken;
//            String decodeToken = new String(Base64Utils.decode(tmpToken.getBytes()));
//            if (StringUtils.isBlank(decodeToken) || decodeToken.split("\\|").length != 4) {
//                LOGGER.error("ssoToken解析错误，decodeToken=" + decodeToken);
//                return null;
//            }
//
//            String[] splitToken = decodeToken.split("\\|");
//            String sourceCode = splitToken[0] + splitToken[1];
//            String encryptionCode = splitToken[2];
//            String tmpSourceCode = "";
//            for (int i = 0; i < encryptionCode.length(); i++) {
//                int c = (int) encryptionCode.charAt(i);
//
//                if (c >= 48 && c <= 57) {
//                    c = c - 48;
//                    c = (c + 5) % 10;
//                    c = c + 48;
//                } else if (c >= 97 && c <= 122) {
//                    c = c - 97;
//                    c = (c + 13) % 26;
//                    c = c + 97;
//                }
//                tmpSourceCode = tmpSourceCode + (char) c;
//            }
//            if (!StringUtils.equals(sourceCode, tmpSourceCode)) {
//                LOGGER.error("ssoToken解析错误，sourceCode=[{}] tmpSourceCode=[{}]", sourceCode, tmpSourceCode);
//                return null;
//            }
//            return this.getUserByName(splitToken[0]);
//        } catch (Exception e) {
//            LOGGER.error("token解析错误， message=" + e);
//        }
//        return null;
//    }
//
//    /**
//     * 根据用户名获取用户信息
//     *
//     * @param userName
//     * @return
//     */
//    private LoginInfo getUserByName(String userName) {
//        try {
//            ChopUserEntity chopuser = userService.queryByUserName(userName);
//            LOGGER.info("loginUserName=" + userName);
//            LOGGER.info("loginEntity=" + JSONObject.toJSON(chopuser));
//            List<ChopRoleCapable> roleCapableList = userService.roleCapableList(chopuser.getChopuserid() + "");
//            List<String> groupList = userService.groupList(chopuser.getSiteid(), chopuser.getChopgroupid());
//
//            chopuser.setRoleCapableList(roleCapableList);
//            chopuser.setAllgroupidList(groupList);
//            return new LoginInfo(chopuser);
//        } catch (Exception e) {
//            LOGGER.error("用户信息解析失败:" + e);
//        }
//        return null;
//    }
//
//    /**
//     * 响应错误信息
//     *
//     * @param response
//     */
//    private void writeErrorResponse(HttpServletResponse response) throws IOException{
//        BaseResult failResult = new BaseResult(Errcode.A00001.name(), "登录状态失效");
//        failResult.setData(loginUrl);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        response.getWriter().write(JSONObject.toJSONString(failResult));
//        response.flushBuffer();
//    }
//
//    /**
//     * 响应注销结果
//     * @param response
//     * @throws IOException
//     */
//    private void writeLogoutResponse(HttpServletResponse response) throws IOException{
//        BaseResult successResult = BaseResult.buildSuccess();
//        successResult.setData(loginUrl);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        response.getWriter().write(JSONObject.toJSONString(successResult));
//        response.flushBuffer();
//    }
//
//    /**
//     * 延长cookie生命周期(2h)
//     *
//     * @param response
//     * @param request
//     * @param cookieVal
//     */
//    private void setCookieExpires(HttpServletRequest request, HttpServletResponse response, String cookieVal) {
//        try {
//            LOGGER.info("setCookieExpires::" + cookieVal + "::===" + request.getRequestURL());
//            String domain = HttpRequestUtils.getDomain(request);
//            HttpResponseUtils.setCookie(response, INFO_INSIDE, cookieVal, domain, "/", 60 * 60 * 2);
//        } catch (Exception e) {
//            LOGGER.error("cookie生命周期延长出错, message=[{}]" + e);
//        }
//
//    }
//}
