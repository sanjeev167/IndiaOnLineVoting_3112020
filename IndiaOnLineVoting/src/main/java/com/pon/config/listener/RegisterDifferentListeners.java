/**
 * 
 */
package com.pon.config.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;



/**
 * @author Sanjeev
 *
 */
@Configuration
public class RegisterDifferentListeners {

	// Register SessionCounterListener
		@Bean
		public ServletListenerRegistrationBean<SessionCounterListener> sessionCountListener() {
			
			ServletListenerRegistrationBean<SessionCounterListener> listenerRegBean = new ServletListenerRegistrationBean<>();
			
			listenerRegBean.setListener(new SessionCounterListener());
			
			return listenerRegBean;
		}

	
		
		
}
