/**
 * 
 */
package com.pon.pvt.voting.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.VoteRepository;
import com.support.custom.exception.CustomRuntimeException;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface ElectionResultService {
		
	
	public DataTableResults<VoteRepository> getAssemblyResult(String yearId,String electionTypeId,String stateId,String loksabhaId,String assemblyId,  HttpServletRequest request)throws CustomRuntimeException;
	public DataTableResults<LoksabhaVoteRepository> getLoksabhaResult(String yearId,String electionTypeId,String stateId,String loksabhaId,String assemblyId,  HttpServletRequest request)throws CustomRuntimeException;

}
