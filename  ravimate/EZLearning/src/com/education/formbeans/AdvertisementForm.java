package com.education.formbeans;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

import com.education.transferobj.AdvertisementTO;

public class AdvertisementForm extends ActionForm {

	public static final String tableName = "t_Advertisement";
	private int id;
	private String text;
	private String keyword;
	private String type;
	private String category;
	private String Target;
	private String Status;
	private Date startDate;
	private Date endDate;

	public AdvertisementForm() {

	}

	public AdvertisementForm(AdvertisementTO advertisement) {
		id = advertisement.getId();
		text = advertisement.getText();
		keyword = advertisement.getKeyword();
		type = advertisement.getType();
		category = advertisement.getCategory();
		Target = advertisement.getTarget();
		Status = advertisement.getStatus();
		startDate = advertisement.getStartDate();
		endDate = advertisement.getEndDate();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTarget() {
		return Target;
	}

	public void setTarget(String target) {
		Target = target;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}