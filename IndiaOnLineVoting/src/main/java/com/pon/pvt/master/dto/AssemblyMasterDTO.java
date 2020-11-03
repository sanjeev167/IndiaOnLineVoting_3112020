/**
 * 
 */
package com.pon.pvt.master.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pon.pvt.master.service.AssemblyService;
import com.support.custom.validation.classLevelValidator.UniqueWithParentId;

/**
 * @author Sanjeev
 *
 */
@UniqueWithParentId(parentId="loksabhaNameId",fieldName = "assemblyName", service = AssemblyService.class, message = "{city.unique.value.violation}",id="id")
	
public class AssemblyMasterDTO {

	private Integer id;
	private String stateName;
	private String loksabhaName;
	
	private Integer assemblyNo;
	
	@NotNull()
	@NotBlank(message = "Select one.")
	private String stateNameId;
	
	@NotNull()
	@NotBlank(message = "Select one.")
	private String loksabhaNameId;
	
	@NotNull()
	@NotBlank(message = "Required.")		
	private String assemblyName;
	
	
	private Integer totalrecords;

	//Will be utilized by @Valid
	public AssemblyMasterDTO() {}
	
	//Will be utilized by grid
	public AssemblyMasterDTO(Integer id, String stateName, String loksabhaName, String assemblyName,Integer assemblyNo, Integer totalrecords) {
		this.id = id;
		this.stateName = stateName;
		this.loksabhaName = loksabhaName;
		this.assemblyName = assemblyName;
		this.assemblyNo=assemblyNo;
		this.totalrecords = totalrecords;
	}
	
	//Will be utilized by grid
		public AssemblyMasterDTO(Integer id, String stateNameId, String loksabhaNameId, String assemblyName,Integer assemblyNo) {
			this.id = id;
			this.stateNameId = stateNameId;
			this.loksabhaNameId = loksabhaNameId;
			this.assemblyName = assemblyName;
			this.assemblyNo=assemblyNo;
			
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

		public String getAssemblyName() {
			return assemblyName;
		}

		public void setAssemblyName(String assemblyName) {
			this.assemblyName = assemblyName;
		}
		
		

		public Integer getAssemblyNo() {
			return assemblyNo;
		}

		public void setAssemblyNo(Integer assemblyNo) {
			this.assemblyNo = assemblyNo;
		}

		public Integer getTotalrecords() {
			return totalrecords;
		}

		public void setTotalrecords(Integer totalrecords) {
			this.totalrecords = totalrecords;
		}

		

	
}
