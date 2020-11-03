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
import com.pon.pvt.master.dto.PartyMasterDTO;
import com.pon.pvt.master.dto.ddData;
import com.pon.pvt.master.service.PartyService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;
/**
 * @author Sanjeev
 *
 */

@Controller
@RequestMapping(value = "/pvt/master/party/")
public class PartyMasterController{

	static final Logger log = LoggerFactory.getLogger(PartyMasterController.class);

	@Autowired
	PartyService partyService;
	
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listParties(Model model) {
		log.info("PartyMasterController :==> listParties :==> Started");
		String target = "/master/party_master";
		log.info("PartyMasterController :==> listParties :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listPartiesPaginated(HttpServletRequest request, HttpServletResponse response,
			String partyTypeId, String stateNameId,
			String stateName,String partyName, String partyType){
		System.out.println("partyTypeId = "+partyTypeId);
		log.info("PartyMasterController :==> listPartiesPaginated :==> Started");
		String whereClauseForBaseQuery = "";
		DataTableResults<PartyMasterDTO> dataTableResults=null;
		try {		
			if (partyTypeId!=null && !partyTypeId.isEmpty()&&!partyTypeId.equals(""))
				whereClauseForBaseQuery ="  and pm.party_type Like '%" + partyTypeId+"%'";	
			
			if (stateNameId!=null && !stateNameId.isEmpty()&&!stateNameId.equals(""))
				whereClauseForBaseQuery =whereClauseForBaseQuery+" and  sm.id=" + stateNameId;	
			
			if (stateName!=null && !stateName.isEmpty()&&!stateName.equals(""))
				whereClauseForBaseQuery =whereClauseForBaseQuery+" and  sm.name=" + stateName;	
			if (partyName!=null && !partyName.isEmpty()&&!partyName.equals(""))
				whereClauseForBaseQuery =whereClauseForBaseQuery+"  and pm.name=" + partyName;	
			if (partyType!=null && !partyType.isEmpty()&&!partyType.equals(""))
				whereClauseForBaseQuery =whereClauseForBaseQuery+" and  pm.party_type=" + partyType;	
			
			dataTableResults = partyService.loadGrid(request, whereClauseForBaseQuery);
			
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("PartyMasterController :==> listPartiesPaginated :==> Started");
		return new Gson().toJson(dataTableResults);
	}

	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("PartyMasterController :==> getRecord :==> Started");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			jsonResponse.setFormObject(partyService.getReordById(id));
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
		log.info("PartyMasterController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "saveAndUpdateRecord", method = RequestMethod.POST)	
	@ResponseBody
	public String saveAndUpdateRecord(@RequestBody @Valid PartyMasterDTO partyMasterDTO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("PartyMasterController :==> saveAndUpdateRecord :==> Started");
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
				jsonResponse.setFormObject(partyService.saveAndUpdate(partyMasterDTO));
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
		log.info("PartyMasterController :==> saveAndUpdateRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "deleteRecord", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("PartyMasterController :==> deleteRecord :==> Start");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			jsonResponse.setStatus(partyService.deleteOneRecord(id));
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
		log.info("PartyMasterController :==> deleteRecord :==> End");
		return new Gson().toJson(jsonResponse);

	}

	@RequestMapping(value = "deleteSelected", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelected(@RequestBody Integer[] recordIdArray, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("PartyMasterController :==> deleteSelected :==> Start");
		JsonResponse jsonResponse=new JsonResponse();
		try {			 
			partyService.deleteMultipleRecords(recordIdArray);
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
		log.info("PartyMasterController :==> deleteSelected :==> End");
		return new Gson().toJson(jsonResponse);


	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody public String partyList(@RequestParam Integer stateId,HttpServletRequest request,	HttpServletResponse response) {
		log.info("PartyMasterController :==> partyList :==> Start");
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<NameValue> partyList=partyService.getPartyList(stateId);	
			jsonResponse.setComboList(partyList);			 
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
		log.info("PartyMasterController :==> partyList :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	
	
	@RequestMapping(value = "partySymbollist", method = RequestMethod.GET)
	@ResponseBody public String getPartySymbolList(HttpServletRequest request,	HttpServletResponse response) {
		log.info("PartyMasterController :==> getPartySymbolList :==> Start");
		
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<ddData> stateList=partyService.getPartySymbolList(request.getContextPath());	
			jsonResponse.setImgComboList(stateList);			 
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
		log.info("PartyMasterController :==> getPartySymbolList :==> End");
		return new Gson().toJson(jsonResponse);
	}
	

}// End of PartyMasterController
