package com.mec.volunteer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.ILocationURL;
import com.mec.volunteer.model.IUserIdFixed;
import com.mec.volunteer.model.LoginModel;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;
import com.mec.volunteer.service.LoginService;

@Controller
public class LoginAction {
	@Autowired
	private LoginService loginService;
	
	@ResponseBody
	@RequestMapping("/userLoginAction")
	public ErrorMessage userLoginAction(LoginModel model) {
		String userId = model.getId();
		String password = model.getPassword();
		ErrorMessage errorMessage = new ErrorMessage();
		
		if("admin".equals(model.getId())) {
			AdminModel adminModel = new AdminModel();
			adminModel.setId(userId);
			adminModel.setPassword(password);
			
			ErrorMessage message = loginService.adminLoginAction(adminModel);
			message.setUrl(ILocationURL.LOCATION_ADMIN_URL);
			
			return message;
		}
				
		if(userId.length() != 12) {
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_USER_ID_NOT_RIGHT_NUMBER);
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_USER_ID_NOT_RIGHT_MESSAGE);
			
			return errorMessage;
		}
		
		String middleId = userId.substring(6, 6+2);
		
		if (middleId.equals(IUserIdFixed.ORGANIZATION_FIXED)) {
			OrganizationModel organizationModel = new OrganizationModel();
			organizationModel.setId(userId);
			organizationModel.setPassword(password);
			ErrorMessage message = loginService.organizationLoginAction(organizationModel);
			message.setUrl(ILocationURL.LOCATION_ORGANIZATION_URL);
			
			return message;
		} else if(middleId.equals(IUserIdFixed.VOLUBTEER_FIXED)) {
			VolunteerModel volunteerModel = new VolunteerModel();
			volunteerModel.setId(userId);
			volunteerModel.setPassword(password);
			ErrorMessage message = loginService.volunteerLoginAction(volunteerModel);
			message.setUrl(ILocationURL.LOCATION_VOLUNTEER_URL);
			
			return message;
		} else if(middleId.equals(IUserIdFixed.ADMIN_FIXED)) {
			AdminModel adminModel = new AdminModel();
			adminModel.setId(userId);
			adminModel.setPassword(password);
			
			ErrorMessage message = loginService.adminLoginAction(adminModel);
			message.setUrl(ILocationURL.LOCATION_ADMIN_URL);
			
			return message;
		}
		
		errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_USER_ID_NOT_RIGHT_NUMBER);
		errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_USER_ID_NOT_RIGHT_MESSAGE);
		return errorMessage;
	}
}
