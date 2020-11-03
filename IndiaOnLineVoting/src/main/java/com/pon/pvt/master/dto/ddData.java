/**
 * 
 */
package com.pon.pvt.master.dto;

/**
 * @author Sanjeev
 *
 */
public class ddData {
private String text;
private String value;
private String selected;
private String description;
private String imageSrc;
public ddData(String text, String value, String selected, String description, String imagesrc) {
	super();
	this.text = text;
	this.value = value;
	this.selected = selected;
	this.description = description;
	this.imageSrc = imagesrc;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public String getSelected() {
	return selected;
}
public void setSelected(String selected) {
	this.selected = selected;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImageSrc() {
	return imageSrc;
}
public void setImageSrc(String imageSrc) {
	this.imageSrc = imageSrc;
}



}
