package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
import com.synectiks.cms.service.StudentFacilityLinkService;
import com.synectiks.cms.service.dto.StudentFacilityLinkDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing StudentFacilityLink.
 */
@RestController
@RequestMapping("/api")
public class StudentFacilityLinkResource {

    private final Logger log = LoggerFactory.getLogger(StudentFacilityLinkResource.class);

    private static final String ENTITY_NAME = "studentFacilityLink";

    private final StudentFacilityLinkService studentFacilityLinkService;

    public StudentFacilityLinkResource(StudentFacilityLinkService studentFacilityLinkService) {
        this.studentFacilityLinkService = studentFacilityLinkService;
    }

    /**
     * POST  /student-facility-links : Create a new studentFacilityLink.
     *
     * @param studentFacilityLinkDTO the studentFacilityLinkDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studentFacilityLinkDTO, or with status 400 (Bad Request) if the studentFacilityLink has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/student-facility-links")
    @Timed
    public ResponseEntity<StudentFacilityLinkDTO> createStudentFacilityLink(@RequestBody StudentFacilityLinkDTO studentFacilityLinkDTO) throws URISyntaxException {
        log.debug("REST request to save StudentFacilityLink : {}", studentFacilityLinkDTO);
        if (studentFacilityLinkDTO.getId() != null) {
            throw new BadRequestAlertException("A new studentFacilityLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentFacilityLinkDTO result = studentFacilityLinkService.save(studentFacilityLinkDTO);
        return ResponseEntity.created(new URI("/api/student-facility-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /student-facility-links : Updates an existing studentFacilityLink.
     *
     * @param studentFacilityLinkDTO the studentFacilityLinkDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentFacilityLinkDTO,
     * or with status 400 (Bad Request) if the studentFacilityLinkDTO is not valid,
     * or with status 500 (Internal Server Error) if the studentFacilityLinkDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/student-facility-links")
    @Timed
    public ResponseEntity<StudentFacilityLinkDTO> updateStudentFacilityLink(@RequestBody StudentFacilityLinkDTO studentFacilityLinkDTO) throws URISyntaxException {
        log.debug("REST request to update StudentFacilityLink : {}", studentFacilityLinkDTO);
        if (studentFacilityLinkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentFacilityLinkDTO result = studentFacilityLinkService.save(studentFacilityLinkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentFacilityLinkDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /student-facility-links : get all the studentFacilityLinks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of studentFacilityLinks in body
     */
    @GetMapping("/student-facility-links")
    @Timed
    public List<StudentFacilityLinkDTO> getAllStudentFacilityLinks() {
        log.debug("REST request to get all StudentFacilityLinks");
        return studentFacilityLinkService.findAll();
    }

    /**
     * GET  /student-facility-links/:id : get the "id" studentFacilityLink.
     *
     * @param id the id of the studentFacilityLinkDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentFacilityLinkDTO, or with status 404 (Not Found)
     */
    @GetMapping("/student-facility-links/{id}")
    @Timed
    public ResponseEntity<StudentFacilityLinkDTO> getStudentFacilityLink(@PathVariable Long id) {
        log.debug("REST request to get StudentFacilityLink : {}", id);
        Optional<StudentFacilityLinkDTO> studentFacilityLinkDTO = studentFacilityLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentFacilityLinkDTO);
    }

    /**
     * DELETE  /student-facility-links/:id : delete the "id" studentFacilityLink.
     *
     * @param id the id of the studentFacilityLinkDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/student-facility-links/{id}")
    @Timed
    public ResponseEntity<Void> deleteStudentFacilityLink(@PathVariable Long id) {
        log.debug("REST request to delete StudentFacilityLink : {}", id);
        studentFacilityLinkService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/student-facility-links?query=:query : search for the studentFacilityLink corresponding
     * to the query.
     *
     * @param query the query of the studentFacilityLink search
     * @return the result of the search
     */
    @GetMapping("/_search/student-facility-links")
    @Timed
    public List<StudentFacilityLinkDTO> searchStudentFacilityLinks(@RequestParam String query) {
        log.debug("REST request to search StudentFacilityLinks for query {}", query);
        return studentFacilityLinkService.search(query);
    }

}
