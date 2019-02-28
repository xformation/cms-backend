package com.synectiks.cms.web.rest;

import java.util.List;
import java.util.Map;

import com.synectiks.cms.domain.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.AcademicSubjectService;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.service.dto.AcademicSubjectDTO;

/**
 * REST controller for managing Subject and Teach.
 */
@RestController
@RequestMapping("/api")
public class AcademicSubjectRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AcademicSubjectService academicSubjectService; 
	
	@RequestMapping(method = RequestMethod.GET, value = "/academicsubject/getallsubjects")
	public List<Subject> getAllSubjects(@RequestParam Map<String, String> dataMap) {
		List<Subject> list = this.academicSubjectService.getAllSubjects(dataMap);
		logger.info("Returning list of subjects.");
		return list;
	}

    @RequestMapping(method = RequestMethod.GET, value = "/academicsubject/getallteachers")
    public List<Teacher> getAllTeachers(@RequestParam Map<String, String> dataMap) {
        List<Teacher> list = this.academicSubjectService.getAllTeachers(dataMap);
        logger.info("Returning list of teachers.");
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
	@RequestMapping(method = RequestMethod.POST, value = "/academicsubject/createsubjects")
	public void createSubjects(@RequestBody List<AcademicSubjectDTO> list) {
		this.academicSubjectService.createSubjects(list);
		logger.info("Subject and Teach data created successfully.");
	}

}
