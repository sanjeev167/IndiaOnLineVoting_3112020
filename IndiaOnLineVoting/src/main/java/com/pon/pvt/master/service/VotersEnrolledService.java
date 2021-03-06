/**
 * 
 */
package com.pon.pvt.master.service;

import javax.servlet.http.HttpServletRequest;

import com.pon.pub.hm.dto.LoginDTO;
import com.pon.pvt.master.dto.VotersEnrolledDTO;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface VotersEnrolledService extends FieldValueWithParentIdExists{
	
	
	public DataTableResults<VotersEnrolledDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery)throws CustomRuntimeException;
	

	public DataTableResults<VotersEnrolledDTO> loadGridOfflineOnline(HttpServletRequest request,String whereClauseForBaseQuery)throws CustomRuntimeException;
	

    public VotersEnrolledDTO getReordById(Integer id)throws CustomRuntimeException;
    
    public VotersEnrolledDTO saveAndUpdate(VotersEnrolledDTO votersEnrolledDTO)throws CustomRuntimeException;

	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

	boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
	
	boolean findByVoterName(LoginDTO loginDTO)throws CustomRuntimeException;
	
	public Integer findTotalRegisteredVoters()throws CustomRuntimeException;
	public Integer findTotalOnlineVoters()throws CustomRuntimeException;
	public Integer findTotalOfflineVoters()throws CustomRuntimeException;
	
	
}
