/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.PollingBoothMasterDTO;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface PollingBoothService extends FieldValueWithParentIdExists{
	public DataTableResults<PollingBoothMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery)throws CustomRuntimeException;
	

    public PollingBoothMasterDTO getReordById(Integer id)throws CustomRuntimeException;
    
    public PollingBoothMasterDTO saveAndUpdate(PollingBoothMasterDTO pollingBoothMasterDTO)throws CustomRuntimeException;

	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

	boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;


	public List<NameValue> getPollingBoothList(Integer id)throws CustomRuntimeException;
}
