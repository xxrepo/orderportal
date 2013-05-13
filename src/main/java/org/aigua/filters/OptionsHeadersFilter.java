package org.aigua.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// http://raibledesigns.com/rd/entry/implementing_ajax_authentication_using_jquery
// https://www.owasp.org/index.php/Main_Page
public class OptionsHeadersFilter implements Filter{
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
		
		HttpServletResponse response = (HttpServletResponse)res;
		response.setHeader("Access-Control-Allow-Origin", "http://" + req.getServerName());
		response.setHeader("Access-Control-Allow-Methods", "GET,PUT,DELETE,POST");
		response.setHeader("Access-Control-Max-Age", "360");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		chain.doFilter(req, res);
		
	}
	
	public void init(FilterConfig filterConfig){}
	
	public void destroy(){}
	
}