package com.synectiks.cms.web.rest;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.business.service.LectureService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.CmsLectureVo;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.StudentAttendance;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.filter.lecture.LectureScheduleInput;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.AttendanceMasterRepository;
import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.StudentAttendanceRepository;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.TeachRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.service.dto.LectureScheduleDTO;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Lecture.
 */
@RestController
@RequestMapping("/api")
public class LectureRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LectureService lectureService;
	
	@Autowired
    private AcademicYearRepository academicYearRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private TeachRepository teachRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private AttendanceMasterRepository attendanceMasterRepository;
	
	@Autowired
	private StudentAttendanceRepository studentAttendanceRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmslectures")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<CmsLectureVo> addLectures(@RequestBody List<LectureScheduleDTO> list, @RequestParam Map<String, String> dataMap) throws JSONException, ParseException, JsonProcessingException {
		LectureScheduleInput lectureScheduleInput = new LectureScheduleInput();
		
		LectureScheduleFilter filter = new LectureScheduleFilter();
		filter.setTermId(dataMap.get("termId") != null ? dataMap.get("termId").trim() : dataMap.get("termId") );
		filter.setSectionId(dataMap.get("sectionId") != null ? dataMap.get("sectionId").trim() : dataMap.get("sectionId"));
		filter.setBatchId(dataMap.get("batchId") != null ? dataMap.get("batchId").trim() : dataMap.get("batchId") );
		filter.setBranchId(dataMap.get("branchId") != null ? dataMap.get("branchId").trim() : dataMap.get("branchId"));
		filter.setDepartmentId(dataMap.get("departmentId") != null ? dataMap.get("departmentId").trim() : dataMap.get("departmentId"));
		
//		filter.setAcademicYear(dataMap.get("academicYear") != null ? dataMap.get("academicYear").trim() : dataMap.get("academicYear"));
		Long id = Long.valueOf(dataMap.get("academicYearId"));
		Optional<AcademicYear> oay = this.academicYearRepository.findById(id);

		ObjectMapper mapper = new ObjectMapper();
//		String values[] = new String[list.size()];
		List<String> addList = new ArrayList<String>();
		int i=0;
		for (Iterator<LectureScheduleDTO> iterator = list.iterator(); iterator.hasNext();) {
			LectureScheduleDTO dto = iterator.next();
//			values[i] = mapper.writeValueAsString(dto);
			i++;
			logger.debug("Going in addMetaLectureData.");
			boolean isFound = this.lectureService.addMetaLectureData(dto, filter, oay);
			if(isFound == false) {
				addList.add(mapper.writeValueAsString(dto));
			}
		}
		String values[] = new String[addList.size()];
		int index=0;
		for(String str: addList) {
			values[index] = str;
			index++;
		}
		lectureScheduleInput.setValues(values);
		
		logger.debug("Saving data in lecture table.");
		List<CmsLectureVo> lsList = this.lectureService.addLectureSchedule(lectureScheduleInput, filter, oay);
		
