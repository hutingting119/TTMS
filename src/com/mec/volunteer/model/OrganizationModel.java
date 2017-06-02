package com.mec.volunteer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name=IRepositoryMapping.TABLE_ORGANIZATION)
@JsonInclude(content=Include.NON_NULL)
public class OrganizationModel extends ErrorMessage {
	@Id
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_ID)
	private String id;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_PASSWORD)
	private String password;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_NAME)
	private String name;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_PEOPLE)
	private String people;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_NUMBER)
	private String number;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_STYLE)
	private String style;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_PHONE)
	private String phone;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_BUSINESS)
	private String business;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_FORM)
	private String form;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_LOCATION)
	private String location;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_CHECK)
	private String check;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_EMAIL)
	private String email;
	@Column(name=IRepositoryMapping.TABLE_ORGANIZATION_TYPE)
	private String type;
	
	public OrganizationModel() {}

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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPeople() {
		return people;
	}
	
	public void setPeople(String people) {
		this.people = people;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getStyle() {
		return style;
	}
	
	public void setStyle(String style) {
		this.style = style;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getBusiness() {
		return business;
	}
	
	public void setBusiness(String business) {
		this.business = business;
	}
	
	public String getForm() {
		return form;
	}
	
	public void setForm(String form) {
		this.form = form;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getCheck() {
		return check;
	}
	
	public void setCheck(String check) {
		this.check = check;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "OrganizationModel [id=" + id + ", password=" + password + ", name=" + name + ", people=" + people
				+ ", number=" + number + ", style=" + style + ", phone=" + phone + ", business=" + business + ", form="
				+ form + ", location=" + location + ", check=" + check + ", email=" + email + ", type=" + type + "]";
	}
}
