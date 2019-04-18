package cn.jxufe.web.realms;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class captchaformauthenticationfilter extends FormAuthenticationFilter {
	
	/**
     * 所有被拦截的请求都会经过的方法 。
     * @return 
     */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		 HttpServletRequest httpservletrequest = (HttpServletRequest) request;
	        String resurl = httpservletrequest.getServletPath();
	        System.out.println("===========请求路径======="+resurl);
	        if(resurl.indexOf("/login") < 0){
	            String querystring =  httpservletrequest.getQueryString();
	            String nexturl = resurl+"?"+querystring;
	            HttpSession session = httpservletrequest.getSession();
	            session.setAttribute("nextUrl", nexturl);
	        }
	        saveRequestAndRedirectToLogin(request, response);
	        return false;

	}
	
	

}
