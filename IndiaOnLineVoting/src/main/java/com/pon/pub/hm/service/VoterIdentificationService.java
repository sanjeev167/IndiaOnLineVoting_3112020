/**
 * 
 */
package com.pon.pub.hm.service;

import com.support.custom.exception.CustomRuntimeException;

/**
 * @author Sanjeev
 *
 */
public interface VoterIdentificationService {
	
	String [] verifyOfflineVoterId(String voterId) throws CustomRuntimeException;
	
	String [] verifyOnlineVoterId(String voterId) throws CustomRuntimeException;

	String [] verifyVoterId(String voterId) throws CustomRuntimeException;

	String [] verifyAadharId(String aadharId) throws CustomRuntimeException;

	String [] verifyAadharOTP(String aadharOtp) throws CustomRuntimeException;

	String[] thumbImpressionVerification(String impression) throws CustomRuntimeException;

	String[] verifyMobileNo(String mobileNo)throws CustomRuntimeException;

	String[] verifyMobileOtpNo(String mobileOtp)throws CustomRuntimeException;
	
	String[] checkWhetherHeIsAlreadyCastedVoteOffline(String voterId)throws CustomRuntimeException;
	String[] checkWhetherHeIsAlreadyCastedVoteOnline(String voterId)throws CustomRuntimeException;
	String[] checkSecretOfOnlineVoter(String secret,String voteVoterId)throws CustomRuntimeException;

	String[] hasHeLockedHisVoteForOnline(String voteVoterId,String currentLoggedInUser)throws CustomRuntimeException;


}
