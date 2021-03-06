/**
 * 
 */
package com.pon.pub.hm.ctrl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("")
public class LoginController {

	static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/pub/voter/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error,
			@RequestParam(value = "logout", required = false) boolean logout,
			@RequestParam(value = "authenticate", required = false) boolean authenticate,
			@RequestParam(value = "invalid", required = false) boolean invalid,
			@RequestParam(value = "expired", required = false) boolean expired, Model model) {

		if (error)
			model.addAttribute("errorMessge", "Either Username or Password is incorrect !!");
		if (logout)
			model.addAttribute("logout", "You have been logged out successfully !!");
		if (authenticate)
			model.addAttribute("authenticate", "First authenticate yourself !!");
		if (expired)
			model.addAttribute("expired", "Your current session has expired !!");
		if (invalid)
			model.addAttribute("invalid", "Your session is invalid !!");

		return "login";
	}

	// After a successful login the control can be transfered anywhere as per
	// business need.
	@RequestMapping(value = "/pvt/voter/postLogin", method = RequestMethod.GET)
	public RedirectView  postLogin(Model model, HttpSession session,HttpServletRequest request) {

		log.info("postLogin()");
		String target="/pvt/voter/db";
		RedirectView redirectView = new RedirectView();     
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println("San = "+auth.getAuthorities().contains("ROLE_ADMIN"));
		System.out.println("Man ="+auth.getAuthorities());
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {			
			target= "/pvt/adm/db"; 			 
		}
		

		// User loggedInUser = ((PdfUserDetails)
		// authentication.getPrincipal()).getUserDetails();
		// model.addAttribute("currentUser", loggedInUser.getUsername());
		// session.setAttribute("userId", loggedInUser.getId());
		// return "redirect:/wallPage";
		redirectView.setUrl(target);
		
		return redirectView;
	}
	
	
	

	@RequestMapping(value = "/pub/voter/fgotpwd", method = RequestMethod.GET)
	public String fgotpwd(Model model) {
		log.info("fgotpwd()");
		return "fgotpwd";
	}

	@RequestMapping(value = "/pub/voter/fgotpwd", method = RequestMethod.POST)
	public String sendFgotpwd(Model model) {
		log.info("fgotpwd()");
		return "fgotpwd";
	}

	private void validatePrinciple(Object principal) {

		// if (!(principal instanceof PdfUserDetails)) {
		// throw new IllegalArgumentException("Principal can not be null!");
		// }
	}

}
