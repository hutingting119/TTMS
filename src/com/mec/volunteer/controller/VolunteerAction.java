package com.mec.volunteer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.ModifyPasswordModel;
import com.mec.volunteer.model.ServiceModel;
import com.mec.volunteer.model.ServiceVolunteerModel;
import com.mec.volunteer.model.VolunteerActivityModel;
import com.mec.volunteer.model.VolunteerModel;
import com.mec.volunteer.service.VolunteerService;

@Controller
public class VolunteerAction {
	@Autowired
	private VolunteerService volunteerService;
	
	@ResponseBody
	@RequestMapping("/getVolunteerModel")
	public VolunteerModel getVolunteerModel(String id) {
		return volunteerService.getVolunteerModelById(id);
	}
	
	@ResponseBody
	@RequestMapping("/getAllActivityByVolunteer")
	public List<ActivityModel> getActivityList(String id) {
		return volunteerService.getAllActivityByVolunteer(id);
	}
	
	@ResponseBody
	@RequestMapping("/modfiyVolunteerPassword")
	public ErrorMessage volunteerModfiyPassword(ModifyPasswordModel model) {
		return volunteerService.modifyPassword(model);
	}
	
	@ResponseBody
	@RequestMapping("/addVolunteerActivityAction")
	public VolunteerActivityModel addVolunteerActivityAction(VolunteerActivityModel model) {
		return volunteerService.addVolunteerActivityAction(model);
	}
	
	@ResponseBody
	@RequestMapping("/getSelfSelectService")
	public ServiceModel getSelfSelectService(String id) {
		return volunteerService.getSelfSelectService(id);
	}
	
	@ResponseBody
	@RequestMapping("/getSelfSelectActivity")
	public List<?> getSelfSelectActivity(VolunteerModel model) {
		return volunteerService.getSelfSelectActivity(model);
	}

	@ResponseBody
	@RequestMapping("/removeSelectActivity")
	public ErrorMessage removeSelectActivity(VolunteerActivityModel model) {
		return volunteerService.removeSelectActivity(model);
	}
	
	@ResponseBody
	@RequestMapping("/getAllService")
	public List<?> getAllService(String id) {
		return volunteerService.getAllService(id);
	}
	@ResponseBody
	@RequestMapping("/saveServiceInfo")
	public ErrorMessage saveServiceInfo(ServiceModel serviceModel) {
		return volunteerService.saveServiceInfo(serviceModel);
	}
	
	@ResponseBody
	@RequestMapping("/updateServiceInfo")
	public ErrorMessage updateServiceInfo(ServiceModel serviceModel) {
		return volunteerService.updateServiceInfo(serviceModel);
	}
	
	@ResponseBody
	@RequestMapping("/removeServiceInfo")
	public ErrorMessage removeServiceInfo(ServiceVolunteerModel model) {
		return volunteerService.removeServiceInfo(model);
	}
	
	@ResponseBody
	@RequestMapping("/addServiceBySelf")
	public ErrorMessage addServiceBySelf(ServiceVolunteerModel model) {
		return volunteerService.addServiceBySelf(model);
	}
	
	@ResponseBody
	@RequestMapping("/removeServiceBySelf")
	public ErrorMessage removeServiceBySelf(ServiceVolunteerModel model) {
		return volunteerService.removeServiceBySelf(model);
	}
}