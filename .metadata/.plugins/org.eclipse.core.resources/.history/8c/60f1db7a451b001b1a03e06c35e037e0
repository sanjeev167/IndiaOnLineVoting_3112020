/**
 * 
 */
package com.pon.pvt.voting.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.pon.pvt.voting.dto.AssemblyBalletForEnrolledVotersDTO;
import com.pon.pvt.voting.dto.ElectionDetailDTO;
import com.pon.pvt.voting.dto.VoteDetailDTO;
import com.pon.pvt.voting.dto.VoterDetailsDTO;
import com.support.custom.exception.CustomRuntimeException;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface AssemblyBalletService {
	
	public DataTableResults<AssemblyBalletForEnrolledVotersDTO> loadElectionBalletPaperGrid(String voterId,HttpServletRequest request) throws CustomRuntimeException;

	public ElectionDetailDTO loadElectionDetails(String voterId, String electionType) throws CustomRuntimeException;
	public VoterDetailsDTO loadVotersDetails(String voterId) throws CustomRuntimeException;	
	
	public boolean castVote(@Valid VoteDetailDTO voteDetailDTO)throws CustomRuntimeException;	
}
