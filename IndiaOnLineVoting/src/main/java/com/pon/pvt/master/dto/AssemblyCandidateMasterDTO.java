/**
 * 
 */
package com.pon.pvt.master.dto;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;

import com.pon.pvt.master.service.AssemblyCandidateService;
import com.support.custom.validation.classLevelValidator.UniqueWithParentId;
import com.support.util.FileResourcesUtils;

/**
 * @author Sanjeev
 *
 */
@UniqueWithParentId(parentId="assemblyNameId",fieldName = "assemblyName", service = AssemblyCandidateService.class, message = "{city.unique.value.violation}",id="id")
	
public class AssemblyCandidateMasterDTO {

	private Integer id;
	private String stateName;
	private String loksabhaName;
	private String assemblyName;
	private Integer electionYear;
	
	private Integer acandidateNo;
	
	private String imgName;
	private String partyName;
	
	private String nominated;
	
	//@NotNull()
	@NotBlank(message = "Select one.")
	private String stateNameId;
	
	//@NotNull()
	@NotBlank(message = "Select one.")
	private String loksabhaNameId;
	
	//@NotNull()
		@NotBlank(message = "Select one.")
		private String assemblyNameId;
	
	//@NotNull()
		@NotBlank(message = "Select one.")
		private String partyNameId;
	
	//@NotNull()
	@NotBlank(message = "Required.")		
	private String acandidateName;
	
	
	private Integer totalrecords;

	//Will be utilized by @Valid
	public AssemblyCandidateMasterDTO() {}
	
	//Will be utilized by grid
	public AssemblyCandidateMasterDTO(Integer id, String stateName, String loksabhaName,String assemblyName,String partyName, String acandidateName,Integer lcandidateNo,
			Integer electionYear,String base64ImgName, Integer totalrecords) {
		//Prepare base64Img with base64ImgName		
				InputStream inputStream=new FileResourcesUtils().getFileFromResourceAsStream("image/"+base64ImgName);		
				String fileExt = base64ImgName.substring(base64ImgName.length() - 3);
				byte[] bytes = null;
				try {
					bytes = IOUtils.toByteArray(inputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 // Encode 
				String resultBase64Encoded = java.util.Base64.getEncoder().encodeToString(bytes);	
		
		
		
		this.id = id;
		this.stateName = stateName;
		this.loksabhaName = loksabhaName;		
		this.assemblyName =assemblyName;
		this.partyName=partyName;
		this.acandidateName = acandidateName;
		this.acandidateNo=acandidateNo;
		this.electionYear=electionYear;
		this.imgName="data:image/"+fileExt+";base64,"+resultBase64Encoded;
		this.totalrecords = totalrecords;
	}
	
	//Will be utilized by grid
		public AssemblyCandidateMasterDTO(Integer id, String stateNameId, String loksabhaNameId,String assemblyNameId, String acandidateName,Integer electionYear,
				Integer acandidateNo,String imgName,String partyNameId,String nominated ) {
			this.id = id;
			this.stateNameId = stateNameId;
			this.loksabhaNameId = loksabhaNameId;
			this.assemblyNameId = assemblyNameId;
			
			this.acandidateName = acandidateName;			
			this.electionYear=electionYear;
			this.acandidateNo=acandidateNo;
			this.imgName=imgName;
			this.partyNameId=partyNameId;
			this.nominated= nominated;
			
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

		public String getAssemblyNameId() {
			return assemblyNameId;
		}

		public void setAssemblyNameId(String assemblyNameId) {
			this.assemblyNameId = assemblyNameId;
		}

		public String getAcandidateName() {
			return acandidateName;
		}

		public void setAcandidateName(String acandidateName) {
			this.acandidateName = acandidateName;
		}
		
		

		public String getNominated() {
			return nominated;
		}

		public void setNominated(String nominated) {
			this.nominated = nominated;
		}

		public Integer getAcandidateNo() {
			return acandidateNo;
		}

		public void setAcandidateNo(Integer acandidateNo) {
			this.acandidateNo = acandidateNo;
		}

		
		public Integer getElectionYear() {
			return electionYear;
		}

		
		
		public String getPartyName() {
			return partyName;
		}

		public void setPartyName(String partyName) {
			this.partyName = partyName;
		}

		public String getPartyNameId() {
			return partyNameId;
		}

		public void setPartyNameId(String partyNameId) {
			this.partyNameId = partyNameId;
		}

		public String getImgName() {
			return imgName;
		}

		public void setImgName(String imgName) {
			this.imgName = imgName;
		}

		public void setElectionYear(Integer electionYear) {
			this.electionYear = electionYear;
		}

		public Integer getTotalrecords() {
			return totalrecords;
		}

		public void setTotalrecords(Integer totalrecords) {
			this.totalrecords = totalrecords;
		}

		

	
}
