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
					
				//Home page decorator
				.addDecoratorPath("/", "/WEB-INF/decorator/home_decorator.jsp")				
				.addDecoratorPath("/home", "/WEB-INF/decorator/home_decorator.jsp")	
				
				//Error page decorator
				.addDecoratorPath("/error/**", "/WEB-INF/decorator/home_decorator.jsp")	
				
				
				//Ec page decorator
				.addDecoratorPath("/pub/ec/**", "/WEB-INF/decorator/home_decorator.jsp")
				
				//Vote page decorator
				.addDecoratorPath("/pub/vote/**", "/WEB-INF/decorator/home_decorator.jsp")	
				.addDecoratorPath("/pvt/vote/**", "/WEB-INF/decorator/home_decorator.jsp")
				.addDecoratorPath("/pvt/vote/onlineV", "/WEB-INF/decorator/home_decorator.jsp")	
				
				//Voters' page
				.addDecoratorPath("/pvt/voter/**", "/WEB-INF/decorator/voter_decorator.jsp")
				.addDecoratorPath("/pub/voter/**", "/WEB-INF/decorator/home_decorator.jsp")
				
				//Admin' private page
				.addDecoratorPath("/pvt/adm/**", "/WEB-INF/decorator/admin_decorator.jsp")
				.addDecoratorPath("/pvt/master/**", "/WEB-INF/decorator/admin_decorator.jsp")		
				
				.create();			
				
		
	}// End of applyCustomConfiguration
	
	

}// End of CustomSiteMeshFilter