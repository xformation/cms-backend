package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.StudentFeeService;
import com.synectiks.cms.service.dto.StudentFeeDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing StudentFee.
 */
@RestController
@RequestMapping("/api")
public class StudentFeeResource {

    private final Logger log = LoggerFactory.getLogger(StudentFeeResource.class);

    private static final String ENTITY_NAME = "studentFee";

    private final StudentFeeService studentFeeService;

    public StudentFeeResource(StudentFeeService studentFeeService) {
        this.studentFeeService = studentFeeService;
    }

    /**
     * POST  /student-fees : Create a new studentFee.
     *
     * @param studentFeeDTO the studentFeeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studentFeeDTO, or with status 400 (Bad Request) if the studentFee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/student-fees")
    @Timed
    public ResponseEntity<StudentFeeDTO> createStudentFee(@Valid @RequestBody StudentFeeDTO studentFeeDTO) throws URISyntaxException {
        log.debug("REST request to save StudentFee : {}", studentFeeDTO);
        if (studentFeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new studentFee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentFeeDTO result = studentFeeService.save(studentFeeDTO);
        return ResponseEntity.created(new URI("/api/student-fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /student-fees : Updates an existing studentFee.
     *
     * @param studentFeeDTO the studentFeeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentFeeDTO,
     * or with status 400 (Bad Request) if the studentFeeDTO is not valid,
     * or with status 500 (Internal Server Error) if the studentFeeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/student-fees")
    @Timed
    public ResponseEntity<StudentFeeDTO> updateStudentFee(@Valid @RequestBody StudentFeeDTO studentFeeDTO) throws URISyntaxException {
        log.debug("REST request to update StudentFee : {}", studentFeeDTO);
        if (studentFeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentFeeDTO result = studentFeeService.save(studentFeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentFeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /student-fees : get all the studentFees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of studentFees in body
     */
    @GetMapping("/student-fees")
    @Timed
    public List<StudentFeeDTO> getAllStudentFees() {
        log.debug("REST request to get all StudentFees");
        return studentFeeService.findAll();
    }

    /**
     * GET  /student-fees/:id : get the "id" studentFee.
     *
     * @param id the id of the studentFeeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentFeeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/student-fees/{id}")
    @Timed
    public ResponseEntity<StudentFeeDTO> getStudentFee(@PathVariable Long id) {
        log.debug("REST request to get StudentFee : {}", id);
        Optional<StudentFeeDTO> studentFeeDTO = studentFeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentFeeDTO);
    }

    /**
     * DELETE  /student-fees/:id : delete the "id" studentFee.
     *
     * @param id the id of the studentFeeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/student-fees/{id}")
    @Timed
    public ResponseEntity<Void> deleteStudentFee(@PathVariable Long id) {
        log.debug("REST request to delete StudentFee : {}", id);
        studentFeeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/student-fees?query=:query : search for the studentFee corresponding
     * to the query.
     *
     * @param query the query of the studentFee search
     * @return the result of the search
     */
    @GetMapping("/_search/student-fees")
    @Timed
    public List<StudentFeeDTO> searchStudentFees(@RequestParam String query) {
        log.debug("REST request to search StudentFees for query {}", query);
        return studentFeeService.search(query);
    }

}
