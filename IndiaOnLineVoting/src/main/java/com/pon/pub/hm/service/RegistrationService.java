/**
 * 
 */
package com.pon.pub.hm.service;

import java.util.List;

import com.pon.pub.hm.dto.RegistrationDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.voter.dto.VoteLockinigDTO;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.validation.interfaceForServices.FieldValueExists;

/**
 * @author Sanjeev
 *
 */
public interface RegistrationService  extends FieldValueExists{
	
	  public RegistrationDTO getReordById(Integer id)throws CustomRuntimeException;
	    
	    public RegistrationDTO saveAndUpdate(RegistrationDTO registrationDTO)throws CustomRuntimeException;

		public boolean deleteRecordById(Integer id)throws CustomRuntimeException;

		public boolean deleteMultipleRecords(Integer[] recordIdArray)throws CustomRuntimeException;
		
		public List<NameValue> getOnlineUserList(Integer id)throws CustomRuntimeException;

		public VoteLockinigDTO loadByMailId(String username)throws CustomRuntimeException;
    

}
