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
import com.pon.pvt.master.dto.AssemblyMasterDTO;
import com.pon.pvt.master.dto.LoksabhaMasterDTO;
import com.pon.pvt.master.service.AssemblyService;
import com.pon.pvt.master.service.LoksabhaService;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;



/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pub/ec/assemblies/")
public class AssemblyListController {
	static final Logger log = LoggerFactory.getLogger(AssemblyListController.class);
	
	@Autowired
	AssemblyService assemblyService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listAssemblies(Model model) {
		log.info("AssemblyListController :==> listAssemblies :==> Started");
		String target = "/ec/assembly_list";
		log.info("AssemblyListController :==> listAssemblies :==> End");
		return target;
	}

	@RequestMapping(value = "paginated", method = RequestMethod.POST)
	@ResponseBody
	public String listAssemblyPaginated(HttpServletRequest request, HttpServletResponse response, String stateNameId,
			String loksabhaNameId,String assemblyNameId){
		log.info("AssemblyListController :==> listAssemblyPaginated :==> Started");
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
		log.info("AssemblyListController :==> listAssemblyPaginated :==> End");
		return new Gson().toJson(dataTableResults);
	}

}
