package com.mec.volunteer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name=IRepositoryMapping.TABLE_SERVICE)
@JsonInclude(content=Include.NON_NULL)
public class ServiceModel {
	@Id
	@Column(name=IRepositoryMapping.TABLE_SERVICE_CAPTAIN_ID)
	private String id;
	@Column(name=IRepositoryMapping.TABLE_SERVICE_NAME)
	private String name;
	@Column(name=IRepositoryMapping.TABLE_SERVICE_CAPTAIN)
	private String captatin;
	@Column(name=IRepositoryMapping.TABLE_SERVICE_PHONE)
	private String phone;
	@Column(name=IRepositoryMapping.TABLE_SERVICE_STATUS)
	private String status;
	@Column(name="service_situation")
	private String situation;
	@Column(name="service_introduce")
	private String introduce;
	@Column(name="service_count")
	private String count;
	
	public ServiceModel() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaptatin() {
		return captatin;
	}

	public void setCaptatin(String captatin) {
		this.captatin = captatin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatuc() {
		return status;
	}

	public void setStatuc(String statuc) {
		this.status = statuc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ServiceModel [id=" + id + ", name=" + name + ", captatin=" + captatin + ", phone=" + phone + ", status="
				+ status + ", situation=" + situation + ", introduce=" + introduce + ", count=" + count + "]";
	}
}
