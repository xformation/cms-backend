package com.synectiks.cms.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.commons.entities.cms.Lecture;
import com.synectiks.commons.entities.cms.QueryResult;
import com.synectiks.commons.entities.cms.Student;
import com.synectiks.commons.entities.cms.StudentAttendance;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterImpl;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceUpdateFilter;
import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.StudentAttendanceRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.dto.StudentAttendanceDTO;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing StudentAttendance.
 */
@RestController
@RequestMapping("/api")
public class StudentAttendanceRestController {

    private final Logger logger = LoggerFactory.getLogger(StudentAttendanceRestController.class);

    private static final String ENTITY_NAME = "studentAttendance";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;
    
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private StudentAttendanceFilterImpl studentAttendanceFilterImpl;
    
    @RequestMapping(method = RequestMethod.POST, value = "/cmsstudent-attendances")
    public ResponseEntity<StudentAttendance> createStudentAttendance(@Valid @RequestBody StudentAttendanceDTO studentAttendanceDTO) throws URISyntaxException {
        logger.debug("REST request to save StudentAttendance : {}", studentAttendanceDTO);
        if (studentAttendanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new studentAttendance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentAttendance sa = CommonUtil.createCopyProperties(studentAttendanceDTO, StudentAttendance.class);
        Student student = this.studentRepository.findById(studentAttendanceDTO.getStudentId()).get();
        Lecture lecture = this.lectureRepository.findById(studentAttendanceDTO.getLectureId()).get();
        sa.setStudent(student);
        sa.setLecture(lecture);
        sa = this.studentAttendanceRepository.save(sa);
        return ResponseEntity.created(new URI("/api/student-attendances/" + sa.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, sa.getId().toString()))
            .body(sa);
    }

    /**
     * PUT  /student-attendances : Updates an existing studentAttendance.
     *
     * @param studentAttendanceDTO the studentAttendanceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentAttendanceDTO,
     * or with status 400 (Bad Request) if the studentAttendanceDTO is not valid,
     * or with status 500 (Internal Server Error) if the studentAttendanceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
   
    @RequestMapping(method = RequestMethod.PUT, value = "/cmsstudent-attendances-updatebyid")
    public ResponseEntity<StudentAttendance> updateStudentAttendance(@Valid @RequestBody StudentAttendanceDTO studentAttendanceDTO) throws URISyntaxException {
        logger.debug("REST request to update StudentAttendance : {}", studentAttendanceDTO);
        if (studentAttendanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentAttendance sa = CommonUtil.createCopyProperties(studentAttendanceDTO, StudentAttendance.class);
        Student student = this.studentRepository.findById(studentAttendanceDTO.getStudentId()).get();
        Lecture lecture = this.lectureRepository.findById(studentAttendanceDTO.getLectureId()).get();
        sa.setStudent(student);
        sa.setLecture(lecture);
        sa = this.studentAttendanceRepository.save(sa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentAttendanceDTO.getId().toString()))
            .body(sa);
    }

    /**
     * GET  /student-attendances : get all the studentAttendances.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of studentAttendances in body
     */
    @RequestMapping(method = RequestMethod.GET, value = "/cmsstudent-attendances")
    public List<StudentAttendance> getAllStudentAttendances() {
        logger.debug("REST request to get all StudentAttendances");
        return this.studentAttendanceRepository.findAll();
    }

    /**
     * GET  /student-attendances/:id : get the "id" studentAttendance.
     *
     * @param id the id of the studentAttendanceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentAttendanceDTO, or with status 404 (Not Found)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/cmsstudent-attendances/{id}")
    public ResponseEntity<StudentAttendance> getStudentAttendance(@PathVariable Long id) {
        logger.debug("REST request to get StudentAttendance : {}", id);
        Optional<StudentAttendance> sa = this.studentAttendanceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sa);
    }

    /**
     * DELETE  /student-attendances/:id : delete the "id" studentAttendance.
     *
     * @param id the id of the studentAttendanceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsstudent-attendances/{id}")
    public ResponseEntity<Void> deleteStudentAttendance(@PathVariable Long id) {
        logger.debug("REST request to delete StudentAttendance : {}", id);
        this.studentAttendanceRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

 
    @RequestMapping(method = RequestMethod.PUT, value = "/cmsstudent-attendances")
    public ResponseEntity<QueryResult> updateStudentAttendance(@RequestBody List<StudentAttendanceUpdateFilter> list) throws URISyntaxException {
    	QueryResult result = this.studentAttendanceFilterImpl.updateStudentStatus(list);
    	Optional<QueryResult> r = Optional.of(result);
    	if(result.getStatusCode() == 0) {
    		logger.info("Student attendance data updated successfully.");
    	}else {
    		logger.info("Due to some error student attendance data could not be updated successfully.");
    	}
		return ResponseUtil.wrapOrNotFound(r);
    }

}
