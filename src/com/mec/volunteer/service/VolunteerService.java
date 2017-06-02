package com.mec.volunteer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.ModifyPasswordModel;
import com.mec.volunteer.model.ServiceModel;
import com.mec.volunteer.model.ServiceVolunteerModel;
import com.mec.volunteer.model.VolunteerActivityModel;
import com.mec.volunteer.model.VolunteerModel;
import com.mec.volunteer.repository.VolunteerRepository;

@Service
public class VolunteerService {
	@Autowired
	public VolunteerRepository volunteerRepository;
	
	public VolunteerModel getVolunteerModelById(String id) {
		return volunteerRepository.getVolunteerModelById(id);
	}
	
	public List<ActivityModel> getAllActivityByVolunteer(String id) {
		return volunteerRepository.getAllActivityByVolunteer(id);
	}
	
	public ErrorMessage modifyPassword(ModifyPasswordModel model) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		VolunteerModel volunteerModel = getVolunteerModelById(model.getId());
		
		if(model.getNewPassword().equals(volunteerModel.getPassword())) {
			errorMessage.setErrorMessage(IErrorMessage.FAILURE_NORMAL_PASSWORD_SAME_MESSAGE);
			errorMessage.setErrorNumber(IErrorMessage.FAILURE_NORMAL_PASSWORD_SAME_NUMBER);
			
			return errorMessage;
		}
		
		volunteerRepository.modifyPassword(model.getNewPassword(), volunteerModel);
		
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		
		return errorMessage;
	}
	
	public VolunteerActivityModel addVolunteerActivityAction(VolunteerActivityModel model) {
		return volunteerRepository.addVolunteerActivityAction(model);
	}
	
	public List<?> getSelfSelectActivity(VolunteerModel model) {
		return volunteerRepository.getSelfSelectActivity(model);
	}
	
	public ErrorMessage removeSelectActivity(VolunteerActivityModel model) {
		return volunteerRepository.removeSelectActivity(model);
	}

	public List<?> getAllService(String id) {
		return volunteerRepository.getAllService(id);
	}
	
	public ServiceModel getSelfSelectService(String id) {
		return volunteerRepository.getSelfSelectService(id);
	}
	
	public ErrorMessage saveServiceInfo(ServiceModel serviceModel) {
		return volunteerRepository.saveServiceInfo(serviceModel);
	}
	
	public ErrorMessage updateServiceInfo(ServiceModel serviceModel) {
		return volunteerRepository.updateServiceInfo(serviceModel);
	}
	
	public ErrorMessage removeServiceInfo(ServiceVolunteerModel model) {
		return volunteerRepository.removeServiceInfo(model);
	}
	
	public ErrorMessage addServiceBySelf(ServiceVolunteerModel model) {
		return volunteerRepository.addServiceBySelf(model);
	}

	public ErrorMessage removeServiceBySelf(ServiceVolunteerModel model) {
		return volunteerRepository.removeServiceBySelf(model);
	}
}
