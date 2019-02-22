package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.FacilityService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.FacilityDTO;
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
 * REST controller for managing Facility.
 */
@RestController
@RequestMapping("/api")
public class FacilityResource {

    private final Logger log = LoggerFactory.getLogger(FacilityResource.class);

    private static final String ENTITY_NAME = "facility";

    private final FacilityService facilityService;

    public FacilityResource(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    /**
     * POST  /facilities : Create a new facility.
     *
     * @param facilityDTO the facilityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new facilityDTO, or with status 400 (Bad Request) if the facility has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/facilities")
    @Timed
    public ResponseEntity<FacilityDTO> createFacility(@Valid @RequestBody FacilityDTO facilityDTO) throws URISyntaxException {
        log.debug("REST request to save Facility : {}", facilityDTO);
        if (facilityDTO.getId() != null) {
            throw new BadRequestAlertException("A new facility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FacilityDTO result = facilityService.save(facilityDTO);
        return ResponseEntity.created(new URI("/api/facilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /facilities : Updates an existing facility.
     *
     * @param facilityDTO the facilityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated facilityDTO,
     * or with status 400 (Bad Request) if the facilityDTO is not valid,
     * or with status 500 (Internal Server Error) if the facilityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/facilities")
    @Timed
    public ResponseEntity<FacilityDTO> updateFacility(@Valid @RequestBody FacilityDTO facilityDTO) throws URISyntaxException {
        log.debug("REST request to update Facility : {}", facilityDTO);
        if (facilityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FacilityDTO result = facilityService.save(facilityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, facilityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /facilities : get all the facilities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of facilities in body
     */
    @GetMapping("/facilities")
    @Timed
    public List<FacilityDTO> getAllFacilities() {
        log.debug("REST request to get all Facilities");
        return facilityService.findAll();
    }

    /**
     * GET  /facilities/:id : get the "id" facility.
     *
     * @param id the id of the facilityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the facilityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/facilities/{id}")
    @Timed
    public ResponseEntity<FacilityDTO> getFacility(@PathVariable Long id) {
        log.debug("REST request to get Facility : {}", id);
        Optional<FacilityDTO> facilityDTO = facilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(facilityDTO);
    }

    /**
     * DELETE  /facilities/:id : delete the "id" facility.
     *
     * @param id the id of the facilityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/facilities/{id}")
    @Timed
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        log.debug("REST request to delete Facility : {}", id);
        facilityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/facilities?query=:query : search for the facility corresponding
     * to the query.
     *
     * @param query the query of the facility search
     * @return the result of the search
     */
    @GetMapping("/_search/facilities")
    @Timed
    public List<FacilityDTO> searchFacilities(@RequestParam String query) {
        log.debug("REST request to search Facilities for query {}", query);
        return facilityService.search(query);
    }

}
