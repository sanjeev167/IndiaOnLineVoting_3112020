/**
 * 
 */
package com.pon.pvt.master.dto;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;

import com.pon.pvt.master.service.LoksabhaCandidateService;
import com.support.custom.validation.classLevelValidator.UniqueWithParentId;
import com.support.util.FileResourcesUtils;

/**
 * @author Sanjeev
 *
 */
@UniqueWithParentId(parentId="loksabhaNameId",fieldName = "lcandidateName", service = LoksabhaCandidateService.class, message = "{city.unique.value.violation}",id="id")
	
public class LoksabhaCandidateMasterDTO {

	private Integer id;
	private String stateName;
	private String loksabhaName;
	private Integer electionYear;
	
	private Integer lcandidateNo;
	
	private String imgName;
	private String partyName;
	
	private char nominated;
	
	//@NotNull()
	@NotBlank(message = "Select one.")
	private String stateNameId;
	
	//@NotNull()
	@NotBlank(message = "Select one.")
	private String loksabhaNameId;
	
	//@NotNull()
		@NotBlank(message = "Select one.")
		private String partyNameId;
	
	//@NotNull()
	@NotBlank(message = "Required.")		
	private String lcandidateName;
	
	
	private Integer totalrecords;

	//Will be utilized by @Valid
	public LoksabhaCandidateMasterDTO() {}
	
	//Will be utilized by grid
	public LoksabhaCandidateMasterDTO(Integer id, String stateName, String loksabhaName,String partyName, String lcandidateName,Integer lcandidateNo,Integer electionYear,String base64ImgName, Integer totalrecords) {
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
		this.partyName=partyName;
		this.lcandidateName = lcandidateName;
		this.lcandidateNo=lcandidateNo;
		this.electionYear=electionYear;
		this.imgName="data:image/"+fileExt+";base64,"+resultBase64Encoded;
		this.totalrecords = totalrecords;
	}
	
	//Will be utilized by grid
		public LoksabhaCandidateMasterDTO(Integer id, String stateNameId, String loksabhaNameId, String lcandidateName,Integer electionYear,Integer lcandidateNo,String imgName,String partyNameId,char nominated ) {
			this.id = id;
			this.stateNameId = stateNameId;
			this.loksabhaNameId = loksabhaNameId;
			this.lcandidateName = lcandidateName;			
			this.electionYear=electionYear;
			this.lcandidateNo=lcandidateNo;
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

		public String getLcandidateName() {
			return lcandidateName;
		}

		public void setLcandidateName(String lcandidateName) {
			this.lcandidateName = lcandidateName;
		}
		
		

		public char getNominated() {
			return nominated;
		}

		public void setNominated(char nominated) {
			this.nominated = nominated;
		}

		public Integer getLcandidateNo() {
			return lcandidateNo;
		}

		public void setLcandidateNo(Integer lcandidateNo) {
			this.lcandidateNo = lcandidateNo;
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
