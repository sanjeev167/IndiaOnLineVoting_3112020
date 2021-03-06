/**
 * 
 */
package com.pon.pub.hm.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pon.pub.hm.service.VoterIdentificationService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;

/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pvt/online/voter/")
public class OnlineVotingVerificationController {
	static final Logger log = LoggerFactory.getLogger(OnlineVotingVerificationController.class);
	@Autowired
	VoterIdentificationService voterIdentificationService;

	
	@RequestMapping(value = "voterIdVerification", method = RequestMethod.POST)
	@ResponseBody
	public String voterIdVerification(@RequestParam String voteVoterId,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineVotingVerificationController :==> voterIdVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentLoggedInUser;
		if (principal instanceof UserDetails) {
			currentLoggedInUser = ((UserDetails)principal).getUsername();
		} else {
			currentLoggedInUser = principal.toString();
		}
		
		try {
			String status[]=voterIdentificationService.verifyOnlineVoterId(voteVoterId);			
			if(status[0].equals("1")) {				
				String lockStatus[]=voterIdentificationService.hasHeLockedHisVoteForOnline(voteVoterId,currentLoggedInUser);				
				if(lockStatus[0].equals("1")) {
				//Check whether he is already casted his vote or not					
				String statusCast[]=voterIdentificationService.checkWhetherHeIsAlreadyCastedVoteOnline(voteVoterId);				
				if(statusCast[0].equals("0")) {//Already casted his vote
					jsonResponse.setStatus(false);
					jsonResponse.setStatusMsg(statusCast[1]);
				}else {//Can cast his vote					
					jsonResponse.setStatus(true);
					jsonResponse.setStatusMsg(statusCast[1]);	
				}
			}else {
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg(lockStatus[1]);
			}								
			}else {
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg(status[1]);								
			}			
		} catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		} 
		log.info("OnlineVotingVerificationController :==> voterIdVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	@RequestMapping(value = "secretIdVerification", method = RequestMethod.POST)
	@ResponseBody
	public String secretIdVerification(@RequestParam String secretId,@RequestParam String voterId,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineVotingVerificationController :==> secretIdVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		
		try {
			String status[]=voterIdentificationService.checkSecretOfOnlineVoter(secretId,voterId);	
			
			if(status[0].equals("1")) {			
				jsonResponse.setStatus(true);
				jsonResponse.setStatusMsg(status[1]);				
			}else {
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg(status[1]);								
			}			
		} catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		} 
		log.info("OnlineVotingVerificationController :==> secretIdVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	
	//votingMobileOtpVerification
	@RequestMapping(value = "mobileOtpVerification", method = RequestMethod.POST)
	@ResponseBody
	public String mobileOtpVerification(@RequestParam String mobileOtp,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineVotingVerificationController :==> mobileOtpVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();	
		try {	
			String status[]=voterIdentificationService.verifyMobileOtpNo(mobileOtp);		
			if(status[0].equals("1")) {				
				jsonResponse.setStatus(true);
				jsonResponse.setStatusMsg(status[1]);
			}else {
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg(status[1]);				
			}			
		} catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		}
		log.info("OnlineVotingVerificationController :==> mobileOtpVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	
	
}
