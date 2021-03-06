/**
 * 
 */
package com.pon.pub.hm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.pon.pub.hm.service.RegistrationService;
import com.support.custom.validation.classLevelValidator.MatchPassword;
import com.support.custom.validation.classLevelValidator.Unique;

/**
 * @author Sanjeev
 *
 */
@Unique(message = "{email.unique.value.violation}", fieldName = "userLoginId", id = "id", service = RegistrationService.class)
@MatchPassword(message = "Passwords don't match.", password = "password", passwordConf = "passwordConf")
public class LoginDTO {
	
	private Integer id;

	@NotEmpty(message = "User name is required.")
	private String name;

	@NotEmpty(message = "User login is required.")
	@Email(message = "Invalid email")
	private String userLoginId;

	// @ValidPassword
	@NotBlank(message = "Password is required.")
	private String password;

	@NotBlank(message = "Password confirm is required.")
	private String passwordConf;

	private String voterId;
	
	
	
	
	
	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
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

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
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
