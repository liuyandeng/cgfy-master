package com.cgfy.oauth.base.util;

import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
	
	public HttpUtil()
	{
	}
	
	/**
	 * 解析form提交的数据
	 * 
	 * @param paramString
	 * @return
	 */
	public static Map<String, String> parseParamString(String paramString) {
		Map<String, String> map = null;
		if(paramString!=null && !"".equals(paramString)) {
			String[] paramArr = paramString.split("&");
			if(paramArr!=null && paramArr.length>0) {
				map = new HashMap<String, String>();
				for(String paramInfo : paramArr) {
					String[] split = paramInfo.split("=");
					if(split!=null && split.length>1) {
						if(split.length<=1) {
							map.put(split[0], "");
						}else {
							map.put(split[0], split[1]);
						}
					}
				}
			}
		}
		
		return map;
	}

	/**
	 * 设置页面响应无缓存
	 * 
	 * @param httpservletresponse
	 */
	public static void setResponseNoCache(HttpServletResponse httpservletresponse)
	{
		httpservletresponse.setHeader("Cache-Control", "no-cache");
		httpservletresponse.setDateHeader("Expires", 0L);
		httpservletresponse.setHeader("Pragma", "No-cache");
	}

	/**
	 * 获取客户端的真实ip地址
	 * 
	 * @param request
	 * @return 客户端的真实ip地址
	 */
	public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("GATEWAY-PROXY-CLIENT-IP");;
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
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
	public static String trimUrl(String url)
	{
		String trimUrl = url;

		if (url != null && !url.trim().equals(""))
		{
			int firstAnd = url.indexOf("&", 1);
			if (firstAnd < 0)
			{
				trimUrl = url;
			}
			else
			{
				trimUrl = url.substring(0, firstAnd);
			}
		}

		return trimUrl;
	}
	
	/**
	 * 获取浏览器类型
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowserType(HttpServletRequest request) {
		return getBrowserType(request.getHeader("USER-AGENT"));
	}

	/**
	 * 获取浏览器类型
	 * 
	 * @param userAgent
	 * @return
	 */
	public static String getBrowserType(String userAgent){
		
		UserAgent userAgentObj = UserAgent.parseUserAgentString(userAgent);
		String browserType = userAgentObj.getBrowser().getName();
		return browserType;
	}
	
	public static void main(String[] args){
		
		
	}

}
