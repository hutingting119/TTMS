package com.mec.volunteer.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.AdminModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.OrganizationModel;
import com.mec.volunteer.model.VolunteerModel;

@Repository
public class AdminRepository {
	@Autowired(required=false)
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.openSession();
	}
	
	public List<AdminModel> addAdminListModel(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		String HQLString = "FROM AdminModel model WHERE model.id != 'admin'";
		@SuppressWarnings("unchecked")
		Query<AdminModel> query = session.createQuery(HQLString);
		List<AdminModel> list = query.getResultList();
		
		transaction.commit();
		session.close();
		
		return list;
	}
	
	public List<VolunteerModel> addVolunteerListModel(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		String HQLString = "FROM VolunteerModel model";
		@SuppressWarnings("unchecked")
		Query<VolunteerModel> query = session.createQuery(HQLString);
		List<VolunteerModel> list = query.getResultList();
		
		transaction.commit();
		session.close();
		
		return list;
	}
	
	public List<OrganizationModel> addOrganizationListModel(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		String HQLString = "FROM OrganizationModel model";
		@SuppressWarnings("unchecked")
		Query<OrganizationModel> query = session.createQuery(HQLString);
		List<OrganizationModel> list = query.getResultList();
		
		transaction.commit();
		session.close();
		
		return list;
	}
	
	public List<ActivityModel> addActivityListModel(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		String HQLString = "FROM ActivityModel model";
		@SuppressWarnings("unchecked")
		Query<ActivityModel> query = session.createQuery(HQLString);
		List<ActivityModel> list = query.getResultList();
		
		transaction.commit();
		session.close();
		
		return list;
	}
	
	public ErrorMessage removeOneAdminAction(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		ErrorMessage errorMessage = new ErrorMessage();
		
		AdminModel adminModel = session.get(AdminModel.class, id);
		transaction.commit();
		
		transaction = session.beginTransaction();
		session.delete(adminModel);
		transaction.commit();
		
		session.close();
		
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		
		return errorMessage;
	}
	public AdminModel getAdminSelfInfo(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		AdminModel adminModel = session.get(AdminModel.class, id);
		
		transaction.commit();
		session.close();
		
		return adminModel;
	}
	
	public ErrorMessage modfiyAdminPassword(AdminModel model) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		session.update(model);

		transaction.commit();
		session.close();

		return model;
	}
	
	public AdminModel getAdminModelById(String id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		AdminModel adminModel = session.get(AdminModel.class, id);
		
		transaction.commit();
		session.close();
		
		return adminModel;
	}
}
