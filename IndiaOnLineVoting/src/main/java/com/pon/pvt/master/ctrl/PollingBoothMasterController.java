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
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.PollingBoothMasterDTO;
import com.pon.pvt.master.service.PollingBoothService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */

@Controller
@RequestMapping(value = "/pvt/master/pollingBooth/")
public class PollingBoothMasterController {
	static final Logger log = LoggerFactory.getLogger(PollingBoothMasterController.class);
	@Autowired
	PollingBoothService pollingBoothService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listPollingBooths(Model model) {
		log.info("PollingBoothMasterController :==> listPollingBooths :==> Started");
		String target = "/master/pollingBooth_master";
		log.info("PollingBoothMasterController :==> listPollingBooths :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listPollingBoothsPaginated(HttpServletRequest request, HttpServletResponse response, String stateNameId,
			String loksabhaNameId,String assemblyNameId,String pollingBoothNameId){
		log.info("PollingBoothMasterController :==> listPollingBoothsPaginated :==> Started");
		
		
		DataTableResults<PollingBoothMasterDTO> dataTableResults=null;
		try {
			String whereClauseForBaseQuery = "";

			if (!stateNameId.isEmpty() && !stateNameId.equals("")&&loksabhaNameId.isEmpty() && loksabhaNameId.equals(""))
				whereClauseForBaseQuery =  " lm.state_id=" + Integer.parseInt(stateNameId);

			if (!loksabhaNameId.isEmpty() && !loksabhaNameId.equals("")&& stateNameId.isEmpty() && stateNameId.equals(""))
				whereClauseForBaseQuery =  " am.loksabha_id=" + Integer.parseInt(loksabhaNameId);

			if (!stateNameId.isEmpty() && !stateNameId.equals("")&& !loksabhaNameId.isEmpty() && !loksabhaNameId.equals(""))
				whereClauseForBaseQuery =  " lm.state_id=" + Integer.parseInt(stateNameId)+
				" and am.loksabha_id=" + Integer.parseInt(loksabhaNameId);

			dataTableResults=pollingBoothService.loadGrid(request, whereClauseForBaseQuery);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("PollingBoothMasterController :==> listPollingBoothsPaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}

	
	//@PreAuthorize("@methodSecurityService.hasAccess(#num)")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response){
		log.info("PollingBoothMasterController :==> getRecord :==> Started");
		
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setFormObject(pollingBoothService.getReordById(id));
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
		log.info("PollingBoothMasterController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "saveAndUpdateRecord", method = RequestMethod.POST)
	@ResponseBody
	public String saveAndUpdateRecord(@RequestBody @Valid PollingBoothMasterDTO pollingBoothMasterDTO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		
		log.info("PollingBoothMasterController :==> saveAndUpdateRecord :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			if (result.hasErrors()) {
				// Get error message
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				jsonResponse.setStatus(false);
				jsonResponse.setStatusMsg("Error found");
				jsonResponse.setFieldErrMsgMap(errors);
			} else {
				// Implement business logic to save record into database
				jsonResponse.setFormObject(pollingBoothService.saveAndUpdate(pollingBoothMasterDTO));
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
		log.info("PollingBoothMasterController :==> saveAndUpdateRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteRecord", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("PollingBoothMasterController :==> deleteRecord :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setStatus(pollingBoothService.deleteOneRecord(id));
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
		log.info("PollingBoothMasterController :==> deleteRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteSelected", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelected(@RequestBody Integer[] recordIdArray, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("PollingBoothMasterController :==> deleteSelected :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {			
			jsonResponse.setStatus(pollingBoothService.deleteMultipleRecords(recordIdArray));
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
		log.info("PollingBoothMasterController :==> deleteSelected :==> End");
		return new Gson().toJson(jsonResponse);

	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody public String pollingBoothList(@RequestParam Integer id,HttpServletRequest request,	HttpServletResponse response) {
		log.info("PollingBoothMasterController :==> pollingBoothList :==> Start");
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<NameValue> stateList=pollingBoothService.getPollingBoothList(id);	
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
		log.info("PollingBoothMasterController :==> pollingBoothList :==> End");
		return new Gson().toJson(jsonResponse);
	}
}
