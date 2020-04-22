package com.synectiks.cms.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import com.synectiks.cms.utils.ESEvent;
import com.synectiks.cms.utils.ESEvent.EventType;
import com.synectiks.cms.utils.IESEntity;
import com.synectiks.cms.utils.IUtils;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller to manage Student.
 */
@RestController
@RequestMapping("/api")
public class StudentRestController {

    private final Logger log = LoggerFactory.getLogger(StudentRestController.class);

    private static final String ENTITY_NAME = "student";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CommonService commonService;

    @PostMapping("/cmsstudents-bulk-load")
    public List<CmsStudentVo> bulkLoad(@RequestBody List<CmsStudentVo> list) throws Exception {
        List<CmsStudentVo> failedRecords = new ArrayList<>();
        for(CmsStudentVo cmsStudentVo: list) {
    		try {
    			createStudent(cmsStudentVo);
            }catch(Exception e) {
            	failedRecords.add(cmsStudentVo);
            	log.error("Exception. Saving of student failed. ", e);
            }
        }

    	return failedRecords;
    }

    /**
     * POST  /students : Create a new student.
     *
     * @param cmsStudentVo the studentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studentDTO, or with status 400 (Bad Request) if the student has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cmsstudents")
    public ResponseEntity<CmsStudentVo> createStudent(@Valid @RequestBody CmsStudentVo cmsStudentVo) throws Exception {
        log.debug("REST request to save a student : {}", cmsStudentVo);
        if (cmsStudentVo.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Student student = CommonUtil.createCopyProperties(cmsStudentVo, Student.class);
        Student result = studentRepository.save(student);
        CmsStudentVo vo = CommonUtil.createCopyProperties(student, CmsStudentVo.class);
        return ResponseEntity.created(new URI("/api/cmsstudents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }

    @PostMapping("/cms-grant-admission-to-student")
    public Long grantAdmissionToStudent(@RequestBody CmsStudentVo cmsStudentVo, @RequestParam Map<String, String> dataMap) throws Exception {
        log.debug("REST request to save a student : {}", cmsStudentVo);
        if (cmsStudentVo.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String admissionNo = dataMap.get("admissionNo") != null ? dataMap.get("admissionNo").trim() : null;
        Student student = CommonUtil.createCopyProperties(cmsStudentVo, Student.class);
        if(cmsStudentVo.getStrDateOfBirth() != null) {
        	student.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(cmsStudentVo.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        student.setAdmissionNo(admissionNo);
        student.setStatus(Status.ACTIVE);
        student.setCreatedOn(LocalDate.now());
        student.setBranchId(cmsStudentVo.getBranchId());

        student.setDepartmentId(cmsStudentVo.getDepartmentId());

        student.setBatchId(cmsStudentVo.getBatchId());

        student.setSectionId(cmsStudentVo.getSectionId());

        Student result = studentRepository.save(student);
        CmsStudentVo vo = CommonUtil.createCopyProperties(student, CmsStudentVo.class);
        return result.getId();
    }

    @PutMapping("/cms-grant-admission-to-student")
    public Long updateStudentToGrantAdmissionNo(@RequestBody CmsStudentVo cmsStudentVo, @RequestParam Map<String, String> dataMap) throws Exception {
        log.debug("REST request to update a student : {}", cmsStudentVo);
        if (cmsStudentVo.getId() == null) {
            throw new BadRequestAlertException("Invalid student record. Id is null", ENTITY_NAME, "idnull");
        }
        String admissionNo = dataMap.get("admissionNo") != null ? dataMap.get("admissionNo").trim() : null;
        Student student = CommonUtil.createCopyProperties(cmsStudentVo, Student.class);
        student.setAdmissionNo(admissionNo);
        student.setUpdatedOn(LocalDate.now());
        Student result = studentRepository.save(student);
        CmsStudentVo vo = CommonUtil.createCopyProperties(student, CmsStudentVo.class);
        return result.getId();
    }

    @GetMapping("/cms-students-for-admission")
    public List<CmsStudentVo> getStudentsToGrantAdmission(@RequestParam Map<String, String> dataMap) {
        log.debug("REST request to get all students with draft status to grant admission");
        if(CommonUtil.isNullOrEmpty(dataMap.get("branchId"))) {
        	log.error("Branch id not provided. Returning empty student list");
        	return Collections.emptyList();
        }
        Long branchId = Long.parseLong(dataMap.get("branchId"));
//      Long academicYearId = Long.parseLong(dataMap.get("academicYearId"));
//      AcademicYear ay = this.commonService.getAcademicYearById(academicYearId);

//        Branch branch = this.commonService.getBranchById(branchId);
//        if(branch == null) {
//        	log.error("Branch not found for the given branch id : "+branchId+". Returning empty student list");
//        	return Collections.emptyList();
//        }

        Student student = new Student();
//        student.setBranch(branch);
        student.setStatus(Status.DRAFT);

        Example example = Example.of(student);
        List<Student> list = studentRepository.findAll(example, Sort.by(Direction.DESC, "id"));
        List<CmsStudentVo> ls = new ArrayList<>();
        for(Student st: list) {
        	if(st.getAdmissionNo() == null) {
        		CmsStudentVo vo = CommonUtil.createCopyProperties(st, CmsStudentVo.class);
            	ls.add(vo);
        	}
        }
        return ls;
    }

    /**
     * PUT  /students : Updates an existing student.
     *
//     * @param studentDTO the studentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentDTO,
     * or with status 400 (Bad Request) if the studentDTO is not valid,
     * or with status 500 (Internal Server Error) if the studentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cmsstudents")
    public ResponseEntity<CmsStudentVo> updateStudent(@Valid @RequestBody CmsStudentVo cmsStudentVo) throws URISyntaxException {
        log.debug("REST request to update a student : {}", cmsStudentVo);
        if (cmsStudentVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Student student = CommonUtil.createCopyProperties(cmsStudentVo, Student.class);
        Student result = studentRepository.save(student);
        CmsStudentVo vo = CommonUtil.createCopyProperties(student, CmsStudentVo.class);
        return ResponseEntity.created(new URI("/api/cmsstudents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }

    /**
     * GET  /students : get all the students.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of students in body
     */
    @GetMapping("/cmsstudents")
    public List<CmsStudentVo> getAllStudents(@RequestParam Map<String, String> dataMap) {
        List<Student> list = null;
        List<CmsStudentVo> ls = null;

        Student obj = new Student();
        boolean isFilter = false;
    	if(!CommonUtil.isNullOrEmpty(dataMap.get("id"))) {
    		obj.setId(Long.parseLong(dataMap.get("id")));
    		isFilter = true;
    	}
    	if(!CommonUtil.isNullOrEmpty(dataMap.get("branchId"))) {
//    		Branch branch = this.commonService.getBranchById(Long.parseLong(dataMap.get("branchId")));
    		obj.setBranchId(Long.parseLong(dataMap.get("branchId")));
    		isFilter = true;
    	}
    	if(!CommonUtil.isNullOrEmpty(dataMap.get("departmentId"))) {
//    		Department department = this.commonService.getDepartmentById(Long.parseLong(dataMap.get("departmentId")));
    		obj.setDepartmentId(Long.parseLong(dataMap.get("departmentId")));
    		isFilter = true;
    	}
//    	if(dataMap.get("status") != null) {
//    		obj.setStatus(dataMap.get("status"));
//    		isFilter = true;
//    	}

        if(!CommonUtil.isNullOrEmpty(dataMap.get("studentName"))) {
        	isFilter = true;
        	String name = dataMap.get("studentName");
        	StringTokenizer token = new StringTokenizer(name, " ");
    		int cnt = 0;
    		while(token.hasMoreTokens()) {
    			if(cnt == 0) {
    				obj.setStudentName(token.nextToken());
    			}else if(cnt == 1) {
    				obj.setStudentMiddleName(token.nextToken());
    			}else if(cnt == 2) {
    				obj.setStudentLastName(token.nextToken());
    			}
    			cnt++;
    		}
//        	ls = getStudentListByName(name) ;
        }
//        List<Teacher> list = null;
    	if(isFilter) {
    		list = this.studentRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.studentRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
    	ls = new ArrayList<>();
        for(Student st: list) {
        	CmsStudentVo vo = CommonUtil.createCopyProperties(st, CmsStudentVo.class);
        	ls.add(vo);
        }
//        else {
//        	log.debug("REST request to get all Students");
//        	list = studentRepository.findAll();
//            ls = new ArrayList<>();
//            for(Student st: list) {
//            	CmsStudentVo vo = CommonUtil.createCopyProperties(st, CmsStudentVo.class);
//            	ls.add(vo);
//            }
//        }

        return ls;
    }

    /**
     * GET  /students/:id : get the "id" student.
     *
     * @param id the id of the studentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cmsstudents/{id}")
    public ResponseEntity<CmsStudentVo> getStudent(@PathVariable Long id) {
        log.debug("REST request to get Student : {}", id);
        Optional<Student> studentDTO = studentRepository.findById(id);
        CmsStudentVo vo = null;
        if(studentDTO.isPresent()) {
        	Student st = studentDTO.get();
        	vo = CommonUtil.createCopyProperties(st,  CmsStudentVo.class);
        }else {
        	vo = new  CmsStudentVo();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

    public List<CmsStudentVo> getStudentListByName(String name) {
    	Student student = null;
    	if(!CommonUtil.isNullOrEmpty(name)) {
    		student = new Student();
    		StringTokenizer token = new StringTokenizer(name, " ");
    		int cnt = 0;
    		while(token.hasMoreTokens()) {
    			if(cnt == 0) {
    				student.setStudentName(token.nextToken());
    			}else if(cnt == 1) {
    				student.setStudentMiddleName(token.nextToken());
    			}else if(cnt == 2) {
    				student.setStudentLastName(token.nextToken());
    			}
    			cnt++;
    		}
    	}
        log.debug("REST request to get Student by name : {}", name);
        List<Student> list = null;
        if(student != null) {
        	list = studentRepository.findAll(Example.of(student));
        }else {
        	list = Collections.emptyList();
        }

        List<CmsStudentVo> ls = new ArrayList<>();
        for(Student st: list) {
        	CmsStudentVo vo = CommonUtil.createCopyProperties(st, CmsStudentVo.class);
        	ls.add(vo);
        }
        return ls;
    }

    /**
     * DELETE  /students/:id : delete the "id" student.
     *
     * @param id the id of the studentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cmsstudents/{id}")
    public Integer deleteStudent(@PathVariable Long id) {
    	log.debug("REST request to delete a Student : {}", id);
        try {
        	Student st = new Student();
            st.setId(id);
            st.setStatus(Status.DEACTIVE);
            this.studentRepository.save(st);
        }catch(Exception e) {
    		return HttpStatus.FAILED_DEPENDENCY.value();
    	}
        return HttpStatus.OK.value();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/send-student-to-kafka")
    public void pushToKafka() {
    	log.info("Start pushing student entities to kafka"); 
    	List<Student> list = this.studentRepository.findAll();
    	for(Student student: list) {
    		Batch batch = this.commonService.getBatchById(student.getBatchId());
    		Section section = this.commonService.getSectionById(student.getSectionId());
    		student.setBatchName(batch.getBatch().toString());
    		student.setSectionName(section.getSection().toString());
    		fireEvent(EventType.CREATE, student);
    	}
    	log.info("All student entities successfully uploaded to kafka");
    }
    
    private void fireEvent(EventType type, Student entity) {
        	Environment env = CmsApp.getBean(Environment.class);
        	RestTemplate rest = CmsApp.getBean(RestTemplate.class);
        	ApplicationProperties applicationProperties = CmsApp.getBean(ApplicationProperties.class);
		log.info("Event type : "+type + ": " + IUtils.getStringFromValue(entity));
		if (!IUtils.isNull(entity) && entity instanceof IESEntity) {
			ESEvent event = ESEvent.builder(type, entity).build();
			log.info("Event msg string : " + IUtils.getStringFromValue(event));
			String res = null;
			try {
				res = IUtils.sendGetRestRequest(rest, IUtils.getValueByKey(
						env, IUtils.KEY_KAFKA_CONFIG, applicationProperties.getKafkaUrl()),
						IUtils.getRestParamMap(IUtils.PRM_TOPIC,
								IUtils.getValueByKey(env, IUtils.KEY_KAFKA_TOPIC, "cms"),
								IUtils.PRM_MSG,
								IUtils.getStringFromValue(event)), String.class);
			} catch(Exception ex) {
				log.error(ex.getMessage(), ex);
				res = null;
			}
			
		} else {
			log.error("Entity should not be null");
		}
	}
}
