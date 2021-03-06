/**
 * 
 */
package com.pon.pub.hm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pon.pub.hm.entity.OnlineVoterAccount;
/**
 * @author Sanjeev
 *
 */
public class CustomVoterUserDetailsImpl extends OnlineVoterAccount implements UserDetails {

	private static final long serialVersionUID = 1L;

	
	//@Override
	public Collection<? extends GrantedAuthority> getAuthorities(OnlineVoterAccount onlineVoterAccount) {

		List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<SimpleGrantedAuthority>();
		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + "VOTER");
		simpleGrantedAuthorityList.add(simpleGrantedAuthority);
		return simpleGrantedAuthorityList;
	}

	@Override
	public String getPassword() {
		return super.getPwd();
	}

	@Override
	public String getUsername() {
		return super.getName();
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

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}