/**
 * 
 */
package com.pon.config.sec;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestParam;

import com.pon.pub.hm.service.VoterUserDetailsServiceImpl;

import com.pon.pub.hm.component.HitCountComponent;
/**
 * @author Sanjeev
 *
 */
@Configuration
@EnableWebSecurity(debug=false)//Requires for spring security
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true,jsr250Enabled = true)//Requires for method level security
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	static final Logger log= LoggerFactory.getLogger(SpringSecurityConfig.class);
	   
	
    @Autowired
    VoterUserDetailsServiceImpl DAO_VoterUserDetailsServiceImpl;    
	
	
    @Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	    return new MySimpleUrlAuthenticationSuccessHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public UserDetailsService InMemory_CustomUserDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("contactsanjiv@gmail.com").password(passwordEncoder().encode("pass"))
				.roles("ADMIN").build());		
		return manager;
	}	
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {   
		
		  auth.inMemoryAuthentication().withUser("contactsanjiv@gmail.com").password(passwordEncoder().encode("pass")).roles("ADMIN");//Through in memory
		 //need to tell the manager which password encoder has been used while saving the password
		  auth.userDetailsService(DAO_VoterUserDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}	

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/*.css","/*.js","/favicon.ico");
		web.ignoring().antMatchers("/", "/home",  "/error/**", "/pub/**", "/resources/**");
	}

	
	@Override
	public void configure(HttpSecurity http) throws Exception {		
		
		//http.requiresChannel().anyRequest().requiresSecure();//Redirect to https	   	
		http.csrf().disable();		
		
		//####### Url and access rights Configuration Section :Started ########	
		http.authorizeRequests().antMatchers("/pvt/voter/**","/pvt/vote/**").hasAnyRole("VOTER");		
		http.authorizeRequests().antMatchers("/pvt/adm/**").hasAnyRole("ADMIN");		
	    // .anyRequest().denyAll() 
	   //####### Url and access rights Configuration Section :Ended ########	
		
		
			
	 //####### Login Configuration Section : Started ####################
	   http			   
		  .formLogin()
		  .loginPage("/pub/voter/login")
		  .loginProcessingUrl("/doLogin")		
		  .successHandler(myAuthenticationSuccessHandler())		 	  
		  .failureUrl("/pub/voter/login?error=true") 
		  .permitAll()
     //####### Login Configuration Section : Ended ####################		     
		
		  
         //####### Logout Configuration Section : Started ############
		   .and()
			   .logout()
				   .logoutUrl("/perform_logout")	   
				   .logoutSuccessUrl("/pub/voter/login?logout=true")				                                     
				   .clearAuthentication(true)
				   .invalidateHttpSession(true)		   
				   .deleteCookies("JSESSIONID")
				   .permitAll()		  
		   //####### Logout Configuration Section : Ended #############	
				   
				   
		   //####### Exception handling : Started ####################		   
		    .and()
		             .exceptionHandling()
		             //.accessDeniedPage("")//No need to configure as the raised exception no is already being catched and handled        
		             
		             //Objective: If any unauthenticated users comes with the url /pvt/** , 
		             //it will redirect to the login page through this default entry point.		             
		     	    .defaultAuthenticationEntryPointFor(loginUrlAuthenticationEntryPoint(),new AntPathRequestMatcher("/pvt/**"))
		     	    
		    //####### Exception handling : Started ####################  	   
		   
		   
		  //####### Session time out configuration : Started #############
		   .and()
			   .sessionManagement()           
		           .invalidSessionUrl("/pub/voter/login?invalid=true")//This will be called when wrong session id comes in the cookie        
				   
		           .maximumSessions(1)
				   .maxSessionsPreventsLogin(false)//It will prevent a user to login concurrently if maximumSessions has reached its limit.
				                                  //While preventing it forcibly expires the session.
				                                  //Don't use true here otherwise it will keep throwing an error and will not allow expiredUrl to be called
				   .expiredUrl("/pub/voter/login?expired=true"); //This will be called when the user session is forcibly expired due to more concurrent login 
				                                        // than allowed quota.		
		   //####### Session time out configuration : Ended ###############	   
	}
	
	
    
	@Bean
	public AuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
		//This will take the user at login apge
		return new LoginUrlAuthenticationEntryPoint("/pub/voter/login?authenticate=true");
	}
	
	
	@Bean
	public ActiveUserStore activeUserStore(){
	    return new ActiveUserStore();
	}
}// End of SpringSecurityConfig
