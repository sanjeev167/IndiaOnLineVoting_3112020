/**
 * 
 */
package com.pon.config.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.stereotype.Component;

/**
 * @author Sanjeev
 *
 */
@Component
public class CustomSiteMeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder
				// Exclude path from decoration.
				.addExcludedPath("/resources/*")
				//.setIncludeErrorPages(true)
				// Assigning default decorator if no path specific decorator found
				// .addDecoratorPath("/*.jsp", "/WEB-INF/decorator/no_decorator.jsp")
					
				.addDecoratorPath("/", "/WEB-INF/decorator/home_decorator.jsp")				
				.addDecoratorPath("/home", "/WEB-INF/decorator/home_decorator.jsp")	
				.addDecoratorPath("/pvt/vote/onlineV", "/WEB-INF/decorator/home_decorator.jsp")	
				
				.addDecoratorPath("/error/**", "/WEB-INF/decorator/home_decorator.jsp")	
				
				.addDecoratorPath("/pub/ec/**", "/WEB-INF/decorator/home_decorator.jsp")
				
				//.addDecoratorPath("/pub/hm/reg/**", "/WEB-INF/decorator/vote_decorator.jsp")
				.addDecoratorPath("/pub/voter/login**", "/WEB-INF/decorator/vote_decorator.jsp")
				.addDecoratorPath("/pub/voter/fgotpwd**", "/WEB-INF/decorator/vote_decorator.jsp")
				
				.addDecoratorPath("/pub/vote/**", "/WEB-INF/decorator/vote_decorator.jsp")
				.addDecoratorPath("/pvt/vote/**", "/WEB-INF/decorator/vote_decorator.jsp")
				.addDecoratorPath("/pvt/voter/**", "/WEB-INF/decorator/voter_decorator.jsp")
				
				.addDecoratorPath("/pvt/master/**", "/WEB-INF/decorator/admin_decorator.jsp")
				.addDecoratorPath("/pvt/adm/**", "/WEB-INF/decorator/admin_decorator.jsp")
				
				
				
				
				.create();			
				
		
	}// End of applyCustomConfiguration
	
	

}// End of CustomSiteMeshFilter