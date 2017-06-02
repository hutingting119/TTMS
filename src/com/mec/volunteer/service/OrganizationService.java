package com.mec.volunteer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.ModifyPasswordModel;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.repository.OrganizationRepository;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationRepository organizationRepository;
	
	public OrganizationModel getOrganizationModel(String id) {
		return organizationRepository.getOrganizationModel(id);
	}
	
	public ErrorMessage registNewActivity(ActivityModel model) {
		String id = organizationRepository.getId();
		model.setId(id);
		
		return organizationRepository.registNewActivity(model);
	}
	
	public List<?> getAllActivityByOrganization(String id) {
		return organizationRepository.getAllActivityByOrganization(id);
	}
	
	public ErrorMessage modifyOrganizationInfo(OrganizationModel model) {
		return organizationRepository.modifyOrganizationInfo(model);
	}

	public ErrorMessage removeOrganizationActivity(String id) {
		return  organizationRepository.removeOrganizationActivity(id);
	}

	public ErrorMessage modfiyOrganizationPassword(ModifyPasswordModel model) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		OrganizationModel volunteerModel = organizationRepository.getAdminModelById(model.getId());
		
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
		
		organizationRepository.modfiyOrganizationPassword(volunteerModel);
		
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		
		return errorMessage;
	}
}
