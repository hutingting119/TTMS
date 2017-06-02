package com.mec.volunteer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.ModifyPasswordModel;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;
import com.mec.volunteer.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired(required=false)
	private AdminRepository adminRepository;
	
	public List<AdminModel> addAdminListModel(String id) {
		return adminRepository.addAdminListModel(id);
	}
	
	public List<VolunteerModel> addVolunteerListModel(String id) {
		return adminRepository.addVolunteerListModel(id);
	}
	
	public List<OrganizationModel> addOrganizationListModel(String id) {
		return adminRepository.addOrganizationListModel(id);
	}
	
	public List<ActivityModel> addActivityListModel(String id) {
		return adminRepository.addActivityListModel(id);
	}
	
	public ErrorMessage removeOneAdminAction(String id) {
		return adminRepository.removeOneAdminAction(id);
	}
	
	public AdminModel getAdminSelfInfo(String id) {
		return adminRepository.getAdminSelfInfo(id);
	}
	
	public ErrorMessage modfiyAdminPassword(ModifyPasswordModel model) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		AdminModel volunteerModel = adminRepository.getAdminModelById(model.getId());
		
		if(!volunteerModel.getPassword().equals(model.getOldPassword())) {
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_PASSWORD_NOT_RIGHT_MESSAGE);
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_PASSWORD_NOT_RIGHT_NUMBER);
			
			return errorMessage;
		}
		
		if(model.getNewPassword().equals(volunteerModel.getPassword())) {
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_PASSWORD_SAME_MESSAGE);
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_PASSWORD_SAME_NUMBER);
			
			return errorMessage;
		}
		
		volunteerModel.setPassword(model.getNewPassword());
		
		adminRepository.modfiyAdminPassword(volunteerModel);
		
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		
		return errorMessage;
	}
}
