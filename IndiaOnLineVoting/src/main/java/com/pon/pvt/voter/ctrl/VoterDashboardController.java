/**
 * 
 */
package com.pon.pvt.voter.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pvt/voter/")
public class VoterDashboardController {
	@GetMapping("db")	
	public String listVoters(Model model,HttpServletRequest request, HttpServletResponse response) {	
		String target="/voter/db";
		
		return target;
	}
	
	@GetMapping("profile")	
	public String profile(Model model,HttpServletRequest request, HttpServletResponse response) {	
		String target="/voter/profile";
		
		return target;
	}
}
