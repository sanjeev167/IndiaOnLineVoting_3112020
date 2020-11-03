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
@UniqueWithParentId(parentId="assemblyNameId",fieldName = "pollingBoothName", service = AssemblyService.class, message = "{city.unique.value.violation}",id="id")
	
public class PollingBoothMasterDTO {

	private Integer id;
	private String stateName;
	private String loksabhaName;	
	private String assemblyName;
	
	
	@NotNull()
	@NotBlank(message = "Select one.")
	private String stateNameId;
	
	@NotNull()
	@NotBlank(message = "Select one.")
	private String loksabhaNameId;
	
	@NotNull()
	@NotBlank(message = "Select one.")		
	private String assemblyNameId;
	
	@NotNull()
	@NotBlank(message = "Required.")
	private String pollingBoothName;
	
	private Integer pollingBoothNo;
	
	
	private Integer totalrecords;

	//Will be utilized by @Valid
	public PollingBoothMasterDTO() {}
	
	//Will be utilized by grid
	public PollingBoothMasterDTO(Integer id, String stateName, String loksabhaName, String assemblyName,String pollingBoothName, Integer pollingBoothNo, Integer totalrecords) {
		this.id = id;
		this.stateName = stateName;
		this.loksabhaName = loksabhaName;
		this.assemblyName = assemblyName;
		this.pollingBoothName=pollingBoothName;
		this.pollingBoothNo=pollingBoothNo;
		this.totalrecords = totalrecords;
	}
	
	
	
	//Will be utilized by modal
		public PollingBoothMasterDTO(Integer id, String stateNameId, String loksabhaNameId, String assemblyNameId,String pollingBoothName, Integer pollingBoothNo) {
			this.id = id;
			this.stateNameId = stateNameId;
			this.loksabhaNameId = loksabhaNameId;
			this.assemblyNameId = assemblyNameId;			
			this.pollingBoothName=pollingBoothName;
			this.pollingBoothNo=pollingBoothNo;			
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
			this.assemblyNameId = assemblyNameId;
		}

		public Integer getTotalrecords() {
			return totalrecords;
		}

		public void setTotalrecords(Integer totalrecords) {
			this.totalrecords = totalrecords;
		}

		
}
