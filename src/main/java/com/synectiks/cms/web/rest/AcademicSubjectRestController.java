package com.synectiks.cms.web.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.SubTypeEnum;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.TeachRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.service.dto.AcademicSubjectDTO;

/**
 * REST controller for managing Subject and Teach.
 */
@RestController
@RequestMapping("/api")
public class AcademicSubjectRestController {

	private final Logger log = LoggerFactory.getLogger(AcademicSubjectRestController.class);

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeachRepository teachRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/academicsubject/getallsubjects")
	List<Subject> getAllSubjects(@RequestParam Map<String, String> dataMap) {
		
		Subject subject = new Subject();
		subject.setStatus(Status.ACTIVE);
    	if(dataMap.containsKey("subCode")) {
    		String subjectCode = dataMap.get("subCode");
    		subject.setSubjectCode(subjectCode);
    	}
		if (dataMap.containsKey("deptId")) {
			String deptId = dataMap.get("deptId");
			Department dt = getDepartmentById(Long.parseLong(deptId));
			subject.setDepartment(dt);
		}
		if (dataMap.containsKey("batchId")) {
			String batchId = dataMap.get("batchId");
			Batch bt = getBatchById(Long.parseLong(batchId));
			subject.setBatch(bt);
		}
		Example<Subject> example = Example.of(subject);
		List<Subject> list = this.subjectRepository.findAll(example);
		return list;
	}
	
	private Department getDepartmentById(Long deptId) {
		Department dt = new Department();
		dt.setId(deptId);
		Example<Department> example = Example.of(dt);
		Optional<Department> newDt = this.departmentRepository.findOne(example);
		if (newDt.isPresent()) {
			return newDt.get();
		}
		return null;
	}

	private Batch getBatchById(Long batchId) {
		Batch bt = new Batch();
		bt.setId(batchId);
		Example<Batch> example = Example.of(bt);
		Optional<Batch> newbt = this.batchRepository.findOne(example);
		if (newbt.isPresent()) {
			return newbt.get();
		}
		return null;
	}

	private Teacher getTeacherById(Long teacherId) {
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		Example<Teacher> example = Example.of(teacher);
		Optional<Teacher> newTeacher = this.teacherRepository.findOne(example);
		if (newTeacher.isPresent()) {
			return newTeacher.get();
		}
		return null;
	}
	
	
	/**
	 * createSubjects method will create entry in subject and teach table.
	 * @param list
	 * 
	 * 
	 * [{"id":null,"subjectCode":"ww","subjectDesc":null,"departmentId":1101,"batchId":1151,"teacherId":1310},{"id":null,"subjectCode":"qq","subjectDesc":null,"departmentId":1101,"batchId":1151,"teacherId":1310},{"id":null,"subjectCode":"aa","subjectDesc":null,"departmentId":1101,"batchId":1151,"teacherId":1310}]
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/academicsubject/createsubjects")
	@Transactional(propagation=Propagation.REQUIRED)
	public void createSubjects(@RequestBody List<AcademicSubjectDTO> list) {
		for (Iterator<AcademicSubjectDTO> iterator = list.iterator(); iterator.hasNext();) {
			AcademicSubjectDTO dto = iterator.next();
			Subject sub = new Subject();
			sub.setSubjectCode(dto.getSubjectCode());
			if(dto.getSubjectType() == null) {
				sub.setSubjectType(SubTypeEnum.COMMON);
			}else {
				sub.setSubjectType(dto.getSubjectType());
			}
			
			if(dto.getSubjectDesc() != null) {
				sub.setSubjectDesc(dto.getSubjectDesc());
			}else {
				sub.setSubjectDesc(dto.getSubjectCode());
			}
			
			if(dto.getStatus() != null) {
				sub.setStatus(dto.getStatus());
			}else {
				sub.setStatus(Status.ACTIVE);
			}
			
			Department dt = getDepartmentById(dto.getDepartmentId());
			sub.setDepartment(dt);
			Batch bt =  getBatchById(dto.getBatchId());
			sub.setBatch(bt);
			
			Subject newSub = this.subjectRepository.save(sub);
			log.info("Subject data saved.");
			Teach teach = new Teach();
			teach.setSubject(newSub);
			Teacher teacher = getTeacherById(dto.getTeacherId());
			teach.setTeacher(teacher);
			teach.setDesc(dto.getSubjectCode());
			this.teachRepository.save(teach);
			log.info("Teach data saved.");
		}
		log.info("Subject and Teach data created successfully.");
		
	}

}