//		LectureReport lr = CommonUtil.createCopyProperties(filter, LectureReport.class);
//		lr.setAcademicYear(String.valueOf(oay.get().getId()));
//		
//		Department dt = this.lectureService.getDepartment(Long.valueOf(filter.getDepartmentId()));
//		Batch bt = this.lectureService.getBatch(Long.parseLong(filter.getBatchId()));
//		
//		lr.setDepartmentName(dt.getName());
//		lr.setYear(bt.getBatch().toString());
//		
//		logger.info("Lecture data created successfully.");
//		List<LectureReport> ls = new ArrayList<>();
//		ls.add(lr);
		
		return lsList; 
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cmslectures")
	public ResponseEntity<QueryResult> updateLectures(@Valid @RequestBody CmsLectureVo vo, @RequestParam Map<String, String> dataMap) throws Exception {
		QueryResult res = new QueryResult();
		
		Optional<Lecture> oLec = this.lectureRepository.findById(vo.getId());
		String strSectionId = dataMap.get("sectionId") != null ? dataMap.get("sectionId").trim() : "0";
		String strBatchId = dataMap.get("batchId") != null ? dataMap.get("batchId").trim() : "0";
		String strSubjectId = dataMap.get("subjectId") != null ? dataMap.get("subjectId").trim() : "0";
		String strTeacherId = dataMap.get("teacherId") != null ? dataMap.get("teacherId").trim() : "0";
		
		if(oLec.isPresent()) {
			// check if lecture with attendance master of given subject/teachr is present and their lecture is different with the current lecture
			Subject subject = this.commonService.getSubjectById(Long.parseLong(strSubjectId));
			Teacher teacher = this.commonService.getTeacherById(Long.parseLong(strTeacherId));
			Teach teach = new Teach();
			teach.setSubject(subject);
			teach.setTeacher(teacher);
			List<Teach> teachList = this.teachRepository.findAll(Example.of(teach));
			Batch batch = this.commonService.getBatchById(Long.parseLong(strBatchId));
			Section section = this.commonService.getSectionById(Long.parseLong(strSectionId));
			
			String confirm = dataMap.get("confirm") != null ? dataMap.get("confirm").trim() : null;
			
			if(confirm != null) {
				updateLecture(vo, res, oLec, subject, teacher, teach, teachList, batch, section);
			}else {
				List<Batch> batchList = new ArrayList<>();
				batchList.add(batch);
				List<Section> sectionList = new ArrayList<>();
				if(section != null) {
					sectionList.add(section);
				}
				
				List<AttendanceMaster> amList = this.commonService.getAttendanceMasterForCriteria(batchList, sectionList, teachList);
				List<Lecture> lectureList = this.commonService.getAllLecturesAlreadyScheduled(amList, vo);
				if(lectureList.size() > 0) {
					res.setStatusCode(1);
					res.setStatusDesc("Some of the lectures are already scheduled with the chosen update criteria. Please confirm if you want to update the lecture ");
				}else {
					updateLecture(vo, res, oLec, subject, teacher, teach, teachList, batch, section);
				}
			}
			
		}
		
		Optional<QueryResult> r = Optional.of(res);
		logger.info("Lecture data updated successfully.");
		return ResponseUtil.wrapOrNotFound(r);
	}

	private void updateLecture(CmsLectureVo vo, QueryResult res, Optional<Lecture> oLec, Subject subject,
			Teacher teacher, Teach teach, List<Teach> teachList, Batch batch, Section section) {
		if(teachList.size() == 0) {
			teach.setSubject(subject);
			teach.setTeacher(teacher);
			teach.setDesc(subject.getSubjectCode());
			teach = this.teachRepository.save(teach);
		}
		
		List<AttendanceMaster> amList = new ArrayList<>();
		AttendanceMaster am = new AttendanceMaster();
		am.setBatch(batch);
		if(section != null) {
			am.setSection(section);
		}
		if(teachList.size() == 0) {
			am.setTeach(teach);
			am = this.attendanceMasterRepository.save(am);
		}else {
			am.setTeach(teachList.get(0));
			amList = this.attendanceMasterRepository.findAll(Example.of(am));
		}
		
		Lecture lec = oLec.get();
		LocalDate lecDate = DateFormatUtil.convertStringToLocalDate(vo.getStrLecDate(), "MM/dd/yyyy");
		lec.setLecDate(lecDate);
		lec.setStartTime(vo.getStartTime());
		lec.setEndTime(vo.getEndTime());
		if(amList.size() > 0) {
			lec.setAttendancemaster(amList.get(0));
		}else {
			lec.setAttendancemaster(am);
		}
		
		this.lectureRepository.save(lec);
		
		res.setStatusCode(0);
		res.setStatusDesc("Lecture updated successfully");
	}
	
