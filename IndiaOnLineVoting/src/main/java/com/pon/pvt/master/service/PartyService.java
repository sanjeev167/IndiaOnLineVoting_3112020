/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.PartyMasterDTO;
import com.pon.pvt.master.dto.ddData;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueWithParentIdExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface PartyService extends FieldValueWithParentIdExists{
	
	    public DataTableResults<PartyMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery) throws CustomRuntimeException;

	    public PartyMasterDTO getReordById(Integer id)throws CustomRuntimeException;
	    
	    public PartyMasterDTO saveAndUpdate(PartyMasterDTO partyMasterDTO)throws CustomRuntimeException;

		public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

		boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
		public List<NameValue> getPartyList(Integer id)throws CustomRuntimeException;
		public List<NameValue> getPartyList()throws CustomRuntimeException;
		public List<ddData> getPartySymbolList(String contextPath)throws CustomRuntimeException;
}
