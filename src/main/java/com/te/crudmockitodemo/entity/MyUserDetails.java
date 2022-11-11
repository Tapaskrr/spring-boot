package com.te.crudmockitodemo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private Authority authority;
	
	public MyUserDetails(Authority authority) {
		this.authority = authority;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.authority.getRole());
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return authority.getPwd();
	}

	@Override
	public String getUsername() {
		return authority.getName();
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
		return true;
	}

}
