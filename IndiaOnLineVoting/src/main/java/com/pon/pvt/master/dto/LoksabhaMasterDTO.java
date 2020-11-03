/**
 * 
 */
package com.pon.pvt.master.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pon.pvt.master.service.StateService;
import com.support.custom.validation.classLevelValidator.UniqueWithParentId;



/**
 * @author Sanjeev
 *
 */
@UniqueWithParentId(parentId="stateId",fieldName = "loksabhaName", service = StateService.class, message = "{state.unique.value.violation}",id="id")	
public class LoksabhaMasterDTO {
	
	private Integer id;
	private String stateName;	
	private Integer loksabhaNo;
	
	@NotNull
	@NotBlank(message = "Select one.")
	private String stateId;	
	
	@NotNull
	@NotBlank(message = "Required.")	
	private String loksabhaName;

	private Integer totalrecords;

	public LoksabhaMasterDTO(Integer id, Integer loksabhaNo, String stateName,String loksabhaName, Integer totalrecords) {
		this.id = id;
		this.loksabhaNo=loksabhaNo;
		this.stateName = stateName;
		this.loksabhaName = loksabhaName;
		this.totalrecords = totalrecords;
	}

	public LoksabhaMasterDTO(Integer id, String stateName, Integer stateId, String loksabhaName,Integer loksabhaNo) {
		this.id = id;
		this.stateName = stateName;		
		this.stateId = stateId+"";
		this.loksabhaName = loksabhaName;
		this.loksabhaNo=loksabhaNo;
	}

	public LoksabhaMasterDTO(Integer id, String stateId, String loksabhaName,Integer loksabhaNo) {
		this.id = id;			
		this.stateId = stateId;
		this.loksabhaName = loksabhaName;
		this.loksabhaNo=loksabhaNo;
	}
	
	//Will be utilized by @Valid
	public LoksabhaMasterDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	
	public Integer getLoksabhaNo() {
		return loksabhaNo;
	}

	public void setLoksabhaNo(Integer loksabhaNo) {
		this.loksabhaNo = loksabhaNo;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getLoksabhaName() {
		return loksabhaName;
	}

	public void setLoksabhaName(String loksabhaName) {
		this.loksabhaName = loksabhaName;
	}

	public Integer getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
	}

	
	
	

}
