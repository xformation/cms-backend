package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.AcademicYearService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.AcademicYearDTO;
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
 * REST controller for managing AcademicYear.
 */
@RestController
@RequestMapping("/api")
public class AcademicYearResource {

    private final Logger log = LoggerFactory.getLogger(AcademicYearResource.class);

    private static final String ENTITY_NAME = "academicYear";

    private final AcademicYearService academicYearService;

    public AcademicYearResource(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    /**
     * POST  /academic-years : Create a new academicYear.
     *
     * @param academicYearDTO the academicYearDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new academicYearDTO, or with status 400 (Bad Request) if the academicYear has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/academic-years")
    @Timed
    public ResponseEntity<AcademicYearDTO> createAcademicYear(@Valid @RequestBody AcademicYearDTO academicYearDTO) throws URISyntaxException {
        log.debug("REST request to save AcademicYear : {}", academicYearDTO);
        if (academicYearDTO.getId() != null) {
            throw new BadRequestAlertException("A new academicYear cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcademicYearDTO result = academicYearService.save(academicYearDTO);
        return ResponseEntity.created(new URI("/api/academic-years/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /academic-years : Updates an existing academicYear.
     *
     * @param academicYearDTO the academicYearDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated academicYearDTO,
     * or with status 400 (Bad Request) if the academicYearDTO is not valid,
     * or with status 500 (Internal Server Error) if the academicYearDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/academic-years")
    @Timed
    public ResponseEntity<AcademicYearDTO> updateAcademicYear(@Valid @RequestBody AcademicYearDTO academicYearDTO) throws URISyntaxException {
        log.debug("REST request to update AcademicYear : {}", academicYearDTO);
        if (academicYearDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcademicYearDTO result = academicYearService.save(academicYearDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, academicYearDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /academic-years : get all the academicYears.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of academicYears in body
     */
    @GetMapping("/academic-years")
    @Timed
    public List<AcademicYearDTO> getAllAcademicYears() {
        log.debug("REST request to get all AcademicYears");
        return academicYearService.findAll();
    }

    /**
     * GET  /academic-years/:id : get the "id" academicYear.
     *
     * @param id the id of the academicYearDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the academicYearDTO, or with status 404 (Not Found)
     */
    @GetMapping("/academic-years/{id}")
    @Timed
    public ResponseEntity<AcademicYearDTO> getAcademicYear(@PathVariable Long id) {
        log.debug("REST request to get AcademicYear : {}", id);
        Optional<AcademicYearDTO> academicYearDTO = academicYearService.findOne(id);
        return ResponseUtil.wrapOrNotFound(academicYearDTO);
    }

    /**
     * DELETE  /academic-years/:id : delete the "id" academicYear.
     *
     * @param id the id of the academicYearDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/academic-years/{id}")
    @Timed
    public ResponseEntity<Void> deleteAcademicYear(@PathVariable Long id) {
        log.debug("REST request to delete AcademicYear : {}", id);
        academicYearService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/academic-years?query=:query : search for the academicYear corresponding
     * to the query.
     *
     * @param query the query of the academicYear search
     * @return the result of the search
     */
    @GetMapping("/_search/academic-years")
    @Timed
    public List<AcademicYearDTO> searchAcademicYears(@RequestParam String query) {
        log.debug("REST request to search AcademicYears for query {}", query);
        return academicYearService.search(query);
    }

}
