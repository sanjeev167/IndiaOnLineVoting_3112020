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
import com.pon.pvt.master.dto.PollingBoothMasterDTO;
import com.pon.pvt.master.service.PollingBoothService;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping(value = "/pub/ec/pollingBooths/")
public class PollingBoothListController {
	static final Logger log = LoggerFactory.getLogger(PollingBoothListController.class);
	@Autowired
	PollingBoothService pollingBoothService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listPollingBooths(Model model) {
		log.info("PollingBoothListController :==> listPollingBooths :==> Started");
		String target = "/ec/pollingBooth_list";
		log.info("PollingBoothListController :==> listPollingBooths :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listPollingBoothsPaginated(HttpServletRequest request, HttpServletResponse response, String stateNameId,
			String loksabhaNameId,String assemblyNameId,String pollingBoothNameId){
		log.info("PollingBoothListController :==> listPollingBoothsPaginated :==> Started");
		
		
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
		log.info("PollingBoothListController :==> listPollingBoothsPaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}

	
}
