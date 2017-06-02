package com.mec.volunteer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="service_volunteer_tab")
@JsonInclude(content=Include.NON_NULL)
public class ServiceVolunteerModel {
	@Id
	@Column(name="volunteer_id")
	private String volunteerId;
	@Column(name="service_id")
	private String serviceId;
	
	public ServiceVolunteerModel() {}

	public String getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String toString() {
		return "ServiceVolunteerModel [ volunteerId=" + volunteerId + ", serviceId=" + serviceId + "]";
	}
}
