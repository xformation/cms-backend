package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.StudentYearService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.StudentYearDTO;
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
 * REST controller for managing StudentYear.
 */
@RestController
@RequestMapping("/api")
public class StudentYearResource {

    private final Logger log = LoggerFactory.getLogger(StudentYearResource.class);

    private static final String ENTITY_NAME = "studentYear";

    private final StudentYearService studentYearService;

    public StudentYearResource(StudentYearService studentYearService) {
        this.studentYearService = studentYearService;
    }

    /**
     * POST  /student-years : Create a new studentYear.
     *
     * @param studentYearDTO the studentYearDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studentYearDTO, or with status 400 (Bad Request) if the studentYear has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/student-years")
    @Timed
    public ResponseEntity<StudentYearDTO> createStudentYear(@Valid @RequestBody StudentYearDTO studentYearDTO) throws URISyntaxException {
        log.debug("REST request to save StudentYear : {}", studentYearDTO);
        if (studentYearDTO.getId() != null) {
            throw new BadRequestAlertException("A new studentYear cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentYearDTO result = studentYearService.save(studentYearDTO);
        return ResponseEntity.created(new URI("/api/student-years/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /student-years : Updates an existing studentYear.
     *
     * @param studentYearDTO the studentYearDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentYearDTO,
     * or with status 400 (Bad Request) if the studentYearDTO is not valid,
     * or with status 500 (Internal Server Error) if the studentYearDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/student-years")
    @Timed
    public ResponseEntity<StudentYearDTO> updateStudentYear(@Valid @RequestBody StudentYearDTO studentYearDTO) throws URISyntaxException {
        log.debug("REST request to update StudentYear : {}", studentYearDTO);
        if (studentYearDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentYearDTO result = studentYearService.save(studentYearDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentYearDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /student-years : get all the studentYears.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of studentYears in body
     */
    @GetMapping("/student-years")
    @Timed
    public List<StudentYearDTO> getAllStudentYears() {
        log.debug("REST request to get all StudentYears");
        return studentYearService.findAll();
    }

    /**
     * GET  /student-years/:id : get the "id" studentYear.
     *
     * @param id the id of the studentYearDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentYearDTO, or with status 404 (Not Found)
     */
    @GetMapping("/student-years/{id}")
    @Timed
    public ResponseEntity<StudentYearDTO> getStudentYear(@PathVariable Long id) {
        log.debug("REST request to get StudentYear : {}", id);
        Optional<StudentYearDTO> studentYearDTO = studentYearService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentYearDTO);
    }

    /**
     * DELETE  /student-years/:id : delete the "id" studentYear.
     *
     * @param id the id of the studentYearDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/student-years/{id}")
    @Timed
    public ResponseEntity<Void> deleteStudentYear(@PathVariable Long id) {
        log.debug("REST request to delete StudentYear : {}", id);
        studentYearService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/student-years?query=:query : search for the studentYear corresponding
     * to the query.
     *
     * @param query the query of the studentYear search
     * @return the result of the search
     */
    @GetMapping("/_search/student-years")
    @Timed
    public List<StudentYearDTO> searchStudentYears(@RequestParam String query) {
        log.debug("REST request to search StudentYears for query {}", query);
        return studentYearService.search(query);
    }

}
