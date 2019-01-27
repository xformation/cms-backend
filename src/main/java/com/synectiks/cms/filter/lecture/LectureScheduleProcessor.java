package com.synectiks.cms.filter.lecture;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.domain.Term;

@Component
public class LectureScheduleProcessor {
	
	private final static Logger logger = LoggerFactory.getLogger(Class.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Transactional(propagation=Propagation.REQUIRED)
	public QueryResult updateLectureSchedule(LectureScheduleInput lectureScheduleInput, LectureScheduleFilter filter) {
		QueryResult res = new QueryResult();
		res.setStatusCode(0);
    	res.setStatusDesc("Records updated successfully.");
    	String values[] = lectureScheduleInput.getValues();
    	try {

//        	AcademicYear ay = getAcademicYearDates(filter.getAcademicYear());
        	Term tr = getTerms(filter.getAcademicYear());
        	List<LectureScheduleVo> lectureList = getLectureRecords(tr.getStartDate(), tr.getEndDate());
        	List<LectureScheduleVo> mondayList = filterDataOnDayOfweek(lectureList, Calendar.MONDAY);
        	List<LectureScheduleVo> tuesdayList = filterDataOnDayOfweek(lectureList, Calendar.TUESDAY);
        	List<LectureScheduleVo> wednesdayList = filterDataOnDayOfweek(lectureList, Calendar.WEDNESDAY);
        	List<LectureScheduleVo> thursdayList = filterDataOnDayOfweek(lectureList, Calendar.THURSDAY);
        	List<LectureScheduleVo> fridayList = filterDataOnDayOfweek(lectureList, Calendar.FRIDAY);
        	List<LectureScheduleVo> saturdayList = filterDataOnDayOfweek(lectureList, Calendar.SATURDAY);
        	
        	JSONObject jsonObj = null;
        	
        	for (Iterator<LectureScheduleVo> iterator = mondayList.iterator(); iterator.hasNext();) {
    			LectureScheduleVo vo = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"MONDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					String stTime = jsonObj.getString("startTime");
    					String endTime = jsonObj.getString("endTime");
    					if(stTime.equalsIgnoreCase(vo.getStartTime()) && endTime.equalsIgnoreCase(vo.getEndTime())) {
    						String subjectId = jsonObj.getString("subjectId");
    						Integer id = getAttendanceMasterId(filter, Integer.parseInt(subjectId));
    						updateLecture(vo, id);
    					}
    				}
    			}
    		}
        	
        	for (Iterator<LectureScheduleVo> iterator = tuesdayList.iterator(); iterator.hasNext();) {
    			LectureScheduleVo vo = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"TUESDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					String stTime = jsonObj.getString("startTime");
    					String endTime = jsonObj.getString("endTime");
    					if(stTime.equalsIgnoreCase(vo.getStartTime()) && endTime.equalsIgnoreCase(vo.getEndTime())) {
    						String subjectId = jsonObj.getString("subjectId");
    						Integer id = getAttendanceMasterId(filter, Integer.parseInt(subjectId));
    						updateLecture(vo, id);
    					}
    				}
    			}
    		}
        	
        	for (Iterator<LectureScheduleVo> iterator = wednesdayList.iterator(); iterator.hasNext();) {
    			LectureScheduleVo vo = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"WEDNESDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					String stTime = jsonObj.getString("startTime");
    					String endTime = jsonObj.getString("endTime");
    					if(stTime.equalsIgnoreCase(vo.getStartTime()) && endTime.equalsIgnoreCase(vo.getEndTime())) {
    						String subjectId = jsonObj.getString("subjectId");
    						Integer id = getAttendanceMasterId(filter, Integer.parseInt(subjectId));
    						updateLecture(vo, id);
    					}
    				}
    			}
    		}
        	
        	for (Iterator<LectureScheduleVo> iterator = thursdayList.iterator(); iterator.hasNext();) {
    			LectureScheduleVo vo = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"THURSDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					String stTime = jsonObj.getString("startTime");
    					String endTime = jsonObj.getString("endTime");
    					if(stTime.equalsIgnoreCase(vo.getStartTime()) && endTime.equalsIgnoreCase(vo.getEndTime())) {
    						String subjectId = jsonObj.getString("subjectId");
    						Integer id = getAttendanceMasterId(filter, Integer.parseInt(subjectId));
    						updateLecture(vo, id);
    					}
    				}
    			}
    		}
        	
        	for (Iterator<LectureScheduleVo> iterator = fridayList.iterator(); iterator.hasNext();) {
    			LectureScheduleVo vo = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"FRIDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					String stTime = jsonObj.getString("startTime");
    					String endTime = jsonObj.getString("endTime");
    					if(stTime.equalsIgnoreCase(vo.getStartTime()) && endTime.equalsIgnoreCase(vo.getEndTime())) {
    						String subjectId = jsonObj.getString("subjectId");
    						Integer id = getAttendanceMasterId(filter, Integer.parseInt(subjectId));
    						updateLecture(vo, id);
    					}
    				}
    			}
    		}
        	
        	for (Iterator<LectureScheduleVo> iterator = saturdayList.iterator(); iterator.hasNext();) {
    			LectureScheduleVo vo = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"SATURDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					String stTime = jsonObj.getString("startTime");
    					String endTime = jsonObj.getString("endTime");
    					if(stTime.equalsIgnoreCase(vo.getStartTime()) && endTime.equalsIgnoreCase(vo.getEndTime())) {
    						String subjectId = jsonObj.getString("subjectId");
    						Integer id = getAttendanceMasterId(filter, Integer.parseInt(subjectId));
    						updateLecture(vo, id);
    					}
    				}
    			}
    		}
    	}catch(Exception e) {
    		logger.error("Exception. There is some error in updating lecture records. ",e);
    		res.setStatusCode(1);
        	res.setStatusDesc("There is some error in updating lecture records.");
    	}
    	
    	return res;
	}
	
	private void updateLecture(LectureScheduleVo vo, Integer attendanceMstrId) {
		String sql = "update lecture set attendancemaster_id = ? where lec_date = ? and start_time = ? and end_time = ?";
    	Query query = this.entityManager.createNativeQuery(sql);
    	query.setParameter(1, attendanceMstrId);
		query.setParameter(2, vo.getLecDate());
		query.setParameter(3, vo.getStartTime());
		query.setParameter(4, vo.getEndTime());
		query.executeUpdate();
	}
	
	private Integer getAttendanceMasterId(LectureScheduleFilter filter, int subjectId) {
		String sql = "select am.id from attendance_master am, teach tc, subject sb where am.teach_id = tc.id and tc.subject_id = sb.id and am.section_id = ? and sb.batch_id = ? and sb.department_id = ? and sb.id = ?";
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter(1, filter.getSectionId());
		query.setParameter(2, filter.getBatchId());
		query.setParameter(3, filter.getDepartmentId());
		query.setParameter(4, subjectId);
		List ls = query.getResultList();
		Integer id = ((BigInteger)ls.get(0)).intValue();
		
		return id;
	}
	
	private List<LectureScheduleVo> filterDataOnDayOfweek(List<LectureScheduleVo> dateList, int dayOfweek) {
		Calendar cal = Calendar.getInstance();
		List<LectureScheduleVo> list = new ArrayList<LectureScheduleVo>();
		for (Iterator<LectureScheduleVo> iterator = dateList.iterator(); iterator.hasNext();) {
			LectureScheduleVo vo = iterator.next();
			cal.setTime(vo.getLecDate());
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if( day == dayOfweek) {
				list.add(vo);
			}
		}
		return list;
	}
	
	private List<Date> filterDateListOnDayOfweek(List<Date> dateList, int dayOfweek) {
		Calendar cal = Calendar.getInstance();
		List<Date> list = new ArrayList<Date>();
		for (Iterator<Date> iterator = dateList.iterator(); iterator.hasNext();) {
			Date date = iterator.next();
			cal.setTime(date);
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if( day == dayOfweek) {
				list.add(date);
			}
		}
		return list;
	}
	
	private List<LectureScheduleVo> getLectureRecords(Date startDate, Date endDate) {
		String sql = "select id, lec_date, last_updated_on, last_updated_by, start_time, end_time, attendancemaster_id from lecture where lec_date between ? and ?";
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter(1, new java.sql.Date(startDate.getTime()));
		query.setParameter(2, new java.sql.Date(endDate.getTime()));
		List<Object[]> ls = query.getResultList();
		List<LectureScheduleVo> list = new ArrayList<LectureScheduleVo>();
		LectureScheduleVo vo = null;
		for (Object[] result : ls){
			vo = new LectureScheduleVo();
			vo.setId((BigInteger)result[0]);
			vo.setLecDate((java.sql.Date)result[1]);
			vo.setLastUpdatedOn((java.sql.Date)result[2]);
            vo.setLastUpdatedBy((String)result[3]);
            vo.setStartTime((String)result[4]);
            vo.setEndTime((String)result[5]);
            vo.setAttendanceMasterId((BigInteger)result[6]);
            list.add(vo);
        }
		return list;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public QueryResult addLectureSchedule(LectureScheduleInput lectureScheduleInput, LectureScheduleFilter filter) throws JSONException, ParseException {
		
//		AcademicYear ay = getAcademicYearDates(filter.getAcademicYear());
		Term tr = getTerms(filter.getAcademicYear());
		System.out.println("Term data retrieved.");
		List<Date> dateList = createDates(tr.getStartDate(), tr.getEndDate());
		System.out.println("Date list created.");
		List<Holiday> holidayList = getHolidayList(filter.getAcademicYear());
		System.out.println("Holiday data retrieved.");
		filterHolidays (holidayList,dateList);
		System.out.println("Holiday data filtered.");
		filterSundays(dateList);
		System.out.println("Sundays filtered.");
		
		List<Date> mondayList = filterDateListOnDayOfweek(dateList, Calendar.MONDAY);
		List<Date> tuesdayList = filterDateListOnDayOfweek(dateList, Calendar.TUESDAY);
		List<Date> wednesdayList = filterDateListOnDayOfweek(dateList, Calendar.WEDNESDAY);
		List<Date> thursdayList = filterDateListOnDayOfweek(dateList, Calendar.THURSDAY);
		List<Date> fridayList = filterDateListOnDayOfweek(dateList, Calendar.FRIDAY);
		List<Date> saturdayList = filterDateListOnDayOfweek(dateList, Calendar.SATURDAY);
		
		
		QueryResult res = new QueryResult();
		String[] values = lectureScheduleInput.getValues();
//		String sql = "INSERT INTO LECTURE (id,lec_date,last_updated_on,last_updated_by,start_time,end_time,attendancemaster_id) VALUES ((select nextval('hibernate_sequence')),?,?,?,?,?,(select id from attendance_master where teach_id = (select id from teach where subject_id = ? and teacher_id = ?))) ";
		res.setStatusCode(0);
    	res.setStatusDesc("Records inserted successfully.");
    	
		try {
//			Query query = this.entityManager.createNativeQuery(sql);
			JSONObject jsonObj = null;
			System.out.println("Inserting records for monday.");
			for (Iterator<Date> iterator = mondayList.iterator(); iterator.hasNext();) {
				Date dt = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"MONDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					insertLecture(dt, jsonObj, filter);
    				}
    			}
    		}
			System.out.println("Inserting records for tuesday.");
			for (Iterator<Date> iterator = tuesdayList.iterator(); iterator.hasNext();) {
				Date dt = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"TUESDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					insertLecture(dt, jsonObj, filter);
    				}
    			}
    		}
			System.out.println("Inserting records for wednesday.");
			for (Iterator<Date> iterator = wednesdayList.iterator(); iterator.hasNext();) {
				Date dt = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"WEDNESDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					insertLecture(dt, jsonObj,filter);
    				}
    			}
    		}
			
			for (Iterator<Date> iterator = thursdayList.iterator(); iterator.hasNext();) {
				Date dt = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"THURSDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					insertLecture(dt, jsonObj, filter);
    				}
    			}
    		}
			
			for (Iterator<Date> iterator = fridayList.iterator(); iterator.hasNext();) {
				Date dt = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"FRIDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					insertLecture(dt, jsonObj, filter);
    				}
    			}
    		}
			
			for (Iterator<Date> iterator = saturdayList.iterator(); iterator.hasNext();) {
				Date dt = iterator.next();
    			for(String val: values) {
    				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
    				jsonObj = new JSONObject(val);
    				if(!"SATURDAY".equalsIgnoreCase(jsonObj.getString("WEEKDAY"))) {
    					continue;
    				}else {
    					insertLecture(dt, jsonObj, filter);
    				}
    			}
    		}
	    	
		}catch(Exception e) {
    		logger.error("Exception. There is some error in inserting lecture records. ",e);
    		res.setStatusCode(1);
        	res.setStatusDesc("There is some error in inserting lecture records.");
    	}
    	return res;
	}
	
	private void insertLecture(Date date, JSONObject jsonObj, LectureScheduleFilter filter) throws ParseException {
		String sql = "INSERT INTO LECTURE (id,lec_date,last_updated_on,last_updated_by,start_time,end_time,attendancemaster_id) VALUES ((select nextval('hibernate_sequence')),?,?,?,?,?,(select id from attendance_master where teach_id = (select id from teach where subject_id = ? and teacher_id = ?) and batch_id = ? and section_id = ? )) ";
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter(1, getDate(date));
		query.setParameter(2, new java.sql.Date(System.currentTimeMillis()));
		query.setParameter(3, getLoggedInUser());
		query.setParameter(4, jsonObj.getString("startTime"));
		query.setParameter(5, jsonObj.getString("endTime"));
		query.setParameter(6, Integer.parseInt(jsonObj.getString("subjectId")));
		query.setParameter(7, Integer.parseInt(jsonObj.getString("teacherId")));
		query.setParameter(8, Integer.parseInt(jsonObj.getString("batchId")));
		query.setParameter(9, Integer.parseInt(jsonObj.getString("sectionId")));
		
		query.executeUpdate();
	}
	
	private java.sql.Date getDate(String dt) throws ParseException{
    	Date date=this.format.parse(dt);
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		return sDate;
	}
	
	private java.sql.Date getDate(Date dt) throws ParseException{
		String dateString = this.format.format(dt);
		Date date = this.format.parse (dateString);
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		return sDate;
	}
	
	private String getLoggedInUser() {
		return "Application User";
	}
	
	private AcademicYear getAcademicYearDates(int academicYear) {
		String sql = "select start_date, end_date from academic_year where jhi_year = ?";
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter(1, academicYear);
		List<Object[]> ls = query.getResultList();
		AcademicYear ay = null;
		for (Object[] result : ls){
			ay = new AcademicYear();
			java.sql.Date dt = (java.sql.Date)result[0];
			ay.setStartDate(new Date(dt.getTime()));
			dt = (java.sql.Date)result[1];
			ay.setEndDate(new Date(dt.getTime()));
        }
		return ay;
	}
	
	private Term getTerms(int academicYear) {
		String sql = "select start_date, end_date from term where term_status = 'ACTIVE' and academicyear_id = (select id from academic_year where jhi_year = ?)";
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter(1, academicYear);
		List<Object[]> ls = query.getResultList();
		Term tm = null;
		for (Object[] result : ls){
			tm = new Term();
			java.sql.Date dt = (java.sql.Date)result[0];
			tm.setStartDate(new Date(dt.getTime()));
			dt = (java.sql.Date)result[1];
			tm.setEndDate(new Date(dt.getTime()));
        }
		return tm;
	}
	
	private List<Holiday> getHolidayList(int academicYear) throws ParseException {
		String sql = "select holiday_date from holiday where holiday_status = 'ACTIVE' and academicyear_id = (select id from academic_year where jhi_year = ?)";
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter(1, academicYear);
		List<Object[]> ls = query.getResultList();
		Holiday hl = null;
		List<Holiday> list = new ArrayList<Holiday>();
		java.sql.Date dt = null;
		for (Object[] result : ls){
			hl = new Holiday();
			dt = (java.sql.Date)result[0];
			hl.setHolidayDate(new Date(dt.getTime()));
			list.add(hl);
		}
		return list;
	}
	
	private List<Date> createDates(Date startDate, Date endDate) {
		List<Date> dateList = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		while(cal.getTime().compareTo(endDate) <1) {
			Date dt = cal.getTime();
			dateList.add(dt);
			cal.add(Calendar.DATE, 1);
		}
		return dateList;
	}
	
	private void filterHolidays (List<Holiday> holidayList, List<Date> dateList) {
		for (Iterator<Date> iterator = dateList.iterator(); iterator.hasNext();) {
			Date date = iterator.next();
			for(Iterator<Holiday> itrHoliday = holidayList.iterator(); itrHoliday.hasNext();) {
				Holiday hl = itrHoliday.next();
				if(date.compareTo(hl.getHolidayDate()) == 0) {
					iterator.remove();
				}
				break;
			}
		}
	}
	
	private void filterSundays(List<Date> dateList) {
		Calendar cal = Calendar.getInstance();
		for (Iterator<Date> iterator = dateList.iterator(); iterator.hasNext();) {
			Date date = iterator.next();
			cal.setTime(date);
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (day == Calendar.SUNDAY) {
				iterator.remove();
		    }
		}
	}
	

	
	
	
	
	
}
