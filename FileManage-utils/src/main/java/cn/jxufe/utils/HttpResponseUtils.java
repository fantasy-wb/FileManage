package cn.jxufe.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * HttpResponseUtil 封装HttpResponse的方法
 *
 * @author lijian
 * @version 0.0.1
 */
public class HttpResponseUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpResponseUtils.class);
	
	public HttpResponseUtils() {
	}
	
	/**
	 * 设置cookie
	 * @param response HttpResponse对象
	 * @param name cookie名
	 * @param value cookie值
	 * @param domain 域名
	 * @param path 路径
	 * @param expireSecond 失效时间，-1表示关闭浏览器窗口后失效
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, String domain, String path, int expireSecond ) {
		try {

			Cookie cookie = new Cookie(name, value);
			cookie.setSecure(false);
			cookie.setPath(path);
			cookie.setMaxAge(expireSecond);
			if (!StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			response.addCookie(cookie);
		} catch (Exception e) {
			logger.error(" 设置cookie错误", e);
		}
	}
	
	/**
	 * 设置cookie，关闭浏览器窗口后失效
	 * @param response HttpResponse对象
	 * @param name cookie名
	 * @param value cookie值
	 * @param domain 域名
	 * @param path 路径
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, String domain, String path ) {
		setCookie( response,  name,  value,  domain, path , -1 );
	}

	/**
	 * 设置根路径（/）上的cookie，关闭浏览器窗口后失效
	 * @param response HttpResponse对象
	 * @param name cookie名
	 * @param value cookie值
	 * @param domain 域名
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, String domain ) {
		setCookie( response,  name,  value,  domain, "/", -1 );
	}

	/**
	 * 设置当前域名的根路径（/）上的cookie，关闭浏览器窗口后失效
	 * @param response HttpResponse对象
	 * @param name cookie名
	 * @param value cookie值
	 */
	public static void setCookie(HttpServletResponse response, String name, String value ) {
		setCookie( response,  name,  value,  null, "/", -1 );
	}
	
	/**
	 * 删除指定的cookie
	 * @param response HttpResponse对象
	 * @param cookie 
	 */
	public static void deleteCookie(HttpServletResponse response, Cookie cookie ) {
		if (cookie != null) {
			cookie.setMaxAge(0);	//	Delete the cookie by setting its maximum age to zero
			response.addCookie(cookie);
		}
	}

	/**
	 * 删除指定的cookie
	 * @param response HttpResponse对象
	 * @param name  cookie名
	 */
	public static void deleteCookie(HttpServletResponse response, String name ) {
		setCookie( response, name, null, null, "/", 0 );
	}
	
	/**
	 * 删除指定的cookie
	 * @param response HttpResponse对象
	 * @param name  cookie名
	 * @param domain 域名
	 */
	public static void deleteCookie(HttpServletResponse response, String name, String domain ) {
		setCookie( response, name, null, domain,  "/", 0 );
	}
		
	/**
	 * 删除指定的cookie
	 * @param response HttpResponse对象
	 * @param name  cookie名
	 * @param domain 域名
	 * @param path 路径
	 */
	public static void deleteCookie(HttpServletResponse response, String name, String domain, String path ) {
		setCookie( response, name, null, domain, path, 0 );
	}
	
	public static void setCacheHeader(HttpServletResponse response, int expireSeconds, Date lastModified ) throws IOException {
		//response.setHeader( "Cache-Control", "public" );
		response.setHeader( "Cache-Control", "public, max-age="+ expireSeconds );
		if( lastModified != null ){
			//response.setHeader( "Last-Modified", lastModified.toString() );
			response.setDateHeader( "Last-Modified", lastModified.getTime() );
		}
		//response.setHeader( "Expires",  new Date(System.currentTimeMillis() + expireSeconds*1000).toString() );
		response.setDateHeader( "Expires", System.currentTimeMillis() + expireSeconds*1000 );
	}

	public static void cacheAndRedriect(HttpServletResponse response, int expireSeconds, String url ) throws IOException {
		setCacheHeader( response, expireSeconds, new Date() );
		response.sendRedirect( url );
	}
	
	public static void noCacheAndRedriect(HttpServletResponse response, String url ) throws IOException {
		setNoCacheHeader( response );
		response.sendRedirect( url );
	}
	
	public static void setNoCacheHeader( HttpServletResponse response ) throws IOException {
		response.setHeader( "Pragma", "no-cache" );
		response.setHeader( "Cache-Control", "max-age=0" );
		//response.setHeader( "Expires",  new Date(0).toString() );
		response.setHeader( "Expires",  "0" );
	}  
}
