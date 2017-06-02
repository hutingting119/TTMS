package com.mec.volunteer.repository;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerActivityModel;

@Repository
public class OrganizationRepository {
	@Autowired(required=false)
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.openSession();
	}
	
	private String getFixedId() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		return year + "" + (((month + 101) + "").substring(1));
	}
	
	public String getId() {
		String HQLString = "FROM ActivityModel model";
		Session session = getSession();
		
		Query<?> query = session.createQuery(HQLString);
		List<?> list = query.getResultList();
		session.close();
		
		String id = getFixedId() + ((list.size() + 10001 + "").substring(1));
		
		return id;
	}
	
	public OrganizationModel getOrganizationModel(String id) {
		Session session = getSession();
		
		OrganizationModel organizationModel = session.get(OrganizationModel.class, id);
		
		session.close();
		
		return organizationModel;
	}
	
	public ErrorMessage registNewActivity(ActivityModel model) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		ErrorMessage errorMessage = new ErrorMessage();
		
		session.save(model);
		
		transaction.commit();
		session.close();
		
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		
		return errorMessage;
	}

	public List<?> getAllActivityByOrganization(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		String HQLString = "FROM ActivityModel model WHERE model.userId = " + id;
		Query<?> query = session.createQuery(HQLString);
		List<?> list = query.getResultList();
		
		transaction.commit();
		session.close();
		
		return list;
	}

	public ErrorMessage modifyOrganizationInfo(OrganizationModel model) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		ErrorMessage errorMessage = new ErrorMessage();
		
		session.update(model);
		
		transaction.commit();
		session.close();
		
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		
		return errorMessage;
	}
	
	public ErrorMessage removeOrganizationActivity(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		ErrorMessage errorMessage = new ErrorMessage();
		
		ActivityModel activityModel = session.get(ActivityModel.class, id);
		transaction.commit();
		
		transaction = session.beginTransaction();
		session.delete(activityModel);
		transaction.commit();
		
		String HQLString = "FROM VolunteerActivityModel model WHERE model.activityId = " + id;
		transaction = session.beginTransaction();
		Query<?> query = session.createQuery(HQLString);
		List<?> list = query.getResultList();
		transaction.commit();

		for(int index = 0; index < list.size(); index++) {
			transaction = session.beginTransaction();
			VolunteerActivityModel volunteerActivityModel = (VolunteerActivityModel) list.get(index);
			session.delete(volunteerActivityModel);
			transaction.commit();
		}
		session.close();
		
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		
		return errorMessage;
	}
	
	public OrganizationModel modfiyOrganizationPassword(OrganizationModel model) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		session.update(model);

		transaction.commit();
		session.close();

		return model;
	}
	
	public OrganizationModel getAdminModelById(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		OrganizationModel adminModel = session.get(OrganizationModel.class, id);
		
		transaction.commit();
		session.close();
		
		return adminModel;
	}
}
