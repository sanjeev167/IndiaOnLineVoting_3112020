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

import com.support.JsonResponse.JsonResponse;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.StateMasterDTO;
import com.pon.pvt.master.service.StateService;

import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.google.gson.Gson;
import com.support.grid_pagination.DataTableResults;
/**
 * @author Sanjeev
 *
 */

@Controller
@RequestMapping(value = "/pvt/master/state/")
public class StateMasterController{

	static final Logger log = LoggerFactory.getLogger(StateMasterController.class);

	@Autowired
	StateService stateService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listStates(Model model) {
		log.info("StateMasterController :==> listStates :==> Started");
		String target = "/master/state_master";
		log.info("StateMasterController :==> listStates :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listStatesPaginated(HttpServletRequest request, HttpServletResponse response,
			String stateName,String countryName, String countryId){
		log.info("StateMasterController :==> listStatesPaginated :==> Started");
		String whereClauseForBaseQuery = "";
		DataTableResults<StateMasterDTO> dataTableResults=null;
		try {
			if (countryId!=null && !countryId.isEmpty()&&!countryId.equals(""))
				whereClauseForBaseQuery ="  sm.country_id=" + Integer.parseInt(countryId);			
			if (countryName!=null && !countryName.isEmpty()&&!countryName.equals(""))
				whereClauseForBaseQuery ="  sm.country_id.name Like %" + countryName;		
			if (stateName!=null && !stateName.isEmpty()&&!stateName.equals(""))
				whereClauseForBaseQuery ="  sm.name Like %" + stateName;
			
			dataTableResults = stateService.loadGrid(request, whereClauseForBaseQuery);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("StateMasterController :==> listStatesPaginated :==> Started");
		return new Gson().toJson(dataTableResults);
	}

	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("StateMasterController :==> getRecord :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			jsonResponse.setFormObject(stateService.getReordById(id));
			jsonResponse.setStatus(true);
			jsonResponse.setStatusMsg("Record found.");
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
		log.info("StateMasterController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "saveAndUpdateRecord", method = RequestMethod.POST)	
	@ResponseBody
	public String saveAndUpdateRecord(@RequestBody @Valid StateMasterDTO stateMasterDTO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("StateMasterController :==> saveAndUpdateRecord :==> Started");
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
				jsonResponse.setFormObject(stateService.saveAndUpdate(stateMasterDTO));
				jsonResponse.setStatus(true);
				jsonResponse.setStatusMsg("Record has been saved or updated successfully.");
			} 
		}
		catch (CustomRuntimeException ex) {
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
		log.info("StateMasterController :==> saveAndUpdateRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "deleteRecord", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("StateMasterController :==> deleteRecord :==> Start");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			jsonResponse.setStatus(stateService.deleteOneRecord(id));
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
		log.info("StateMasterController :==> deleteRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteSelected", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelected(@RequestBody Integer[] recordIdArray, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("StateMasterController :==> deleteSelected :==> Start");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			stateService.deleteMultipleRecords(recordIdArray);
			jsonResponse.setStatus(true);
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
		log.info("StateMasterController :==> deleteSelected :==> End");
		return new Gson().toJson(jsonResponse);


	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody public String stateList(HttpServletRequest request,	HttpServletResponse response) {
		log.info("StateMasterController :==> stateList :==> Start");
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<NameValue> stateList=stateService.getStateList();	
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
		log.info("StateMasterController :==> stateList :==> End");
		return new Gson().toJson(jsonResponse);
	}

}// End of StateMasterController
