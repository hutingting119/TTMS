package com.mec.volunteer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name=IRepositoryMapping.TABLE_VOLUNTEER)
@JsonInclude(content=Include.NON_NULL)
public class VolunteerModel extends ErrorMessage {
	@Id
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_ID)
	private String id;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_PASSWORD)
	private String password;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_NAME)
	private String name;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_SEX)
	private String sex;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_ETHNIC)
	private String ethnic;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_PHONE)
	private String phone;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_EMAIL)
	private String email;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_IDCARD)
	private String idcard;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_BIRTHDAY)
	private String birthday;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_PROFESSION)
	private String profession;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_QQ)
	private String qq;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_ADDRESS)
	private String address;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_SERVICE)
	private String service;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_SPECIALTY)
	private String specialty;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_EXPERIENCE)
	private String experience;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_STREET)
	private String street;
	@Column(name=IRepositoryMapping.TABLE_VOLUNTEER_TYPE)
	private String type;

	public VolunteerModel() {}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", password=" + password + ", name=" + name + ", sex=" + sex + ", ethnic="
				+ ethnic + ", phone=" + phone + ", email=" + email + ", idcard=" + idcard + ", birthday=" + birthday
				+ ", profession=" + profession + ", qq=" + qq + ", address=" + address + ", service=" + service
				+ ", specialty=" + specialty + ", experience=" + experience + ", street=" + street + ", type=" + type
				+ "]";
	}
}
