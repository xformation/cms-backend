package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.InsuranceService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.InsuranceDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Insurance.
 */
@RestController
@RequestMapping("/api")
public class InsuranceResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceResource.class);

    private static final String ENTITY_NAME = "insurance";

    private final InsuranceService insuranceService;

    public InsuranceResource(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * POST  /insurances : Create a new insurance.
     *
     * @param insuranceDTO the insuranceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new insuranceDTO, or with status 400 (Bad Request) if the insurance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/insurances")
    public ResponseEntity<InsuranceDTO> createInsurance(@RequestBody InsuranceDTO insuranceDTO) throws URISyntaxException {
        log.debug("REST request to save Insurance : {}", insuranceDTO);
        if (insuranceDTO.getId() != null) {
            throw new BadRequestAlertException("A new insurance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceDTO result = insuranceService.save(insuranceDTO);
        return ResponseEntity.created(new URI("/api/insurances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /insurances : Updates an existing insurance.
     *
     * @param insuranceDTO the insuranceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated insuranceDTO,
     * or with status 400 (Bad Request) if the insuranceDTO is not valid,
     * or with status 500 (Internal Server Error) if the insuranceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/insurances")
    public ResponseEntity<InsuranceDTO> updateInsurance(@RequestBody InsuranceDTO insuranceDTO) throws URISyntaxException {
        log.debug("REST request to update Insurance : {}", insuranceDTO);
        if (insuranceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceDTO result = insuranceService.save(insuranceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, insuranceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /insurances : get all the insurances.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of insurances in body
     */
    @GetMapping("/insurances")
    public List<InsuranceDTO> getAllInsurances(@RequestParam(required = false) String filter) {
        if ("vehicle-is-null".equals(filter)) {
            log.debug("REST request to get all Insurances where vehicle is null");
            return insuranceService.findAllWhereVehicleIsNull();
        }
        log.debug("REST request to get all Insurances");
        return insuranceService.findAll();
    }

    /**
     * GET  /insurances/:id : get the "id" insurance.
     *
     * @param id the id of the insuranceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the insuranceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/insurances/{id}")
    public ResponseEntity<InsuranceDTO> getInsurance(@PathVariable Long id) {
        log.debug("REST request to get Insurance : {}", id);
        Optional<InsuranceDTO> insuranceDTO = insuranceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceDTO);
    }

    /**
     * DELETE  /insurances/:id : delete the "id" insurance.
     *
     * @param id the id of the insuranceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/insurances/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable Long id) {
        log.debug("REST request to delete Insurance : {}", id);
        insuranceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/insurances?query=:query : search for the insurance corresponding
     * to the query.
     *
     * @param query the query of the insurance search
     * @return the result of the search
     */
    @GetMapping("/_search/insurances")
    public List<InsuranceDTO> searchInsurances(@RequestParam String query) {
        log.debug("REST request to search Insurances for query {}", query);
        return insuranceService.search(query);
    }

}
