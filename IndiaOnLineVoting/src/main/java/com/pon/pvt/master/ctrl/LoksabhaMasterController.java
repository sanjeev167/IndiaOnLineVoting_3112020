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
import com.pon.pvt.master.dto.LoksabhaMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.StateMasterDTO;
import com.pon.pvt.master.service.LoksabhaService;
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
@RequestMapping(value = "/pvt/master/loksabha/")
public class LoksabhaMasterController{

	static final Logger log = LoggerFactory.getLogger(LoksabhaMasterController.class);

	@Autowired
	LoksabhaService loksabhaService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listStates(Model model) {
		log.info("LoksabhaMasterController :==> listStates :==> Started");
		String target = "/master/loksabha_master";
		log.info("LoksabhaMasterController :==> listStates :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listLoksabhaPaginated(HttpServletRequest request, HttpServletResponse response,
			String loksabhaName,String stateName, String stateId,Integer loksabhaNo){
		
		
		log.info("LoksabhaMasterController :==> listLoksabhaPaginated :==> Started");
				
		String whereClauseForBaseQuery = "";
		DataTableResults<LoksabhaMasterDTO> dataTableResults=null;
		try {
			if (!stateId.isEmpty()&&!stateId.equals(""))
				whereClauseForBaseQuery ="  lm.state_id=" + Integer.parseInt(stateId);	
			if (!loksabhaName.isEmpty())
				whereClauseForBaseQuery ="  lm.name Like %" +loksabhaName +"%";	
			if (!stateId.isEmpty() && !loksabhaName.isEmpty())
				whereClauseForBaseQuery = "  lm.state_id=" + Integer.parseInt(stateId)+
				" and lm.name Like '%" +loksabhaName +"%'";	
			
			dataTableResults = loksabhaService.loadGrid(request, whereClauseForBaseQuery);
			
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("LoksabhaMasterController :==> listLoksabhaPaginated :==> Ended");
		return new Gson().toJson(dataTableResults);
	}

	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("LoksabhaMasterController :==> getRecord :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			jsonResponse.setFormObject(loksabhaService.getReordById(id));
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
		log.info("LoksabhaMasterController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "saveAndUpdateRecord", method = RequestMethod.POST)	
	@ResponseBody
	public String saveAndUpdateRecord(@RequestBody @Valid LoksabhaMasterDTO loksabhaMasterDTO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("LoksabhaMasterController :==> saveAndUpdateRecord :==> Started");
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
				jsonResponse.setFormObject(loksabhaService.saveAndUpdate(loksabhaMasterDTO));
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
		log.info("LoksabhaMasterController :==> saveAndUpdateRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "deleteRecord", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("LoksabhaMasterController :==> deleteRecord :==> Start");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			jsonResponse.setStatus(loksabhaService.deleteOneRecord(id));
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
		log.info("LoksabhaMasterController :==> deleteRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteSelected", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelected(@RequestBody Integer[] recordIdArray, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("LoksabhaMasterController :==> deleteSelected :==> Start");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			loksabhaService.deleteMultipleRecords(recordIdArray);
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
		log.info("LoksabhaMasterController :==> deleteSelected :==> End");
		return new Gson().toJson(jsonResponse);


	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody public String stateList(@RequestParam Integer id, HttpServletRequest request,	HttpServletResponse response) {
		log.info("LoksabhaMasterController :==> stateList :==> Start");
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<NameValue> stateList=loksabhaService.getLoksabhaList(id);	
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
		log.info("LoksabhaMasterController :==> stateList :==> End");
		return new Gson().toJson(jsonResponse);
	}

}// End of LoksabhaMasterController
