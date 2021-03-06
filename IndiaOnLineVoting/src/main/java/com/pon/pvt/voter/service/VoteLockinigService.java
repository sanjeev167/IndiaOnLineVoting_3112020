/**
 * 
 */
package com.pon.pvt.voter.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.voter.dto.VoteLockinigDTO;
import com.pon.pvt.voter.entity.VoteLockinig;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface VoteLockinigService extends FieldValueWithParentIdExists{

	
	 public DataTableResults<VoteLockinigDTO> loadGrid(HttpServletRequest request,String voterId) throws CustomRuntimeException;
	 public DataTableResults<VoteLockinigDTO> loadGridForVoter(HttpServletRequest request,String whereClauseForBaseQuery) throws CustomRuntimeException;

	 
	 
	    public VoteLockinigDTO getReordById(Integer id)throws CustomRuntimeException;
	    
	    public VoteLockinigDTO saveAndUpdate(VoteLockinigDTO voteLockinigDTO)throws CustomRuntimeException;

		public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

		boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
		public List<NameValue> getVoteLockingList(Integer id)throws CustomRuntimeException;
		public List<NameValue> getVoteLockingList()throws CustomRuntimeException;

		public String[] verifyVotingSecret(String secret,String username)throws CustomRuntimeException;

		public VoteLockinig lockUnlockVote(@Valid VoteLockinigDTO voteLockinigDTO)throws CustomRuntimeException;

		public String[] verifyMobileOtp(String mobileOtp)throws CustomRuntimeException;

		public String[] verifyMailOtp(String mailOtp)throws CustomRuntimeException;

		
		
}
