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
import com.pon.pvt.master.dto.AssemblyMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.service.AssemblyService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */

@Controller
@RequestMapping(value = "/pvt/master/assembly/")
public class AssemblyMasterController {
	static final Logger log = LoggerFactory.getLogger(AssemblyMasterController.class);
	@Autowired
	AssemblyService assemblyService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listAssemblies(Model model) {
		log.info("AssemblyMasterController :==> listAssemblies :==> Started");
		String target = "/master/assembly_master";
		log.info("AssemblyMasterController :==> listAssemblies :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listAssemblyPaginated(HttpServletRequest request, HttpServletResponse response, String stateNameId,
			String loksabhaNameId,String assemblyNameId){
		log.info("AssemblyMasterController :==> listAssemblyPaginated :==> Started");
		DataTableResults<AssemblyMasterDTO> dataTableResults=null;
		try {
			String whereClauseForBaseQuery = "";

			if (!stateNameId.isEmpty() && !stateNameId.equals("")&&loksabhaNameId.isEmpty() && loksabhaNameId.equals(""))
				whereClauseForBaseQuery =  " lm.state_id=" + Integer.parseInt(stateNameId);

			if (!loksabhaNameId.isEmpty() && !loksabhaNameId.equals("")&& stateNameId.isEmpty() && stateNameId.equals(""))
				whereClauseForBaseQuery =  " am.loksabha_id=" + Integer.parseInt(loksabhaNameId);

			if (!stateNameId.isEmpty() && !stateNameId.equals("")&& !loksabhaNameId.isEmpty() && !loksabhaNameId.equals(""))
				whereClauseForBaseQuery =  " lm.state_id=" + Integer.parseInt(stateNameId)+
				" and am.loksabha_id=" + Integer.parseInt(loksabhaNameId);

			dataTableResults=assemblyService.loadGrid(request, whereClauseForBaseQuery);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("AssemblyMasterController :==> listAssemblyPaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}

	
	//@PreAuthorize("@methodSecurityService.hasAccess(#num)")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response){
		log.info("AssemblyMasterController :==> getRecord :==> Started");
		
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setFormObject(assemblyService.getReordById(id));
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
		log.info("AssemblyMasterController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "saveAndUpdateRecord", method = RequestMethod.POST)
	@ResponseBody
	public String saveAndUpdateRecord(@RequestBody @Valid AssemblyMasterDTO assemblyMasterDTO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		
		log.info("AssemblyMasterController :==> saveAndUpdateRecord :==> Started");
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
				jsonResponse.setFormObject(assemblyService.saveAndUpdate(assemblyMasterDTO));
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
		log.info("AssemblyMasterController :==> saveAndUpdateRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteRecord", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("AssemblyMasterController :==> deleteRecord :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setStatus(assemblyService.deleteOneRecord(id));
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
		log.info("AssemblyMasterController :==> deleteRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteSelected", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelected(@RequestBody Integer[] recordIdArray, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("AssemblyMasterController :==> deleteSelected :==> Started");
		JsonResponse jsonResponse = new JsonResponse();
		try {			
			jsonResponse.setStatus(assemblyService.deleteMultipleRecords(recordIdArray));
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
		log.info("AssemblyMasterController :==> deleteSelected :==> End");
		return new Gson().toJson(jsonResponse);

	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody public String assemblyList(@RequestParam Integer id,HttpServletRequest request,	HttpServletResponse response) {
		log.info("AssemblyMasterController :==> assemblyList :==> Start");
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<NameValue> stateList=assemblyService.getAssemblyList(id);	
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
		log.info("AssemblyMasterController :==> assemblyList :==> End");
		return new Gson().toJson(jsonResponse);
	}
}
