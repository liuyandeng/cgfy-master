
package com.cgfy.oauth.base.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;
import java.util.UUID;

public class BaseCommonUtil {
	
	/***
	 * 获取随机主见ID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	

	
	/**
	 * 取得权限信息
	 * 
	 * @return 用户信息
	 */
	public static String getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        
        if (authentication instanceof OAuth2Authentication) {
        	Map userAuthenticationDetails = (Map) OAuth2Authentication.class.cast(authentication).getUserAuthentication().getDetails();
        	Map principal = (Map)userAuthenticationDetails.get("principal");
        	
        	return (String)principal.get("id");
        }

        return null;
    }
	
}
