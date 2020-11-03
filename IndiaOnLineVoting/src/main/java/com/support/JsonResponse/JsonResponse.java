/**
 * 
 */
package com.support.JsonResponse;

import java.util.List;
import java.util.Map;

import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.ddData;

/**
 * @author Sanjeev
 *
 */
public class JsonResponse {
	private String recordId;
	private Object formObject;
	private boolean status;
	private String statusMsg;	
	private Map<String, String> fieldErrMsgMap;
	List<NameValue> comboList;
	List<ddData> imgComboList;
	
	
	
	
	
	
	
	public List<ddData> getImgComboList() {
		return imgComboList;
	}
	public void setImgComboList(List<ddData> imgComboList) {
		this.imgComboList = imgComboList;
	}
	/**
	 * @return the recordId
	 */
	public String getRecordId() {
		return recordId;
	}
	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public List<NameValue> getComboList() {
		return comboList;
	}
	public void setComboList(List<NameValue> comboList) {
		this.comboList = comboList;
	}
	/**
	 * @return the formObject
	 */
	public Object getFormObject() {
		return formObject;
	}
	/**
	 * @param formObject the formObject to set
	 */
	public void setFormObject(Object formObject) {
		this.formObject = formObject;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the statusMsg
	 */
	public String getStatusMsg() {
		return statusMsg;
	}
	/**
	 * @param statusMsg the statusMsg to set
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	/**
	 * @return the fieldErrMsgMap
	 */
	public Map<String, String> getFieldErrMsgMap() {
		return fieldErrMsgMap;
	}
	/**
	 * @param fieldErrMsgMap the fieldErrMsgMap to set
	 */
	public void setFieldErrMsgMap(Map<String, String> fieldErrMsgMap) {
		this.fieldErrMsgMap = fieldErrMsgMap;
	}
	
	
}


