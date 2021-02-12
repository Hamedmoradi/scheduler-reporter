package com.pooyabyte.training.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
public static final Logger logger = LoggerFactory.getLogger(LoginEventListener.class);

@Override
public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
	
	UserDetails user = (UserDetails) event.getAuthentication().getPrincipal();
	//todo update
	
	logger.info("LOGIN name: " + user.getUsername());
}
}
