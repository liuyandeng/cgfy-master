/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cgfy.oauth.base.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Dave Syer
 * 
 */
public class VerificationCodeTokenGranter extends AbstractTokenGranter {

	private static final String GRANT_TYPE = "v_code";

	private final AuthenticationManager authenticationManager;

	public VerificationCodeTokenGranter(AuthenticationManager authenticationManager,
                                        AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
		this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
	}

	protected VerificationCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                                           ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
		super(tokenServices, clientDetailsService, requestFactory, grantType);
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

		Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
		String username = parameters.get("username");
		String password = parameters.get("password");
		String v_code = parameters.get("v_code");
		// Protect from downstream leaks of password
		System.out.println("username="+username);
		System.out.println("password="+password);
		System.out.println("v_code="+v_code);
		System.out.println("uuid="+parameters.get("uuid"));
		
		
		parameters.remove("password");

		Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);
//		try {
//			userAuth = authenticationManager.authenticate(userAuth);
//		}
//		catch (AccountStatusException ase) {
//			//covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
//			throw new InvalidGrantException(ase.getMessage());
//		}
//		catch (BadCredentialsException e) {
//			// If the username/password are wrong the spec says we should send 400/invalid grant
//			throw new InvalidGrantException(e.getMessage());
//		}
//		if (userAuth == null || !userAuth.isAuthenticated()) {
//			throw new InvalidGrantException("Could not authenticate user: " + username);
//		}
		
		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
		return new OAuth2Authentication(storedOAuth2Request, userAuth);
	}
}
