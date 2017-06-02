package com.mec.volunteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;
import com.mec.volunteer.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired
	private LoginRepository loginRepository;
	
	public ErrorMessage adminLoginAction(AdminModel model) {
		ErrorMessage errorMessage = new ErrorMessage();
		AdminModel organizationModel = loginRepository.getAdminModelById(model.getId());
		
		if(null == organizationModel) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_MESSAGE);
			return errorMessage;
		}
		
		if(!(organizationModel.getId().equals(model.getId()))) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_MESSAGE);
			return errorMessage;
		}
		
		if(!(organizationModel.getPassword().equals(model.getPassword()))) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_PASSWORD_NOT_RIGHT_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_PASSWORD_NOT_RIGHT_MESSAGE);
			return errorMessage;
		}
		
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		
		return errorMessage;
	}
	
	public ErrorMessage organizationLoginAction(OrganizationModel model) {
		ErrorMessage errorMessage = new ErrorMessage();
		OrganizationModel organizationModel = loginRepository.getOrganizationModelById(model.getId());
		
		if(null == organizationModel) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_MESSAGE);
			return errorMessage;
		}
		
		if(!(organizationModel.getId().equals(model.getId()))) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_MESSAGE);
			return errorMessage;
		}
		
		if(!(organizationModel.getPassword().equals(model.getPassword()))) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_PASSWORD_NOT_RIGHT_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_PASSWORD_NOT_RIGHT_MESSAGE);
			return errorMessage;
		}
		
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		
		return errorMessage;
	}
	
	public ErrorMessage volunteerLoginAction(VolunteerModel model) {
		ErrorMessage errorMessage = new ErrorMessage();
		VolunteerModel userModel = loginRepository.getVolunteerModelById(model.getId());
		if(null == userModel) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_MESSAGE);
			return errorMessage;
		}
		
		if(!(userModel.getId().equals(model.getId()))) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_USER_NOT_EXIST_MESSAGE);
			return errorMessage;
		}
		
		if(!(userModel.getPassword().equals(model.getPassword()))) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_PASSWORD_NOT_RIGHT_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_PASSWORD_NOT_RIGHT_MESSAGE);
			return errorMessage;
		}
		
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		
		return errorMessage;
	}
}
