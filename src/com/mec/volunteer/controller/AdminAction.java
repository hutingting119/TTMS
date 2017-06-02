package com.mec.volunteer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.ModifyPasswordModel;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;
import com.mec.volunteer.service.AdminService;

@Controller
public class AdminAction {
	@Autowired(required=false)
	private AdminService adminService;

	@ResponseBody
	@RequestMapping("/addAdminListModel")
	public List<AdminModel> addAdminListModel(String id) {
		return adminService.addAdminListModel(id);
	}

	@ResponseBody
	@RequestMapping("/addVolunteerListModel")
	public List<VolunteerModel> addVolunteerListModel(String id) {
		return adminService.addVolunteerListModel(id);
	}

	@ResponseBody
	@RequestMapping("/addOrganizationListModel")
	public List<OrganizationModel> addOrganizationListModel(String id) {
		return adminService.addOrganizationListModel(id);
	}

	@ResponseBody
	@RequestMapping("/addActivityListModel")
	public List<ActivityModel> addActivityListModel(String id) {
		return adminService.addActivityListModel(id);
	}

	@ResponseBody
	@RequestMapping("/removeOneAdminAction")
	public ErrorMessage removeOneAdminAction(String id) {
		return adminService.removeOneAdminAction(id);
	}

	@ResponseBody
	@RequestMapping("/getAdminSelfInfo")
	public AdminModel getAdminSelfInfo(String id) {
		return adminService.getAdminSelfInfo(id);
	}

	@ResponseBody
	@RequestMapping("/modfiyAdminPassword")
	public ErrorMessage modfiyAdminPassword(ModifyPasswordModel model) {
		return adminService.modfiyAdminPassword(model);
	}
}
