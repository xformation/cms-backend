package com.synectiks.cms.web.rest;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.domain.Employee;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.repository.EmployeeRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.web.rest.util.HeaderUtil;

/**
 * REST controller for managing user export.
 */
@RestController
@RequestMapping("/api")
public class UserExportRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//	
//	@Autowired
//    private AcademicYearRepository academicYearRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmsuserexport")
	public ResponseEntity<Integer> exportUser(@RequestParam Map<String, String> dataMap) throws JSONException, ParseException, JsonProcessingException {
		boolean isTecher = dataMap.get("chkTeacher") != null ? Boolean.parseBoolean(dataMap.get("chkTeacher")) : false;
		boolean isStudent = dataMap.get("chkStudent") != null ? Boolean.parseBoolean(dataMap.get("chkStudent")) : false;
		boolean isEmployee = dataMap.get("chkEmployee") != null ? Boolean.parseBoolean(dataMap.get("chkEmployee")) : false;
		logger.debug("Teacher selected : "+isTecher+", Student selected : "+isStudent+", Employee selected : "+isEmployee);
		
		Set<String> email = new HashSet<>();
		 
		if(isTecher) {
			List<Teacher> teacherList = this.teacherRepository.findAll();
			for(Teacher t: teacherList) {
				email.add(t.getTeacherEmailAddress());
			}
		}
		if(isStudent) {
			List<Student> studentList = this.studentRepository.findAll();
			for(Student s: studentList) {
				email.add(s.getStudentEmailAddress());
			}
		}
		if(isEmployee) {
			List<Employee> employeeList = this.employeeRepository.findAll();
			for(Employee e: employeeList) {
				email.add(e.getOfficialMailId());
			}
		}
		List<String> finalList = email.stream().collect(Collectors.toList());
		if(finalList != null && finalList.size() > 0) {
			int statusCode = exportUserToSecuryService(finalList);
			return ResponseEntity.status(statusCode)
		            .headers(HeaderUtil.createEntityUpdateAlert("", ""))
		            .body(new Integer(statusCode));
		}
		return ResponseEntity.status(200)
	            .headers(HeaderUtil.createEntityUpdateAlert("", ""))
	            .body(new Integer(200));
	}
	
	private int exportUserToSecuryService(List<String> list) throws JsonProcessingException {
		
		String status = restTemplate.postForObject(applicationProperties.getSecSrvUrl()+"/security/public/importUser", 
				list, String.class, list);
		
		logger.info("Export users response status : "+status);
		return status.equalsIgnoreCase("SUCCESS") ? 200 : 500;
	}
	
	

}
