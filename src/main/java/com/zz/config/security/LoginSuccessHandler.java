package com.zz.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.zz.model.User;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	protected Log log = LogFactory.getLog(getClass());
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		User userDetails = (User)authentication.getPrincipal();
		log.info("登录用户 user :" + userDetails.getName() + "login " +request.getContextPath());
		log.info("IP:" + request.getRemoteAddr());
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
