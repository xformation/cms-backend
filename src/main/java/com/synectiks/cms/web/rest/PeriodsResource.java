package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.PeriodsService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.PeriodsDTO;
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
 * REST controller for managing Periods.
 */
@RestController
@RequestMapping("/api")
public class PeriodsResource {

    private final Logger log = LoggerFactory.getLogger(PeriodsResource.class);

    private static final String ENTITY_NAME = "periods";

    private final PeriodsService periodsService;

    public PeriodsResource(PeriodsService periodsService) {
        this.periodsService = periodsService;
    }

    /**
     * POST  /periods : Create a new periods.
     *
     * @param periodsDTO the periodsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new periodsDTO, or with status 400 (Bad Request) if the periods has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/periods")
    @Timed
    public ResponseEntity<PeriodsDTO> createPeriods(@Valid @RequestBody PeriodsDTO periodsDTO) throws URISyntaxException {
        log.debug("REST request to save Periods : {}", periodsDTO);
        if (periodsDTO.getId() != null) {
            throw new BadRequestAlertException("A new periods cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PeriodsDTO result = periodsService.save(periodsDTO);
        return ResponseEntity.created(new URI("/api/periods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /periods : Updates an existing periods.
     *
     * @param periodsDTO the periodsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated periodsDTO,
     * or with status 400 (Bad Request) if the periodsDTO is not valid,
     * or with status 500 (Internal Server Error) if the periodsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/periods")
    @Timed
    public ResponseEntity<PeriodsDTO> updatePeriods(@Valid @RequestBody PeriodsDTO periodsDTO) throws URISyntaxException {
        log.debug("REST request to update Periods : {}", periodsDTO);
        if (periodsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PeriodsDTO result = periodsService.save(periodsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, periodsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /periods : get all the periods.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of periods in body
     */
    @GetMapping("/periods")
    @Timed
    public List<PeriodsDTO> getAllPeriods() {
        log.debug("REST request to get all Periods");
        return periodsService.findAll();
    }

    /**
     * GET  /periods/:id : get the "id" periods.
     *
     * @param id the id of the periodsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the periodsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/periods/{id}")
    @Timed
    public ResponseEntity<PeriodsDTO> getPeriods(@PathVariable Long id) {
        log.debug("REST request to get Periods : {}", id);
        Optional<PeriodsDTO> periodsDTO = periodsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(periodsDTO);
    }

    /**
     * DELETE  /periods/:id : delete the "id" periods.
     *
     * @param id the id of the periodsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/periods/{id}")
    @Timed
    public ResponseEntity<Void> deletePeriods(@PathVariable Long id) {
        log.debug("REST request to delete Periods : {}", id);
        periodsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/periods?query=:query : search for the periods corresponding
     * to the query.
     *
     * @param query the query of the periods search
     * @return the result of the search
     */
    @GetMapping("/_search/periods")
    @Timed
    public List<PeriodsDTO> searchPeriods(@RequestParam String query) {
        log.debug("REST request to search Periods for query {}", query);
        return periodsService.search(query);
    }

}
