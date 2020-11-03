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
import com.pon.pvt.master.dto.AssemblyCandidateMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.service.AssemblyCandidateService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */

@Controller
@RequestMapping(value = "/pvt/master/Acandidate/")
public class AssemblyCandidateMasterController {
	static final Logger log = LoggerFactory.getLogger(AssemblyCandidateMasterController.class);
	@Autowired
	AssemblyCandidateService assemblyCandidateService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listLcandidate(Model model) {
		log.info("AssemblyCandidateMasterController :==> listLcandidate :==> Started");
		String target = "/master/acandidate_master";
		log.info("AssemblyCandidateMasterController :==> listLcandidate :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listLcandidatePaginated(HttpServletRequest request, HttpServletResponse response, String stateNameId,
			String loksabhaNameId,String lcandidateName){
		log.info("AssemblyCandidateMasterController :==> listLcandidatePaginated :==> Started");
		DataTableResults<AssemblyCandidateMasterDTO> dataTableResults=null;
		try {
			String whereClauseForBaseQuery = "";

			if (stateNameId!=null&& !stateNameId.isEmpty() && !stateNameId.equals(""))
				whereClauseForBaseQuery =  " and lm.state_id=" + Integer.parseInt(stateNameId);
			if (loksabhaNameId!=null&& !loksabhaNameId.isEmpty() && !loksabhaNameId.equals(""))
				whereClauseForBaseQuery =whereClauseForBaseQuery+  " and lcm.loksabha_id=" + Integer.parseInt(loksabhaNameId);
			if (lcandidateName!=null&& !lcandidateName.isEmpty() && !lcandidateName.equals(""))
				whereClauseForBaseQuery = whereClauseForBaseQuery+  " and  lcm.name Like % '" +lcandidateName +"'";
			
			
			

			dataTableResults=assemblyCandidateService.loadGrid(request, whereClauseForBaseQuery);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("AssemblyCandidateMasterController :==> listLcandidatePaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}

	
	//@PreAuthorize("@methodSecurityService.hasAccess(#num)")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response){
		log.info("AssemblyCandidateMasterController :==> getRecord :==> Started");
		
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setFormObject(assemblyCandidateService.getReordById(id));
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
		log.info("AssemblyCandidateMasterController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "saveAndUpdateRecord", method = RequestMethod.POST)
	@ResponseBody
	public String saveAndUpdateRecord(@RequestBody @Valid AssemblyCandidateMasterDTO assemblyCandidateMasterDTO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		
		log.info("AssemblyCandidateMasterController :==> saveAndUpdateRecord :==> Started");
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
				// Implement business logic to save record into database
				jsonResponse.setFormObject(assemblyCandidateService.saveAndUpdate(assemblyCandidateMasterDTO));
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
		log.info("AssemblyCandidateMasterController :==> saveAndUpdateRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteRecord", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("AssemblyCandidateMasterController :==> deleteRecord :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setStatus(assemblyCandidateService.deleteOneRecord(id));
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
		log.info("AssemblyCandidateMasterController :==> deleteRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteSelected", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelected(@RequestBody Integer[] recordIdArray, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("AssemblyCandidateMasterController :==> deleteSelected :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {			
			jsonResponse.setStatus(assemblyCandidateService.deleteMultipleRecords(recordIdArray));
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
		log.info("AssemblyCandidateMasterController :==> deleteSelected :==> End");
		return new Gson().toJson(jsonResponse);

	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody public String lcandidateList(@RequestParam Integer id,HttpServletRequest request,	HttpServletResponse response) {
		log.info("AssemblyCandidateMasterController :==> lcandidateList :==> Start");
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<NameValue> stateList=assemblyCandidateService.getAssemblyCandidateList(id);	
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
		log.info("AssemblyCandidateMasterController :==> lcandidateList :==> End");
		return new Gson().toJson(jsonResponse);
	}
}
