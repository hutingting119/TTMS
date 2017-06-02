package com.mec.volunteer.model;

public interface IRepositoryMapping {
	String TABLE_VOLUNTEER = "sys_volunteer_tab";
	String TABLE_VOLUNTEER_ID = "volunteer_id";
	String TABLE_VOLUNTEER_PASSWORD = "volunteer_password";
	String TABLE_VOLUNTEER_NAME = "volunteer_name";
	String TABLE_VOLUNTEER_SEX = "volunteer_sex";
	String TABLE_VOLUNTEER_ETHNIC = "volunteer_ethnic";
	String TABLE_VOLUNTEER_PHONE = "volunteer_phone";
	String TABLE_VOLUNTEER_EMAIL = "volunteer_email";
	String TABLE_VOLUNTEER_IDCARD = "volunteer_idcard";
	String TABLE_VOLUNTEER_BIRTHDAY = "volunteer_birthday";
	String TABLE_VOLUNTEER_PROFESSION = "volunteer_profession";
	String TABLE_VOLUNTEER_QQ = "volunteer_qq";
	String TABLE_VOLUNTEER_ADDRESS = "volunteer_address";
	String TABLE_VOLUNTEER_SERVICE = "volunteer_service";
	String TABLE_VOLUNTEER_SPECIALTY = "volunteer_specialty";
	String TABLE_VOLUNTEER_EXPERIENCE = "volunteer_experience";
	String TABLE_VOLUNTEER_STREET = "volunteer_street";
	String TABLE_VOLUNTEER_TYPE = "volunteer_type";
	
	String TABLE_ORGANIZATION = "sys_organization_tab";
	String TABLE_ORGANIZATION_ID = "organization_id";
	String TABLE_ORGANIZATION_PASSWORD = "organization_password";
	String TABLE_ORGANIZATION_NAME = "organization_name";
	String TABLE_ORGANIZATION_PEOPLE = "organization_people";
	String TABLE_ORGANIZATION_NUMBER = "organization_number";
	String TABLE_ORGANIZATION_STYLE = "organization_style";
	String TABLE_ORGANIZATION_PHONE = "organization_phone";
	String TABLE_ORGANIZATION_BUSINESS = "organization_business";
	String TABLE_ORGANIZATION_FORM = "organization_form";
	String TABLE_ORGANIZATION_LOCATION = "organization_location";
	String TABLE_ORGANIZATION_CHECK = "organization_check";
	String TABLE_ORGANIZATION_EMAIL = "organization_email";
	String TABLE_ORGANIZATION_TYPE = "organization_type";
	
	String TABLE_ADMIN = "sys_admin_tab";
	String TABLE_ADMIN_ID = "admin_id";
	String TABLE_ADMIN_PASSWORD = "admin_password";
	
	String TABLE_ACTIVITY = "sys_activity_tab";
	String TABLE_ACTIVITY_ID = "activity_id";
	String TABLE_ACTIVITY_COMPANY = "activity_company";
	String TABLE_ACTIVITY_COMPANY_ID = "activity_company_id";
	String TABLE_ACTIVITY_HEAD = "activity_head";
	String TABLE_ACTIVITY_PHONE = "activity_phone";
	String TABLE_ACTIVITY_TYPE = "activity_type";
	String TABLE_ACTIVITY_TIME = "activity_time";
	String TABLE_ACTIVITY_CONTENT = "activity_content";
	String TABLE_ACTIVITY_TITLE = "activity_title";
	String TABLE_ACTIVITY_ADDRESS = "activity_address";
	String TABLE_ACTIVITY_STATUS = "activity_status";
	
	String TABLE_VOLUNTEER_ACTIVITY = "volunteer_activity_tab";
	String TABLE_VOLUNTEER_ACTIVITY_ID = "id";
	String TABLE_VOLUNTEER_ACTIVITY_VOLUNTEER_ID = "volunteer_id";
	String TABLE_VOLUNTEER_ACTIVITY_ACTIVITY_ID = "activity_id";
	
	String TABLE_SERVICE = "sys_service_tab";
	String TABLE_SERVICE_NAME = "service_name";
	String TABLE_SERVICE_CAPTAIN_ID = "service_captainId";
	String TABLE_SERVICE_CAPTAIN = "service_captain";
	String TABLE_SERVICE_PHONE = "service_phone";
	String TABLE_SERVICE_STATUS = "service_status";
	String TABLE_SERVICE_COUNT = "service_count";
}
