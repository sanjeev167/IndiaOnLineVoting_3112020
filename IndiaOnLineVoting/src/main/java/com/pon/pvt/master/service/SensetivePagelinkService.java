/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pon.pvt.master.dto.CountryMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.SensetivePagelinkDTO;
import com.pon.pvt.master.entity.SensetivePagelinkMaster;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueExists;
import com.support.grid_pagination.DataTableResults;

/**
 * @author Sanjeev
 *
 */
public interface SensetivePagelinkService extends FieldValueExists{
	
  public DataTableResults<SensetivePagelinkDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery) throws CustomRuntimeException;

    public SensetivePagelinkDTO getReordById(Integer id)throws CustomRuntimeException;
    
    public SensetivePagelinkDTO saveAndUpdate(SensetivePagelinkDTO sensetivePagelinkDTO)throws CustomRuntimeException;

	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException;

	void deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
	
	List<NameValue> getSensetivePagelinkList() throws CustomRuntimeException;

	public List<String> getActiveMarqueList()throws CustomRuntimeException;  
	
	public SensetivePagelinkMaster getSensetivePagelinkDetails(String pageUrl) throws CustomRuntimeException;
  
}
