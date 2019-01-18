package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.StudentSubjectService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.StudentSubjectDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing StudentSubject.
 */
@RestController
@RequestMapping("/api")
public class StudentSubjectResource {

    private final Logger log = LoggerFactory.getLogger(StudentSubjectResource.class);

    private static final String ENTITY_NAME = "studentSubject";

    private final StudentSubjectService studentSubjectService;

    public StudentSubjectResource(StudentSubjectService studentSubjectService) {
        this.studentSubjectService = studentSubjectService;
    }

    /**
     * POST  /student-subjects : Create a new studentSubject.
     *
     * @param studentSubjectDTO the studentSubjectDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studentSubjectDTO, or with status 400 (Bad Request) if the studentSubject has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/student-subjects")
    @Timed
    public ResponseEntity<StudentSubjectDTO> createStudentSubject(@Valid @RequestBody StudentSubjectDTO studentSubjectDTO) throws URISyntaxException {
        log.debug("REST request to save StudentSubject : {}", studentSubjectDTO);
        if (studentSubjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new studentSubject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentSubjectDTO result = studentSubjectService.save(studentSubjectDTO);
        return ResponseEntity.created(new URI("/api/student-subjects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /student-subjects : Updates an existing studentSubject.
     *
     * @param studentSubjectDTO the studentSubjectDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentSubjectDTO,
     * or with status 400 (Bad Request) if the studentSubjectDTO is not valid,
     * or with status 500 (Internal Server Error) if the studentSubjectDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/student-subjects")
    @Timed
    public ResponseEntity<StudentSubjectDTO> updateStudentSubject(@Valid @RequestBody StudentSubjectDTO studentSubjectDTO) throws URISyntaxException {
        log.debug("REST request to update StudentSubject : {}", studentSubjectDTO);
        if (studentSubjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentSubjectDTO result = studentSubjectService.save(studentSubjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentSubjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /student-subjects : get all the studentSubjects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of studentSubjects in body
     */
    @GetMapping("/student-subjects")
    @Timed
    public List<StudentSubjectDTO> getAllStudentSubjects() {
        log.debug("REST request to get all StudentSubjects");
        return studentSubjectService.findAll();
    }

    /**
     * GET  /student-subjects/:id : get the "id" studentSubject.
     *
     * @param id the id of the studentSubjectDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentSubjectDTO, or with status 404 (Not Found)
     */
    @GetMapping("/student-subjects/{id}")
    @Timed
    public ResponseEntity<StudentSubjectDTO> getStudentSubject(@PathVariable Long id) {
        log.debug("REST request to get StudentSubject : {}", id);
        Optional<StudentSubjectDTO> studentSubjectDTO = studentSubjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentSubjectDTO);
    }

    /**
     * DELETE  /student-subjects/:id : delete the "id" studentSubject.
     *
     * @param id the id of the studentSubjectDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/student-subjects/{id}")
    @Timed
    public ResponseEntity<Void> deleteStudentSubject(@PathVariable Long id) {
        log.debug("REST request to delete StudentSubject : {}", id);
        studentSubjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/student-subjects?query=:query : search for the studentSubject corresponding
     * to the query.
     *
     * @param query the query of the studentSubject search
     * @return the result of the search
     */
    @GetMapping("/_search/student-subjects")
    @Timed
    public List<StudentSubjectDTO> searchStudentSubjects(@RequestParam String query) {
        log.debug("REST request to search StudentSubjects for query {}", query);
        return studentSubjectService.search(query);
    }

}
