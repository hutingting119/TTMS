package com.mec.volunteer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;
import com.mec.volunteer.service.RegistService;

@Controller
public class RegistAction {
	@Autowired
	private RegistService registService;
	
	@ResponseBody
	@RequestMapping("/volunteerRegistAction")
	public ErrorMessage volunteerRegistAction(VolunteerModel model) {
		return registService.volunteerRegistAction(model);
	}
	
	@ResponseBody
	@RequestMapping("/organizationRegistAction")
	public ErrorMessage organizationRegistAction(OrganizationModel model) {
		return registService.organizationRegistAction(model);
	}
	
	@ResponseBody
	@RequestMapping("/adminRegistAction")
	public ErrorMessage adminRegistAction(AdminModel model) {
		return registService.adminRegistAction(model);
	}
}
