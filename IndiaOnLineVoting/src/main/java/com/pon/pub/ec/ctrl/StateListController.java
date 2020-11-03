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
import com.pon.pvt.master.dto.StateMasterDTO;
import com.pon.pvt.master.service.LoksabhaService;
import com.pon.pvt.master.service.StateService;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;



/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pub/ec/states/")
public class StateListController {
	static final Logger log = LoggerFactory.getLogger(StateListController.class);

	@Autowired
	StateService stateService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listStates(Model model) {
		log.info("StateListController :==> listStates :==> Started");
		String target = "/ec/state_list";
		log.info("StateListController :==> listStates :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listStatesPaginated(HttpServletRequest request, HttpServletResponse response,
			String stateName,String countryName, String countryId){
		log.info("StateListController :==> listStatesPaginated :==> Started");
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
		log.info("StateListController :==> listStatesPaginated :==> Started");
		return new Gson().toJson(dataTableResults);
	}
}
