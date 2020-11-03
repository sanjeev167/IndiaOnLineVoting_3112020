/**
 * 
 */
package com.pon.pvt.master.ctrl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pon.pvt.master.dto.LoksabhaCandidateMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.service.LoksabhaCandidateService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */

@Controller
@RequestMapping(value = "/pvt/master/Lcandidate/")
public class LoksabhaCandidateMasterController {
	static final Logger log = LoggerFactory.getLogger(LoksabhaCandidateMasterController.class);
	@Autowired
	LoksabhaCandidateService loksabhaCandidateService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listLcandidate(Model model) {
		log.info("LoksabhaCandidateMasterController :==> listLcandidate :==> Started");
		String target = "/master/lcandidate_master";
		log.info("LoksabhaCandidateMasterController :==> listLcandidate :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listLcandidatePaginated(HttpServletRequest request, HttpServletResponse response, String stateNameId,
			String loksabhaNameId,String lcandidateName){
		log.info("LoksabhaCandidateMasterController :==> listLcandidatePaginated :==> Started");
		DataTableResults<LoksabhaCandidateMasterDTO> dataTableResults=null;
		try {
			String whereClauseForBaseQuery = "";

			if (stateNameId!=null&& !stateNameId.isEmpty() && !stateNameId.equals(""))
				whereClauseForBaseQuery =  " and lm.state_id=" + Integer.parseInt(stateNameId);
			if (loksabhaNameId!=null&& !loksabhaNameId.isEmpty() && !loksabhaNameId.equals(""))
				whereClauseForBaseQuery =whereClauseForBaseQuery+  " and lcm.loksabha_id=" + Integer.parseInt(loksabhaNameId);
			if (lcandidateName!=null&& !lcandidateName.isEmpty() && !lcandidateName.equals(""))
				whereClauseForBaseQuery = whereClauseForBaseQuery+  " and  lcm.name Like % '" +lcandidateName +"'";
			
			
			

			dataTableResults=loksabhaCandidateService.loadGrid(request, whereClauseForBaseQuery);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("LoksabhaCandidateMasterController :==> listLcandidatePaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}

	
	//@PreAuthorize("@methodSecurityService.hasAccess(#num)")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response){
		log.info("LoksabhaCandidateMasterController :==> getRecord :==> Started");
		
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setFormObject(loksabhaCandidateService.getReordById(id));
			jsonResponse.setStatus(true);
			jsonResponse.setStatusMsg("Record found.");
		}catch (CustomRuntimeException ex) {
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
		log.info("LoksabhaCandidateMasterController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "saveAndUpdateRecord", method = RequestMethod.POST)
	@ResponseBody
	public String saveAndUpdateRecord(@RequestBody @Valid LoksabhaCandidateMasterDTO loksabhaCandidateMasterDTO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		
		log.info("LoksabhaCandidateMasterController :==> saveAndUpdateRecord :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			if (result.hasErrors()) {
				// Get error message
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				
				System.out.println(errors);
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg("Error found");
				jsonResponse.setFieldErrMsgMap(errors);
			} else {
				// Implement business logic to save record into database
				jsonResponse.setFormObject(loksabhaCandidateService.saveAndUpdate(loksabhaCandidateMasterDTO));
				jsonResponse.setStatus(true);
				jsonResponse.setStatusMsg("Record has been saved or updated successfully.");
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
		log.info("LoksabhaCandidateMasterController :==> saveAndUpdateRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteRecord", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("LoksabhaCandidateMasterController :==> deleteRecord :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setStatus(loksabhaCandidateService.deleteOneRecord(id));
			jsonResponse.setStatusMsg("Record has been deleted successfully.");			
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
		log.info("LoksabhaCandidateMasterController :==> deleteRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteSelected", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelected(@RequestBody Integer[] recordIdArray, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("LoksabhaCandidateMasterController :==> deleteSelected :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {			
			jsonResponse.setStatus(loksabhaCandidateService.deleteMultipleRecords(recordIdArray));
			jsonResponse.setStatusMsg("All selected records have been deleted.");
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
		log.info("LoksabhaCandidateMasterController :==> deleteSelected :==> End");
		return new Gson().toJson(jsonResponse);

	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody public String lcandidateList(@RequestParam Integer id,HttpServletRequest request,	HttpServletResponse response) {
		log.info("LoksabhaCandidateMasterController :==> lcandidateList :==> Start");
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<NameValue> stateList=loksabhaCandidateService.getLoksabhaCandidateList(id);	
			jsonResponse.setComboList(stateList);			 
			jsonResponse.setStatus(true);
			jsonResponse.setStatusMsg("All records have been fetched.");
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
		log.info("LoksabhaCandidateMasterController :==> lcandidateList :==> End");
		return new Gson().toJson(jsonResponse);
	}
}
