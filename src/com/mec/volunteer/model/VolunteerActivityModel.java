package com.mec.volunteer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name=IRepositoryMapping.TABLE_VOLUNTEER_ACTIVITY)
@JsonInclude(content=Include.NON_NULL)
public class VolunteerActivityModel {
	@Id
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_ACTIVITY_ID)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_ACTIVITY_VOLUNTEER_ID)
	private String volunteerId;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_ACTIVITY_ACTIVITY_ID)
	private String activityId;
	
	public VolunteerActivityModel() {}

	public String getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "VolunteerActivityModel [id=" + id + ", volunteerId=" + volunteerId + ", activityId=" + activityId + "]";
	}
}
