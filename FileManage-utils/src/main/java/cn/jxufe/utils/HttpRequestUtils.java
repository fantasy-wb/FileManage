package cn.jxufe.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * HttpRequestInfo 对http Request进行进一步的封装，可以获取cookie、参数等信息
 *
 */
public class HttpRequestUtils {

    private Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);
    /**
     * http协议头
     */
    private static final String PROTOCOL_HTTP = "http";
    private static final String FULL_PROTOCOL_HTTP = "http://";
    /**
     * https协议头
     */
    private static final String PROTOCOL_HTTPS = "https";
    private static final String FULL_PROTOCOL_HTTPS = "https://";

    private final String url;
    private final Cookie[] cookies;
    private HttpServletRequest request;
    private String userIp = null;
    private Properties headers = null;
    private Map<String,Object> attributes = null;
    private Properties parameters = null;

    /**
     * 构造函数
     * @param request http请求对象
     */
    public HttpRequestUtils(HttpServletRequest request) {
        this.request = request;
        url = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        cookies = request.getCookies();
    }

    /**
     * 获取请求的完整的url
     * @param request http请求对象
     * @return 请求的完整的url
     */
    public static String getAppURL(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80;
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((PROTOCOL_HTTP.equals(scheme) && (port != 80))
                || (PROTOCOL_HTTPS.equals(scheme) && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }

    /**
     * 获取域名
     * @param request
     * @return
     */
    public static String getDomain(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url.replace(FULL_PROTOCOL_HTTP, "").replace(FULL_PROTOCOL_HTTPS, "").split("\\/")[0];
    }

    /**
     * 获取用户端的ip地址，如果经过CDN,或proxy，通常用户端的ip地址是在http头信息中，目前支持X-Forwarded-For，
     * X-Real-IP，Cdn-Src-Ip，Proxy-Client-IP，WL-Proxy-Client-IP等头信息
     * @param request http请求对象
     * @return 用户端的ip地址
     */
    public static String getUserIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Cdn-Src-Ip");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip.indexOf(",") > -1) {
            ip = ip.substring(0,ip.indexOf(","));
        }
        return ip;
    }

    /**
     * 获取指定的参数的值，如果没有相关参数则返回默认值
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 指定的参数的值
     */
    public String getParameter(String name, String defaultValue) {
        String value= request.getParameter( name );
        return ( value != null && value.trim().length()>0 ) ? value.trim(): defaultValue;
    }

    /**
     * 获取指定的参数的值，如果没有相关参数则返回null
     * @param name 参数名
     * @return 指定的参数的值
     */
    public String getParameter(String name) {
        return getParameter(name, null);
    }

    /**
     * 获取指定头信息的值，如果没有相关头信息则返回默认值
     * @param name 头信息名
     * @param defaultValue 默认值
     * @return 指定头信息的值
     */
    public String getHeader(String name, String defaultValue) {
        String value = request.getHeader(name);
        return (value != null && value.trim().length()>0) ? value : defaultValue;
    }

    /**
     * 获取指定头信息的值，如果没有相关头信息则返回null
     * @param name 头信息名
     * @return 指定头信息的值
     */
    public String getHeader(String name) {
        return getHeader(name, null);
    }

    /**
     * 获取指定的参数的整型值，如果没有相关参数或参数错误则返回0
     * @param name 参数名
     * @return 指定的参数的值
     */
    public int getIntParameter(String name) {
        return getIntParameter(name, 0);
    }

    /**
     * 获取指定的参数的整型值，如果没有相关参数或参数错误则返回0
     * @param name 参数名
     * @return 指定的参数的值
     */
    public byte getByteParameter(String name) {
        return getByteParameter(name, (byte) 0);
    }

    /**
     * 获取指定的参数的BigDecimal值，没有则返回null
     * @param name
     * @return
     */
    public BigDecimal getBigDecimalParameter(String name) {
        return convertBigDecimal(request.getParameter( name ));
    }

    /**
     * 获取指定的参数的整型值，如果没有相关参数则返回默认值
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 指定的参数的值
     */
    public int getIntParameter(String name, int defaultValue) {
        return convertInt(request.getParameter(name), defaultValue);
    }

    /**
     * 获取指定的参数的整型值，如果没有相关参数则返回默认值
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 指定的参数的值
     */
    public byte getByteParameter(String name, byte defaultValue) {
        return convertByte(request.getParameter(name), defaultValue);
    }

    /**
     * 获取指定的参数的整型值，如果没有相关参数则返回默认值
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 指定的参数的值
     */
    public byte getByteParameter(String name, int defaultValue) {
        return convertByte(request.getParameter(name), (byte) defaultValue);
    }

    /**
     * 获取指定的属性的整型值，如果没有相关属性或发生错误则返回0
     * @param name 属性名
     * @return 指定的属性的值
     */
    public int getIntAttribute(String name) {
        return getIntAttribute(name, 0);
    }

    /**
     * 获取指定的属性的整型值，如果没有相关属性或发生错误则返回默认值
     * @param name 属性名
     * @param defaultValue 默认值
     * @return 指定的属性的值
     */
    public int getIntAttribute(String name, int defaultValue) {
        Object value = request.getAttribute(name);
        return value == null ? defaultValue : convertInt(value.toString(), defaultValue);
    }

    /**
     * 获取指定的属性的字符串型值，如果没有相关属性或发生错误则返回默认值
     * @param name 属性名
     * @param defaultValue 默认值
     * @return 指定的属性的值
     */
    public String getStringAttribute(String name, String defaultValue) {
        Object value= request.getAttribute( name );
        return (value != null && value.toString().length()>0) ? value.toString() : defaultValue;
    }

    /**
     * 获取指定的参数的长整型值，如果没有相关参数或参数错误则返回0
     * @param name 参数名
     * @return 指定的参数的值
     */
    public long getLongParameter(String name) {
        return getLongParameter(name, 0);
    }

    /**
     * 获取指定的参数的长整型值，如果没有相关参数或参数错误则返回默认值
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 指定的参数的值
     */
    public long getLongParameter(String name, long defaultValue) {
        return convertLong(request.getParameter(name), defaultValue);
    }

    /**
     * 获取指定的参数的长整型值，如果没有相关参数或参数错误则返回0
     * @param name 参数名
     * @return 指定的参数的值
     */
    public float getFloatParameter(String name) {
        return getFloatParameter(name, 0.0f);
    }

    /**
     * 获取指定的参数的浮点型值，如果没有相关参数或参数错误则返回默认值
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 指定的参数的值
     */
    public float getFloatParameter(String name, float defaultValue) {
        return convertFloat(request.getParameter(name), defaultValue);
    }

    /**
     * 获取指定的参数的double型值，如果没有相关参数或参数错误则返回默认值
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 指定的参数的值
     */
    public double getDoubleParameter(String name, double defaultValue) {
        return convertDouble(request.getParameter(name), defaultValue);
    }

    /**
     * 获取指定的参数的日期型值，如果没有相关参数或参数错误则返回null
     * @param name 参数名
     * @param dateFormat 日期格式
     * @return 指定的参数的值
     */
    public Date getDateParameter(String name, String dateFormat) {
        return getDateParameter(name, dateFormat, null);
    }

    /**
     * 获取指定的参数的日期型值，如果没有相关参数或参数错误则返回默认值
     * @param name 参数名
     * @param dateFormat 日期格式
     * @param defaultValue 默认值
     * @return 指定的参数的值
     */
    public Date getDateParameter(String name, String dateFormat, Date defaultValue) {
        return convertDate(request.getParameter(name), dateFormat, defaultValue);
    }

    /**
     * 获取指定头信息的整型值，如果没有相关头信息则返回0
     * @param name 头信息名
     * @return 指定头信息的值
     */
    public int getIntHeader(String name) {
        return convertInt(request.getHeader(name), 0);
    }

    /**
     * 获取指定头信息的整型值，如果没有相关头信息则返回默认值
     * @param name 头信息名
     * @param defaultValue 默认值
     * @return 指定头信息的值
     */
    public int getIntHeader(String name, int defaultValue) {
        return convertInt(request.getHeader(name), defaultValue);
    }

    /**
     * 获取指定头信息的日期型值，如果没有相关头信息或参数错误则返回null
     * @param name 头信息名
     * @param dateFormat 日期格式
     * @return 指定头信息的值
     */
    public Date getDateHeader(String name, String dateFormat) {
        return getDateHeader(name, dateFormat, null);
    }

    /**
     * 获取指定头信息的日期型值，如果没有相关头信息或参数错误则返回默认值
     * @param name 头信息名
     * @param dateFormat 日期格式
     * @param defaultValue 默认值
     * @return 指定头信息的值
     */
    public Date getDateHeader(String name, String dateFormat, Date defaultValue) {
        return convertDate(request.getHeader(name),dateFormat, defaultValue);
    }

    /**
     * 获取指定名称的cookie
     * @param cookieName 指定的cookie名称
     * @return 指定名称的cookie
     */
    public Cookie getCookie(String cookieName) {
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(cookieName)) {
                return cookies[i];
            }
        }
        return null;
    }

    /**
     * 获取指定名称的cookie
     * @param cookieName 指定的cookie名称
     * @param defaultValue 默认值
     * @return 指定名称的cookie
     */
    public String getCookieValue(String cookieName, String defaultValue) {
        Cookie c = getCookie(cookieName);
        if (c == null) {
            return defaultValue;
        }
        String value = c.getValue();
        return (value != null && value.trim().length()>0) ? value : defaultValue;
    }

    /**
     * 获取指定名称的cookie
     * @param cookieName 指定的cookie名称
     * @param defaultValue 默认值
     * @return 指定名称的cookie
     */
    public int getCookieIntValue(String cookieName, int defaultValue) {
        Cookie c = getCookie(cookieName);
        if( c == null )
            return defaultValue;
        return convertInt(c.getValue(), defaultValue);
    }

    /**
     * 获取指定名称的cookie
     * @param cookieName 指定的cookie名称
     * @param defaultValue 默认值
     * @return 指定名称的cookie
     */
    public long getCookieLongValue(String cookieName, long defaultValue) {
        Cookie c = getCookie(cookieName);
        if(c == null) {
            return defaultValue;
        }
        return convertLong(c.getValue(), defaultValue);
    }

    /**
     * 获取指定名称的cookie
     * @param cookieName 指定的cookie名称
     * @param defaultValue 默认值
     * @return 指定名称的cookie
     */
    public float getCookieLongValue(String cookieName, float defaultValue) {
        Cookie c = getCookie(cookieName);
        if (c == null) {
            return defaultValue;
        }
        return convertFloat(c.getValue(), defaultValue);
    }

    /**
     * @return 返回 所有的cookie。
     */
    public Cookie[] getCookies() {
        return cookies;
    }

    /**
     * @return 返回 所有的header。
     */
    public synchronized Properties getHeaders() {
        if( headers == null ){
            headers = new Properties();
            for( Enumeration<String> names = request.getHeaderNames(); names.hasMoreElements(); ) {
                String name = names.nextElement();
                headers.setProperty(name, request.getHeader(name));
            }
        }
        return headers;
    }

    /**
     * @return 返回 所有的parameter。
     */
    public synchronized Properties getParameters() {
        if( parameters == null ){
            parameters = new Properties();
            for(Enumeration<String> names = request.getParameterNames(); names.hasMoreElements();) {
                String name = names.nextElement();
                parameters.setProperty(name, request.getParameter(name));
            }
        }
        return parameters;
    }

    /**
     * @return 返回 所有的attribute。
     */
    public synchronized Map<String,Object> getAttributes() {
        if(attributes == null) {
            attributes = new HashMap<>();
            for (Enumeration<String> names = request.getAttributeNames(); names.hasMoreElements();) {
                String name = names.nextElement();
                attributes.put(name, request.getAttribute(name));
            }
        }
        return attributes;
    }

    /**
     * 返回 用户所请求的url。
     */
    public String getUrl() {
        return url;
    }

    /**
     * 返回用户端的ip地址。
     */
    public synchronized String getUserIp() {
        if(userIp == null) {
            userIp = getUserIp(request);
        }
        return userIp;
    }

    /**
     * @return 返回 原始的http request对象。
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 将请求转换成String类型的字符串（只能执行一次，第二次执行将为空）
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestToString(HttpServletRequest request) throws IOException {
        return request.getReader().readLine();
    }

    @Override
    public String toString(){
        return new StringBuilder("url:").append(url).append(",userIp:").append(userIp)
                .append(",cookies:").append(Arrays.toString(cookies)).append(",headers:").append(getHeaders())
                .append(",parameters:").append(getParameters()).append(",attributes:").append(getAttributes()).toString();
    }

    /**
     * 转换成int类型
     * @param value
     * @param defaultValue
     * @return
     */
    private int convertInt( String value, int defaultValue ) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            logger.error("转换格式出错" + e);
            return defaultValue;
        }
    }

    /**
     * 转换成bigDecimal
     * @param value
     * @return
     */
    private BigDecimal convertBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            logger.error("转换格式出错" + e);
            return null;
        }
    }

    /**
     * 转换成double
     * @param value
     * @param defaultValue
     * @return
     */
    private double convertDouble( String value, double defaultValue ) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            logger.error("转换格式出错" + e);
            return defaultValue;
        }
    }

    /**
     * 转换成float
     * @param value
     * @param defaultValue
     * @return
     */
    private float convertFloat( String value, float defaultValue ) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            logger.error("转换格式出错" + e);
            return defaultValue;
        }
    }

    /**
     * 转换成long
     * @param value
     * @param defaultValue
     * @return
     */
    private long convertLong( String value, long defaultValue ) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            logger.error("转换格式出错" + e);
            return defaultValue;
        }
    }

    /**
     * 转换成date
     * @param value
     * @param defaultValue
     * @return
     */
    private Date convertDate( String value,  String dateFormat, Date defaultValue ) {
        DateFormat df = new SimpleDateFormat( dateFormat );
        try {
            return df.parse( value );
        } catch (Exception e) {
            logger.error("日期转换错误" + e);
            return defaultValue;
        }
    }

    /**
     * 转换成byte
     * @param value
     * @param defaultValue
     * @return
     */
    private byte convertByte(String value, byte defaultValue) {
        try {
            return Byte.parseByte(value);
        } catch (Exception e) {
            logger.error("转换格式出错" + e);
            return defaultValue;
        }
    }
}


