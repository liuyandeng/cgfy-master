package com.cgfy.oauth.base.config;

import com.cgfy.oauth.base.interceptor.CustomAuthPreInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	/**
	 * DB数据源
	 */
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Redis数据源
	 */
	@Autowired
	private RedisConnectionFactory redisConnection;
	
	
	/** 注入授权管理类 **/
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * 自定义UserDetailsService对象
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 *TokenStore接口的实现类
	 * InMemoryTokenStore
	 * JdbcTokenStore
	 * JwkTokenStore
	 * JwtTokenStore
	 * RedisTokenStore
	 * @return
	 */
	@Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnection);//改造RedisTokenStore
    }
	
	@Bean
	public CustomAuthPreInterceptor customAuthPreInterceptor() {
		return new CustomAuthPreInterceptor();
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()")
				.allowFormAuthenticationForClients();
	}
	
	/**
	 * Oauth的Client信息的存储方式->DB
	 * 
	 * @param clients 设定对象
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	/**
	 *
	 * @param endpoints
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.addInterceptor(customAuthPreInterceptor());
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.userDetailsService(userDetailsService);
//		ClientDetailsService clientDetails = endpoints.getClientDetailsService();
//		AuthorizationServerTokenServices tokenServices = endpoints.getTokenServices();
//		AuthorizationCodeServices authorizationCodeServices = endpoints.getAuthorizationCodeServices();
//		OAuth2RequestFactory requestFactory = endpoints.getOAuth2RequestFactory();
//		
//		List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
//		tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails,
//				requestFactory));
//		tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
//		ImplicitTokenGranter implicit = new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory);
//		tokenGranters.add(implicit);
//		tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
//		if (authenticationManager != null) {
//			tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices,
//					clientDetails, requestFactory));
//			
//			tokenGranters.add(new VerificationCodeTokenGranter(authenticationManager, tokenServices,
//					clientDetails, requestFactory));
//		}
//		
//		TokenGranter tokenGranter = new TokenGranter() {
//				private CompositeTokenGranter delegate;
//
//				@Override
//				public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
//					if (delegate == null) {
//						delegate = new CompositeTokenGranter(tokenGranters);
//					}
//					return delegate.grant(grantType, tokenRequest);
//				}
//			};
//			
//		endpoints.tokenGranter(tokenGranter);
	}

}
