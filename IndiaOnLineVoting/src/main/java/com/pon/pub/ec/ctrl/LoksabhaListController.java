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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pon.pvt.master.dto.LoksabhaMasterDTO;
import com.pon.pvt.master.service.LoksabhaService;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;



/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pub/ec/loksabhas/")
public class LoksabhaListController {
	static final Logger log = LoggerFactory.getLogger(LoksabhaListController.class);

	@Autowired
	LoksabhaService loksabhaService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listStates(Model model) {
		log.info("LoksabhaListController :==> listStates :==> Started");
		String target = "/ec/loksabha_list";
		log.info("LoksabhaListController :==> listStates :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listLoksabhaPaginated(HttpServletRequest request, HttpServletResponse response,
			String loksabhaName,String stateName, String stateId,Integer loksabhaNo){
		
		
		log.info("LoksabhaListController :==> listLoksabhaPaginated :==> Started");
				
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
		log.info("LoksabhaListController :==> listLoksabhaPaginated :==> Ended");
		return new Gson().toJson(dataTableResults);
	}
}
