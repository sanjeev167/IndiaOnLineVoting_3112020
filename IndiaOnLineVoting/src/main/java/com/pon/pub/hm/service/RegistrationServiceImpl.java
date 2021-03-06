/**
 * 
 */
package com.pon.pub.hm.service;

import com.pon.pub.hm.dto.LoginDTO;
import com.pon.pub.hm.dto.RegistrationDTO;
import com.pon.pub.hm.entity.OnlineVoterAccount;
import com.pon.pub.hm.repo.OnlineVoterAccountRepository;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.repo.VotersEnrolledRepository;
import com.pon.pvt.voter.dto.VoteLockinigDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.util.EncrytedPasswordUtils;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.internal.util.Assert;

/**
 * @author Sanjeev
 *
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

	static final Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	OnlineVoterAccountRepository onlineVoterAccountRepository;
	
	@Autowired
	VotersEnrolledRepository votersEnrolledRepository;

	
	@Override
	public RegistrationDTO getReordById(Integer id) throws CustomRuntimeException {
		log.info("     RegistrationServiceImpl :==> getReordById ==> Started");
		RegistrationDTO registrationDTO = null;
		try {
			OnlineVoterAccount onlineVoterAccount = onlineVoterAccountRepository.getOne(id);
			registrationDTO = new RegistrationDTO();
			registrationDTO.setId(onlineVoterAccount.getId());

			registrationDTO.setName(onlineVoterAccount.getName());
			registrationDTO.setUserLoginId(onlineVoterAccount.getMailId());
			registrationDTO.setPassword(onlineVoterAccount.getPwd());
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     RegistrationServiceImpl :==> getReordById ==> Ended");
		return registrationDTO;
	}

	@Override
	public RegistrationDTO saveAndUpdate(RegistrationDTO registrationDTO) throws CustomRuntimeException {
		log.info("     RegistrationServiceImpl :==> saveAndUpdate ==> Started");

		OnlineVoterAccount onlineVoterAccount = null;
		RegistrationDTO registrationDTONew = null;
		try {
			if (registrationDTO.getId() != null)
				onlineVoterAccount = onlineVoterAccountRepository.getOne(registrationDTO.getId());
			else
				onlineVoterAccount = new OnlineVoterAccount();

			onlineVoterAccount.setId(registrationDTO.getId());
			onlineVoterAccount.setName(registrationDTO.getName());
			onlineVoterAccount.setMailId(registrationDTO.getUserLoginId());
			onlineVoterAccount.setPwd(EncrytedPasswordUtils.encrytePassword(registrationDTO.getPassword()));			
			onlineVoterAccount.setVoterId(votersEnrolledRepository.findAVoterByVoterId(registrationDTO.getVoterId()));
			
			onlineVoterAccount.setAadharId(registrationDTO.getAadharId().replace("-", ""));
			
			onlineVoterAccount.setMobile(registrationDTO.getMobileNo());
			onlineVoterAccount.setSecret(registrationDTO.getSecret());
			onlineVoterAccount.setIpAdd(registrationDTO.getVoterRequestIpAddress());
			

			OnlineVoterAccount returnedOnlineVoterAccount = onlineVoterAccountRepository
					.saveAndFlush(onlineVoterAccount);
            //This will be readjusted when we require
			registrationDTONew = new RegistrationDTO(returnedOnlineVoterAccount.getId(),
					                                 returnedOnlineVoterAccount.getName(), returnedOnlineVoterAccount.getMailId(),
					                                 returnedOnlineVoterAccount.getPwd());
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     RegistrationServiceImpl :==> saveAndUpdate ==> Ended");

		return registrationDTONew;
	}

	@Override
	public boolean deleteRecordById(Integer id) throws CustomRuntimeException {
		log.info("     RegistrationServiceImpl :==> deleteRecordById ==> Started");
		boolean isDeleted = true;
		try {
			onlineVoterAccountRepository.deleteById(id);
		} catch (Exception ex) {
			isDeleted = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     RegistrationServiceImpl :==> deleteRecordById ==> Ended");
		return isDeleted;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException {

		log.info("     RegistrationServiceImpl :==> deleteMultipleRecords ==> Started");
		boolean isDeleted = true;
		try {
			onlineVoterAccountRepository.deleteOnlineVoterWithIds(recordIdArray);
		} catch (Exception ex) {
			isDeleted = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     RegistrationServiceImpl :==> deleteMultipleRecords ==> Ended");
		return isDeleted;
	}

	@Override
	public List<NameValue> getOnlineUserList(Integer id) throws CustomRuntimeException {
		log.info("     RegistrationServiceImpl :==> getOnlineUserList ==> Started");
		List<NameValue> onlineVoterAccountListNew = new ArrayList<NameValue>();
		NameValue nameValue = null;
		try {
			List<OnlineVoterAccount> onlineVoterAccountList = onlineVoterAccountRepository.findAll();

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     RegistrationServiceImpl :==> getOnlineUserList ==> Ended");
		return onlineVoterAccountListNew;
	}

	@Override
	public boolean fieldValueExists(Object value, String fieldName) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fieldValueExists(Object loginIdValue, String loginIdName, Object idValue, String id)
			throws CustomRuntimeException {
		log.info("     RegistrationServiceImpl :==> fieldValueExists ==> Started");

		boolean recordFound = false;
		try {
			Assert.notNull(loginIdName);
			Assert.notNull(id);
			if (!loginIdName.equals("userLoginId") && !id.equals("id")) {
				throw ExceptionApplicationUtility
				.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));
			}

			if (loginIdValue == null) {
				return false;
			}
			if (!loginIdValue.equals("") && idValue == null) {
				// Case of adding new one
				recordFound = this.onlineVoterAccountRepository.existsByOnlineVoterLoginId(loginIdValue.toString());

			}
			if (!loginIdValue.equals("") && idValue != null) {
				// Case of editing existing one
				recordFound = this.onlineVoterAccountRepository.existsByOnlineVoterLoginIdExceptThisId(
						loginIdValue.toString(), Integer.parseInt(idValue.toString()));

			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     RegistrationServiceImpl :==> fieldValueExists ==> Ended");
		return recordFound;

	}

	@Override
	public VoteLockinigDTO loadByMailId(String mailId) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		OnlineVoterAccount onlineVoterAccount=onlineVoterAccountRepository.findByMailId(mailId);		
		VoteLockinigDTO voteLockinigDTO=new VoteLockinigDTO();			
		voteLockinigDTO.setOnlineVoterAccountId(onlineVoterAccount.getId());
		voteLockinigDTO.setName(onlineVoterAccount.getName());
		voteLockinigDTO.setVoterId(onlineVoterAccount.getVoterId().getVoterId());
		voteLockinigDTO.setAadharId(onlineVoterAccount.getAadharId());
		
		
		if(onlineVoterAccount.getVoterId().getVotingMode().equals("0"))
		    voteLockinigDTO.setLockStatus("Not Locked");
		else
			voteLockinigDTO.setLockStatus("Locked");
		
		return voteLockinigDTO;
	}
}
