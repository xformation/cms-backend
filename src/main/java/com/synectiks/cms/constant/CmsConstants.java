package com.synectiks.cms.constant;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

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
	
	List<String> tabelName = initTableList();
	
	public static List<String> initTableList() {
		List<String> list = new LinkedList<String>();
		list.add("All");
		list.add("country");
		list.add("state");  
		list.add("city");   
		list.add("currency");             
		list.add("college");
		list.add("branch"); 
		list.add("authorized_signatory"); 
	    list.add("bank_accounts");
	    list.add("legal_entity");         
	    list.add("academic_year");
	    list.add("holiday");              
	    list.add("term");                 
	    list.add("department");           
	    list.add("batch");                
	    list.add("teacher");              
	    list.add("subject");              
	    list.add("teach");                
	    list.add("attendance_master");    
	    list.add("invoice");              
	    list.add("due_date");             
	    list.add("payment_remainder");    
	    list.add("late_fee");             
	    list.add("fee_category");         
	    list.add("fee_details");          
	    list.add("academic_exam_setting"); 
	    list.add("academic_history");
	    list.add("admin_attendance");
	    list.add("admission_application"); 
	    list.add("admission_enquiry");   
	    list.add("competitive_exam");     
	    list.add("documents");            
	    list.add("facility");             
	    list.add("meta_lecture");         
	    list.add("lecture");              
	    list.add("section");              
	    list.add("student");              
	    list.add("student_attendance");   
	    list.add("student_exam_report");  
	    list.add("student_facility_link"); 
	    list.add("transport_route");      
	    list.add("type_of_grading");      

	    return list;
	}
	
	String XLSX_FILE_EXTENSION = "xlsx";
	String XLS_FILE_EXTENSION = "xls";
	int BATCH_SIZE = 100;
}
