package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.LateFeeService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.LateFeeDTO;
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
 * REST controller for managing LateFee.
 */
@RestController
@RequestMapping("/api")
public class LateFeeResource {

    private final Logger log = LoggerFactory.getLogger(LateFeeResource.class);

    private static final String ENTITY_NAME = "lateFee";

    private final LateFeeService lateFeeService;

    public LateFeeResource(LateFeeService lateFeeService) {
        this.lateFeeService = lateFeeService;
    }

    /**
     * POST  /late-fees : Create a new lateFee.
     *
     * @param lateFeeDTO the lateFeeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lateFeeDTO, or with status 400 (Bad Request) if the lateFee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/late-fees")
    @Timed
    public ResponseEntity<LateFeeDTO> createLateFee(@Valid @RequestBody LateFeeDTO lateFeeDTO) throws URISyntaxException {
        log.debug("REST request to save LateFee : {}", lateFeeDTO);
        if (lateFeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new lateFee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LateFeeDTO result = lateFeeService.save(lateFeeDTO);
        return ResponseEntity.created(new URI("/api/late-fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /late-fees : Updates an existing lateFee.
     *
     * @param lateFeeDTO the lateFeeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated lateFeeDTO,
     * or with status 400 (Bad Request) if the lateFeeDTO is not valid,
     * or with status 500 (Internal Server Error) if the lateFeeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/late-fees")
    @Timed
    public ResponseEntity<LateFeeDTO> updateLateFee(@Valid @RequestBody LateFeeDTO lateFeeDTO) throws URISyntaxException {
        log.debug("REST request to update LateFee : {}", lateFeeDTO);
        if (lateFeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LateFeeDTO result = lateFeeService.save(lateFeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, lateFeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /late-fees : get all the lateFees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of lateFees in body
     */
    @GetMapping("/late-fees")
    @Timed
    public List<LateFeeDTO> getAllLateFees() {
        log.debug("REST request to get all LateFees");
        return lateFeeService.findAll();
    }

    /**
     * GET  /late-fees/:id : get the "id" lateFee.
     *
     * @param id the id of the lateFeeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lateFeeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/late-fees/{id}")
    @Timed
    public ResponseEntity<LateFeeDTO> getLateFee(@PathVariable Long id) {
        log.debug("REST request to get LateFee : {}", id);
        Optional<LateFeeDTO> lateFeeDTO = lateFeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lateFeeDTO);
    }

    /**
     * DELETE  /late-fees/:id : delete the "id" lateFee.
     *
     * @param id the id of the lateFeeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/late-fees/{id}")
    @Timed
    public ResponseEntity<Void> deleteLateFee(@PathVariable Long id) {
        log.debug("REST request to delete LateFee : {}", id);
        lateFeeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/late-fees?query=:query : search for the lateFee corresponding
     * to the query.
     *
     * @param query the query of the lateFee search
     * @return the result of the search
     */
    @GetMapping("/_search/late-fees")
    @Timed
    public List<LateFeeDTO> searchLateFees(@RequestParam String query) {
        log.debug("REST request to search LateFees for query {}", query);
        return lateFeeService.search(query);
    }

}
