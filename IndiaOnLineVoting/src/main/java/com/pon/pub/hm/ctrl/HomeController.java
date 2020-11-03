/**
 * 
 */
package com.pon.pub.hm.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * @author Sanjeev
 *
 */

@Controller

public class HomeController {
	
	
	@GetMapping({"/","home"})
	public String welcome(Model model,HttpServletRequest request, HttpServletResponse response) {
		
		return "home";
		
		
	}

}
