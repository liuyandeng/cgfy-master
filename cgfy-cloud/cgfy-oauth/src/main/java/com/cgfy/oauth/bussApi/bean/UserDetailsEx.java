package com.cgfy.oauth.bussApi.bean;

import com.cgfy.oauth.bussApi.domain.model.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsEx implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private UserInfo info = null;
	
	public UserDetailsEx(UserInfo info) {
		this.info = info;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	public String getId() {
        return info.getId();
    }

	@Override
	public String getPassword() {
		return info.getPassword();
	}

	@Override
	public String getUsername() {
		return info.getLoginName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return info.getStatus().equals("0");
	}

	public UserInfo getInfo() {
		return info;
	}

}
