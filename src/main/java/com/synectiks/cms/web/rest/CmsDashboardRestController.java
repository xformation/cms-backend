package com.synectiks.cms.web.rest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.CmsInvoiceService;
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.CmsDashboardVo;
import com.synectiks.cms.domain.CmsHolidayVo;
import com.synectiks.cms.domain.CmsInvoice;
import com.synectiks.cms.domain.CmsLectureVo;
import com.synectiks.cms.domain.CmsNotificationsVo;
import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.StudentFacilityLink;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.repository.StudentFacilityLinkRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;

/**
 * REST controller for managing Dashboard.
 */
@RestController
@RequestMapping("/api")
public class CmsDashboardRestController {

    private final Logger logger = LoggerFactory.getLogger(CmsDashboardRestController.class);

//    @Autowired
//    private InvoiceRepository invoiceRepository;

    @Autowired
    private CmsInvoiceService cmsInvoiceService;

    @Autowired
    private StudentRepository studentRepository;

//    @Autowired
//    private TeacherRepository teacherRepository;

//    @Autowired
//    private AttendanceMasterRepository attendanceMasterRepository;

//    @Autowired
//    private LectureRepository lectureRepository;

    @Autowired
    private StudentFacilityLinkRepository studentFacilityLinkRepository;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsdashboard")
    public ResponseEntity<Object> cmsDashboardUser(@RequestParam  String userName, @RequestParam  String role) {
		CmsDashboardVo obj = new CmsDashboardVo();
		try {
			if("STUDENT".equalsIgnoreCase(role)) {
				logger.info("Getting student data for dashboard");
				Student st = null; //new Student();
				Optional<Student> ost = getStudent(userName);
				if(ost.isPresent()) {
					st = ost.get();
				}
				CmsStudentVo csvo = CommonUtil.createCopyProperties(st, CmsStudentVo.class);
				csvo.setAcademicYear(this.commonService.getAcademicYearById(st.getAcademicYearId()));
				csvo.setBranch(this.commonService.getBranchById(st.getBranchId()));
				csvo.setDepartment(this.commonService.getDepartmentById(st.getDepartmentId()));
				csvo.setBatch(this.commonService.getBatchById(st.getBatchId()));
				csvo.setSection(this.commonService.getSectionById(st.getSectionId()));
				
				obj.setStudent(csvo);
				
				CmsInvoice inv = getInvoiceByStudentId(st);
				List<StudentFacilityLink> list = getStudentFacilityLinkByStudentUserName(st);
				List<Lecture> lecList = this.commonService.getTotalLecturesScheculedOnCurrentDayForGivenBatchAndSection(st.getBatchId(), st.getSectionId());
				List<CmsLectureVo> ls = new ArrayList<>();
				for(Lecture l: lecList) {
					CmsLectureVo vo = CommonUtil.createCopyProperties(l, CmsLectureVo.class);
					vo.setStrLecDate(DateFormatUtil.changeLocalDateFormat(l.getLecDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
					vo.setLecDate(null);
					ls.add(vo);
				}
				List<CmsNotificationsVo> ntfList = this.commonService.getNotifications();

				obj.setCmsInvoice(inv);
				obj.setStudentFacilityLinkList(list);
				obj.setCmsLectureVoList(ls);
				obj.setCmsNotificationsVoList(ntfList);

			}else if("TEACHER".equalsIgnoreCase(role)) {
				logger.info("Getting teacher data for dashboard");
				Teacher th = this.commonService.getTeacherByEmail(userName);
//				th.setTeacherEmailAddress(userName);
//				Optional<Teacher> oth = this.teacherRepository.findOne(Example.of(th));
//				if(oth.isPresent()) {
//					th = oth.get();
//				}
				obj.setTeacher(th);
				List<CmsNotificationsVo> ntfList = this.commonService.getNotifications();
				List<CmsHolidayVo> holidayList = getHolidayList();
				String currentDate = DateFormatUtil.changeLocalDateFormat(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
				List<CmsLectureVo> lecList = this.commonService.getAllCurrentDateCmsLectureForTeacher(th.getId(), currentDate);
				
				obj.setCmsNotificationsVoList(ntfList);
				obj.setCmsHolidayVoList(holidayList);
				obj.setCmsLectureVoList(lecList);
			}

		}catch(Exception e) {
			logger.error("Exception in getting logged in user dashboard data :", e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}

    private CmsInvoice getInvoiceByStudentId(Student student) throws Exception{
    	logger.info("Getting student invoice data for dashboard");
		CmsInvoice obj = this.cmsInvoiceService.getInvoiceByStudentId(student);
        return obj;
    }

    private List<StudentFacilityLink> getStudentFacilityLinkByStudentUserName(Student student) {
        logger.info("Getting student facilities data for dashboard :");
//        Optional<Student> ost = getStudent(userName);
        List<StudentFacilityLink> ls = new ArrayList<>();
        if(student != null) {
        	StudentFacilityLink sfl = new StudentFacilityLink();
        	sfl.setStudent(student);
            ls = this.studentFacilityLinkRepository.findAll(Example.of(sfl), Sort.by(Direction.DESC, "id"));
        }
        return ls;
    }

//    private List<CmsLectureVo> getScheduledLecturesForStudent(Student student){
//    	logger.info("Getting student's scheduled lectures data for dashboard :");
//    	List<CmsLectureVo> ls = new ArrayList<>();
////    	Optional<Student> ost = getStudent(userName);
//    	if(student != null) {
//    		AttendanceMaster am = new AttendanceMaster();
////    		am.setBatch(ost.get().getBatch());
////    		am.setSection(ost.get().getSection());
//    		List<AttendanceMaster> amList = this.attendanceMasterRepository.findAll(Example.of(am));
//    		for(AttendanceMaster a: amList) {
//    			Lecture lec = new Lecture();
//        		lec.setAttendancemaster(a);
//        		lec.setLecDate(LocalDate.now());
//        		List<Lecture> list = this.lectureRepository.findAll(Example.of(lec));
//        		for(Lecture l: list) {
//        			CmsLectureVo vo = CommonUtil.createCopyProperties(l, CmsLectureVo.class);
//        			vo.setStrLecDate(DateFormatUtil.changeLocalDateFormat(l.getLecDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//        			ls.add(vo);
//        		}
//    		}
//
//    	}
//    	logger.debug("Total lectures scheduled today for student : "+ls.size());
//    	return ls;
//    }


//    private List<CmsLectureVo> getScheduledLecturesForTeacher(Teacher teacher){
//    	logger.info("Getting teacher's scheduled lectures data for dashboard :");
//    	List<CmsLectureVo> ls = new ArrayList<>();
//    	if(teacher != null) {
//    		Teach teach = new Teach();
//    		teach.setTeacher(teacher);
//    		AttendanceMaster am = new AttendanceMaster();
//    		am.setTeach(teach);
//
////    		List<AttendanceMaster> amList = this.attendanceMasterRepository.findAll(Example.of(am));
////    		for(AttendanceMaster a: amList) {
//    			Lecture lec = new Lecture();
//        		lec.setAttendancemaster(am);
//        		lec.setLecDate(LocalDate.now());
//        		List<Lecture> list = this.lectureRepository.findAll(Example.of(lec));
//        		for(Lecture l: list) {
//        			CmsLectureVo vo = CommonUtil.createCopyProperties(l, CmsLectureVo.class);
//        			vo.setStrLecDate(DateFormatUtil.changeLocalDateFormat(l.getLecDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//        			ls.add(vo);
//        		}
////    		}
//
//    	}
//    	logger.debug("Total lectures scheduled today for teacher : "+ls.size());
//    	return ls;
//    }



    private Optional<Student> getStudent(String userName){
    	Student st = new Student();
		st.setStudentPrimaryEmailId(userName);
		Optional<Student> ost = this.studentRepository.findOne(Example.of(st));
		return ost;
    }

    private List<CmsHolidayVo> getHolidayList(){
    	AcademicYear ay = this.commonService.getActiveAcademicYear();
    	List<Holiday> list = this.commonService.getHolidayList(Optional.of(ay));
    	List<CmsHolidayVo> ls = new ArrayList<>();
    	for(Holiday hl: list) {
    		CmsHolidayVo vo = CommonUtil.createCopyProperties(hl, CmsHolidayVo.class);
    		vo.setStrHolidayDate(DateFormatUtil.changeLocalDateFormat(vo.getHolidayDate(), CmsConstants.DATE_FORMAT_d_MMM_yyyy));
    		ls.add(vo);
    	}
    	return ls;
    }
}
