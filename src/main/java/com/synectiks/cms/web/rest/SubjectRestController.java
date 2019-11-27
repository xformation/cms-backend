package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.AcademicSubjectService;
import com.synectiks.cms.domain.CmsSubjectVo;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Subject and Teach.
 */
@RestController
@RequestMapping("/api")
public class SubjectRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String ENTITY_NAME = "subject";
	
	@Autowired
	private AcademicSubjectService academicSubjectService; 
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmssubjects")
	public List<CmsSubjectVo> getAllSubjects(@RequestParam Map<String, String> dataMap) {
		List<CmsSubjectVo> list = this.academicSubjectService.getAllSubjectAndTeachData(dataMap);
		logger.info("Returning list of subjects.");
		return list;
	}
	
	
	
	/**
	 * createSubjects method will create an entry in subject and teach table.
	 * @param list
	 * 
	 * [
	 *	{"id":null,"subjectCode":"ww","subjectDesc":null,"departmentId":1101,"batchId":1151,"teacherId":1310},
	 *	{"id":null,"subjectCode":"qq","subjectDesc":null,"departmentId":1101,"batchId":1151,"teacherId":1310},
	 *	{"id":null,"subjectCode":"aa","subjectDesc":null,"departmentId":1101,"batchId":1151,"teacherId":1310}
	 * ]
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/cmscreatesubjects")
	public void createSubjects(@RequestBody List<CmsSubjectVo> list) {
		this.academicSubjectService.createSubjects(list);
		logger.info("Subject and Teach data created successfully.");
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/cmssubjects")
    public ResponseEntity<CmsSubjectVo> createSubject(@RequestBody CmsSubjectVo cmsSubjectvo, @RequestParam Map<String, String> dataMap) throws URISyntaxException {
        logger.debug("REST request to save a subject : {}", cmsSubjectvo);
        if (cmsSubjectvo.getId() != null) {
            throw new BadRequestAlertException("A new subject cannot have an ID which already exists", ENTITY_NAME, "idexists");
        }
        cmsSubjectvo = this.academicSubjectService.createSubject(cmsSubjectvo, dataMap);
        return ResponseEntity.created(new URI("/api/cmssubjects/" + cmsSubjectvo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsSubjectvo.getId().toString()))
            .body(cmsSubjectvo);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cmssubjects")
    public ResponseEntity<CmsSubjectVo> updateSubject(@RequestBody CmsSubjectVo cmsSubjectvo, @RequestParam Map<String, String> dataMap) throws URISyntaxException {
        logger.debug("REST request to save a subject : {}", cmsSubjectvo);
        if (cmsSubjectvo.getId() == null) {
            throw new BadRequestAlertException("Invalid subject id", ENTITY_NAME, "idexists");
        }
        cmsSubjectvo = this.academicSubjectService.updateSubject(cmsSubjectvo, dataMap);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsSubjectvo.getId().toString()))
                .body(cmsSubjectvo);
    }
	
	
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmssubjects/{id}")
    public Integer deleteSubject(@PathVariable Long id) {
    	try {
    		logger.debug("REST request to delete a subject. Deactivating the subject : {}", id);
    		Subject sub = new Subject();
    		sub.setStatus(Status.DEACTIVE);
    		sub.setId(id);
        	this.subjectRepository.save(sub);
    	}catch(Exception e) {
    		return HttpStatus.FAILED_DEPENDENCY.value();
    	}
    	return HttpStatus.OK.value();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssubjects-bydepartment-batchid")
    public List<Subject> getSubjectsByDepartmentBatchId(@RequestParam Map<String, String> dataMap) {
    	Long departmentId =0L;
    	Long batchId = 0L;
    	if(dataMap.containsKey("departmentId")) {
    		departmentId = Long.valueOf(dataMap.get("departmentId"));
    	}else {
    		logger.warn("Department id not provided. Returning empty list.");
    		return Collections.emptyList();
    	}
    	if(dataMap.containsKey("batchId")) {
    		batchId = Long.valueOf(dataMap.get("batchId"));
    	}else {
    		logger.warn("Batch id not provided. Returning empty list.");
    		return Collections.emptyList();
    	}
    	logger.debug(String.format("Retrieving subject based on department id: %d and batch id: %d", departmentId, batchId));
    	List<Subject> list = this.academicSubjectService.getSubjectList(departmentId, batchId);
    	logger.debug(String.format("Totale subjects retrieved %d", list.size()));
    	return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssubjects-bydepartmentid")
    public List<Subject> getSubjectsByDepartmentId(@RequestParam Map<String, String> dataMap) {
    	Long departmentId =0L;
    	if(dataMap.containsKey("departmentId")) {
    		departmentId = Long.parseLong(dataMap.get("departmentId"));
    	}else {
    		logger.warn("Department id not provided. Returning empty subject list.");
    		return Collections.emptyList();
    	}
    	logger.debug(String.format("Retrieving subject based on department id: %d ", departmentId));
    	List<Subject> list = this.academicSubjectService.getSubjectList(departmentId);
    	logger.debug(String.format("Totale subjects retrieved %d", list.size()));
    	return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssubjects-teacher-bydepartmentid")
    public List<Teach> getSubjectsWithTeacher(@RequestParam Map<String, String> dataMap) {
    	Long departmentId =0L;
    	if(dataMap.containsKey("departmentId")) {
    		departmentId = Long.parseLong(dataMap.get("departmentId"));
    	}else {
    		logger.warn("Department id not provided. Returning empty subject-teaher list.");
    		return Collections.emptyList();
    	}
    	List<Teach> list = this.academicSubjectService.getAllSubjectsWithTeacher(departmentId);
    	logger.debug(String.format("Totale subjects-teacher records %d", list.size()));
    	return list;
    }
}
