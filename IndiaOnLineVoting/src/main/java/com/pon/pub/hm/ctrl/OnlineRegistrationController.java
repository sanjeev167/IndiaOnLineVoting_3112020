/**
 * 
 */
package com.pon.pub.hm.ctrl;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pon.pub.hm.dto.RegistrationDTO;
import com.pon.pub.hm.service.RegistrationService;
import com.pon.pub.hm.service.VoterIdentificationService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;

/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pub/hm/reg/")
public class OnlineRegistrationController {

static final Logger log = LoggerFactory.getLogger(OnlineRegistrationController.class);
	
	@Autowired
	VoterIdentificationService voterIdentificationService;
	
	@Autowired
	RegistrationService registrationService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String regGet(RegistrationDTO registrationDTO,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineRegistrationController :==> regGet :==> Started");
		String target = "/register";
		log.info("OnlineRegistrationController :==> regGet :==> End");
		return target;
	}
	
	@RequestMapping(value = "voterIdVerification", method = RequestMethod.POST)
	@ResponseBody
	public String voterIdVerification(@RequestParam String voterId,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineRegistrationController :==> voterIdVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		try {
			String status[]=voterIdentificationService.verifyVoterId(voterId);					
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
		log.info("OnlineRegistrationController :==> voterIdVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	@RequestMapping(value = "aadharIdVerification", method = RequestMethod.POST)
	@ResponseBody
	public String aadharIdVerification(@RequestParam String aadharId,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineRegistrationController :==> aadharIdVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		try {		
			String status[]=voterIdentificationService.verifyAadharId(aadharId);
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
		log.info("OnlineRegistrationController :==> aadharIdVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	@RequestMapping(value = "aadharOTPVerification", method = RequestMethod.POST)
	@ResponseBody
	public String aadharOTPVerification(@RequestParam String aadharOtp,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineRegistrationController :==> aadharOTPVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();	
		try {	
			String status[]=voterIdentificationService.verifyAadharOTP(aadharOtp);		
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
		log.info("OnlineRegistrationController :==> aadharOTPVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	@RequestMapping(value = "thumbImpressionVerification", method = RequestMethod.POST)
	@ResponseBody
	public String thumbImpressionVerification(@RequestParam String impression,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineRegistrationController :==> aadharOTPVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();	
		try {	
			String status[]=voterIdentificationService.thumbImpressionVerification(impression);		
			if(status[0].equals("1")) {	
				jsonResponse.setFormObject(status[2]);
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
		log.info("OnlineRegistrationController :==> aadharOTPVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	@RequestMapping(value = "mobileVerification", method = RequestMethod.POST)
	@ResponseBody
	public String mobileVerification(@RequestParam String mobileNo,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineRegistrationController :==> mobileVerification :==> Started");
		JsonResponse jsonResponse=new JsonResponse();	
		try {	
			String status[]=voterIdentificationService.verifyMobileNo(mobileNo);		
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
		log.info("OnlineRegistrationController :==> mobileVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	@RequestMapping(value = "mobileOtpVerification", method = RequestMethod.POST)
	@ResponseBody
	public String mobileOtpVerification(@RequestParam String mobileOtp,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineRegistrationController :==> mobileOtpVerification :==> Started");
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
		log.info("OnlineRegistrationController :==> mobileOtpVerification :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	
	
	//String ipAddress = request.getRemoteAddr();
	@RequestMapping(value = "onlineRegisteration", method = RequestMethod.POST)
	@ResponseBody
	public String onlineRegister(@RequestBody @Valid RegistrationDTO registrationDTO, BindingResult result,HttpServletRequest request, HttpServletResponse response) {
		log.info("OnlineRegistrationController :==> onlineRegister :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			if (result.hasErrors()) {
				// Get error message
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				
				//System.out.println(errors);
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg("Error found");
				jsonResponse.setFieldErrMsgMap(errors);
			} else {
				registrationDTO.setVoterRequestIpAddress(request.getRemoteAddr());
				registrationService.saveAndUpdate(registrationDTO);
				jsonResponse.setStatus(true);//Form has no error
				jsonResponse.setStatusMsg("You have been successfully registered for online voting."
						+ " <br><strong style='color:black;'>Please click the link received at your mail to activate your account.</strong> "
						+ "<Strong><br> Now, <span style='color:red;'>you can lock your vote for online voting.</span></strong>");
			}
		}  catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		}
		log.info("LoginController :==> onlineRegister :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
}
