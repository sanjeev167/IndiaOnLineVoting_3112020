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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestParam;

import com.pon.pub.hm.service.VoterUserDetailsServiceImpl;


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
	
	
	/**
	 * This will be used for encoding password
	 **/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * In-Memory user details
	 * **/
	@Bean
	public UserDetailsService InMemory_CustomUserDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("contactsanjiv@gmail.com").password(passwordEncoder().encode("pass"))
				.roles("ADMIN").build());		
		return manager;
	}	
	
	/**
	 * Here I am creating an authentication manager globally through
	 * AuthenticationManagerBuilder. It will authenticate the incoming user wherever
	 * the security is required.
	 **/
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {   
		
		  auth.inMemoryAuthentication()
         .withUser("contactsanjiv@gmail.com")
         .password(passwordEncoder().encode("pass"))
         .roles("ADMIN");//Through in memory
		 
		 auth.userDetailsService(DAO_VoterUserDetailsServiceImpl);//Through DAO
	}	

	/**
	 * We are telling spring to ignore these path from the security
	 **/
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/*.css","/*.js","/favicon.ico");
		web.ignoring().antMatchers("/", "/hm",  "/error/*", "/pub/*", "/resources/**");//Ignore from security
	}

	/**
	 * Now configure actual security here.
	 * @throws Exception 
	
	 * The control comes here just after authentication processing [required or not]
	 * is completed. Here, the access decision manger will examine whether this role
	 * has rights on this url or not. AccessDecissionManger receives an
	 * authentication object from AuthenticationManger.The authentication object has
	 * all the details viz. authentication status and all the assigned roles. They
	 * are loaded by the AuthenticationManager while authenticating the user. If the
	 * AccessDecissionManger finds that any role assigned to this user has access
	 * rights on this url, it allows him to go further into the application. Now,
	 * where is the url list and its associated role access? And who will tell the
	 * access Decision manger that this is the list that you can use it for checking
	 * the access rights?. So, we have to configure these urls pattern and access rights
	 * together somewhere.
	 * 
	 * SpringBoot has defined a process for configuring such urls and access rights together.
	 **/
	
	@Override
	public void configure(HttpSecurity http) throws Exception {		
		
		//http.requiresChannel().anyRequest().requiresSecure();//Redirect to https	   	
		http.csrf().disable();		
		
		//####### Url and access rights Configuration Section :Started ########	
		http.authorizeRequests().antMatchers("/pvt/voter/**","/pvt/vote/**").hasAnyRole("VOTER");
		
		http.authorizeRequests().antMatchers("/pvt/adm/**").hasAnyRole("ADMIN");
		
	    // .anyRequest().denyAll()//This will restrict all the request except the authorized role.You can mention some path through antMmatcher  
	   //####### Url and access rights Configuration Section :Ended ########			
		
		
		
	//####### Login Configuration Section : Started ####################
	   http			   
		  .formLogin()
		  .loginPage("/pub/voter/login")//For opening a custom login page, specify the url for opening this page.
		  .loginProcessingUrl("/doLogin")//An url for submitting login page. It will not be implemented.
		  .defaultSuccessUrl("/pvt/voter/postLogin")//Will transfer the control on this url after successful login.This is post login
				   //.failureForwardUrl("/loginUrl")//Will transfer the control on this url once there is a login failure		  
		  .failureUrl("/pub/voter/login?error=true")//Here error is a param that will be checked at the login page for showing error message.
		  .permitAll()//Allowing everyone to access the login page.
		  //####### Login Configuration Section : Ended ####################		     
		
		  
		   //####### Logout Configuration Section : Started ############
		   .and()
			   .logout()
				   .logoutUrl("/perform_logout")//Will not be implemented but used as a logout url on the page.		   
				   .logoutSuccessUrl("/pub/voter/login?logout=true")//Transferring control at the login page with with a logout flag.logout is a param which will be used 
				                                       //for showing message
				   .clearAuthentication(true)//Will clear all the authentication details
				   .invalidateHttpSession(true)		   
				   .deleteCookies("JSESSIONID")
				   .permitAll()
				   
				  
		   //####### Logout Configuration Section : Ended #############	
				   
				   
		   //####### Exception handling : Started ####################		   
		    .and()
		             .exceptionHandling()
		             .accessDeniedPage("/403") //This will be used access denied exception will be raised.           
		             //Objective: If any unauthenticated users comes with the url /pvt/** , 
		             //it will redirect to the login page through this default entry point.
		             //User is not authenticated and coming with path matching with /pvt/admin/**.
		     	    .defaultAuthenticationEntryPointFor(loginUrlAuthenticationEntryPoint(),new AntPathRequestMatcher("/pvt/**"))
		     	    
		    //####### Exception handling : Started ####################   
		   
		   
		   
		   
		  //####### Session time out configuration : Started #############
		   .and()
			   .sessionManagement()           
		           .invalidSessionUrl("/pub/voter/login?invalid=true")//This will be called when wrong session id comes in the cookie        
				   
		           .maximumSessions(1)//Maximum current session has been set as 1
				   .maxSessionsPreventsLogin(false)//It will prevent a user to login concurrently if maximumSessions has reached its limit.
				                                  //While preventing it forcibly expires the session.
				                                  //Don't use true here otherwise it will keep throwing an error and will not allow expiredUrl to be called
				   .expiredUrl("/pub/voter/login?expired=true"); //This will be called when the user session is forcibly expired due to more concurrent login 
				                                        // than allowed quota.		
		   //####### Session time out configuration : Ended ###############
		
	   
	}
	
	
    //This will be used for redirecting to the login page when a user wants to access secured resources
	@Bean
	public AuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
		//This will take the user at login apge
		return new LoginUrlAuthenticationEntryPoint("/pub/voter/login?authenticate=true");
	}

	
	

}// End of SpringSecurityConfig
