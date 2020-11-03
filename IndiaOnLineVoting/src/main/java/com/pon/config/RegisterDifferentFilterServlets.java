/**
 * 
 */
package com.pon.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pon.config.filter.CustomSiteMeshFilter;;
/**
 * @author Sanjeev
 *
 */

@Configuration
public class RegisterDifferentFilterServlets extends SpringBootServletInitializer {
	
	// Register CustomSiteMeshFilter.This filter will work on a request path starts
	// with "/*
	@Bean
	public FilterRegistrationBean<CustomSiteMeshFilter> CustomSiteMeshFilter() {
		FilterRegistrationBean<CustomSiteMeshFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(new CustomSiteMeshFilter());
		filterRegBean.addUrlPatterns("/*");	
		return filterRegBean;
	}

	

	
}
