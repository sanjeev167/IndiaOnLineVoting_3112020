/**
 * 
 */
package com.pon.pvt.voting.dto;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.support.util.FileResourcesUtils;

/**
 * @author Sanjeev
 *
 */
public class LoksabhaBalletForEnrolledVotersDTO {
	
	private Integer loksabhaCandidateId;
	private String pollingBoothName;
	private Integer pollingBoothNo;
	private String loksabhaName;
	private String loksabhaCandidateName;
	private String partyName;
	private Integer partyNameId;
	private String symbolName;
	private Integer totalrecords;
	
	private String imageName;
	
	
	public LoksabhaBalletForEnrolledVotersDTO(Integer loksabhaCandidateId,  String loksabhaName, String loksabhaCandidateName, String partyName,Integer partyNameId,
			String symbolName, Integer totalrecords) {
		super();
		this.loksabhaCandidateId = loksabhaCandidateId;
		
		this.loksabhaName = loksabhaName;
		this.loksabhaCandidateName = loksabhaCandidateName;
		this.partyName = partyName;
		this.partyNameId=partyNameId;
		// Prepare base64Img with base64ImgName
		InputStream inputStream = new FileResourcesUtils().getFileFromResourceAsStream("image/" + symbolName);
		String fileExt = symbolName.substring(symbolName.length() - 3);
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Encode
		String resultBase64Encoded = java.util.Base64.getEncoder().encodeToString(bytes);
		this.imageName=symbolName;
		this.symbolName="data:image/"+fileExt+";base64,"+resultBase64Encoded;		
		this.totalrecords = totalrecords;
	}
	
	
	
	public Integer getLoksabhaCandidateId() {
		return loksabhaCandidateId;
	}
	public void setLoksabhaCandidateId(Integer loksabhaCandidateId) {
		this.loksabhaCandidateId = loksabhaCandidateId;
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
	public String getLoksabhaName() {
		return loksabhaName;
	}
	public void setLoksabhaName(String loksabhaName) {
		this.loksabhaName = loksabhaName;
	}
	public String getLoksabhaCandidateName() {
		return loksabhaCandidateName;
	}
	public void setLoksabhaCandidateName(String loksabhaCandidateName) {
		this.loksabhaCandidateName = loksabhaCandidateName;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getSymbolName() {
		return symbolName;
	}
	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}
	public Integer getTotalrecords() {
		return totalrecords;
	}
	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
	}



	public String getImageName() {
		return imageName;
	}



	public void setImageName(String imageName) {
		this.imageName = imageName;
	}



	public Integer getPartyNameId() {
		return partyNameId;
	}



	public void setPartyNameId(Integer partyNameId) {
		this.partyNameId = partyNameId;
	}
	
	
}
