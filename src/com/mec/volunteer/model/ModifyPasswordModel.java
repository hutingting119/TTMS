package com.mec.volunteer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content=Include.NON_NULL)
public class ModifyPasswordModel {
	private String id;
	private String oldPassword;
	private String newPassword;
	
	public ModifyPasswordModel() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ModifyPasswordModel [id=" + id + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}
}
