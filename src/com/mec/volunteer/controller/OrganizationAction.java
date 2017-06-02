package com.mec.volunteer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.ModifyPasswordModel;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.service.OrganizationService;

@Controller
public class OrganizationAction {
	@Autowired
	private OrganizationService organizationService;

	@ResponseBody
	@RequestMapping("/getOrganizationModel")
	public OrganizationModel getOrganizationModel(String id) {
		return organizationService.getOrganizationModel(id);
	}
	
	@ResponseBody
	@RequestMapping("/registNewActivity")
	public ErrorMessage registNewActivity(ActivityModel model) {
		return organizationService.registNewActivity(model);
	}
	
	@ResponseBody
	@RequestMapping("/getAllActivityByOrganization")
	public List<?> getAllActivityByOrganization(String id) {
		return organizationService.getAllActivityByOrganization(id);
	}
	
	@ResponseBody
	@RequestMapping("/modifyOrganizationInfo")
	public ErrorMessage modifyOrganizationInfo(OrganizationModel model) {
		return organizationService.modifyOrganizationInfo(model);
	}
	
	@ResponseBody
	@RequestMapping("/removeOrganizationActivity")
	public ErrorMessage removeOrganizationActivity(String id) {
		return organizationService.removeOrganizationActivity(id);
	}
	
	@ResponseBody
	@RequestMapping("/modfiyOrganizationPassword")
	public ErrorMessage modfiyOrganizationPassword(ModifyPasswordModel model) {
		return organizationService.modfiyOrganizationPassword(model);
	}
}
