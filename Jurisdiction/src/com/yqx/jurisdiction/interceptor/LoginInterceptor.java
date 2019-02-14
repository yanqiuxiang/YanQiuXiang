package com.yqx.jurisdiction.interceptor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView; 

@SuppressWarnings("all")
public class LoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
	
			HttpSession session=request.getSession();
			List<String> str = (List<String>) session.getAttribute("str");
			
			if(null!=str && str.size()>0){
				
				String servletPath=request.getServletPath();
				String splServletPath = servletPath.substring(servletPath.indexOf("/")+1, servletPath.lastIndexOf("/"));
				if(!str.contains(splServletPath)){
					
					request.getRequestDispatcher("/accessRight").forward(request, response);
				}
			}
			
		return true;
	} 

}