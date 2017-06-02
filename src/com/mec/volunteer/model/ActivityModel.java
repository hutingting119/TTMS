package com.mec.volunteer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name=IRepositoryMapping.TABLE_ACTIVITY)
@JsonInclude(content=Include.NON_NULL)
public class ActivityModel {
	@Id
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_ID)
	private String id;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_COMPANY)
	private String company;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_HEAD)
	private String head;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_PHONE)
	private String phone;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_TYPE)
	private String type;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_TIME)
	private String time;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_CONTENT)
	private String content;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_ADDRESS)
	private String address;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_TITLE)
	private String title;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_STATUS)
	private String status;
	@Column(name=IRepositoryMapping.TABLE_ACTIVITY_COMPANY_ID)
	private String userId;
	
	public ActivityModel() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ActivityModel [id=" + id + ", company=" + company + ", head=" + head + ", phone=" + phone + ", type="
				+ type + ", time=" + time + ", content=" + content + ", address=" + address + ", title=" + title
				+ ", status=" + status + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
