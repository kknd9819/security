package com.zz.config.security;



import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher{
	
	protected Log log = LogFactory.getLog(getClass());
	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
	
	/**
	 * 需要排除的url列表
	 */
	private List<String> execludeUrls;

	@Override
	public boolean matches(HttpServletRequest request) {
		if(execludeUrls != null && execludeUrls.size() > 0){
			String servletPath = request.getServletPath();
			for(String url:execludeUrls){
				if(servletPath.contains(url)){
					log.info("++++" + servletPath);
					return false;
				}
			}
		}
		return !allowedMethods.matcher(request.getMethod()).matches();
	}

	public Pattern getAllowedMethods() {
		return allowedMethods;
	}

	public void setAllowedMethods(Pattern allowedMethods) {
		this.allowedMethods = allowedMethods;
	}

	public List<String> getExecludeUrls() {
		return execludeUrls;
	}

	public void setExecludeUrls(List<String> execludeUrls) {
		this.execludeUrls = execludeUrls;
	}
	
	

}
