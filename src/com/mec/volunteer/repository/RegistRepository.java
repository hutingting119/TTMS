package com.mec.volunteer.repository;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.IUserIdFixed;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;

@Repository
@Transactional
public class RegistRepository {
	@Autowired(required=false)
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.openSession();
	}
	
	private String getUserIdFirst() {
		StringBuffer stringBuffer = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		stringBuffer.append(year).append((month + 101 + "").substring(1));
		
		return stringBuffer.toString();
	}
	
	private String getUserIdLast(String HQLString) {
		Session session = getSession();
		
		Query<?> query = session.createQuery(HQLString);
		List<?> list = query.getResultList();
		String str = (list.size() + 10001 + "").substring(1);
		
		session.close();
		
		return str;
	}
	
	public void serAdminId(AdminModel model) {
		String HQLString = "FROM AdminModel model WHERE model.id != 'admin'";
		String userId = getUserIdFirst() + IUserIdFixed.ADMIN_FIXED + getUserIdLast(HQLString);
		model.setId(userId);
	}
	
	public AdminModel adminRegistAction(AdminModel model) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		serAdminId(model);
		session.save(model);
		
		transaction.commit();
		session.close();
		
		AdminModel adminModel = new AdminModel();
		adminModel.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		adminModel.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		adminModel.setId(model.getId());
		
		return adminModel;
	}
	
	private void setVolunteerId(VolunteerModel model) {
		String HQLString = "FROM VolunteerModel model";
		String userId = getUserIdFirst() + IUserIdFixed.VOLUBTEER_FIXED + getUserIdLast(HQLString);
		model.setId(userId);
	}
	
	public VolunteerModel volunteerRegistAction(VolunteerModel model) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		setVolunteerId(model);
		session.save(model);
		
		transaction.commit();
		session.close();
		
		VolunteerModel volunteerModel = new VolunteerModel();
		volunteerModel.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		volunteerModel.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		volunteerModel.setId(model.getId());
		
		return volunteerModel;
	}
	
	private void setOrganizationId(OrganizationModel model) {
		String HQLString = "FROM OrganizationModel model";
		String userId = getUserIdFirst() + IUserIdFixed.ORGANIZATION_FIXED + getUserIdLast(HQLString);
		model.setId(userId);
	}
	
	public OrganizationModel organizationRegistAction(OrganizationModel model) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		setOrganizationId(model);
		session.save(model);
		
		transaction.commit();
		session.close();
		
		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		organizationModel.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		organizationModel.setId(model.getId());
		
		return organizationModel;
	}
}
