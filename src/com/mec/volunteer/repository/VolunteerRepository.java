package com.mec.volunteer.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mec.volunteer.model.ActivityModel;
import com.mec.volunteer.model.ErrorMessage;
import com.mec.volunteer.model.IErrorMessage;
import com.mec.volunteer.model.ServiceModel;
import com.mec.volunteer.model.ServiceVolunteerModel;
import com.mec.volunteer.model.VolunteerActivityModel;
import com.mec.volunteer.model.VolunteerModel;

@Repository
@Transactional
public class VolunteerRepository {
	@Autowired(required=false)
	private SessionFactory sessionFactory;
	
	private Session getSesstion() {
		return sessionFactory.openSession();
	}
	
	public List<ActivityModel> getAllActivityByVolunteer(String id) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();

		String HQLString = "FROM VolunteerActivityModel model WHERE model.volunteerId = " + id;
		Query<VolunteerActivityModel> query = session.createQuery(HQLString, VolunteerActivityModel.class);
		List<VolunteerActivityModel> list = query.getResultList();
		
		transaction.commit();
		
		transaction = session.beginTransaction();
		HQLString = "FROM ActivityModel activity";
		Query<ActivityModel> resultQuery = session.createQuery(HQLString, ActivityModel.class);
		List<ActivityModel> resultList = resultQuery.getResultList();
		
		transaction.commit();
		
		for(int index = 0; index < list.size(); index++) {
			VolunteerActivityModel volunteerActivityModel = list.get(index);
			for(int j = 0; j < resultList.size(); j++) {
				ActivityModel activityModel = resultList.get(j);
				if(activityModel.getId().equals(volunteerActivityModel.getActivityId())) {
					resultList.remove(j);
				}
			}
		}
		
		session.close();
		
