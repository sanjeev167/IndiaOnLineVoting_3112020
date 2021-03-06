/**
 * 
 */
package com.pon.pvt.voting.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author Sanjeev
 *
 */
public class VoteDetailDTO extends ElectionDetailDTO{
	
	@NotBlank(message = "No candidate has been selected.")
	private String selectedCandidateIdForVoting;
	
	
	private String usedIpAddress;
	
	private String voterId;
	private String votingPartyId;
	
	private String votingMode;
	
	
	
	
	public String getVotingMode() {
		return votingMode;
	}
	public void setVotingMode(String votingMode) {
		this.votingMode = votingMode;
	}
	public String getSelectedCandidateIdForVoting() {
		return selectedCandidateIdForVoting;
	}
	public void setSelectedCandidateIdForVoting(String selectedCandidateIdForVoting) {
		this.selectedCandidateIdForVoting = selectedCandidateIdForVoting;
	}
	
	public String getUsedIpAddress() {
		return usedIpAddress;
	}

	public void setUsedIpAddress(String usedIpAddress) {
		this.usedIpAddress = usedIpAddress;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getVotingPartyId() {
		return votingPartyId;
	}
	public void setVotingPartyId(String votingPartyId) {
		this.votingPartyId = votingPartyId;
	}
	

}
