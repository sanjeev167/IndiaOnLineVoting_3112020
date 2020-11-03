/**
 * 
 */
package com.pon.pvt.voting.dto;

/**
 * @author Sanjeev
 *
 */
public class ElectionBalletPaperDTO {

	private Integer id;
	private String candidateName;	
	private String party;
	private String symbol;
	private String voteForCandidate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getVoteForCandidate() {
		return voteForCandidate;
	}
	public void setVoteForCandidate(String voteForCandidate) {
		this.voteForCandidate = voteForCandidate;
	}
	
	
}
