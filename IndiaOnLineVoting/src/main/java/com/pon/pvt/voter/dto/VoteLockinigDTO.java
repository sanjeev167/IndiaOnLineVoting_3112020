/**
 * 
 */
package com.pon.pvt.voter.dto;

import java.sql.Date;

/**
 * @author Sanjeev
 *
 */
public class VoteLockinigDTO {

	private Integer id;
	private String name;
	
	private String lockStatus;
	private Integer lockStatusId;
	private String voterId;
	private String aadharId;
	private String reqIpAdd;
	private Integer onlineVoterAccountId;
	private String election_year;
	private String dateOfLocking;
	
	
	private String father;	
	private String boothName;
	private String boothNo;
	private String address;
	private Integer totalrecords;
		
	public VoteLockinigDTO(Integer id, String name, Integer lockStatusId, String voterId,  String reqIpAdd,
			Integer onlineVoterAccountId,  
			String dateOfLocking, 
			Integer totalrecords) {
		super();
		this.id = id;
		this.name = name;
		this.lockStatusId = lockStatusId;
		this.voterId = voterId;		
		this.reqIpAdd = reqIpAdd;
		this.onlineVoterAccountId = onlineVoterAccountId;		
		this.dateOfLocking = dateOfLocking;		
		this.totalrecords=totalrecords;
		
	}

	public VoteLockinigDTO(Integer id, String name,String lockStatus, String voterId,String aadharId, String reqIpAdd, Integer onlineVoterAccountId,
			String election_year,String dateOfLocking) {
		super();
		this.id = id;
		this.name=name;
		this.lockStatus = lockStatus;
		this.voterId = voterId;
		this.aadharId=aadharId;
		this.reqIpAdd = reqIpAdd;
		this.onlineVoterAccountId = onlineVoterAccountId;
		this.election_year = election_year;
		this.dateOfLocking=dateOfLocking;
	}


	public VoteLockinigDTO() {
		// TODO Auto-generated constructor stub
	}


	public Integer getTotalrecords() {
		return totalrecords;
	}



	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
	}



	


	public Integer getLockStatusId() {
		return lockStatusId;
	}



	public void setLockStatusId(Integer lockStatusId) {
		this.lockStatusId = lockStatusId;
	}



	public void setBoothNo(String boothNo) {
		this.boothNo = boothNo;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLockStatus() {
		return lockStatus;
	}


	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}


	public String getVoterId() {
		return voterId;
	}


	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}


	public String getAadharId() {
		return aadharId;
	}


	public void setAadharId(String aadharId) {
		this.aadharId = aadharId;
	}


	public String getReqIpAdd() {
		return reqIpAdd;
	}


	public void setReqIpAdd(String reqIpAdd) {
		this.reqIpAdd = reqIpAdd;
	}


	public Integer getOnlineVoterAccountId() {
		return onlineVoterAccountId;
	}


	public void setOnlineVoterAccountId(Integer onlineVoterAccountId) {
		this.onlineVoterAccountId = onlineVoterAccountId;
	}


	public String getElection_year() {
		return election_year;
	}


	public void setElection_year(String election_year) {
		this.election_year = election_year;
	}


	public String getDateOfLocking() {
		return dateOfLocking;
	}


	public void setDateOfLocking(String dateOfLocking) {
		this.dateOfLocking = dateOfLocking;
	}


	public String getFather() {
		return father;
	}


	public void setFather(String father) {
		this.father = father;
	}


	public String getBoothName() {
		return boothName;
	}


	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}


	public String getBoothNo() {
		return boothNo;
	}


	public void setBoothNO(String boothNo) {
		this.boothNo = boothNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	  
	 
	 
	  
}
