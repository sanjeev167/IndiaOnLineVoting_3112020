/**
 * 
 */
package com.pon.pvt.voting.dto;

/**
 * @author Sanjeev
 *
 */
public class ElectionCandidateDTO {

	private Integer positionInBallet;
	private String candidateName;
	private String yearOfElection;
	private String candidateParty;
	private String candidateSymbol;
	
	
	public Integer getPositionInBallet() {
		return positionInBallet;
	}
	public void setPositionInBallet(Integer positionInBallet) {
		this.positionInBallet = positionInBallet;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getYearOfElection() {
		return yearOfElection;
	}
	public void setYearOfElection(String yearOfElection) {
		this.yearOfElection = yearOfElection;
	}
	public String getCandidateParty() {
		return candidateParty;
	}
	public void setCandidateParty(String candidateParty) {
		this.candidateParty = candidateParty;
	}
	public String getCandidateSymbol() {
		return candidateSymbol;
	}
	public void setCandidateSymbol(String candidateSymbol) {
		this.candidateSymbol = candidateSymbol;
	}
	
	
	
	
}
