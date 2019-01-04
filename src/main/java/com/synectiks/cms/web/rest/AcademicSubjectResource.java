package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.AcademicSubjectService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.AcademicSubjectDTO;
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
 * REST controller for managing AcademicSubject.
 */
@RestController
@RequestMapping("/api")
public class AcademicSubjectResource {

    private final Logger log = LoggerFactory.getLogger(AcademicSubjectResource.class);

    private static final String ENTITY_NAME = "academicSubject";

    private final AcademicSubjectService academicSubjectService;

    public AcademicSubjectResource(AcademicSubjectService academicSubjectService) {
        this.academicSubjectService = academicSubjectService;
    }

    /**
     * POST  /academic-subjects : Create a new academicSubject.
     *
     * @param academicSubjectDTO the academicSubjectDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new academicSubjectDTO, or with status 400 (Bad Request) if the academicSubject has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/academic-subjects")
    @Timed
    public ResponseEntity<AcademicSubjectDTO> createAcademicSubject(@Valid @RequestBody AcademicSubjectDTO academicSubjectDTO) throws URISyntaxException {
        log.debug("REST request to save AcademicSubject : {}", academicSubjectDTO);
        if (academicSubjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new academicSubject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcademicSubjectDTO result = academicSubjectService.save(academicSubjectDTO);
        return ResponseEntity.created(new URI("/api/academic-subjects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /academic-subjects : Updates an existing academicSubject.
     *
     * @param academicSubjectDTO the academicSubjectDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated academicSubjectDTO,
     * or with status 400 (Bad Request) if the academicSubjectDTO is not valid,
     * or with status 500 (Internal Server Error) if the academicSubjectDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/academic-subjects")
    @Timed
    public ResponseEntity<AcademicSubjectDTO> updateAcademicSubject(@Valid @RequestBody AcademicSubjectDTO academicSubjectDTO) throws URISyntaxException {
        log.debug("REST request to update AcademicSubject : {}", academicSubjectDTO);
        if (academicSubjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcademicSubjectDTO result = academicSubjectService.save(academicSubjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, academicSubjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /academic-subjects : get all the academicSubjects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of academicSubjects in body
     */
    @GetMapping("/academic-subjects")
    @Timed
    public List<AcademicSubjectDTO> getAllAcademicSubjects() {
        log.debug("REST request to get all AcademicSubjects");
        return academicSubjectService.findAll();
    }

    /**
     * GET  /academic-subjects/:id : get the "id" academicSubject.
     *
     * @param id the id of the academicSubjectDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the academicSubjectDTO, or with status 404 (Not Found)
     */
    @GetMapping("/academic-subjects/{id}")
    @Timed
    public ResponseEntity<AcademicSubjectDTO> getAcademicSubject(@PathVariable Long id) {
        log.debug("REST request to get AcademicSubject : {}", id);
        Optional<AcademicSubjectDTO> academicSubjectDTO = academicSubjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(academicSubjectDTO);
    }

    /**
     * DELETE  /academic-subjects/:id : delete the "id" academicSubject.
     *
     * @param id the id of the academicSubjectDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/academic-subjects/{id}")
    @Timed
    public ResponseEntity<Void> deleteAcademicSubject(@PathVariable Long id) {
        log.debug("REST request to delete AcademicSubject : {}", id);
        academicSubjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/academic-subjects?query=:query : search for the academicSubject corresponding
     * to the query.
     *
     * @param query the query of the academicSubject search
     * @return the result of the search
     */
    @GetMapping("/_search/academic-subjects")
    @Timed
    public List<AcademicSubjectDTO> searchAcademicSubjects(@RequestParam String query) {
        log.debug("REST request to search AcademicSubjects for query {}", query);
        return academicSubjectService.search(query);
    }

}
