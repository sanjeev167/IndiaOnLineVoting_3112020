/**
 * 
 */
package com.pon.pub.hm.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.pon.config.filter.HitCounterFilter;
import com.pon.config.sec.ActiveUserStore;
import com.pon.pub.hm.component.HitCountComponent;
import com.pon.pvt.master.service.SensetivePagelinkService;
import com.pon.pvt.master.service.VotersEnrolledService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;



/**
 * @author Sanjeev
 *
 */

@Controller
public class HomeController {
	static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	public static int currentSessionCount; 
	
	@Autowired
	VotersEnrolledService votersEnrolledService;	
	@Autowired
    ActiveUserStore activeUserStore;
	
	@Autowired
	SensetivePagelinkService sensetivePagelinkService;
	
	@GetMapping({"/","/home"})
	public String welcome(Model model,HttpServletRequest request, HttpServletResponse response) {
		
		try {			
			model.addAttribute("totalRegisteredVoters",votersEnrolledService.findTotalRegisteredVoters());
			model.addAttribute("totalOfflineVoters",votersEnrolledService.findTotalOfflineVoters());
			model.addAttribute("totalOnlineVoters",votersEnrolledService.findTotalOnlineVoters());
			model.addAttribute("onLineUsers",activeUserStore.getUsers().size());				
			model.addAttribute("liveSessionCount",currentSessionCount);
			response.setHeader("hitHomePage", "1");			
		} catch (CustomRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "home";		
	}
	
	@RequestMapping(value = "/loadMarque", method = RequestMethod.GET)
	@ResponseBody
	public String loadMarque(HttpServletRequest request, HttpServletResponse response) {
		log.info("HomeController :==> loadMarque :==> Started");
		JsonResponse jsonResponse=new JsonResponse();		
		try {
			List<String>marqueeList=sensetivePagelinkService.getActiveMarqueList();			
				jsonResponse.setStatus(true);
				jsonResponse.setStatusMsg("Marquee found");								
				jsonResponse.setMarqueeList(marqueeList);	
		} /*catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		}*/ catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		} 
		log.info("HomeController :==> loadMarque :==> End");
		return new Gson().toJson(jsonResponse);
	}

}
