package com.pooyabyte.training.service.serviceImpl;

import com.pooyabyte.training.domain.User;
import com.pooyabyte.training.repository.UserRepository;
import com.pooyabyte.training.util.UserPrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

@Autowired
private UserRepository userRepository;


@Override
public UserDetails loadUserByUsername(String username) {
	User user = userRepository.findByUsername(username);
	if (user == null) {
		throw new UsernameNotFoundException(username);
	}
	
	return new UserPrincipalUtil(user);
}
}