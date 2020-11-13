/**
 * 
 */
package com.pon.pvt.master.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pon.pvt.master.service.SensetivePagelinkService;
import com.support.custom.validation.classLevelValidator.Unique;
import com.support.util.AppUtil;

/**
 * @author Sanjeev
 *
 */
@Unique(fieldName = "name", service = SensetivePagelinkService.class, message = "This link is already recorded",id="id")

public class SensetivePagelinkDTO {
	    private Integer id;	
	   
		@NotBlank(message = "Required.")
	    private String name;
	   
		@NotBlank(message = "Required.")
	    private String pageUrl;
	   
		@NotBlank(message = "Required.")
	    private String activateStartDateS;	
	   
		@NotBlank(message = "Required.")
	    private String activateEndDateS;
	    
	    private Date activateStartDate;	    
	    private Date activateEndDate;	
	   
		@NotBlank(message = "Required.")
	    private String activationMessage;	
	   
		@NotBlank(message = "Required.")
	    private String denyMessage;
	   
	    private boolean channelState;
	   
		@NotBlank(message = "Required.")
	    private String channelStateS;
	    
	    private Integer totalrecords;
		public SensetivePagelinkDTO(Integer id, String name, String pageUrl, Date activateStartDate,
				Date activateEndDate, String activationMessage, String denyMessage,boolean channelState,Integer totalrecords) {
			super();
			this.id = id;
			this.name = name;
			this.pageUrl = pageUrl;
			
			this.activateStartDateS = AppUtil.convertJavaDateIntoStringDateWithTime(activateStartDate);
			this.activateEndDateS = AppUtil.convertJavaDateIntoStringDateWithTime(activateEndDate);
			
			this.activationMessage = activationMessage;
			this.denyMessage = denyMessage;
			this.channelState=channelState;
			if(channelState)
			   this.channelStateS="1";
			else
				this.channelStateS="0";
			this.totalrecords=totalrecords;
		}
		
		
		
		public SensetivePagelinkDTO(Integer id, String name, String pageUrl, String activateStartDateS,
				String activateEndDateS, String activationMessage, String denyMessage, String channelStateS) {
			super();
			this.id = id;
			this.name = name;
			this.pageUrl = pageUrl;
			this.activateStartDateS = activateStartDateS;
			this.activateEndDateS = activateEndDateS;
			this.activationMessage = activationMessage;
			this.denyMessage = denyMessage;
			this.channelStateS=channelStateS;
		}



		public SensetivePagelinkDTO() {
			// TODO Auto-generated constructor stub
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
		public String getPageUrl() {
			return pageUrl;
		}
		public void setPageUrl(String pageUrl) {
			this.pageUrl = pageUrl;
		}
		public String getActivateStartDateS() {
			return activateStartDateS;
		}
		public void setActivateStartDateS(String activateStartDateS) {
			this.activateStartDateS = activateStartDateS;
		}
		public String getActivateEndDateS() {
			return activateEndDateS;
		}
		public void setActivateEndDateS(String activateEndDateS) {
			this.activateEndDateS = activateEndDateS;
		}
		public Date getActivateStartDate() {
			return activateStartDate;
		}
		public void setActivateStartDate(Date activateStartDate) {
			this.activateStartDate = activateStartDate;
		}
		public Date getActivateEndDate() {
			return activateEndDate;
		}
		public void setActivateEndDate(Date activateEndDate) {
			this.activateEndDate = activateEndDate;
		}
		public String getActivationMessage() {
			return activationMessage;
		}
		public void setActivationMessage(String activationMessage) {
			this.activationMessage = activationMessage;
		}
		public String getDenyMessage() {
			return denyMessage;
		}
		public void setDenyMessage(String denyMessage) {
			this.denyMessage = denyMessage;
		}
		
		
		public boolean isChannelState() {
			return channelState;
		}



		public void setChannelState(boolean channelState) {
			this.channelState = channelState;
		}



		public String getChannelStateS() {
			return channelStateS;
		}



		public void setChannelStateS(String channelStateS) {
			this.channelStateS = channelStateS;
		}



		public Integer getTotalrecords() {
			return totalrecords;
		}
		public void setTotalrecords(Integer totalrecords) {
			this.totalrecords = totalrecords;
		}
}
