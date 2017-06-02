package com.mec.volunteer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name=IRepositoryMapping.TABLE_ADMIN)
@JsonInclude(content=Include.NON_NULL)
public class AdminModel extends ErrorMessage {
	@Id
	@Column(name=IRepositoryMapping.TABLE_ADMIN_ID)
	private String id;
	@Column(name=IRepositoryMapping.TABLE_ADMIN_PASSWORD)
	private String password;
	@Column(name="admin_phone")
	private String phone;
	@Column(name="admin_time")
	private Date time;
	@Column(name="admin_address")
	private String address;
	@Column(name="admin_people")
	private String people;
	@Column(name="admin_name")
	private String name;
	
	public AdminModel() {}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AdminModel [id=" + id + ", password=" + password + ", phone=" + phone + ", time=" + time + ", address="
				+ address + ", people=" + people + ", name=" + name + "]";
	}
}
