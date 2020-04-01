package com.softserveinc.edu.oms.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.softserveinc.edu.oms.domain.entities.User;

/**
 * @author Orest
 * 
 */
public class UserSecurityData implements UserDetails {

	/**
	 * default serial UID.
	 */
	private static final long serialVersionUID = -7731359760705689256L;

	private final User user;

	public UserSecurityData(final User user) {
		this.user = user;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new GrantedAuthorityImpl("ROLE_"
				+ user.getRole().getRoleName()));

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLogin();
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

	/**
	 * @return
	 */
	public User getUser() {
		return user;
	}
}
