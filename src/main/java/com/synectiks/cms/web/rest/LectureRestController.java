package com.synectiks.cms.web.rest;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.business.service.LectureReport;
import com.synectiks.cms.business.service.LectureService;
import com.synectiks.cms.constant.CmsConstants;
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
import com.synectiks.cms.service.util.DateFormatUtil;

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
        String strAyId = dataMap.get("academicYearId");
        String strBrId = dataMap.get("branchId");
        String strDpId = dataMap.get("departmentId");
        String strTrId = dataMap.get("termId");
        String strBtId = dataMap.get("batchId");
		String strScId = dataMap.get("sectionId");
		String strSbId = dataMap.get("subjectId");
		String strThId = dataMap.get("teacherId");
		String strLecDate = dataMap.get("lecDate");
        Long ayId = 0L;
		Long brId = 0L;
		Long dpId = 0L;
		Long trId = 0L;
		Long btId = 0L;
        Long scId = 0L;
        Long sbId = 0L;
        Long thId = 0L;
        LocalDate lecDate = null;
		if(!CommonUtil.isNullOrEmpty(strAyId) && !"undefined".equalsIgnoreCase(strAyId)) {
        	ayId = Long.parseLong(strAyId);
		}
        if(!CommonUtil.isNullOrEmpty(strBrId) && !"undefined".equalsIgnoreCase(strBrId)) {
        	brId = Long.parseLong(strBrId);
		}
        if(!CommonUtil.isNullOrEmpty(strDpId) && !"undefined".equalsIgnoreCase(strDpId)) {
        	dpId = Long.parseLong(strDpId);
		}
        if(!CommonUtil.isNullOrEmpty(strTrId) && !"undefined".equalsIgnoreCase(strTrId)) {
        	trId = Long.parseLong(strTrId);
		}
        if(!CommonUtil.isNullOrEmpty(strBtId) && !"undefined".equalsIgnoreCase(strBtId)) {
        	btId = Long.parseLong(strBtId);
		}
        if(!CommonUtil.isNullOrEmpty(strScId) && !"undefined".equalsIgnoreCase(strScId)) {
        	scId = Long.parseLong(strScId);
		}
        if(!CommonUtil.isNullOrEmpty(strSbId) && !"undefined".equalsIgnoreCase(strSbId)) {
        	sbId = Long.parseLong(strSbId);
		}
        if(!CommonUtil.isNullOrEmpty(strThId) && !"undefined".equalsIgnoreCase(strThId)) {
        	thId = Long.parseLong(strThId);
		}
        if(!CommonUtil.isNullOrEmpty(strLecDate) && !"undefined".equalsIgnoreCase(strLecDate)) {
        	lecDate = DateFormatUtil.convertStringToLocalDate(strLecDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy);
		}
        return this.lectureService.getAllLecturess(ayId, brId, dpId, trId, btId, scId, sbId, thId, lecDate);
    }
	
	

}
