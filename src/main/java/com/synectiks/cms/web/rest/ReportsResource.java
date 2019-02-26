package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.ReportsService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.ReportsDTO;
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
 * REST controller for managing Reports.
 */
@RestController
@RequestMapping("/api")
public class ReportsResource {

    private final Logger log = LoggerFactory.getLogger(ReportsResource.class);

    private static final String ENTITY_NAME = "reports";

    private final ReportsService reportsService;

    public ReportsResource(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    /**
     * POST  /reports : Create a new reports.
     *
     * @param reportsDTO the reportsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reportsDTO, or with status 400 (Bad Request) if the reports has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reports")
    @Timed
    public ResponseEntity<ReportsDTO> createReports(@Valid @RequestBody ReportsDTO reportsDTO) throws URISyntaxException {
        log.debug("REST request to save Reports : {}", reportsDTO);
        if (reportsDTO.getId() != null) {
            throw new BadRequestAlertException("A new reports cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportsDTO result = reportsService.save(reportsDTO);
        return ResponseEntity.created(new URI("/api/reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reports : Updates an existing reports.
     *
     * @param reportsDTO the reportsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reportsDTO,
     * or with status 400 (Bad Request) if the reportsDTO is not valid,
     * or with status 500 (Internal Server Error) if the reportsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reports")
    @Timed
    public ResponseEntity<ReportsDTO> updateReports(@Valid @RequestBody ReportsDTO reportsDTO) throws URISyntaxException {
        log.debug("REST request to update Reports : {}", reportsDTO);
        if (reportsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportsDTO result = reportsService.save(reportsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reportsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reports : get all the reports.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reports in body
     */
    @GetMapping("/reports")
    @Timed
    public List<ReportsDTO> getAllReports() {
        log.debug("REST request to get all Reports");
        return reportsService.findAll();
    }

    /**
     * GET  /reports/:id : get the "id" reports.
     *
     * @param id the id of the reportsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reportsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reports/{id}")
    @Timed
    public ResponseEntity<ReportsDTO> getReports(@PathVariable Long id) {
        log.debug("REST request to get Reports : {}", id);
        Optional<ReportsDTO> reportsDTO = reportsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportsDTO);
    }

    /**
     * DELETE  /reports/:id : delete the "id" reports.
     *
     * @param id the id of the reportsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reports/{id}")
    @Timed
    public ResponseEntity<Void> deleteReports(@PathVariable Long id) {
        log.debug("REST request to delete Reports : {}", id);
        reportsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/reports?query=:query : search for the reports corresponding
     * to the query.
     *
     * @param query the query of the reports search
     * @return the result of the search
     */
    @GetMapping("/_search/reports")
    @Timed
    public List<ReportsDTO> searchReports(@RequestParam String query) {
        log.debug("REST request to search Reports for query {}", query);
        return reportsService.search(query);
    }

}
