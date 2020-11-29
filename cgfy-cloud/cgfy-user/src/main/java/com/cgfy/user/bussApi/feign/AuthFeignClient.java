package com.cgfy.user.bussApi.feign;
import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.base.config.CustomFeignRequestInterceptor;
import com.cgfy.user.base.config.FeignCommonConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cgfy-oauth", configuration= CustomFeignRequestInterceptor.class)
public interface AuthFeignClient {

	/**
	 * 密码加密
	 *
	 * @param password 密码（明文）
	 * @return 密码（密文）
	 */
	@RequestMapping(method=RequestMethod.POST, value="/oauth/ext/encodedPassword", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String encodedPassword(@RequestParam("password") String password) ;

	/**
	 * 注销
	 *
	 * @param tokenValue Token
	 * @return 成功/失败
	 */
	@RequestMapping(method=RequestMethod.POST, value="/oauth/ext/revokeToken", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public boolean revokeToken(@RequestParam("tokenValue") String tokenValue);

	/**
	 * 验证密码
	 *
	 * @param rawPassword 原始密码
	 * @param encodedPassword 密码（密文）
	 * @return 成功/失败
	 */
	@RequestMapping(method=RequestMethod.POST, value="/oauth/ext/checkPassword", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public boolean checkPassword(@RequestParam("rawPassword") String rawPassword,@RequestParam("encodedPassword") String encodedPassword);


	/**
	 * 读取token
	 * @param userToken 用户凭据access_token）
	 * @return 成功/失败
	 */
	@RequestMapping(method=RequestMethod.POST, value="/oauth/ext/readAccessToken", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public OAuth2AccessToken readAccessToken(@RequestParam("userToken") String userToken);


	/**
	 * 账户登录锁定解除
	 */
	@RequestMapping(method=RequestMethod.POST, value="/oauth/account/accountLockoutRelease/{username}", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AjaxResponse<Object> accountLockoutRelease(@PathVariable("username") String username);

	//consumes = MediaType.APPLICATION_JSON_VALUE
	@RequestMapping(value = "/userSync/update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public AjaxResponse<Object> update(@RequestParam(name = "cn") String cn,
									   @RequestParam(name = "sn") String sn,
									   @RequestParam(name = "uid") String uid,
									   @RequestParam(name = "mail") String mail,
									   @RequestParam(name = "givenName") String givenName,
									   @RequestParam(name = "ou") String ou,
									   @RequestParam(name = "userPassword") String userPassword);

	@RequestMapping(value = "/userSync/create", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public AjaxResponse<Object> create(@RequestParam(name = "cn") String cn,
									   @RequestParam(name = "sn") String sn,
									   @RequestParam(name = "uid") String uid,
									   @RequestParam(name = "mail") String mail,
									   @RequestParam(name = "givenName") String givenName,
									   @RequestParam(name = "ou") String ou,
									   @RequestParam(name = "userPassword") String userPassworld);

	@RequestMapping(value = "/userSync/addUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public AjaxResponse<Object> addUser(@RequestParam(name = "cn") String cn,
										@RequestParam(name = "sn") String sn,
										@RequestParam(name = "uid") String uid,
										@RequestParam(name = "mail") String mail,
										@RequestParam(name = "givenName") String givenName,
										@RequestParam(name = "ou") String ou,
										@RequestParam(name = "userPassword") String userPassworld);

	@RequestMapping(value = "/userSync/delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public AjaxResponse<Object> delete(@RequestParam(name = "cn") String cn);

	@RequestMapping(value = "/userSync/changePassword", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public AjaxResponse<Object> changePassword(@RequestParam(name = "cn") String cn,
											   @RequestParam(name = "oldPassword") String oldPassword,
											   @RequestParam(name = "newPassword") String newPassword);



}
