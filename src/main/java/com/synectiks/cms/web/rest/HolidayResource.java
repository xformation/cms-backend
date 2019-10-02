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

import com.synectiks.cms.service.HolidayService;
import com.synectiks.cms.service.dto.HolidayDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Holiday.
 */
@RestController
@RequestMapping("/api")
public class HolidayResource {

    private final Logger log = LoggerFactory.getLogger(HolidayResource.class);

    private static final String ENTITY_NAME = "holiday";

    private final HolidayService holidayService;

    public HolidayResource(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    /**
     * POST  /holidays : Create a new holiday.
     *
     * @param holidayDTO the holidayDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new holidayDTO, or with status 400 (Bad Request) if the holiday has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/holidays")
    public ResponseEntity<HolidayDTO> createHoliday(@Valid @RequestBody HolidayDTO holidayDTO) throws URISyntaxException {
        log.debug("REST request to save Holiday : {}", holidayDTO);
        if (holidayDTO.getId() != null) {
            throw new BadRequestAlertException("A new holiday cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HolidayDTO result = holidayService.save(holidayDTO);
        return ResponseEntity.created(new URI("/api/holidays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /holidays : Updates an existing holiday.
     *
     * @param holidayDTO the holidayDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated holidayDTO,
     * or with status 400 (Bad Request) if the holidayDTO is not valid,
     * or with status 500 (Internal Server Error) if the holidayDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/holidays")
    public ResponseEntity<HolidayDTO> updateHoliday(@Valid @RequestBody HolidayDTO holidayDTO) throws URISyntaxException {
        log.debug("REST request to update Holiday : {}", holidayDTO);
        if (holidayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HolidayDTO result = holidayService.save(holidayDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, holidayDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /holidays : get all the holidays.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of holidays in body
     */
    @GetMapping("/holidays")
    public List<HolidayDTO> getAllHolidays() {
        log.debug("REST request to get all Holidays");
        return holidayService.findAll();
    }

    /**
     * GET  /holidays/:id : get the "id" holiday.
     *
     * @param id the id of the holidayDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the holidayDTO, or with status 404 (Not Found)
     */
    @GetMapping("/holidays/{id}")
    public ResponseEntity<HolidayDTO> getHoliday(@PathVariable Long id) {
        log.debug("REST request to get Holiday : {}", id);
        Optional<HolidayDTO> holidayDTO = holidayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(holidayDTO);
    }

    /**
     * DELETE  /holidays/:id : delete the "id" holiday.
     *
     * @param id the id of the holidayDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/holidays/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        log.debug("REST request to delete Holiday : {}", id);
        holidayService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/holidays?query=:query : search for the holiday corresponding
     * to the query.
     *
     * @param query the query of the holiday search
     * @return the result of the search
     */
    @GetMapping("/_search/holidays")
    public List<HolidayDTO> searchHolidays(@RequestParam String query) {
        log.debug("REST request to search Holidays for query {}", query);
        return holidayService.search(query);
    }

}
