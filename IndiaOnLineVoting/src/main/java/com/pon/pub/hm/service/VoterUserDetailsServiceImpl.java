/**
 * 
 */
package com.pon.pub.hm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pon.pub.hm.entity.OnlineVoterAccount;
import com.pon.pub.hm.repo.OnlineVoterAccountRepository;

/**
 * @author Sanjeev
 *
 */
@Service
public class VoterUserDetailsServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(VoterUserDetailsServiceImpl.class);
	@Autowired
	private OnlineVoterAccountRepository onlineVoterAccountRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

		UserBuilder builder = null;
		try {
			OnlineVoterAccount onlineVoterAccount = onlineVoterAccountRepository.findByMailId(email);
			CustomVoterUserDetailsImpl customVoterUserDetailsImpl = new CustomVoterUserDetailsImpl();

			//log.info("Sanjeev = " + new BCryptPasswordEncoder().encode(onlineVoterAccount.getPwd()));
			if (onlineVoterAccount != null || email!=null) {
				builder = org.springframework.security.core.userdetails.User.withUsername(email);
				builder.password(onlineVoterAccount.getPwd());
				builder.authorities(customVoterUserDetailsImpl.getAuthorities(onlineVoterAccount));
			}else {
				 throw new UsernameNotFoundException("No Voter is found");
			}
		} catch (Exception ex) {
			;//ex.printStackTrace(); 
		}
		return builder.build();
	}
}
