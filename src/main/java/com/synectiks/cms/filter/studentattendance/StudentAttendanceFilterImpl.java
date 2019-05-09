package com.synectiks.cms.filter.studentattendance;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.StudentAttendance;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;
import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.StudentAttendanceRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.DateFormatUtil;


@Repository
public class StudentAttendanceFilterImpl  {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @PersistenceContext
//    private EntityManager entityManager;
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CommonService commonService;
    
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;
    
    @Autowired
    private LectureRepository lectureRepository;
    
    public List<DailyAttendanceVo> getStudenceAttendance(StudentAttendanceFilterInput filter) throws Exception {
        
        Branch branch = this.commonService.getBranchById(Long.valueOf(filter.getBranchId()));
    	Department department = this.commonService.getDepartmentById(Long.valueOf(filter.getDepartmentId()));
    	Batch batch = this.commonService.getBatchById(Long.valueOf(filter.getBatchId()));
    	Section section = this.commonService.getSectionById(Long.valueOf(filter.getSectionId()));
    	Teach teach = this.commonService.getTeachBySubjectAndTeacherId(Long.valueOf(filter.getTeacherId()), Long.valueOf(filter.getSubjectId()));
    	AttendanceMaster am = this.commonService.getAttendanceMasterByBatchSectionTeach(batch, section, teach);
    	
    	List<DailyAttendanceVo> voList = new ArrayList<>();
        List<Student> studentList = getStudentList(branch, department, batch, section);
        
        Lecture currentDateLecture = lectureScheduleStatus(filter.getAttendanceDate(), am, 0);
        Lecture oneDayPrevLecture = lectureScheduleStatus(filter.getAttendanceDate(), am, 1);
        Lecture twoDayPrevLecture = lectureScheduleStatus(filter.getAttendanceDate(), am, 2);
        Lecture threeDayPrevLecture = lectureScheduleStatus(filter.getAttendanceDate(), am, 3);
        
        // lecture schedule on current date insert/update all the students in student_attendance table.
        setCurrentDateStatus(voList, studentList, currentDateLecture);
        setHistoryDateStatus(voList, oneDayPrevLecture, 1);
        setHistoryDateStatus(voList, twoDayPrevLecture, 2);
        setHistoryDateStatus(voList, threeDayPrevLecture, 3);
        
        return voList;
    }

	private void setCurrentDateStatus(List<DailyAttendanceVo> voList, List<Student> studentList,
			Lecture currentDateLecture) {
		if(currentDateLecture != null) {
        	for(Student st : studentList) {
        		StudentAttendance sa = getStudentAttendance(st, currentDateLecture);
        		DailyAttendanceVo vo = new DailyAttendanceVo();
				if(sa != null) {
				   	vo.setStudentId(String.valueOf(st.getId()));
				   	vo.setStudentName(st.getStudentName() + " " + st.getStudentMiddleName() + " " +st.getStudentLastName());
				   	vo.setCurrentDateStatus(sa.getAttendanceStatus().toString());
				   	vo.setComments(sa.getComments());
				}else {
					// insert record in student_attendance table and mark the status as present by default.
					sa = new StudentAttendance();
					sa.setLecture(currentDateLecture);
					sa.setStudent(st);
					sa.setAttendanceStatus(AttendanceStatusEnum.PRESENT);
					sa = this.studentAttendanceRepository.save(sa);
					vo.setStudentId(String.valueOf(st.getId())); 
					vo.setStudentName(st.getStudentName() + " " + st.getStudentMiddleName() + " " +st.getStudentLastName());
					vo.setCurrentDateStatus(sa.getAttendanceStatus().toString());
				}
				vo.setStudent(st);
				voList.add(vo);
        	}
        }else {
        	for(Student st : studentList) {
        		DailyAttendanceVo vo = new DailyAttendanceVo();
        		vo.setStudentId(String.valueOf(st.getId()));
        		vo.setStudentName(st.getStudentName() + " " + st.getStudentMiddleName() + " " +st.getStudentLastName());
        		vo.setStudent(st);
				vo.setCurrentDateStatus(CmsConstants.LECTURE_NOT_SCHEDULED);
				voList.add(vo);
        	}
        }
	}
	
	private void setHistoryDateStatus(List<DailyAttendanceVo> voList, Lecture historyDateLecture, int day) {
		if(historyDateLecture != null) {
        	for(DailyAttendanceVo vo : voList) {
        		StudentAttendance sa = getStudentAttendance(vo.getStudent(), historyDateLecture);
				if(sa != null) {
					if(day == 1) {
						vo.setPreviousOneDayStatus(sa.getAttendanceStatus().toString());
					}else if(day == 2) {
						vo.setPreviousTwoDayStatus(sa.getAttendanceStatus().toString());
					}else if(day == 3) {
						vo.setPreviousThreeDayStatus(sa.getAttendanceStatus().toString());
					}
				}else {
					if(day == 1) {
						vo.setPreviousOneDayStatus(AttendanceStatusEnum.ABSENT.toString());
					}else if(day == 2) {
						vo.setPreviousTwoDayStatus(AttendanceStatusEnum.ABSENT.toString());
					}else if(day == 3) {
						vo.setPreviousThreeDayStatus(AttendanceStatusEnum.ABSENT.toString());
					}
				}
        	}
        }else {
        	for(DailyAttendanceVo vo : voList) {
        		if(day == 1) {
					vo.setPreviousOneDayStatus(CmsConstants.LECTURE_NOT_SCHEDULED);
				}else if(day == 2) {
					vo.setPreviousTwoDayStatus(CmsConstants.LECTURE_NOT_SCHEDULED);
				}else if(day == 3) {
					vo.setPreviousThreeDayStatus(CmsConstants.LECTURE_NOT_SCHEDULED);
				}
        	}
        }
	}
	
	
    private StudentAttendance getStudentAttendance(Student student, Lecture lecture) {
    	if(null == lecture) return null;
    	StudentAttendance sa = new StudentAttendance();
    	sa.student(student);
    	sa.lecture(lecture);
    	Example<StudentAttendance> example = Example.of(sa);
    	Optional<StudentAttendance> nsa = this.studentAttendanceRepository.findOne(example);
    	if(nsa.isPresent()) {
    		return nsa.get();
    	}
    	return null;
    }
    
