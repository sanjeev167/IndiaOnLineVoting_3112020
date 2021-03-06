/**
 * 
 */
package com.pon.config.filter;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.pon.config.listener.SessionCounterListener;
import com.pon.pub.hm.ctrl.HomeController;
import com.pon.pvt.adm.ctrl.DashboardController;
import com.support.util.FileDao;

/**
 * @author Sanjeev
 *
 */
@WebFilter("/")

public class HitCounterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		RequestMatcher ignoredRequests=new AntPathRequestMatcher("/*");
		
		ServletContext context = request.getServletContext();
		String realWebAppPath = context.getRealPath("");
		HttpServletRequest requestNew = (HttpServletRequest) request;
		if (requestNew.getRequestURI().equals("/")) {
			int hitCount=getUpdatedHitCount(SessionCounterListener.session,realWebAppPath);
			 DashboardController.hitCount=hitCount;
		     request.setAttribute("hitCount", ""+hitCount);
		    
		}
		
		
		if(ignoredRequests.matches(requestNew))			
		      new DelegateRequestMatchingFilter().doFilter(request,response,chain);
		else {
			System.out.println("Before with = "+requestNew.getRequestURI());
			chain.doFilter(request, response);
			System.out.println("After with = "+requestNew.getRequestURI());
		}
		
	}
	
	
	
	private int getUpdatedHitCount(HttpSession session,String realWebAppPath) throws IOException {
		Integer currentHitUpdated=0;		
			String fileWithPath = realWebAppPath.concat("hit.txt");
			int currentHit = FileDao.readHitCounterFromFile(fileWithPath);			   
			if(session!=null && session.isNew()) {
				FileDao.updateHitCounterFile(++currentHit, fileWithPath);
				
			}	
			if(session==null) {
				FileDao.updateHitCounterFile(++currentHit, fileWithPath);				
			}	
				
			currentHitUpdated = FileDao.readHitCounterFromFile(fileWithPath);						
			
		return currentHitUpdated;
	}
}