		return resultList;
	}

	public VolunteerModel getVolunteerModelById(String id) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		
		VolunteerModel model = session.get(VolunteerModel.class, id);
		
		transaction.commit();
		session.close();
		
		return model;
	}
	
	public VolunteerModel modifyPassword(String newPassword, VolunteerModel model) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		
		model.setPassword(newPassword);
		
		session.update(model);

		transaction.commit();
		session.close();

		return model;
	}
	
	public VolunteerActivityModel addVolunteerActivityAction(VolunteerActivityModel model) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		
		session.save(model);
		transaction.commit();
		session.close();
		
		return model;
	}

	public List<?> getSelfSelectActivity(VolunteerModel model) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		
		String HQLString = "FROM VolunteerActivityModel rModel WHERE rModel.volunteerId = " + model.getId() + "";
		Query<?> query = session.createQuery(HQLString);
		List<?> list = query.getResultList();
		transaction.commit();
		
		List<ActivityModel> activityModels = new ArrayList<ActivityModel>();
		for(int index = 0; index < list.size(); index++) {
			VolunteerActivityModel volunteerActivityModel = (VolunteerActivityModel) list.get(index);
			HQLString = "FROM ActivityModel activity WHERE activity.id = " + volunteerActivityModel.getActivityId();
			transaction = session.beginTransaction();
			query = session.createQuery(HQLString);
			List<?> queryList = query.getResultList();
			
			transaction.commit();
			for(int i = 0; i < queryList.size(); i++) {
				ActivityModel resultModel = (ActivityModel) queryList.get(i);
				activityModels.add(resultModel);
			}
		}
		
		session.close();
		
		return activityModels;
	}

	public ErrorMessage removeSelectActivity(VolunteerActivityModel model) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		
		String HQLString = "DELETE FROM VolunteerActivityModel WHERE volunteerId = " + model.getVolunteerId() + " AND activityId = " + model.getActivityId();
		Query<?> query = session.createQuery(HQLString);
		query.executeUpdate();
		
		transaction.commit();
		session.close();
		
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		
		return errorMessage;
	}
	
	public List<?> getAllService(String id) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		
		String HQLString = "FROM ServiceModel model";
		Query<?> query = session.createQuery(HQLString);
		List<?> list = query.getResultList();
		
		transaction.commit();
		session.close();
		
		return list;
	}
	
	public ServiceModel getSelfSelectService(String id) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		
		ServiceVolunteerModel serviceVolunteerModel = session.get(ServiceVolunteerModel.class, id);
		ServiceModel serviceModel = new ServiceModel();
		
		if(serviceVolunteerModel == null) {
			return serviceModel;
		}
		
		transaction.commit();
		
		transaction = session.beginTransaction();
		
		serviceModel = session.get(ServiceModel.class, serviceVolunteerModel.getServiceId());
		
		transaction.commit();
		
		session.close();
		
		return serviceModel;
	}
	
	public ErrorMessage updateServiceInfo(ServiceModel serviceModel) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		ErrorMessage errorMessage = new ErrorMessage();
		
		session.update(serviceModel);
		
		transaction.commit();
		session.close();
		
		return errorMessage;
	}
	
	public ErrorMessage saveServiceInfo(ServiceModel serviceModel) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		ErrorMessage errorMessage = new ErrorMessage();
		
		session.save(serviceModel);
		
		transaction.commit();
		
		transaction = session.beginTransaction();
		
		ServiceVolunteerModel serviceVolunteerModel = new ServiceVolunteerModel();
		serviceVolunteerModel.setServiceId(serviceModel.getId());
		serviceVolunteerModel.setVolunteerId(serviceModel.getId());
		session.save(serviceVolunteerModel);
		
		transaction.commit();
		
		session.close();
		
		return errorMessage;
	}
	
	public ErrorMessage removeServiceInfo(ServiceVolunteerModel model) {
		Session session = getSesstion();
		ErrorMessage errorMessage = new ErrorMessage();
		Transaction transaction = session.beginTransaction();
		
		ServiceModel sm = session.get(ServiceModel.class, model.getServiceId());
		transaction.commit();
		
		if(sm == null) {
			errorMessage.setErrorNumber("000008");
			errorMessage.setErrorMessage("服务队不存在！不能注销");
			
			return errorMessage;
		}
		
		String HQLString = "FROM ServiceVolunteerModel model WHERE model.serviceId = " + model.getServiceId();
		Query<?> query = session.createQuery(HQLString);
		List<?> list = query.getResultList();
		for(int index = 0; index < list.size(); index++) {
			transaction = session.beginTransaction();
			ServiceVolunteerModel serviceVolunteerModel = (ServiceVolunteerModel) list.get(index);
			session.delete(serviceVolunteerModel);
			transaction.commit();
			
		}
		transaction = session.beginTransaction();
		ServiceModel serviceModel = session.get(ServiceModel.class, model.getServiceId());
		transaction.commit();
		
		transaction = session.beginTransaction();
		session.delete(serviceModel);
		transaction.commit();
		session.close();
		
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);

		return errorMessage;
	}
	
	public ErrorMessage addServiceBySelf(ServiceVolunteerModel model) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		ErrorMessage errorMessage = new ErrorMessage();
		
		ServiceVolunteerModel serviceVolunteerModel = session.get(ServiceVolunteerModel.class, model.getVolunteerId());
		transaction.commit();
		
		if(serviceVolunteerModel != null) {
			errorMessage.setErrorNumber("000006");
			errorMessage.setErrorMessage("一个用户只能参加一个服务队！");
			
			return errorMessage;
		}
		transaction = session.beginTransaction();
		session.save(model);
		transaction.commit();
		
		transaction = session.beginTransaction();
		
		ServiceModel serviceModel = session.get(ServiceModel.class, model.getServiceId());
		serviceModel.setCount(String.valueOf(
				Integer.valueOf(serviceModel.getCount())+1));
		
		transaction.commit();
		
		session.close();
		
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		
		return errorMessage;
	}

	public ErrorMessage removeServiceBySelf(ServiceVolunteerModel model) {
		Session session = getSesstion();
		Transaction transaction = session.beginTransaction();
		ErrorMessage errorMessage = new ErrorMessage();
		
		ServiceModel sm = session.get(ServiceModel.class, model.getServiceId());
		
		if(sm == null) {
			return errorMessage;
		}
		
		session.delete(model);
		
		transaction.commit();
		
		transaction = session.beginTransaction();
		
		ServiceModel serviceModel = session.get(ServiceModel.class, model.getServiceId());
		serviceModel.setCount(String.valueOf(
				Integer.valueOf(serviceModel.getCount())-1));
		
		transaction.commit();
		
		session.close();
		
		errorMessage.setErrorNumber(IErrorMessage.SUCCESS_NUMBER);
		errorMessage.setErrorMessage(IErrorMessage.SUCCESS_MESSAGE);
		
		return errorMessage;
	}
}
