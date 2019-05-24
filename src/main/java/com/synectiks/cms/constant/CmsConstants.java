package com.synectiks.cms.constant;

import java.io.File;
import java.nio.file.Paths;

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
}
