/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.LoksabhaCandidateMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface LoksabhaCandidateService extends FieldValueWithParentIdExists{
	public DataTableResults<LoksabhaCandidateMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery)throws CustomRuntimeException;
	

    public LoksabhaCandidateMasterDTO getReordById(Integer id)throws CustomRuntimeException;
    
    public LoksabhaCandidateMasterDTO saveAndUpdate(LoksabhaCandidateMasterDTO loksabhaCandidateMasterDTO)throws CustomRuntimeException;

	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

	boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
	public List<NameValue> getLoksabhaCandidateList(Integer id)throws CustomRuntimeException;
	
}
