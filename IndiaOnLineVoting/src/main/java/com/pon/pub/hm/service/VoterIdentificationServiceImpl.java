/**
 * 
 */
package com.pon.pub.hm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pon.pub.hm.entity.OnlineVoterAccount;
import com.pon.pub.hm.repo.OnlineVoterAccountRepository;
import com.pon.pvt.master.entity.VotersEnrolled;
import com.pon.pvt.master.repo.VotersEnrolledRepository;
import com.pon.pvt.voter.entity.VoteLockinig;
import com.pon.pvt.voter.repo.VoteLockinigRepository;
import com.pon.pvt.voting.entity.MarkOfflineVoter;
import com.pon.pvt.voting.entity.MarkOnlineVoter;
import com.pon.pvt.voting.repo.MarkOfflineVoterRepository;
import com.pon.pvt.voting.repo.MarkOnlineVoterRepository;
import com.support.custom.exception.CustomRuntimeException;
import com.support.util.AppUtil;

/**
 * @author Sanjeev
 *
 */
@Service
public class VoterIdentificationServiceImpl implements VoterIdentificationService{
	
	@Autowired
	VotersEnrolledRepository votersEnrolledRepository;
	@Autowired
	MarkOfflineVoterRepository mrkOfflineVoterRepository;
	@Autowired
	MarkOnlineVoterRepository mrkOnlineVoterRepository;
	
	@Autowired
	OnlineVoterAccountRepository onlineVoterAccountRepository;
	@Autowired
	VoteLockinigRepository voteLockinigRepository;

	@Override
	public String [] verifyVoterId(String voterId) throws CustomRuntimeException{
		
		// TODO Auto-generated method stub
		String status[]=new String[2];
		//First check the format of voter-id
		if(AppUtil.checkVoterIdFormat(voterId)) {
			//Next check it in the database whether it exists or not
			if(votersEnrolledRepository.searchByVoterId(voterId)) {
				status[0]="1";
				status[1]="Voter-Id has been verified. Now, go for Aadhar verification either through OTP or thumb impression.";
			}else {
				status[0]="0";
				status[1]="Wrong Voter-Id supplied.";
			}
		}else {
			status[0]="0";
			status[1]="Wrong Voter-Id format is supplied.";
		}
		return status;
	}

	@Override
	public String [] verifyAadharId(String aadharId) throws CustomRuntimeException{
		// TODO Auto-generated method stub
		String status[]=new String[2];
		//First check the format of aadhar-id
				if(AppUtil.checkAadharIdFormat(aadharId)) {
					//Next check it from Aadhar UID portal
					if(AppUtil.checkItFromAadharPortal(aadharId)) {
						status[0]="1";
						status[1]="Aadhar-Id has been partially verified. Pass Aadhar-OTP received at your mobile for completing Aadhar-Verification.";
						//Ask user to pass OTP sent by UID
					}else {
						status[0]="0";
						status[1]="Wrong Aadhar-Id supplied.";
					}
				}else {
					status[0]="0";
					status[1]="Wrong Aadhar-Id supplied.";
				}
				return status;
	}

	@Override
	public String [] verifyAadharOTP(String aadharOtp) throws CustomRuntimeException{
		// TODO Auto-generated method stub
		String status[]=new String[2];
		//First check the format of OTP
				if(AppUtil.checkAadharOTPFormat(aadharOtp)) {
					//Next check it from Aadhar UID portal
					if(AppUtil.checkOTPFromAadharPortal(aadharOtp)) {
						status[0]="1";
						status[1]="Aadhar-OTP has been verified. You have successfully passed all Voter-Identity-Verification.";
						
					}else {
						status[0]="0";
						status[1]="Wrong Aadhar-OTP supplied.";
					}
				}else {
					status[0]="0";
					status[1]="Wrong Aadhar-OTP supplied.";
				}
				return status;
	}

	@Override
	public String[] thumbImpressionVerification(String impression) throws CustomRuntimeException {
		// TODO Auto-generated method stub
				String status[]=new String[3];
				
							//Next check it from Aadhar UID portal
							if(AppUtil.checkImpressionFromAadharPortal(impression)) {
								status[0]="1";
								status[1]="Thumb impression has been verified. Pass Aadhar OTP.";
								status[2]="999-999-999-999";
								
							}else {
								status[0]="0";
								status[1]="Wrong thumb impression supplied.";
							}
						
						return status;
	}

	@Override
	public String[] verifyMobileNo(String mobileNo) throws CustomRuntimeException {
		String status[]=new String[3];
		
		//Next check it from Aadhar UID portal
		if(AppUtil.checkMobileNoFromServiceProvider(mobileNo)) {
			status[0]="1";
			status[1]="Mobile no has been partially verified. Pass Mobile OTP.";
			}else {
			status[0]="0";
			status[1]="Wrong mobile no. supplied.";
		}
	
	return status;
	}

	@Override
	public String[] verifyMobileOtpNo(String mobileOtp) throws CustomRuntimeException {
		String status[]=new String[3];
		
		//Next check it from Aadhar UID portal
		if(AppUtil.checkMobileOtp(mobileOtp)) {
			status[0]="1";
			status[1]="Mobile OTP has been verified. Pass your online voting secret.";		
		}else {
			status[0]="0";
			status[1]="Wrong mobile OTP is supplied.";
		}
	
	return status;
	}

