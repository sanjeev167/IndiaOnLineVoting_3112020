/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.CountryMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface CountryService extends FieldValueExists{
	
  public DataTableResults<CountryMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery) throws CustomRuntimeException;

    public CountryMasterDTO getReordById(Integer id)throws CustomRuntimeException;
    
    public CountryMasterDTO saveAndUpdate(CountryMasterDTO countryMasterDTO)throws CustomRuntimeException;

	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

	void deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;

	public List<NameValue> getCountryList()throws CustomRuntimeException;
    
  
}
