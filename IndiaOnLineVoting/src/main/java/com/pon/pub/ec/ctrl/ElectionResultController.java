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
import com.pon.pvt.master.entity.SensetivePagelinkMaster;
import com.pon.pvt.master.service.SensetivePagelinkService;
import com.pon.pvt.voting.dto.ElectionDetailDTO;
import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.VoteRepository;
import com.pon.pvt.voting.service.ElectionResultService;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/pub/vote/")
public class ElectionResultController {
	static final Logger log = LoggerFactory.getLogger(ElectionResultController.class);
	
	
	@Autowired
	ElectionResultService electionResultService;
	@Autowired
	SensetivePagelinkService sensetivePagelinkService;
	
	@GetMapping("result")	
	public String electionResult(Model model,HttpServletRequest request, HttpServletResponse response) {		
		log.info("ElectionResultController :==> electionResult :==> Started");
		String target="/vote/result";
			
		try {
			String pageUrl=request.getRequestURI();
			SensetivePagelinkMaster sensetivePagelinkMaster=sensetivePagelinkService.getSensetivePagelinkDetails(pageUrl);
			//Check the voting channel availability
			if(sensetivePagelinkMaster.isChannelState()) {
		ElectionDetailDTO electionDetailDTO=new ElectionDetailDTO();
			}else {								
				model.addAttribute("notAvailabilityMsg", sensetivePagelinkMaster.getDenyMessage());
				target="/resultNotPublished";
			}
		}catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("ElectionResultController :==> electionResult :==> End");
		return target;		
		
	}
	
	
	@RequestMapping(value = "resultPaginated", method = RequestMethod.POST)
	@ResponseBody
	public String resultPaginated( @RequestParam(required = false) String yearId,
						           @RequestParam(required = false) String electionTypeId,
						           @RequestParam(required = false) String stateId,
						           @RequestParam(required = false) String loksabhaId,
						           @RequestParam(required = false) String assemblyId,
						           HttpServletRequest request, HttpServletResponse response){
		 
		
		log.info("ElectionResultController :==> resultPaginated :==> Started");		
		DataTableResults<VoteRepository> dataTableResultsAssembly=null;
		DataTableResults<LoksabhaVoteRepository> dataTableResultsLoksabha=null;	
		
		try {		
			if(electionTypeId.equals("A")) {
				dataTableResultsAssembly = electionResultService.getAssemblyResult(yearId, electionTypeId, stateId, loksabhaId, assemblyId, request);
				return new Gson().toJson(dataTableResultsAssembly);
			}			
			if(electionTypeId.equals("P")) {
				dataTableResultsLoksabha = electionResultService.getLoksabhaResult(yearId, electionTypeId, stateId, loksabhaId, assemblyId, request);
				return new Gson().toJson(dataTableResultsLoksabha);
			}			
		}
		catch (CustomRuntimeException ex) {
			// Handle this exception
			String exceptionCause= ex.getExceptionInfo().exceptionCause;
		} catch (Exception ex) {
			CustomRuntimeException exLocal=ExceptionApplicationUtility.wrapRunTimeException(ex);
			//Handle this exception
			String exceptionCause= exLocal.getExceptionInfo().exceptionCause;
		}
		log.info("ElectionResultController :==> resultPaginated :==> Started");
		
		return null;
	}
	
	
}