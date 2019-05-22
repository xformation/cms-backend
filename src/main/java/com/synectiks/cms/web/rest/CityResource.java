package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.CityService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.CityDTO;
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
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/api")
public class CityResource {

    private final Logger log = LoggerFactory.getLogger(CityResource.class);

    private static final String ENTITY_NAME = "city";

    private final CityService cityService;

    public CityResource(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * POST  /cities : Create a new city.
     *
     * @param cityDTO the cityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cityDTO, or with status 400 (Bad Request) if the city has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cities")
    public ResponseEntity<CityDTO> createCity(@Valid @RequestBody CityDTO cityDTO) throws URISyntaxException {
        log.debug("REST request to save City : {}", cityDTO);
        if (cityDTO.getId() != null) {
            throw new BadRequestAlertException("A new city cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CityDTO result = cityService.save(cityDTO);
        return ResponseEntity.created(new URI("/api/cities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cities : Updates an existing city.
     *
     * @param cityDTO the cityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cityDTO,
     * or with status 400 (Bad Request) if the cityDTO is not valid,
     * or with status 500 (Internal Server Error) if the cityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cities")
    public ResponseEntity<CityDTO> updateCity(@Valid @RequestBody CityDTO cityDTO) throws URISyntaxException {
        log.debug("REST request to update City : {}", cityDTO);
        if (cityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CityDTO result = cityService.save(cityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cities : get all the cities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cities in body
     */
    @GetMapping("/cities")
    public List<CityDTO> getAllCities() {
        log.debug("REST request to get all Cities");
        return cityService.findAll();
    }

    /**
     * GET  /cities/:id : get the "id" city.
     *
     * @param id the id of the cityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cities/{id}")
    public ResponseEntity<CityDTO> getCity(@PathVariable Long id) {
        log.debug("REST request to get City : {}", id);
        Optional<CityDTO> cityDTO = cityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cityDTO);
    }

    /**
     * DELETE  /cities/:id : delete the "id" city.
     *
     * @param id the id of the cityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        log.debug("REST request to delete City : {}", id);
        cityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/cities?query=:query : search for the city corresponding
     * to the query.
     *
     * @param query the query of the city search
     * @return the result of the search
     */
    @GetMapping("/_search/cities")
    public List<CityDTO> searchCities(@RequestParam String query) {
        log.debug("REST request to search Cities for query {}", query);
        return cityService.search(query);
    }

}
