package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.commons.entities.cms.Student;
import com.synectiks.commons.entities.cms.StudentFacilityLink;
import com.synectiks.cms.repository.StudentFacilityLinkRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing StudentFacilityLink.
 */
@RestController
@RequestMapping("/api")
public class StudentFacilityLinkRestController {

    private final Logger log = LoggerFactory.getLogger(StudentFacilityLinkRestController.class);

    private static final String ENTITY_NAME = "studentFacilityLink";

    @Autowired
    private StudentFacilityLinkRepository studentFacilityLinkRepository;

    @Autowired
    private StudentRepository studentRepository;
    /**
     * POST  /student-facility-links : Create a new studentFacilityLink.
     *
     * @param StudentFacilityLink the StudentFacilityLink to create
     * @return the ResponseEntity with status 201 (Created) and with body the new StudentFacilityLink, or with status 400 (Bad Request) if the studentFacilityLink has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cmsstudent-facility-links")
    @Timed
    public ResponseEntity<StudentFacilityLink> createStudentFacilityLink(@RequestBody StudentFacilityLink StudentFacilityLink) throws URISyntaxException {
        log.debug("REST request to save StudentFacilityLink : {}", StudentFacilityLink);
        if (StudentFacilityLink.getId() != null) {
            throw new BadRequestAlertException("A new studentFacilityLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentFacilityLink result = studentFacilityLinkRepository.save(StudentFacilityLink);
        return ResponseEntity.created(new URI("/api/student-facility-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /student-facility-links : Updates an existing studentFacilityLink.
     *
     * @param StudentFacilityLink the StudentFacilityLink to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated StudentFacilityLink,
     * or with status 400 (Bad Request) if the StudentFacilityLink is not valid,
     * or with status 500 (Internal Server Error) if the StudentFacilityLink couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cmsstudent-facility-links")
    @Timed
    public ResponseEntity<StudentFacilityLink> updateStudentFacilityLink(@RequestBody StudentFacilityLink StudentFacilityLink) throws URISyntaxException {
        log.debug("REST request to update StudentFacilityLink : {}", StudentFacilityLink);
        if (StudentFacilityLink.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentFacilityLink result = studentFacilityLinkRepository.save(StudentFacilityLink);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, StudentFacilityLink.getId().toString()))
            .body(result);
    }

    /**
     * GET  /student-facility-links : get all the studentFacilityLinks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of studentFacilityLinks in body
     */
    @GetMapping("/cmsstudent-facility-links")
    @Timed
    public List<StudentFacilityLink> getAllStudentFacilityLinks() {
        log.debug("REST request to get all StudentFacilityLinks");
        return studentFacilityLinkRepository.findAll();
    }

    /**
     * GET  /student-facility-links/:id : get the "id" studentFacilityLink.
     *
     * @param id the id of the StudentFacilityLink to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the StudentFacilityLink, or with status 404 (Not Found)
     */
    @GetMapping("/cmsstudent-facility-links/{id}")
    @Timed
    public ResponseEntity<StudentFacilityLink> getStudentFacilityLink(@PathVariable Long id) {
        log.debug("REST request to get StudentFacilityLink : {}", id);
        Optional<StudentFacilityLink> StudentFacilityLink = studentFacilityLinkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(StudentFacilityLink);
    }

    /**
     * DELETE  /student-facility-links/:id : delete the "id" studentFacilityLink.
     *
     * @param id the id of the StudentFacilityLink to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cmsstudent-facility-links/{id}")
    @Timed
    public ResponseEntity<Void> deleteStudentFacilityLink(@PathVariable Long id) {
        log.debug("REST request to delete StudentFacilityLink : {}", id);
        studentFacilityLinkRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