//	@RequestMapping(method = RequestMethod.PUT, value = "/cmslectures")
//	public ResponseEntity<QueryResult> updateLectures(@RequestBody List<LectureScheduleDTO> list, @RequestParam Map<String, String> dataMap) throws JSONException, ParseException, JsonProcessingException {
//		LectureScheduleInput lectureScheduleInput = new LectureScheduleInput();
//		LectureScheduleFilter filter = new LectureScheduleFilter();
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String values[] = new String[list.size()];
//		int i=0;
//		for (Iterator<LectureScheduleDTO> iterator = list.iterator(); iterator.hasNext();) {
//			LectureScheduleDTO dto = iterator.next();
//			values[i] = mapper.writeValueAsString(dto);
//			i++;
//		}
//		lectureScheduleInput.setValues(values);
//		filter.setBranchId(dataMap.get("branchId") != null ? dataMap.get("branchId").trim() : dataMap.get("branchId"));
//		filter.setDepartmentId(dataMap.get("departmentId") != null ? dataMap.get("departmentId").trim() : dataMap.get("departmentId"));
//		filter.setTermId(dataMap.get("termId") != null ? dataMap.get("termId").trim() : dataMap.get("termId") );
//		filter.setAcademicYear(dataMap.get("academicYear") != null ? dataMap.get("academicYear").trim() : dataMap.get("academicYear"));
//		filter.setSectionId(dataMap.get("sectionId") != null ? dataMap.get("sectionId").trim() : dataMap.get("sectionId"));
//		filter.setBatchId(dataMap.get("batchId") != null ? dataMap.get("batchId").trim() : dataMap.get("batchId") );
//		
//		QueryResult res = this.lectureService.updateLectureSchedule(lectureScheduleInput, filter);
//		Optional<QueryResult> r = Optional.of(res);
//		logger.info("Lecture data updated successfully.");
//		return ResponseUtil.wrapOrNotFound(r);
//	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/cmslectures")
    public List<CmsLectureVo> getAllLectures(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("REST request to get all the Lectures");
        String strAyId = dataMap.get("academicYearId");
        String strBrId = dataMap.get("branchId");
        String strDpId = dataMap.get("departmentId");
        String strTrId = dataMap.get("termId");
        String strBtId = dataMap.get("batchId");
		String strScId = dataMap.get("sectionId");
		String strSbId = dataMap.get("subjectId");
		String strThId = dataMap.get("teacherId");
		String strFromLecDate = dataMap.get("fromDate");
		String strToLecDate = dataMap.get("toDate");
        Long ayId = 0L;
		Long brId = 0L;
		Long dpId = 0L;
		Long trId = 0L;
		Long btId = 0L;
        Long scId = 0L;
        Long sbId = 0L;
        Long thId = 0L;
        LocalDate fromLecDate = null;
        LocalDate toLecDate = null;
		if(!CommonUtil.isNullOrEmpty(strAyId) && !"undefined".equalsIgnoreCase(strAyId) && !"null".equalsIgnoreCase(strAyId)) {
        	ayId = Long.parseLong(strAyId);
		}
        if(!CommonUtil.isNullOrEmpty(strBrId) && !"undefined".equalsIgnoreCase(strBrId) && !"null".equalsIgnoreCase(strBrId)) {
        	brId = Long.parseLong(strBrId);
		}
        if(!CommonUtil.isNullOrEmpty(strDpId) && !"undefined".equalsIgnoreCase(strDpId) && !"null".equalsIgnoreCase(strDpId)) {
        	dpId = Long.parseLong(strDpId);
		}
        if(!CommonUtil.isNullOrEmpty(strTrId) && !"undefined".equalsIgnoreCase(strTrId) && !"null".equalsIgnoreCase(strTrId)) {
        	trId = Long.parseLong(strTrId);
		}
        if(!CommonUtil.isNullOrEmpty(strBtId) && !"undefined".equalsIgnoreCase(strBtId) && !"null".equalsIgnoreCase(strBtId)) {
        	btId = Long.parseLong(strBtId);
		}
        if(!CommonUtil.isNullOrEmpty(strScId) && !"undefined".equalsIgnoreCase(strScId) && !"null".equalsIgnoreCase(strScId)) {
        	scId = Long.parseLong(strScId);
		}
        if(!CommonUtil.isNullOrEmpty(strSbId) && !"undefined".equalsIgnoreCase(strSbId) && !"null".equalsIgnoreCase(strSbId)) {
        	sbId = Long.parseLong(strSbId);
		}
        if(!CommonUtil.isNullOrEmpty(strThId) && !"undefined".equalsIgnoreCase(strThId) && !"null".equalsIgnoreCase(strThId)) {
        	thId = Long.parseLong(strThId);
		}
        if(!CommonUtil.isNullOrEmpty(strFromLecDate) && !"undefined".equalsIgnoreCase(strFromLecDate) && !"null".equalsIgnoreCase(strFromLecDate)) {
        	fromLecDate = DateFormatUtil.convertStringToLocalDate(strFromLecDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy);
		}
        if(!CommonUtil.isNullOrEmpty(strToLecDate) && !"undefined".equalsIgnoreCase(strToLecDate) && !"null".equalsIgnoreCase(strToLecDate)) {
        	toLecDate = DateFormatUtil.convertStringToLocalDate(strToLecDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy);
		}
        return this.lectureService.getAllLecturess(ayId, brId, dpId, trId, btId, scId, sbId, thId, fromLecDate, toLecDate);
    }
	
	
	@DeleteMapping("/cmslectures/{id}")
    public ResponseEntity<QueryResult> deleteLecture(@PathVariable Long id) {
        logger.debug("REST request to delete a Lecture : {}", id);
        QueryResult res = new QueryResult();
        
        Optional<Lecture> lc = this.lectureRepository.findById(id);
        StudentAttendance sa = new StudentAttendance();
        sa.setLecture(lc.get());
        if(!this.studentAttendanceRepository.exists(Example.of(sa))) {
        	this.lectureRepository.deleteById(id);
        	res.setStatusCode(0);
        	res.setStatusDesc("Lecture deleted successfully");
        }else {
        	res.setStatusCode(1);
        	res.setStatusDesc("Lecture cannot be deleted because it has the associated attendace data.");
        }
        
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("Lecture", id.toString())).build();
        Optional<QueryResult> r = Optional.of(res);
		return ResponseUtil.wrapOrNotFound(r);
    }
	
}
