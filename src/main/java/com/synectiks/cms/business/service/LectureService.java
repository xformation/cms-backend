package com.synectiks.cms.business.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.CmsLectureVo;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.domain.MetaLecture;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.filter.lecture.LectureScheduleInput;
import com.synectiks.cms.filter.lecture.LectureScheduleVo;
import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.MetaLectureRepository;
import com.synectiks.cms.service.dto.LectureScheduleDTO;
import com.synectiks.cms.service.util.CommonUtil;

@Component
public class LectureService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private MetaLectureRepository metaLectureRepository;
	
	@Autowired
	private CommonService commonService;
	
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static final String MONDAY = "MONDAY";
	private static final String TUESDAY = "TUESDAY";
	private static final String WEDNESDAY = "WEDNESDAY";
	private static final String THURSDAY = "THURSDAY";
	private static final String FRIDAY = "FRIDAY";
	private static final String SATURDAY = "SATURDAY";
	private static final String WEEKDAY= "weekDay";
	
	private Batch bth = null;
	private Section sec = null;
	private Map<Object, Object> map = new HashMap<Object, Object>();
	
	@Transactional(propagation=Propagation.REQUIRED)
	public QueryResult updateLectureSchedule(LectureScheduleInput lectureScheduleInput, LectureScheduleFilter filter) {
		QueryResult res = new QueryResult();
		res.setStatusCode(0);
    	res.setStatusDesc("Records updated successfully.");
    	String values[] = lectureScheduleInput.getValues();
    	Term tr = commonService.getTermById(Long.parseLong(filter.getTermId()));
    	List<LectureScheduleVo> lectureList = getLectureRecords(tr.getStartDate(), tr.getEndDate());
    	List<LectureScheduleVo> mondayList = filterDataOnDayOfweek(lectureList, Calendar.MONDAY);
    	List<LectureScheduleVo> tuesdayList = filterDataOnDayOfweek(lectureList, Calendar.TUESDAY);
    	List<LectureScheduleVo> wednesdayList = filterDataOnDayOfweek(lectureList, Calendar.WEDNESDAY);
    	List<LectureScheduleVo> thursdayList = filterDataOnDayOfweek(lectureList, Calendar.THURSDAY);
    	List<LectureScheduleVo> fridayList = filterDataOnDayOfweek(lectureList, Calendar.FRIDAY);
    	List<LectureScheduleVo> saturdayList = filterDataOnDayOfweek(lectureList, Calendar.SATURDAY);
    	
    	try {
        	updateAllLectureData(filter, values, mondayList, MONDAY);
        	updateAllLectureData(filter, values, tuesdayList, TUESDAY);
        	updateAllLectureData(filter, values, wednesdayList, WEDNESDAY);
        	updateAllLectureData(filter, values, thursdayList, THURSDAY);
        	updateAllLectureData(filter, values, fridayList, FRIDAY);
        	updateAllLectureData(filter, values, saturdayList, SATURDAY);
    	}catch(Exception e) {
    		logger.error("Exception. There is some error in updating lecture records. ",e);
    		res.setStatusCode(1);
        	res.setStatusDesc("There is some error in updating lecture records.");
    	}
    	
    	return res;
	}

	private void updateAllLectureData(LectureScheduleFilter filter, String[] values,
			List<LectureScheduleVo> dataList, String dayName) {
		JSONObject jsonObj;
		for (Iterator<LectureScheduleVo> iterator = dataList.iterator(); iterator.hasNext();) {
			LectureScheduleVo vo = iterator.next();
			for(String val: values) {
				
				try {
					jsonObj = new JSONObject(val);
				}catch(JSONException je) {
					val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
					jsonObj = new JSONObject(val);
				}
				
				if(!dayName.equalsIgnoreCase(jsonObj.getString(WEEKDAY))) {
					continue;
				}else {
					updateLecture(vo, jsonObj);
				}
			}
		}
	}
	
	private void updateLecture(LectureScheduleVo vo, JSONObject jsonObj) {
		String sql = "update lecture set attendancemaster_id = ?, start_time = ?, end_time = ? where id = ? ";
    	Query query = this.entityManager.createNativeQuery(sql);
    	query.setParameter(1, jsonObj.get("attendancemasterId"));
    	query.setParameter(2, jsonObj.get("startTime"));
    	query.setParameter(3, jsonObj.get("endTime"));
		query.setParameter(4, vo.getId());
		
		query.executeUpdate();
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
	
	public List<Date> filterDateListOnDayOfweek(List<Date> dateList, int dayOfweek) {
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
		QueryResult res = new QueryResult();
		res.setStatusCode(0);
    	res.setStatusDesc("Records inserted successfully.");
    	
		Term tr = commonService.getTermById(Long.parseLong(filter.getTermId()));
		logger.debug("Term data retrieved.");
		List<Date> dateList = createDates(tr);
		logger.debug("Date list created.");
		List<Holiday> holidayList = commonService.getHolidayList(filter.getAcademicYear());
		logger.debug("Holiday data retrieved.");
		filterHolidays (holidayList,dateList);
		logger.debug("Holiday data filtered.");
		filterSundays(dateList);
		logger.debug("Sundays filtered.");
		
		List<Date> mondayList = filterDateListOnDayOfweek(dateList, Calendar.MONDAY);
		List<Date> tuesdayList = filterDateListOnDayOfweek(dateList, Calendar.TUESDAY);
		List<Date> wednesdayList = filterDateListOnDayOfweek(dateList, Calendar.WEDNESDAY);
		List<Date> thursdayList = filterDateListOnDayOfweek(dateList, Calendar.THURSDAY);
		List<Date> fridayList = filterDateListOnDayOfweek(dateList, Calendar.FRIDAY);
		List<Date> saturdayList = filterDateListOnDayOfweek(dateList, Calendar.SATURDAY);
    	String[] values = lectureScheduleInput.getValues();
//    	this.query = this.entityManager.createNativeQuery(INSERT_SQL);
    	this.bth = commonService.getBatchById(Long.valueOf(filter.getBatchId()));
		this.sec = commonService.getSectionById(Long.valueOf(filter.getSectionId()));
		try {
			createLectureSchedule(mondayList, values, MONDAY, filter);
			createLectureSchedule(tuesdayList, values, TUESDAY, filter);
			createLectureSchedule(wednesdayList, values, WEDNESDAY, filter);
			createLectureSchedule(thursdayList, values, THURSDAY, filter);
			createLectureSchedule(fridayList, values, FRIDAY, filter);
			createLectureSchedule(saturdayList, values, SATURDAY, filter);
		}catch(Exception e) {
    		logger.error("Exception. There is some error in inserting lecture records. ",e);
    		res.setStatusCode(1);
        	res.setStatusDesc("There is some error in inserting lecture records.");
    	}
    	return res;
	}

	public void createLectureSchedule(List<Date> dataList, String[] values, String dayName, LectureScheduleFilter filter)
			throws ParseException {
		logger.debug(String.format("Inserting records for %s.",dayName));
		
//		Batch bth = commonService.getBatchById(Long.valueOf(filter.getBatchId()));
//		Section sec = commonService.getSectionById(Long.valueOf(filter.getSectionId()));
		
		JSONObject jsonObj;
		
		Teach th = null;
		AttendanceMaster am = null;
		for (Iterator<Date> iterator = dataList.iterator(); iterator.hasNext();) {
			Date dt = iterator.next();
			for(String val: values) {
				try {
					jsonObj = new JSONObject(val);
				}catch(JSONException je) {
					val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
					jsonObj = new JSONObject(val);
				}
				if(!dayName.equalsIgnoreCase(jsonObj.getString(WEEKDAY))) {
					continue;
				}else {
//					if(this.map.get(th) != null) {
//						th = (Teach)this.map.get(th);
//					}else {
//						if(null == jsonObj.getString("teacherId") || null == jsonObj.getString("subjectId")) {
//							continue;
//						}
						th = commonService.getTeachBySubjectAndTeacherId(Long.parseLong(jsonObj.getString("teacherId")), 
								Long.parseLong(jsonObj.getString("subjectId")));
//						this.map.put(th, th);
//					}
//					if(this.map.get(am) != null) {
//						am = (AttendanceMaster)this.map.get(am);
//					}else {
						am = commonService.getAttendanceMasterByBatchSectionTeach(this.bth, this.sec, th);
//						this.map.put(am, am);
//					}
					Lecture lecture = new Lecture();
//					lecture.setId(null);
					lecture.setAttendancemaster(am);
					lecture.setLecDate(getDate(dt));
					lecture.setStartTime(jsonObj.getString("startTime"));
					lecture.setEndTime(jsonObj.getString("endTime"));
					lecture.setLastUpdatedBy(getLoggedInUser());
					lecture.setLastUpdatedOn(new java.sql.Date(System.currentTimeMillis()));
					this.lectureRepository.save(lecture);
//					insertLecture(dt, jsonObj, filter);
				}
			}
		}
//		this.map.remove("Teach");
//		this.map.remove("AttendanceMaster");
	}
	
	public void createLectureSchedule(Date dt, String[] values, String dayName, LectureScheduleFilter filter)
			throws ParseException {
		logger.debug(String.format("Inserting records for %s.",dayName));
		
		Batch bth = commonService.getBatchById(Long.valueOf(filter.getBatchId()));
		Section sec = commonService.getSectionById(Long.valueOf(filter.getSectionId()));
		Lecture lecture = new Lecture();
		JSONObject jsonObj;
		for(String val: values) {
			try {
				jsonObj = new JSONObject(val);
			}catch(JSONException je) {
				val = val.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
				jsonObj = new JSONObject(val);
			}
			if(!dayName.equalsIgnoreCase(jsonObj.getString(WEEKDAY))) {
				continue;
			}else {
				Teach th = commonService.getTeachBySubjectAndTeacherId(Long.parseLong(jsonObj.getString("teacherId")), Long.parseLong(jsonObj.getString("subjectId")));
				AttendanceMaster am = commonService.getAttendanceMasterByBatchSectionTeach(bth, sec, th);
				lecture.setId(null);
				lecture.setAttendancemaster(am);
				lecture.setLecDate(getDate(dt));
				lecture.setStartTime(jsonObj.getString("startTime"));
				lecture.setEndTime(jsonObj.getString("endTime"));
				lecture.setLastUpdatedBy(getLoggedInUser());
				lecture.setLastUpdatedOn(new java.sql.Date(System.currentTimeMillis()));
				lectureRepository.save(lecture);
//					insertLecture(dt, jsonObj, filter);
			}
		}
	}
	
//	private void insertLecture(Date date, JSONObject jsonObj, LectureScheduleFilter filter) throws ParseException {
////		String sql = "INSERT INTO LECTURE (id,lec_date,last_updated_on,last_updated_by,start_time,end_time,attendancemaster_id) VALUES ((select nextval('hibernate_sequence')),?,?,?,?,?,(select id from attendance_master where teach_id = (select id from teach where subject_id = ? and teacher_id = ?) and batch_id = ? and section_id = ? )) ";
////		Query query = this.entityManager.createNativeQuery(sql);
//		this.query.setParameter(1, getDate(date));
//		this.query.setParameter(2, new java.sql.Date(System.currentTimeMillis()));
//		this.query.setParameter(3, getLoggedInUser());
//		this.query.setParameter(4, jsonObj.getString("startTime"));
//		this.query.setParameter(5, jsonObj.getString("endTime"));
//		this.query.setParameter(6, Long.parseLong(jsonObj.getString("subjectId")));
//		this.query.setParameter(7, Long.parseLong(jsonObj.getString("teacherId")));
//		this.query.setParameter(8, Long.parseLong(filter.getBatchId()));
//		this.query.setParameter(9, Long.parseLong(filter.getSectionId()));
//		
//		this.query.executeUpdate();
//	}
	
	private java.sql.Date getDate(Date dt) throws ParseException{
		String dateString = this.format.format(dt);
		Date date = this.format.parse (dateString);
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		return sDate;
	}
	
	private String getLoggedInUser() {
		return "Application User";
	}
	
	public List<Date> createDates(Term tr) {
		List<Date> dateList = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(tr.getStartDate());
		while(cal.getTime().compareTo(tr.getEndDate()) <1) {
			Date dt = cal.getTime();
			dateList.add(dt);
			cal.add(Calendar.DATE, 1);
		}
		return dateList;
	}
	
	public void filterHolidays (List<Holiday> holidayList, List<Date> dateList) {
		List<Date> hlList = new ArrayList<>();
		for(Iterator<Holiday> itrHoliday = holidayList.iterator(); itrHoliday.hasNext();) {
			Holiday hl = itrHoliday.next();
			hlList.add(hl.getHolidayDate());
		}
		dateList.removeAll(hlList);
	}
	
	public void filterSundays(List<Date> dateList) {
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
	
	
	public List<CmsLectureVo> getAllLecturess(Map<String, String> dataMap) throws ParseException {
		Lecture lecture = new Lecture();
//		String dateFormat1 = 
//		String dateFormat2 = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy);
//		SimpleDateFormat sdf2 = new SimpleDateFormat(dateFormat2);
    	if(dataMap.containsKey("lecDate")) {
    		String lecDate = dataMap.get("subCode");
    		Date dt = sdf.parse(lecDate);  
    		lecture.lecDate(dt);
    	}
		if (dataMap.containsKey("atndMstrId")) {
			String atndMstrId = dataMap.get("atndMstrId");
			AttendanceMaster am = commonService.getAttendanceMasterById(Long.parseLong(atndMstrId));
			logger.debug("AttendanceMaster retrieved by given id : "+atndMstrId);
			lecture.setAttendancemaster(am);
		}
		
		List<Lecture> list = null;
		if(dataMap.size() > 0) {
			Example<Lecture> example = Example.of(lecture);
			list = this.lectureRepository.findAll(example);
		}else {
			list = this.lectureRepository.findAll();
		}
		logger.info("Total lecture retrieved: "+list.size());
		
		List<CmsLectureVo> ls = new ArrayList<>();
		for(Lecture lc : list) {
			CmsLectureVo vo = CommonUtil.createCopyProperties(lc,  CmsLectureVo.class);
			String d = sdf.format(lc.getLecDate());
			vo.setStrLecDate(d);
			vo.setAttendancemasterId(lc.getAttendancemaster().getId());
			ls.add(vo);
		}
		
		return ls;
	}
	
	public boolean addMetaLectureData(LectureScheduleDTO dto, LectureScheduleFilter filter){
		MetaLecture metaLecture = new MetaLecture();
		
		Branch branch = this.commonService.getBranchById(CommonUtil.isNullOrEmpty(filter.getBranchId()) ? null : Long.valueOf(filter.getBranchId().trim()));
        Department department = this.commonService.getDepartmentById(CommonUtil.isNullOrEmpty(filter.getDepartmentId()) ? null : Long.valueOf(filter.getDepartmentId().trim()));
     
        Term term = this.commonService.getTermById(CommonUtil.isNullOrEmpty(filter.getTermId()) ? null : Long.valueOf(filter.getTermId().trim()));
        AcademicYear academicYear = this.commonService.findAcademicYearByYear(CommonUtil.isNullOrEmpty(filter.getAcademicYear()) ? null : filter.getAcademicYear().trim());
        Section section = this.commonService.getSectionById(CommonUtil.isNullOrEmpty(filter.getSectionId()) ? null : Long.valueOf(filter.getSectionId().trim()));
        Batch batch = this.commonService.getBatchById(Long.valueOf(CommonUtil.isNullOrEmpty(filter.getBatchId()) ? null : filter.getBatchId().trim()));
        
        String weekDay = dto.getWeekDay();
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        Subject subject = this.commonService.getSubjectById(Long.valueOf(dto.getSubjectId()));
        Teacher teacher = this.commonService.getTeacherById(Long.valueOf(dto.getTeacherId()));
        if(branch != null && department != null && term != null && academicYear != null && section != null && batch != null && weekDay != null && startTime != null 
        		&& endTime != null && subject != null && teacher != null) {
        	metaLecture.setBranch(branch);
        	metaLecture.setDepartment(department);
        	metaLecture.setTerm(term);
        	metaLecture.academicyear(academicYear);
        	metaLecture.setSection(section);
        	metaLecture.setBatch(batch);
        	metaLecture.setWeekDay(weekDay);
        	metaLecture.setStartTime(startTime);
        	metaLecture.setEndTime(endTime);
        	metaLecture.setSubject(subject);
        	metaLecture.setTeacher(teacher);
        	logger.debug("Saving data in metalecture table.");
        	this.metaLectureRepository.save(metaLecture);
        	return true;
        }
		return false;
	}
}
