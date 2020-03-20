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
import com.synectiks.cms.service.FeeCategoryService;
import com.synectiks.cms.service.dto.FeeCategoryDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FeeCategory.
 */
@RestController
@RequestMapping("/api")
public class FeeCategoryResource {

    private final Logger log = LoggerFactory.getLogger(FeeCategoryResource.class);

    private static final String ENTITY_NAME = "feeCategory";

    private final FeeCategoryService feeCategoryService;

    public FeeCategoryResource(FeeCategoryService feeCategoryService) {
        this.feeCategoryService = feeCategoryService;
    }

    /**
     * POST  /fee-categories : Create a new feeCategory.
     *
     * @param feeCategoryDTO the feeCategoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feeCategoryDTO, or with status 400 (Bad Request) if the feeCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fee-categories")
    @Timed
    public ResponseEntity<FeeCategoryDTO> createFeeCategory(@Valid @RequestBody FeeCategoryDTO feeCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save FeeCategory : {}", feeCategoryDTO);
        if (feeCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new feeCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeeCategoryDTO result = feeCategoryService.save(feeCategoryDTO);
        return ResponseEntity.created(new URI("/api/fee-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fee-categories : Updates an existing feeCategory.
     *
     * @param feeCategoryDTO the feeCategoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated feeCategoryDTO,
     * or with status 400 (Bad Request) if the feeCategoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the feeCategoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fee-categories")
    @Timed
    public ResponseEntity<FeeCategoryDTO> updateFeeCategory(@Valid @RequestBody FeeCategoryDTO feeCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update FeeCategory : {}", feeCategoryDTO);
        if (feeCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeeCategoryDTO result = feeCategoryService.save(feeCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, feeCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fee-categories : get all the feeCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of feeCategories in body
     */
    @GetMapping("/fee-categories")
    @Timed
    public List<FeeCategoryDTO> getAllFeeCategories() {
        log.debug("REST request to get all FeeCategories");
        return feeCategoryService.findAll();
    }

    /**
     * GET  /fee-categories/:id : get the "id" feeCategory.
     *
     * @param id the id of the feeCategoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feeCategoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fee-categories/{id}")
    @Timed
    public ResponseEntity<FeeCategoryDTO> getFeeCategory(@PathVariable Long id) {
        log.debug("REST request to get FeeCategory : {}", id);
        Optional<FeeCategoryDTO> feeCategoryDTO = feeCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feeCategoryDTO);
    }

    /**
     * DELETE  /fee-categories/:id : delete the "id" feeCategory.
     *
     * @param id the id of the feeCategoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fee-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteFeeCategory(@PathVariable Long id) {
        log.debug("REST request to delete FeeCategory : {}", id);
        feeCategoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/fee-categories?query=:query : search for the feeCategory corresponding
     * to the query.
     *
     * @param query the query of the feeCategory search
     * @return the result of the search
     */
    @GetMapping("/_search/fee-categories")
    @Timed
    public List<FeeCategoryDTO> searchFeeCategories(@RequestParam String query) {
        log.debug("REST request to search FeeCategories for query {}", query);
        return feeCategoryService.search(query);
    }

}
