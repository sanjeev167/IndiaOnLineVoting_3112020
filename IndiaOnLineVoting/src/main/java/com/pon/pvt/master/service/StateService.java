/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.StateMasterDTO;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface StateService extends FieldValueWithParentIdExists{
	
	    public DataTableResults<StateMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery) throws CustomRuntimeException;

	    public StateMasterDTO getReordById(Integer id)throws CustomRuntimeException;
	    
	    public StateMasterDTO saveAndUpdate(StateMasterDTO stateMasterDTO)throws CustomRuntimeException;

		public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

		boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
		public List<NameValue> getStateList(Integer id)throws CustomRuntimeException;
		public List<NameValue> getStateList()throws CustomRuntimeException;
}
