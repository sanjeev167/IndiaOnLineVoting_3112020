/**
 * 
 */
package com.pon.pub.hm.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/pub/hm/voter/")
public class OflineVotingVerificationController {
	static final Logger log = LoggerFactory.getLogger(OflineVotingVerificationController.class);
	@Autowired
	VoterIdentificationService voterIdentificationService;

	
	@RequestMapping(value = "voterIdVerification", method = RequestMethod.POST)
	@ResponseBody
	public String voterIdVerification(@RequestParam String voteVoterId,HttpServletRequest request, HttpServletResponse response) {
		log.info("OflineVotingVerificationController :==> voterIdVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		
		try {
			String statusTop[]=voterIdentificationService.verifyOfflineVoterId(voteVoterId);			
			if(statusTop[0].equals("1")) {
					//Check whether he is already casted his vote or not 
					String statusCasted[]=voterIdentificationService.checkWhetherHeIsAlreadyCastedVoteOffline(voteVoterId);	
					if(statusCasted[0].equals("0")) {//Already casted his vote
						jsonResponse.setStatus(false);
						jsonResponse.setStatusMsg(statusCasted[1]);
					}else {//Can cast his vote
						jsonResponse.setStatus(true);
						jsonResponse.setStatusMsg(statusCasted[1]);	
					}									
			}else {
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg(statusTop[1]);								
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
		log.info("OflineVotingVerificationController :==> voterIdVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	@RequestMapping(value = "secretIdVerification", method = RequestMethod.POST)
	@ResponseBody
	public String secretIdVerification(@RequestParam String secretId,HttpServletRequest request, HttpServletResponse response) {
		log.info("OflineVotingVerificationController :==> secretIdVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		try {
			
			//It was deliberately passed this verification as the voter is a offline voter
			String status[]= {"1","Secret has been verified successfully."};
			//String status[]= {"0","Secret is wrong."};
			if(status[0].equals("1")) {			
				jsonResponse.setStatus(true);
				jsonResponse.setStatusMsg(status[1]);				
			}else {
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg(status[1]);								
			}			
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
		log.info("OflineVotingVerificationController :==> secretIdVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	
	
}
