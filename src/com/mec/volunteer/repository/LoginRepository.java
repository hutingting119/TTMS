package com.mec.volunteer.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;

@Repository
@Transactional
public class LoginRepository {
	@Autowired(required=false)
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.openSession();
	}
	
	public VolunteerModel getVolunteerModelById(String id) {
		Session session = getSession();
		
		VolunteerModel model = session.get(VolunteerModel.class, id);
		
		session.close();
		
		return model;
	}

	public OrganizationModel getOrganizationModelById(String id) {
		Session session = getSession();
		
		OrganizationModel model = session.get(OrganizationModel.class, id);
		
		session.close();
		
		return model;
	}
	
	public AdminModel getAdminModelById(String id) {
		Session session = getSession();
		
		AdminModel model = session.get(AdminModel.class, id);
		
		session.close();
		
		return model;
	}
}
