/**
 * 
 */
package com.pon.pvt.master.dto;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.io.IOUtils;

import com.support.util.FileResourcesUtils;




/**
 * @author Sanjeev
 *
 */
//@UniqueWithParentId(parentId="stateNameId",fieldName = "partyName", service = PartyService.class, message = "{state.unique.value.violation}",id="id")	
public class PartyMasterDTO {
	
	private Integer id;
	private String stateName;	
	private String imgUrl;	
	private String partyType;
	
	private String base64ImgName;
	
	//@NotNull(message = "Select one.")
	//@NotEmpty(message = "Select.")
	@NotBlank(message = "Select one.")
	private String stateNameId;	
	
	//@NotNull(message = "Required.")
	@NotBlank(message = "Required.")	
	private String partyName;
	
	//@NotNull(message = "Select one.")
	@NotEmpty(message = "Select one.")
	//@NotBlank(message = "Select one.")
	private String partySymbolId;	
	
	//@NotNull(message = "Required.")
	@NotBlank(message = "Required.")	
	private String partyTypeId;

	private Integer totalrecords;

	public PartyMasterDTO(Integer id, String stateName,String partyName, String partyType,String base64ImgName, Integer totalrecords) {
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
		this.partyName = partyName;
		this.partyType=partyType;
		this.base64ImgName="data:image/"+fileExt+";base64,"+resultBase64Encoded;		
		this.totalrecords = totalrecords;
	}

	public PartyMasterDTO(Integer id, String stateName, String stateNameId, String partyName,String partyTypeId,String partySymbolId) {
		this.id = id;
		this.stateName = stateName;		
		this.stateNameId = stateNameId;
		this.partyName = partyName;
		this.partyTypeId=partyTypeId;
		this.partySymbolId=partySymbolId;
	}	
	
	//Will be utilized by @Valid
	public PartyMasterDTO() {}

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStateNameId() {
		return stateNameId;
	}

	public void setStateNameId(String stateNameId) {
		this.stateNameId = stateNameId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartySymbolId() {
		return partySymbolId;
	}

	public void setPartySymbolId(String partySymbolId) {
		this.partySymbolId = partySymbolId;
	}

	public String getPartyTypeId() {
		return partyTypeId;
	}

	public void setPartyTypeId(String partyTypeId) {
		this.partyTypeId = partyTypeId;
	}
	
	

	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	public String getBase64ImgName() {
		
		return base64ImgName;
	}

	public void setBase64ImgName(String base64ImgName) {		
		
		this.base64ImgName = base64ImgName;
	}

	public Integer getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
	}

	
	
	
}
