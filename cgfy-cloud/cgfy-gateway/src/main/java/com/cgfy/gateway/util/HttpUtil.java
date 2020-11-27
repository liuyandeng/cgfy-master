package com.cgfy.gateway.util;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该类为Http请求响应工具类
 *
 * @author Leibf
 * @version 2008-4-24
 */
public class HttpUtil {

    public HttpUtil() {
    }

    /**
     * 解析form提交的数据
     *
     * @param paramString
     * @return
     */
    public static Map<String, String> parseParamString(String paramString) {
        Map<String, String> map = null;
        if (paramString != null && !"".equals(paramString)) {
            String[] paramArr = paramString.split("&");
            if (paramArr != null && paramArr.length > 0) {
                map = new HashMap<String, String>();
                for (String paramInfo : paramArr) {
                    String[] split = paramInfo.split("=");
                    if (split != null && split.length > 1) {
                        if (split.length <= 1) {
                            map.put(split[0], "");
                        } else {
                            map.put(split[0], split[1]);
                        }
                    }
                }
            }
        }

        return map;
    }

    /**
     * 获取token信息
     *
     * @param headers
     * @return token信息
     */
    public static String getTokenInfo(HttpHeaders headers) {
        String token = "";
        String auth = headers.getFirst("Authorization");
        if (StringUtils.isNotBlank(auth)) {
            //=================TOKEN Info=======================
            if (auth.toLowerCase().contains("bearer")) {
                if (auth.contains("bearer")) {
                    token = auth.replace("bearer", "").trim();
                }
                if (auth.contains("Bearer")) {
                    token = auth.replace("Bearer", "").trim();
                }
            }
        }
        return token;
    }


    /**
     * 获取客户端类型
     *
     * @param headers
     * @return 客户端类型
     */
    public static String getClientType(HttpHeaders headers) {
        // 获取终端类型
        String os = "";
        String userAgent = headers.getFirst("User-Agent");
        if (StringUtils.isNotBlank(userAgent)) {
            //=================OS Info=======================
            if (userAgent.toLowerCase().indexOf("windows") >= 0) {
                os = "Windows";
            } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
                os = "Mac";
            } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
                os = "Unix";
            } else if (userAgent.toLowerCase().indexOf("android") >= 0 || userAgent.toLowerCase().indexOf("okgo") >= 0) {
                os = "Android";
            } else if (userAgent.toLowerCase().indexOf("iphone") >= 0 || userAgent.toLowerCase().indexOf("cfnetwork") >= 0) {
                os = "IPhone";
            } else {
                os = "UnKnown, More-Info: " + userAgent;
            }
        }
        return os;
    }

    /**
     * 获取客户端的真实ip地址
     *
     * @param request
     * @return 客户端的真实ip地址
     */
    public static String getIpAddr(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (request.getRemoteAddress() != null && request.getRemoteAddress().getAddress() != null) {
                InetAddress address = request.getRemoteAddress().getAddress();
                ip = address.getHostAddress();
            }

        }
        ip = ip != null ? ip.trim() : "";
        ip = ip.equals("0:0:0:0:0:0:0:1") ? "localhost" : ip;
        return ip;
    }

    /**
     * trim掉url最后一个"/"之前的部分和"&"所带的部分
     *
     * @param url
     * @return
     */
    public static String trimUrl(String url) {
        String trimUrl = url;

        if (url != null && !url.trim().equals("")) {
            int firstAnd = url.indexOf("&", 1);
            if (firstAnd < 0) {
                trimUrl = url;
            } else {
                trimUrl = url.substring(0, firstAnd);
            }
        }

        return trimUrl;
    }

    /**
     * 判断客户端浏览器是否IE
     *
     * @param request
     * @return
     */
    public static boolean isIEBrowser(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        return isIEBrowser(headers.getFirst("USER-AGENT"));
    }

    /**
     * 判断客户端浏览器是否IE
     *
     * @param userAgent
     * @return
     */
    public static boolean isIEBrowser(String userAgent) {
        return userAgent.toLowerCase().indexOf("msie") > 0 ? true : false;
    }

    /**
     * 获取浏览器类型
     *
     * @param request
     * @return
     */
    public static String getBrowserType(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        return getBrowserType(headers.getFirst("USER-AGENT"));
    }

    /**
     * 获取浏览器类型
     *
     * @param userAgent
     * @return
     */
    public static String getBrowserType(String userAgent) {

        UserAgent userAgentObj = UserAgent.parseUserAgentString(userAgent);
        String browserType = userAgentObj.getBrowser().getName();
        return browserType;

//		String browserType = BrowserType.OTHER;
//		if (regex(BrowserType.OPERA, userAgent))
//			browserType = BrowserType.OPERA;
//		if (regex(BrowserType.FIREFOX, userAgent))
//			browserType = BrowserType.FIREFOX;
//		if (regex(BrowserType.SAFARI, userAgent))
//			browserType = BrowserType.SAFARI;
//		if (regex(BrowserType.CHROME, userAgent)){
//			if (regex("OPR", userAgent)) // 兼容opera15使用Chromium内核
//			{
//				browserType = BrowserType.OPERA;
//			}
//			else
//			{
//				browserType = BrowserType.CHROME;
//			}
//		}
//		if (regex(BrowserType.SE360, userAgent))
//			browserType = BrowserType.SE360;
//		if (regex(BrowserType.GREEN, userAgent))
//			browserType = BrowserType.GREEN;
//		if (regex(BrowserType.QQ, userAgent))
//			browserType = BrowserType.QQ;
//		if (regex(BrowserType.MAXTHON, userAgent))
//			browserType = BrowserType.MAXTHON;
//		if (regex(BrowserType.IE10, userAgent))
//			browserType = BrowserType.IE10;
//		if (regex(BrowserType.IE9, userAgent))
//			browserType = BrowserType.IE9;
//		if (regex(BrowserType.IE8, userAgent))
//			browserType = BrowserType.IE8;
//		if (regex(BrowserType.IE7, userAgent))
//			browserType = BrowserType.IE7;
//		if (regex(BrowserType.IE6, userAgent))
//			browserType = BrowserType.IE6;
//		
//		return browserType;
    }

    public static boolean regex(String regex, String str) {
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(str);
        return m.find();
    }


    public static void main(String[] args) {


    }

//	public class BrowserType {
//		
//		public final static String IE10 = "MSIE 10.0";
//
//		public final static String IE9 = "MSIE 9.0";
//
//		public final static String IE8 = "MSIE 8.0";
//
//		public final static String IE7 = "MSIE 7.0";
//
//		public final static String IE6 = "MSIE 6.0";
//
//		public final static String MAXTHON = "Maxthon";
//
//		public final static String QQ = "QQBrowser";
//
//		public final static String GREEN = "GreenBrowser";
//
//		public final static String SE360 = "360SE";
//
//		public final static String FIREFOX = "Firefox";
//
//		public final static String OPERA = "Opera";
//
//		public final static String CHROME = "Chrome";
//
//		public final static String SAFARI = "Safari";
//
//		public final static String OTHER = "其它";
//	}

}
