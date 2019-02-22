package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.DueDateService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.DueDateDTO;
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
 * REST controller for managing DueDate.
 */
@RestController
@RequestMapping("/api")
public class DueDateResource {

    private final Logger log = LoggerFactory.getLogger(DueDateResource.class);

    private static final String ENTITY_NAME = "dueDate";

    private final DueDateService dueDateService;

    public DueDateResource(DueDateService dueDateService) {
        this.dueDateService = dueDateService;
    }

    /**
     * POST  /due-dates : Create a new dueDate.
     *
     * @param dueDateDTO the dueDateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dueDateDTO, or with status 400 (Bad Request) if the dueDate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/due-dates")
    @Timed
    public ResponseEntity<DueDateDTO> createDueDate(@Valid @RequestBody DueDateDTO dueDateDTO) throws URISyntaxException {
        log.debug("REST request to save DueDate : {}", dueDateDTO);
        if (dueDateDTO.getId() != null) {
            throw new BadRequestAlertException("A new dueDate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DueDateDTO result = dueDateService.save(dueDateDTO);
        return ResponseEntity.created(new URI("/api/due-dates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /due-dates : Updates an existing dueDate.
     *
     * @param dueDateDTO the dueDateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dueDateDTO,
     * or with status 400 (Bad Request) if the dueDateDTO is not valid,
     * or with status 500 (Internal Server Error) if the dueDateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/due-dates")
    @Timed
    public ResponseEntity<DueDateDTO> updateDueDate(@Valid @RequestBody DueDateDTO dueDateDTO) throws URISyntaxException {
        log.debug("REST request to update DueDate : {}", dueDateDTO);
        if (dueDateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DueDateDTO result = dueDateService.save(dueDateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dueDateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /due-dates : get all the dueDates.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dueDates in body
     */
    @GetMapping("/due-dates")
    @Timed
    public List<DueDateDTO> getAllDueDates() {
        log.debug("REST request to get all DueDates");
        return dueDateService.findAll();
    }

    /**
     * GET  /due-dates/:id : get the "id" dueDate.
     *
     * @param id the id of the dueDateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dueDateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/due-dates/{id}")
    @Timed
    public ResponseEntity<DueDateDTO> getDueDate(@PathVariable Long id) {
        log.debug("REST request to get DueDate : {}", id);
        Optional<DueDateDTO> dueDateDTO = dueDateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dueDateDTO);
    }

    /**
     * DELETE  /due-dates/:id : delete the "id" dueDate.
     *
     * @param id the id of the dueDateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/due-dates/{id}")
    @Timed
    public ResponseEntity<Void> deleteDueDate(@PathVariable Long id) {
        log.debug("REST request to delete DueDate : {}", id);
        dueDateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/due-dates?query=:query : search for the dueDate corresponding
     * to the query.
     *
     * @param query the query of the dueDate search
     * @return the result of the search
     */
    @GetMapping("/_search/due-dates")
    @Timed
    public List<DueDateDTO> searchDueDates(@RequestParam String query) {
        log.debug("REST request to search DueDates for query {}", query);
        return dueDateService.search(query);
    }

}
