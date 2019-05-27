package com.synectiks.cms.web.rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synectiks.cms.business.service.LectureReport;
import com.synectiks.cms.business.service.LectureService;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.CmsLectureVo;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.filter.lecture.LectureScheduleInput;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.service.dto.LectureScheduleDTO;
import com.synectiks.cms.service.util.CommonUtil;

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
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmslectures")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<LectureReport> addLectures(@RequestBody List<LectureScheduleDTO> list, @RequestParam Map<String, String> dataMap) throws JSONException, ParseException, JsonProcessingException {
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
		String values[] = new String[list.size()];
		int i=0;
		for (Iterator<LectureScheduleDTO> iterator = list.iterator(); iterator.hasNext();) {
			LectureScheduleDTO dto = iterator.next();
			values[i] = mapper.writeValueAsString(dto);
			i++;
			logger.debug("Going in addMetaLectureData.");
			this.lectureService.addMetaLectureData(dto, filter, oay);
		}
		lectureScheduleInput.setValues(values);
		
		logger.debug("Saving data in lecture table.");
		QueryResult res = this.lectureService.addLectureSchedule(lectureScheduleInput, filter, oay);
		LectureReport lr = CommonUtil.createCopyProperties(filter, LectureReport.class);
		lr.setAcademicYear(String.valueOf(oay.get().getId()));
		
		Department dt = this.lectureService.getDepartment(Long.valueOf(filter.getDepartmentId()));
		Batch bt = this.lectureService.getBatch(Long.valueOf(filter.getBatchId()));
		lr.setDepartmentName(dt.getName());
		lr.setYear(bt.getBatch().toString());
		
		logger.info("Lecture data created successfully.");
		List<LectureReport> ls = new ArrayList<>();
		ls.add(lr);
		
		return ls; 
	}
	
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cmslectures")
	public ResponseEntity<QueryResult> updateLectures(@RequestBody List<LectureScheduleDTO> list, @RequestParam Map<String, String> dataMap) throws JSONException, ParseException, JsonProcessingException {
		LectureScheduleInput lectureScheduleInput = new LectureScheduleInput();
		LectureScheduleFilter filter = new LectureScheduleFilter();
		
		ObjectMapper mapper = new ObjectMapper();
		String values[] = new String[list.size()];
		int i=0;
		for (Iterator<LectureScheduleDTO> iterator = list.iterator(); iterator.hasNext();) {
			LectureScheduleDTO dto = iterator.next();
			values[i] = mapper.writeValueAsString(dto);
			i++;
		}
		lectureScheduleInput.setValues(values);
		filter.setBranchId(dataMap.get("branchId") != null ? dataMap.get("branchId").trim() : dataMap.get("branchId"));
		filter.setDepartmentId(dataMap.get("departmentId") != null ? dataMap.get("departmentId").trim() : dataMap.get("departmentId"));
		filter.setTermId(dataMap.get("termId") != null ? dataMap.get("termId").trim() : dataMap.get("termId") );
		filter.setAcademicYear(dataMap.get("academicYear") != null ? dataMap.get("academicYear").trim() : dataMap.get("academicYear"));
		filter.setSectionId(dataMap.get("sectionId") != null ? dataMap.get("sectionId").trim() : dataMap.get("sectionId"));
		filter.setBatchId(dataMap.get("batchId") != null ? dataMap.get("batchId").trim() : dataMap.get("batchId") );
		
		QueryResult res = this.lectureService.updateLectureSchedule(lectureScheduleInput, filter);
		Optional<QueryResult> r = Optional.of(res);
		logger.info("Lecture data updated successfully.");
		return ResponseUtil.wrapOrNotFound(r);
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/cmslectures")
    public List<CmsLectureVo> getAllLectures(@RequestParam Map<String, String> dataMap) throws ParseException {
        logger.debug("REST request to get all the Lectures");
        return this.lectureService.getAllLecturess(dataMap);
    }
	
	

}
