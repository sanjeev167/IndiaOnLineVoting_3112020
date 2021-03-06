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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pon.pub.hm.dto.LoginDTO;
import com.pon.pvt.master.service.VotersEnrolledService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;

/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pub/hm/login/")
public class LoginDetailsCheckController {
	static final Logger log = LoggerFactory.getLogger(LoginDetailsCheckController.class);
	
	
	@Autowired
	VotersEnrolledService votersEnrolledService;
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String loginFieldsCheck(@RequestBody @Valid LoginDTO loginDTO, BindingResult result,HttpServletRequest request, HttpServletResponse response) {
		log.info("LoginDetailsCheckController :==> loginFieldsCheck :==> Started");
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
				//Check the supplied name is matching against the name associated with voter id
				if(!votersEnrolledService.findByVoterName(loginDTO))
				{
					jsonResponse.setStatus(false);//Form has no error
					jsonResponse.setStatusMsg("Voter with this name is not associated with the voterId that has been verified");
				}else {
				jsonResponse.setStatus(true);//Form has no error
				jsonResponse.setStatusMsg("Login Details acceptable.");
				}
			}
		}  catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
			jsonResponse.setStatus(false);
			jsonResponse.setStatusMsg(exceptionCause);
		}
		log.info("LoginDetailsCheckController :==> loginFieldsCheck :==> End");
		return new Gson().toJson(jsonResponse);
	}
}
