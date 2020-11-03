/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.AssemblyCandidateMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface AssemblyCandidateService extends FieldValueWithParentIdExists{
	public DataTableResults<AssemblyCandidateMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery)throws CustomRuntimeException;
	

    public AssemblyCandidateMasterDTO getReordById(Integer id)throws CustomRuntimeException;
    
    public AssemblyCandidateMasterDTO saveAndUpdate(AssemblyCandidateMasterDTO assemblyCandidateMasterDTO)throws CustomRuntimeException;

	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

	boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
	public List<NameValue> getAssemblyCandidateList(Integer id)throws CustomRuntimeException;
	
}
