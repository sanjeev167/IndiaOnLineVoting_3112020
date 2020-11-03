/**
 * 
 */
package com.pon.pub.ec.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping(value = "/pub/ec/parties/")
public class PartyListController{

	static final Logger log = LoggerFactory.getLogger(PartyListController.class);

	@Autowired
	PartyService partyService;
	
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listParties(Model model) {
		log.info("PartyListController :==> listParties :==> Started");
		String target = "/ec/party_list";
		log.info("PartyListController :==> listParties :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listPartiesPaginated(HttpServletRequest request, HttpServletResponse response,
			String partyTypeId, String stateNameId,
			String stateName,String partyName, String partyType){
		System.out.println("partyTypeId = "+partyTypeId);
		log.info("PartyListController :==> listPartiesPaginated :==> Started");
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
		log.info("PartyListController :==> listPartiesPaginated :==> Started");
		return new Gson().toJson(dataTableResults);
	}

	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("PartyListController :==> getRecord :==> Started");
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
		log.info("PartyListController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody public String partyList(HttpServletRequest request,	HttpServletResponse response) {
		log.info("PartyListController :==> partyList :==> Start");
		JsonResponse jsonResponse=new JsonResponse();		
		try {			 	
			List<NameValue> stateList=partyService.getPartyList();	
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
		log.info("PartyListController :==> partyList :==> End");
		return new Gson().toJson(jsonResponse);
	}
	
	@RequestMapping(value = "partySymbollist", method = RequestMethod.GET)
	@ResponseBody public String getPartySymbolList(HttpServletRequest request,	HttpServletResponse response) {
		log.info("PartyListController :==> getPartySymbolList :==> Start");
		
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
		log.info("PartyListController :==> getPartySymbolList :==> End");
		return new Gson().toJson(jsonResponse);
	}
	

}// End of PartyListController
