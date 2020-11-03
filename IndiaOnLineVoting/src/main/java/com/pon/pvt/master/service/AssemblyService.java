/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.AssemblyMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface AssemblyService extends FieldValueWithParentIdExists{
	public DataTableResults<AssemblyMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery)throws CustomRuntimeException;
	

    public AssemblyMasterDTO getReordById(Integer id)throws CustomRuntimeException;
    
    public AssemblyMasterDTO saveAndUpdate(AssemblyMasterDTO assemblyMasterDTO)throws CustomRuntimeException;

	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

	boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
	public List<NameValue> getAssemblyList(Integer id)throws CustomRuntimeException;
	
}
