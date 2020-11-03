/**
 * 
 */
package com.pon.pub.ec.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pon.pvt.master.dto.VotersEnrolledDTO;
import com.pon.pvt.master.service.VotersEnrolledService;
import com.pon.pvt.voter.dto.VoteLockinigDTO;
import com.pon.pvt.voter.service.VoteLockinigService;
import com.support.JsonResponse.JsonResponse;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pub/ec/") 
public class VotersRepositoryController {
	static final Logger log = LoggerFactory.getLogger(VotersRepositoryController.class);
	@Autowired
	VotersEnrolledService votersEnrolledService;
	@Autowired
	VoteLockinigService voteLockinigService;
	
	
	@RequestMapping(value = "auditTrailPaginated", method = RequestMethod.POST)
	@ResponseBody
	public String auditTrailPaginated(HttpServletRequest request, HttpServletResponse response, String voterId){
		log.info("VotersRepositoryController :==> auditTrailPaginated :==> Started");
		DataTableResults<VoteLockinigDTO> dataTableResults=null;
		//Find the mail id of this voter id 
		
		try {			
			dataTableResults=voteLockinigService.loadGrid(request, voterId);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("VotersRepositoryController :==> auditTrailPaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}

	
	
	
	
	
	
	@RequestMapping(value = "voters", method = RequestMethod.GET)
	public String listVoters(Model model,HttpServletRequest request, HttpServletResponse response) {	
		
		log.info("VotersRepositoryController :==> listVoters :==> Started");
		String target = "/ec/voters";
		log.info("VotersRepositoryController :==> listVoters :==> End");
		return target;
		
	}
	
	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listVotersEnrolledPaginated(HttpServletRequest request, HttpServletResponse response, 
			String stateNameId,
			String loksabhaNameId,String assemblyNameId,String pollingBoothNameId){
		log.info("VotersRepositoryController :==> listVotersEnrolledPaginated :==> Started");
		
		
		DataTableResults<VotersEnrolledDTO> dataTableResults=null;
		try {
			String whereClauseForBaseQuery = "";
			if (stateNameId!=null && !stateNameId.isEmpty() && !stateNameId.equals("") )
				whereClauseForBaseQuery =  " and lm.state_id=" + Integer.parseInt(stateNameId);				
		    if (loksabhaNameId!=null && !loksabhaNameId.isEmpty() && !loksabhaNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and am.loksabha_id=" + Integer.parseInt(loksabhaNameId);
					
		    if (assemblyNameId!=null && !assemblyNameId.isEmpty() && !assemblyNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and pm.assembly_id=" + Integer.parseInt(assemblyNameId);
		    
		    if (pollingBoothNameId!=null && !pollingBoothNameId.isEmpty() && !pollingBoothNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and pm.id=" + Integer.parseInt(pollingBoothNameId);	    	
			
			dataTableResults=votersEnrolledService.loadGrid(request, whereClauseForBaseQuery);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("VotersRepositoryController :==> listVotersEnrolledPaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}
	
	@RequestMapping(value = "paginatedOffline", method = RequestMethod.POST)
	@ResponseBody
	public String offLineOistVotersEnrolledPaginated(HttpServletRequest request, HttpServletResponse response, String stateNameId,
			String loksabhaNameId,String assemblyNameId,String pollingBoothNameId){
		log.info("VotersRepositoryController :==> offLineOistVotersEnrolledPaginated :==> Started");
		
		
		DataTableResults<VotersEnrolledDTO> dataTableResults=null;
		try {//ve.voting_mode=0
			String whereClauseForBaseQuery = "and ve.voting_mode='0'";

			if (stateNameId!=null && !stateNameId.isEmpty() && !stateNameId.equals("") )
				whereClauseForBaseQuery =  " and lm.state_id=" + Integer.parseInt(stateNameId);				
		    if (loksabhaNameId!=null && !loksabhaNameId.isEmpty() && !loksabhaNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and am.loksabha_id=" + Integer.parseInt(loksabhaNameId);
					
		    if (assemblyNameId!=null && !assemblyNameId.isEmpty() && !assemblyNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and pm.assembly_id=" + Integer.parseInt(assemblyNameId);
		    
		    if (pollingBoothNameId!=null && !pollingBoothNameId.isEmpty() && !pollingBoothNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and pm.id=" + Integer.parseInt(pollingBoothNameId);

			dataTableResults=votersEnrolledService.loadGridOfflineOnline(request, whereClauseForBaseQuery);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("VotersRepositoryController :==> offLineOistVotersEnrolledPaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}

	@RequestMapping(value = "paginatedOnline", method = RequestMethod.POST)
	@ResponseBody
	public String onLineOistVotersEnrolledPaginated(HttpServletRequest request, HttpServletResponse response, String stateNameId,
			String loksabhaNameId,String assemblyNameId,String pollingBoothNameId){
		log.info("VotersRepositoryController :==> onLineOistVotersEnrolledPaginated :==> Started");
		
		
		DataTableResults<VotersEnrolledDTO> dataTableResults=null;
		try {
			String whereClauseForBaseQuery = " and ve.voting_mode='1'";

			if (stateNameId!=null && !stateNameId.isEmpty() && !stateNameId.equals("") )
				whereClauseForBaseQuery =  " and lm.state_id=" + Integer.parseInt(stateNameId);				
		    if (loksabhaNameId!=null && !loksabhaNameId.isEmpty() && !loksabhaNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and am.loksabha_id=" + Integer.parseInt(loksabhaNameId);
					
		    if (assemblyNameId!=null && !assemblyNameId.isEmpty() && !assemblyNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and pm.assembly_id=" + Integer.parseInt(assemblyNameId);
		    
		    if (pollingBoothNameId!=null && !pollingBoothNameId.isEmpty() && !pollingBoothNameId.equals(""))
		    	whereClauseForBaseQuery = whereClauseForBaseQuery+ " and pm.id=" + Integer.parseInt(pollingBoothNameId);

			dataTableResults=votersEnrolledService.loadGridOfflineOnline(request, whereClauseForBaseQuery);
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("VotersRepositoryController :==> onLineOistVotersEnrolledPaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}
	
	//@PreAuthorize("@methodSecurityService.hasAccess(#num)")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "getRecord", method = RequestMethod.GET)
	@ResponseBody
	public String getRecord(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response){
		log.info("VotersRepositoryController :==> getRecord :==> Started");
		
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.setFormObject(votersEnrolledService.getReordById(id));
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
		log.info("VotersRepositoryController :==> getRecord :==> End");
		return new Gson().toJson(jsonResponse);
	}

}