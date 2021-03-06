/**
 * 
 */
package com.pon.pub.hm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.support.custom.validation.fieldLevelValidator.ContactNumberConstraint;

/**
 * @author Sanjeev
 *
 */
public class RegistrationDTO {

	private Integer id;	
	private String voterId;
	private String aadharId;
	private String voterRequestIpAddress;
	
	@NotEmpty(message = "User name is required.")	
	private String name;
	
	@NotEmpty(message = "User login is required.")
	@Email(message = "Invalid email")
	private String userLoginId;
	
	@ContactNumberConstraint
	@NotEmpty(message = "Mobile no is required.")
	private String mobileNo;
	
	
	@NotBlank(message = "Give an online voting account secret")
	private String secret;
	
	
	//@ValidPassword	
	@NotBlank(message = "Password is required.")	
	private String password;
	
	//@NotBlank(message = "Password confirm is required.")	
	private String passwordConf;

	
	
	
	public RegistrationDTO(Integer id2, String name2, String mailId, String pwd) {
		// TODO Auto-generated constructor stub
	}

	public RegistrationDTO() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public String getVoterRequestIpAddress() {
		return voterRequestIpAddress;
	}

	public void setVoterRequestIpAddress(String voterRequestIpAddress) {
		this.voterRequestIpAddress = voterRequestIpAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConf() {
		return passwordConf;
	}

	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}

		
	
}
