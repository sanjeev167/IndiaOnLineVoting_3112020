/**
 * 
 */
package com.pon.pvt.adm.ctrl;

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
@RequestMapping("/pvt/adm/")
public class DashboardController {
	@GetMapping("db")	
	public String db(Model model,HttpServletRequest request, HttpServletResponse response) {	
		String target="/adm/db";
		
		return target;
	}
	
	@GetMapping("profile")	
	public String profile(Model model,HttpServletRequest request, HttpServletResponse response) {	
		String target="/adm/profile";
		
		return target;
	}
	
	@GetMapping("sensitiveLink")	
	public String sensitiveLink(Model model,HttpServletRequest request, HttpServletResponse response) {	
		String target="/adm/sensitiveLink";
		
		return target;
	}
	
	@GetMapping("voteLockAudit")	
	public String voteLockAudit(Model model,HttpServletRequest request, HttpServletResponse response) {	
		String target="/adm/voteLockAudit";
		
		return target;
	}
	@GetMapping("electionSchedule")	
	public String electionSchedule(Model model,HttpServletRequest request, HttpServletResponse response) {	
		String target="/adm/electionSchedule";
		
		return target;
	}
	
	
}