package com.synectiks.cms.web.rest;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synectiks.cms.business.dto.LectureScheduleDTO;
import com.synectiks.cms.business.service.LectureService;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.filter.lecture.LectureScheduleInput;

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
	
	@RequestMapping(method = RequestMethod.POST, value = "/lecture/addlectures")
	public ResponseEntity<QueryResult> addLectures(@RequestBody List<LectureScheduleDTO> list, @RequestParam Map<String, String> dataMap) throws JSONException, ParseException, JsonProcessingException {
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
		
		filter.setTermId(dataMap.get("termId"));
		filter.setAcademicYear(dataMap.get("academicYear"));
		filter.setSectionId(dataMap.get("sectionId"));
		filter.setBatchId(dataMap.get("batchId"));
		
		QueryResult res = this.lectureService.addLectureSchedule(lectureScheduleInput, filter);
		Optional<QueryResult> r = Optional.of(res);
		logger.info("Lecture data created successfully.");
		return ResponseUtil.wrapOrNotFound(r);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/lecture/updatelectures")
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
		filter.setTermId(dataMap.get("termId"));
		filter.setAcademicYear(dataMap.get("academicYear"));
		filter.setSectionId(dataMap.get("sectionId"));
		filter.setBatchId(dataMap.get("batchId"));
		filter.setDepartmentId(dataMap.get("departmentId"));
		
		QueryResult res = this.lectureService.updateLectureSchedule(lectureScheduleInput, filter);
		Optional<QueryResult> r = Optional.of(res);
		logger.info("Lecture data updated successfully.");
		return ResponseUtil.wrapOrNotFound(r);
	}

	
	
	
	

}
