/**
 * 
 */
package com.pon.pvt.master.service;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.CityMasterDTO;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface CityService extends FieldValueWithParentIdExists{
	public DataTableResults<CityMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery)throws CustomRuntimeException;
	

    public CityMasterDTO getReordById(Integer id)throws CustomRuntimeException;
    
    public CityMasterDTO saveAndUpdate(CityMasterDTO cityMasterDTO)throws CustomRuntimeException;

	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

	boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
}
