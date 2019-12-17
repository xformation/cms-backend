package com.synectiks.cms.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;
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
        student.setAdmissionNo(admissionNo);
        student.setStatus(Status.DRAFT);
        student.setCreatedOn(LocalDate.now());
        Student result = studentRepository.save(student);
        CmsStudentVo vo = CommonUtil.createCopyProperties(student, CmsStudentVo.class);
        return result.getId();
    }
    
    /**
     * PUT  /students : Updates an existing student.
     *
     * @param studentDTO the studentDTO to update
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
    public List<CmsStudentVo> getAllStudents() {
        log.debug("REST request to get all Students");
        List<Student> list = studentRepository.findAll();
        List<CmsStudentVo> ls = new ArrayList<>();
        for(Student st: list) {
        	CmsStudentVo vo = CommonUtil.createCopyProperties(st, CmsStudentVo.class);
        	ls.add(vo);
        }
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


}
