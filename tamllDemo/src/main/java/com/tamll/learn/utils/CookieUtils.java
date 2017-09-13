package com.tamll.learn.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie工具类
 */
public class CookieUtils {

    /**
     * 获取Cookie的值
     * @param request 请求参数
     * @param cookieName Cookie名称
     * @return 返回Cookie的值
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName){
        return getCookieValue(request,cookieName,false);
    }

    /**
     * 获取Cookie的值方法重载
     * @param request 请求参数
     * @param cookieName Cookie名称
     * @param isDecoder 是否编码(默认utf-8)
     * @return 返回Cookie的值
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName,boolean isDecoder){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try {
            for (int i=0;i<cookies.length;i++){
                if (cookies[i].getName().equals(cookieName)){
                    if (isDecoder){
                        retValue = URLDecoder.decode(cookies[i].getValue(),"UTF-8");
                    }else {
                        retValue = cookies[i].getValue();
                    }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return retValue;
    }

    /**
     * 获取Cookie的值方法重载
     * @param request 请求参数
     * @param cookieName Cookie名称
     * @param encodeString 指定编码
     * @return 返回Cookie的值
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName,String encodeString){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try {
            for (int i=0;i<cookies.length;i++){
                if (cookies[i].getName().equals(cookieName)){
                    retValue =  URLDecoder.decode(cookies[i].getValue(),encodeString);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return retValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     * @param request 请求参数
     * @param response 响应参数
     * @param cookieName Cookie名
     * @param cookieValue Cookie值
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     * @param request 请求参数
     * @param response 响应参数
     * @param cookieName Cookie名
     * @param cookieValue Cookie值
     * @param cookieMaxage cookie生效的最大秒数
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     * @param request 请求参数
     * @param response 响应参数
     * @param cookieName Cookie名
     * @param cookieValue Cookie值
     * @param isEncode 编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码
     * @param request 请求参数
     * @param response 响应参数
     * @param cookieName Cookie名
     * @param cookieValue Cookie值
     * @param cookieMaxage cookie生效的最大秒数
     * @param isEncode 编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 删除Cookie
     * @param request 请求参数
     * @param response 响应参数
     * @param cookieName Cookie名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName) {
        doSetCookie(request, response, cookieName, "", 0, false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     * @param request 请求参数
     * @param response 响应参数 添加Cookie
     * @param cookieName Cookie名
     * @param cookieValue Cookie值
     * @param cookieMaxage cookie生效的最大秒数
     * @param isEncode 是否编码(默认utf-8)
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                          String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            cookie.setMaxAge(cookieMaxage);
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到Cookie的域名
     * @param request 请求参数
     * @return 返回Cookie的域名
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}
