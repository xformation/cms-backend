package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.CollegeService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.CollegeDTO;
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
 * REST controller for managing College.
 */
@RestController
@RequestMapping("/api")
public class CollegeResource {

    private final Logger log = LoggerFactory.getLogger(CollegeResource.class);

    private static final String ENTITY_NAME = "college";

    private final CollegeService collegeService;

    public CollegeResource(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    /**
     * POST  /colleges : Create a new college.
     *
     * @param collegeDTO the collegeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new collegeDTO, or with status 400 (Bad Request) if the college has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/colleges")
    @Timed
    public ResponseEntity<CollegeDTO> createCollege(@Valid @RequestBody CollegeDTO collegeDTO) throws URISyntaxException {
        log.debug("REST request to save College : {}", collegeDTO);
        if (collegeDTO.getId() != null) {
            throw new BadRequestAlertException("A new college cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CollegeDTO result = collegeService.save(collegeDTO);
        return ResponseEntity.created(new URI("/api/colleges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /colleges : Updates an existing college.
     *
     * @param collegeDTO the collegeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated collegeDTO,
     * or with status 400 (Bad Request) if the collegeDTO is not valid,
     * or with status 500 (Internal Server Error) if the collegeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/colleges")
    @Timed
    public ResponseEntity<CollegeDTO> updateCollege(@Valid @RequestBody CollegeDTO collegeDTO) throws URISyntaxException {
        log.debug("REST request to update College : {}", collegeDTO);
        if (collegeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CollegeDTO result = collegeService.save(collegeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, collegeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /colleges : get all the colleges.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of colleges in body
     */
    @GetMapping("/colleges")
    @Timed
    public List<CollegeDTO> getAllColleges() {
        log.debug("REST request to get all Colleges");
        return collegeService.findAll();
    }

    /**
     * GET  /colleges/:id : get the "id" college.
     *
     * @param id the id of the collegeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the collegeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/colleges/{id}")
    @Timed
    public ResponseEntity<CollegeDTO> getCollege(@PathVariable Long id) {
        log.debug("REST request to get College : {}", id);
        Optional<CollegeDTO> collegeDTO = collegeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(collegeDTO);
    }

    /**
     * DELETE  /colleges/:id : delete the "id" college.
     *
     * @param id the id of the collegeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/colleges/{id}")
    @Timed
    public ResponseEntity<Void> deleteCollege(@PathVariable Long id) {
        log.debug("REST request to delete College : {}", id);
        collegeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/colleges?query=:query : search for the college corresponding
     * to the query.
     *
     * @param query the query of the college search
     * @return the result of the search
     */
    @GetMapping("/_search/colleges")
    @Timed
    public List<CollegeDTO> searchColleges(@RequestParam String query) {
        log.debug("REST request to search Colleges for query {}", query);
        return collegeService.search(query);
    }

}