	@Override
	public String[] checkWhetherHeIsAlreadyCastedVoteOffline(String voterId) throws CustomRuntimeException {
		// TODO Auto-generated method stub		
		
		VotersEnrolled votersEnrolled= votersEnrolledRepository.findByVoterId(voterId);
	    MarkOfflineVoter markOfflineVoter=mrkOfflineVoterRepository.findByVoterId(votersEnrolled.getId()+"");
		String status[]=new String[2];
		if(markOfflineVoter!=null) {
			status[0]="0";status[1]="Already casted your vote";
			
		}else {
			status[0]="1";status[1]="Can cast your vote";
			
		}
		
		return status;
	}

	@Override
	public String[] checkWhetherHeIsAlreadyCastedVoteOnline(String voterId) throws CustomRuntimeException {
		// TODO Auto-generated method stub		
         VotersEnrolled votersEnrolled= votersEnrolledRepository.findAVoterByVoterId(voterId);      
	    MarkOnlineVoter markOnlineVoter=mrkOnlineVoterRepository.findByVoterId(votersEnrolled.getId()+"");		
		String status[]=new String[2];
		if(markOnlineVoter!=null) {
			status[0]="0";status[1]="Already casted your vote";
		}else {
			status[0]="1";status[1]="Can cast your vote";
		}
		
		return status;
	}

	@Override
	public String[] checkSecretOfOnlineVoter(String secret,String voterId) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		VotersEnrolled votersEnrolled=votersEnrolledRepository.findByVoterId(voterId);
		OnlineVoterAccount onlineVoterAccount=onlineVoterAccountRepository.findByVoterId(votersEnrolled);
		
		String[] secretStatus=new String[2];
		
		if(onlineVoterAccount.getSecret().equals(secret)) {
			secretStatus[0]="1";
			secretStatus[1]="Secret verified";
		}else {
			secretStatus[0]="0";
			secretStatus[1]="Secret is wrong";
		}
			
		return secretStatus;
	}

	@Override
	public String[] hasHeLockedHisVoteForOnline(String voterId,String currentLoggedInUser) throws CustomRuntimeException {
		System.out.println("currentLoggedInUser = "+currentLoggedInUser);
		// TODO Auto-generated method stub
		String[] lockStatus=new String[2];
		VotersEnrolled votersEnrolled=votersEnrolledRepository.findByVoterId(voterId);
		OnlineVoterAccount onlineVoterAccountLoggedIn=onlineVoterAccountRepository.findByMailId(currentLoggedInUser);
		OnlineVoterAccount onlineVoterAccount=onlineVoterAccountRepository.findByVoterId(votersEnrolled);		
		System.out.println("dataBaseLoggedInUser = "+onlineVoterAccount.getMailId());
		if(onlineVoterAccount!=null) {
			if(onlineVoterAccount.getMailId().equals(onlineVoterAccountLoggedIn.getMailId())) {
		//Fetch the voteLock with heighest id
		VoteLockinig voteLockinig=voteLockinigRepository.getTheLatestVoteLock(onlineVoterAccount.getId());		
		System.out.println("Latest Lock = "+voteLockinig.getLockStatus());
		//Then check whether the vote is locked or not
		
		if(voteLockinig!=null && voteLockinig.getLockStatus()==1) {
			lockStatus[0]="1";
			lockStatus[1]="Locked for online voting";
		}else {
			lockStatus[0]="0";
			lockStatus[1]="Not locked for online voting.";
		}
		}else {
			lockStatus[0]="0";
			lockStatus[1]="You are using other's voter-id.";
		}
		}else {
			lockStatus[0]="0";
			lockStatus[1]="You are not registered online.";
		}
		
		
		return lockStatus;
	}

	@Override
	public String[] verifyOfflineVoterId(String voterId) throws CustomRuntimeException {
		// TODO Auto-generated method stub
				String status[]=new String[2];
				//First check the format of voter-id
				if(AppUtil.checkVoterIdFormat(voterId)) {
					//Next check it in the database whether it exists or not
					if(votersEnrolledRepository.searchByVoterIdForOffline(voterId)) {
						status[0]="1";
						status[1]="Voter-Id has been verified. Now, go for Aadhar verification either through OTP or thumb impression.";
					}else {
						status[0]="0";
						status[1]="Either Wrong Voter-Id supplied or not an offline voter.";
					}
				}else {
					status[0]="0";
					status[1]="Wrong Voter-Id format is supplied.";
				}
				return status;
	}

	@Override
	public String[] verifyOnlineVoterId(String voterId) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		String status[]=new String[2];
		//First check the format of voter-id
		if(AppUtil.checkVoterIdFormat(voterId)) {
			//Next check it in the database whether it exists or not
			if(votersEnrolledRepository.searchByVoterIdForOnline(voterId)) {
				status[0]="1";
				status[1]="Voter-Id has been verified. Now, go for Aadhar verification either through OTP or thumb impression.";
			}else {
				status[0]="0";
				status[1]="Wrong Voter-Id supplied.";
			}
		}else {
			status[0]="0";
			status[1]="Wrong Voter-Id format is supplied.";
		}
		return status;
	}

}