    private Lecture lectureScheduleStatus(String lectureDate, AttendanceMaster attendanceMaster, int days) throws ParseException, Exception {
    	Lecture lec = new Lecture();
    	Date lecDate = DateFormatUtil.getUtilDate(CmsConstants.DATE_FORMAT_dd_MM_yyyy, lectureDate);
//    			DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, "dd/MM/yyyy", lectureDate));
    	if(days > 0) {
    		String sDate = DateFormatUtil.subtractDays(CmsConstants.DATE_FORMAT_dd_MM_yyyy, lecDate, days);
    		lecDate = DateFormatUtil.getUtilDate(CmsConstants.DATE_FORMAT_dd_MM_yyyy,sDate);
    	}
    	lec.setLecDate(lecDate);
    	lec.setAttendancemaster(attendanceMaster);
    	Example<Lecture> example = Example.of(lec);
    	Optional<Lecture> nlec = this.lectureRepository.findOne(example);
    	if(nlec.isPresent()) {
    		return nlec.get();
    	}
    	return null;
    }
    
    private List<Student> getStudentList(Branch branch, Department department, Batch batch, Section section){
    	Student student = new Student();
    	student.setBranch(branch);
    	student.setDepartment(department);
    	student.setBatch(batch);
    	student.setSection(section);
    	Example<Student> example = Example.of(student);
    	List<Student> studentList = this.studentRepository.findAll(example);
    	return studentList;
    }
    
    public QueryResult updateStudentStatus(List<StudentAttendanceUpdateFilter> list) {
    	logger.info("Start updating student attendance data ");
    	QueryResult result = new QueryResult();
    	result.setStatusCode(0);
    	result.setStatusDesc(CmsConstants.UPDATE_SUCCESS_MESSAGE);
    	
    	StudentAttendance stObj = new StudentAttendance();
    	Student student = null;
    	Lecture lecture = null;
    	try {
    		for(StudentAttendanceUpdateFilter sa: list) {
        		String values = sa.getStudentIds();//.split("##delimline##");
        		String lectureId = sa.getLectureId();
        		if(lecture == null) {
    				lecture = this.lectureRepository.findById(Long.valueOf(lectureId)).get();
    			}
//        		for(String val: values) {
    			String data[] = values.split("#~#");
    			student = this.studentRepository.findById(Long.valueOf(data[0])).get();
    			
    			stObj.setStudent(student);
    			stObj.setLecture(lecture);
    			Example<StudentAttendance> example = Example.of(stObj);
    			Optional<StudentAttendance> osa = this.studentAttendanceRepository.findOne((example));
    			if(osa.isPresent()) {
    				stObj.setId(osa.get().getId());
    				stObj.setAttendanceStatus(data[1].equalsIgnoreCase("PRESENT") ? AttendanceStatusEnum.PRESENT : AttendanceStatusEnum.ABSENT);
        			if(data.length > 2) {
        				stObj.setComments(data[2]);
        			}else {
        				stObj.setComments(null);
        			}
    				logger.debug("Updating student attendance id : "+osa.get().getId());
    				this.studentAttendanceRepository.save(stObj);
    				stObj.setId(null);
    				stObj.setAttendanceStatus(null);
    				stObj.setComments(null);
    			}
//        		}
        	}
    	}catch(Exception e) {
    		logger.error("Method updateStudentStatus. "+CmsConstants.UPDATE_FAILURE_MESSAGE, e);
    		result.setStatusCode(1);
    		result.setStatusDesc(CmsConstants.UPDATE_FAILURE_MESSAGE);
    	}
    	
    	return result;
    }
    
//    private List<DailyAttendanceVo> executeSelectQuery(Query query) {
//        List<DailyAttendanceVo> resultList = new ArrayList<DailyAttendanceVo>();
//
//        List<Object[]> ls = query.getResultList();
//        for (Object[] result : ls){
//            DailyAttendanceVo da = new DailyAttendanceVo();
//            da.setStudentId(String.valueOf((BigInteger)result[0]));
//            da.setStudentName((String)result[1]);
//            da.setCurrentDateStatus((String)result[2]);
//            da.setPreviousOneDayStatus((String)result[3]);
//            da.setPreviousTwoDayStatus((String)result[4]);
//            da.setPreviousThreeDayStatus((String)result[5]);
//            da.setComments((String)result[6]);
//            resultList.add(da);
//        }
//        return resultList;
//    }
 

}
