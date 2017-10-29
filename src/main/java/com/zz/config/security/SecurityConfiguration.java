package com.zz.config.security;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecuritySettings settings;
	
	@Autowired @Qualifier("dataSource")
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())
		.and().authorizeRequests()
		.antMatchers("/images/**","/checkcode","/js/**","/css/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
		.and().logout().logoutSuccessUrl(settings.getLogoutsuccessurl())
		.and().exceptionHandling().accessDeniedPage(settings.getDeniedpage())
		.and().rememberMe().tokenValiditySeconds(86400).tokenRepository(tokenRepository());
	}
	
	private CsrfSecurityRequestMatcher csrfSecurityRequestMatcher(){
		CsrfSecurityRequestMatcher csrfSecurityRequestMatcher = new CsrfSecurityRequestMatcher();
		List<String> list = new ArrayList<String>(); 
		list.add("/api/");
		csrfSecurityRequestMatcher.setExecludeUrls(list);
		return csrfSecurityRequestMatcher;
	}
	
	@Bean
	public LoginSuccessHandler loginSuccessHandler(){
		return new LoginSuccessHandler();
	}
	
	@Bean
	public JdbcTokenRepositoryImpl tokenRepository(){
		JdbcTokenRepositoryImpl jtr = new JdbcTokenRepositoryImpl();
		jtr.setDataSource(dataSource);
		return jtr;
	}

}
