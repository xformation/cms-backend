package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.SemesterService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.SemesterDTO;
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
 * REST controller for managing Semester.
 */
@RestController
@RequestMapping("/api")
public class SemesterResource {

    private final Logger log = LoggerFactory.getLogger(SemesterResource.class);

    private static final String ENTITY_NAME = "semester";

    private final SemesterService semesterService;

    public SemesterResource(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    /**
     * POST  /semesters : Create a new semester.
     *
     * @param semesterDTO the semesterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new semesterDTO, or with status 400 (Bad Request) if the semester has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/semesters")
    @Timed
    public ResponseEntity<SemesterDTO> createSemester(@Valid @RequestBody SemesterDTO semesterDTO) throws URISyntaxException {
        log.debug("REST request to save Semester : {}", semesterDTO);
        if (semesterDTO.getId() != null) {
            throw new BadRequestAlertException("A new semester cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SemesterDTO result = semesterService.save(semesterDTO);
        return ResponseEntity.created(new URI("/api/semesters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /semesters : Updates an existing semester.
     *
     * @param semesterDTO the semesterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated semesterDTO,
     * or with status 400 (Bad Request) if the semesterDTO is not valid,
     * or with status 500 (Internal Server Error) if the semesterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/semesters")
    @Timed
    public ResponseEntity<SemesterDTO> updateSemester(@Valid @RequestBody SemesterDTO semesterDTO) throws URISyntaxException {
        log.debug("REST request to update Semester : {}", semesterDTO);
        if (semesterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SemesterDTO result = semesterService.save(semesterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, semesterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /semesters : get all the semesters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of semesters in body
     */
    @GetMapping("/semesters")
    @Timed
    public List<SemesterDTO> getAllSemesters() {
        log.debug("REST request to get all Semesters");
        return semesterService.findAll();
    }

    /**
     * GET  /semesters/:id : get the "id" semester.
     *
     * @param id the id of the semesterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the semesterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/semesters/{id}")
    @Timed
    public ResponseEntity<SemesterDTO> getSemester(@PathVariable Long id) {
        log.debug("REST request to get Semester : {}", id);
        Optional<SemesterDTO> semesterDTO = semesterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(semesterDTO);
    }

    /**
     * DELETE  /semesters/:id : delete the "id" semester.
     *
     * @param id the id of the semesterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/semesters/{id}")
    @Timed
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {
        log.debug("REST request to delete Semester : {}", id);
        semesterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/semesters?query=:query : search for the semester corresponding
     * to the query.
     *
     * @param query the query of the semester search
     * @return the result of the search
     */
    @GetMapping("/_search/semesters")
    @Timed
    public List<SemesterDTO> searchSemesters(@RequestParam String query) {
        log.debug("REST request to search Semesters for query {}", query);
        return semesterService.search(query);
    }

}
