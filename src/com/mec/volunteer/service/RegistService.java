package com.mec.volunteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;
import com.mec.volunteer.repository.RegistRepository;

@Service
public class RegistService {
	@Autowired
	private RegistRepository registRepository;
	

	public VolunteerModel volunteerRegistAction(VolunteerModel model) {
		return registRepository.volunteerRegistAction(model);
	}

	public OrganizationModel organizationRegistAction(OrganizationModel model) {
		return registRepository.organizationRegistAction(model);
	}

	public AdminModel adminRegistAction(AdminModel model) {
		return registRepository.adminRegistAction(model);
	}
}
