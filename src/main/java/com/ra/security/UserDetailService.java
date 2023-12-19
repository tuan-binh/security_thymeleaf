package com.ra.security;

import com.ra.model.Users;
import com.ra.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
		UserPrinciple userPrinciple = new UserPrinciple();
		userPrinciple.setUsers(users);
		userPrinciple.setAuthorities(users.getRoles().stream()
				  .map(item -> new SimpleGrantedAuthority(item.getName()))
				  .collect(Collectors.toSet()));
		return userPrinciple;
	}
}
