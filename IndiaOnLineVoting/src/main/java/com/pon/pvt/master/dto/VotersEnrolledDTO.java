/**
 * 
 */
package com.pon.pvt.master.dto;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



/**
 * @author Sanjeev
 *
 */
//@UniqueWithParentId(parentId="pollingBoothNameId",fieldName = "voterName", service = PollingBoothService.class, message = "{city.unique.value.violation}",id="id")

public class VotersEnrolledDTO {

	private Integer id;
	private String stateName;
	private String loksabhaName;
	private String assemblyName;
	private String pollingBoothName;
	private Integer pollingBoothNo;
	private Date dob;
	private Integer totalVotes;

	@NotBlank(message = "Select one.")
	private String stateNameId;

	@NotBlank(message = "Select one.")
	private String loksabhaNameId;

	@NotBlank(message = "Select one.")
	private String assemblyNameId;

	@NotBlank(message = "Select one.")
	private String pollingBoothNameId;

	@NotBlank(message = "Required.")
	private String voterName;
    // @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	//@Pattern(regexp = "^[M|F]{1}$", message = "Must be M or F")
	
	@NotBlank(message = "Select one.")
	private String sex;

	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "Please provide a correct date.")
	//@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String dobForPage;

	@NotBlank(message = "Required.")
	private String fatherName;

	private String voterId;

	private String voting_mode;

	@NotBlank(message = "Required.")
	private String address;

	private Integer totalrecords;

	// Will be utilized by @Valid
	public VotersEnrolledDTO() {
	}

	// Will be utilized by grid
	public VotersEnrolledDTO(Integer id, String stateName, String loksabhaName, String assemblyName,
			String pollingBoothName, Integer pollingBoothNo, String voterName, String sex, Date dob, String fatherName,
			String voterId, String voting_mode, String address, Integer totalrecords) {
		this.id = id;
		this.stateName = stateName;
		this.loksabhaName = loksabhaName;
		this.assemblyName = assemblyName;
		this.pollingBoothName = pollingBoothName;
		this.pollingBoothNo = pollingBoothNo;

		this.voterName = voterName;
		this.sex = sex;
		this.dob = dob;
		this.fatherName = fatherName;
		
		 String stringArray[]=voterId.split("[-]");
		 stringArray[4]="<strong style='color:orange'>"+voterId.split("[-]")[4]+"</strong>";
		 String x=Arrays.toString(stringArray).replace(',', '-');
		this.voterId = x.substring(1, x.length() - 1); ;
		this.voting_mode = voting_mode;
		this.address = address;
		this.totalrecords = totalrecords;

	}

	// Will be utilized by modal
	public VotersEnrolledDTO(Integer id, String stateNameId, String loksabhaNameId, String assemblyNameId,
			String pollingBoothNameId, String voterName, String sex, Date dob, String fatherName, String voterId,
			String voting_mode, String address) {
		this.id = id;
		this.stateNameId = stateNameId;
		this.loksabhaNameId = loksabhaNameId;
		this.assemblyNameId = assemblyNameId;
		this.pollingBoothNameId = pollingBoothNameId;

		this.voterName = voterName;
		this.sex = sex;
		this.dob = dob;
		this.fatherName = fatherName;
		this.voterId = voterId;
		this.voting_mode = voting_mode;
		this.address = address;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getLoksabhaName() {
		return loksabhaName;
	}

	public void setLoksabhaName(String loksabhaName) {
		this.loksabhaName = loksabhaName;
	}

	public String getAssemblyName() {
		return assemblyName;
	}

	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}

	public String getPollingBoothName() {
		return pollingBoothName;
	}

	public void setPollingBoothName(String pollingBoothName) {
		this.pollingBoothName = pollingBoothName;
	}

	public Integer getPollingBoothNo() {
		return pollingBoothNo;
	}

	public void setPollingBoothNo(Integer pollingBoothNo) {
		this.pollingBoothNo = pollingBoothNo;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}

	public String getStateNameId() {
		return stateNameId;
	}

	public void setStateNameId(String stateNameId) {
		this.stateNameId = stateNameId;
	}

	public String getLoksabhaNameId() {
		return loksabhaNameId;
	}

	public void setLoksabhaNameId(String loksabhaNameId) {
		this.loksabhaNameId = loksabhaNameId;
	}

	public String getAssemblyNameId() {
		return assemblyNameId;
	}

	public void setAssemblyNameId(String assemblyNameId) {
		this.assemblyNameId = assemblyNameId.trim();
	}

	public String getPollingBoothNameId() {
		return pollingBoothNameId;
	}

	public void setPollingBoothNameId(String pollingBoothNameId) {
		this.pollingBoothNameId = pollingBoothNameId.trim();
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDobForPage() {
		return dobForPage;
	}

	public void setDobForPage(String dobForPage) {
		this.dobForPage = dobForPage;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getVoting_mode() {
		return voting_mode;
	}

	public void setVoting_mode(String voting_mode) {
		this.voting_mode = voting_mode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
	}

}
