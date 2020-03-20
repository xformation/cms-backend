package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.synectiks.cms.service.FeeDetailsService;
import com.synectiks.cms.service.dto.FeeDetailsDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FeeDetails.
 */
@RestController
@RequestMapping("/api")
public class FeeDetailsResource {

    private final Logger log = LoggerFactory.getLogger(FeeDetailsResource.class);

    private static final String ENTITY_NAME = "feeDetails";

    private final FeeDetailsService feeDetailsService;

    public FeeDetailsResource(FeeDetailsService feeDetailsService) {
        this.feeDetailsService = feeDetailsService;
    }

    /**
     * POST  /fee-details : Create a new feeDetails.
     *
     * @param feeDetailsDTO the feeDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feeDetailsDTO, or with status 400 (Bad Request) if the feeDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fee-details")
    @Timed
    public ResponseEntity<FeeDetailsDTO> createFeeDetails(@Valid @RequestBody FeeDetailsDTO feeDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save FeeDetails : {}", feeDetailsDTO);
        if (feeDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new feeDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeeDetailsDTO result = feeDetailsService.save(feeDetailsDTO);
        return ResponseEntity.created(new URI("/api/fee-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fee-details : Updates an existing feeDetails.
     *
     * @param feeDetailsDTO the feeDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated feeDetailsDTO,
     * or with status 400 (Bad Request) if the feeDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the feeDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fee-details")
    @Timed
    public ResponseEntity<FeeDetailsDTO> updateFeeDetails(@Valid @RequestBody FeeDetailsDTO feeDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update FeeDetails : {}", feeDetailsDTO);
        if (feeDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeeDetailsDTO result = feeDetailsService.save(feeDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, feeDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fee-details : get all the feeDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of feeDetails in body
     */
    @GetMapping("/fee-details")
    @Timed
    public List<FeeDetailsDTO> getAllFeeDetails() {
        log.debug("REST request to get all FeeDetails");
        return feeDetailsService.findAll();
    }

    /**
     * GET  /fee-details/:id : get the "id" feeDetails.
     *
     * @param id the id of the feeDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feeDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fee-details/{id}")
    @Timed
    public ResponseEntity<FeeDetailsDTO> getFeeDetails(@PathVariable Long id) {
        log.debug("REST request to get FeeDetails : {}", id);
        Optional<FeeDetailsDTO> feeDetailsDTO = feeDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feeDetailsDTO);
    }

    /**
     * DELETE  /fee-details/:id : delete the "id" feeDetails.
     *
     * @param id the id of the feeDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fee-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteFeeDetails(@PathVariable Long id) {
        log.debug("REST request to delete FeeDetails : {}", id);
        feeDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/fee-details?query=:query : search for the feeDetails corresponding
     * to the query.
     *
     * @param query the query of the feeDetails search
     * @return the result of the search
     */
    @GetMapping("/_search/fee-details")
    @Timed
    public List<FeeDetailsDTO> searchFeeDetails(@RequestParam String query) {
        log.debug("REST request to search FeeDetails for query {}", query);
        return feeDetailsService.search(query);
    }

}
