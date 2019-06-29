package com.synectiks.cms.constant;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface CmsConstants {
	
	String CMS_IMAGE_FILE_PATH = "college_images/";

	String CMS_COLLEGE_LOGO_FILE_NAME = "college_logo";
	String CMS_COLLEGE_BACKGROUND_IMAGE_FILE_NAME = "dashboard";
	String CMS_LEGAL_ENTITY_LOGO_FILE_NAME = "legalentity_logo";
	
	String SRC_DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
	String DATE_FORMAT_dd_MM_yyyy = "dd-MM-yyyy";
	
	String LECTURE_NOT_SCHEDULED = "LECTURE_NOT_SCHEDULED";
	
	String ADD_SUCCESS_MESSAGE = "Records added successfully";
	String ADD_FAILURE_MESSAGE = "Due to some exception, records could no be added.";
	String UPDATE_SUCCESS_MESSAGE = "Records updated successfully";
	String UPDATE_FAILURE_MESSAGE = "Due to some exception, records could no be updated.";
	
	String OS_WINDOWS = "windows";
	String OS_UNIX = "unix";
	String OS_MAC = "mac";
	String OS_SOLARIS = "solaris";
	String COLLEGE_ID_PLACEHOLDER_REPLACER = "college_id_";
	String BRANCH_ID_PLACEHOLDER_REPLACER = "branch_id_";
	String STUDENT_IMAGE_FILE_PATH = CMS_IMAGE_FILE_PATH+File.separator+"COLLEGE_ID"+File.separator+"student_images";
	
	String STUDENT = "student_name, student_middle_name, student_last_name, father_name, father_middle_name, father_last_name, mother_name, mother_middle_name, mother_last_name, aadhar_no, date_of_birth, place_of_birth, religion, caste, sub_caste, age, sex, blood_group, address_line_one, address_line_two, address_line_three, town, state, country, pincode, student_contact_number, alternate_contact_number, student_email_address, alternate_email_address, relation_with_student, emergency_contact_name, emergency_contact_middle_name, emergency_contact_last_name, emergency_contact_no, emergency_contact_email_address, upload_photo, admission_no, roll_no, student_type, department_id, batch_id, section_id, branch_id";
	
	Map<String, String> tabelName = new HashMap<String, String>();
	
	default void initTableName() {
		tabelName.put("STUDENT", STUDENT);
	}
	
	String XLSX_FILE_EXTENSION = "xlsx";
	String XLS_FILE_EXTENSION = "xls";
	int BATCH_SIZE = 100;
}
