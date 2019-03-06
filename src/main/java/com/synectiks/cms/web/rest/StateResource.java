package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.StateService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.StateDTO;
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
 * REST controller for managing State.
 */
@RestController
@RequestMapping("/api")
public class StateResource {

    private final Logger log = LoggerFactory.getLogger(StateResource.class);

    private static final String ENTITY_NAME = "state";

    private final StateService stateService;

    public StateResource(StateService stateService) {
        this.stateService = stateService;
    }

    /**
     * POST  /states : Create a new state.
     *
     * @param stateDTO the stateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stateDTO, or with status 400 (Bad Request) if the state has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/states")
    public ResponseEntity<StateDTO> createState(@Valid @RequestBody StateDTO stateDTO) throws URISyntaxException {
        log.debug("REST request to save State : {}", stateDTO);
        if (stateDTO.getId() != null) {
            throw new BadRequestAlertException("A new state cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StateDTO result = stateService.save(stateDTO);
        return ResponseEntity.created(new URI("/api/states/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /states : Updates an existing state.
     *
     * @param stateDTO the stateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stateDTO,
     * or with status 400 (Bad Request) if the stateDTO is not valid,
     * or with status 500 (Internal Server Error) if the stateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/states")
    public ResponseEntity<StateDTO> updateState(@Valid @RequestBody StateDTO stateDTO) throws URISyntaxException {
        log.debug("REST request to update State : {}", stateDTO);
        if (stateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StateDTO result = stateService.save(stateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /states : get all the states.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of states in body
     */
    @GetMapping("/states")
    public List<StateDTO> getAllStates() {
        log.debug("REST request to get all States");
        return stateService.findAll();
    }

    /**
     * GET  /states/:id : get the "id" state.
     *
     * @param id the id of the stateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/states/{id}")
    public ResponseEntity<StateDTO> getState(@PathVariable Long id) {
        log.debug("REST request to get State : {}", id);
        Optional<StateDTO> stateDTO = stateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stateDTO);
    }

    /**
     * DELETE  /states/:id : delete the "id" state.
     *
     * @param id the id of the stateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/states/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        log.debug("REST request to delete State : {}", id);
        stateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/states?query=:query : search for the state corresponding
     * to the query.
     *
     * @param query the query of the state search
     * @return the result of the search
     */
    @GetMapping("/_search/states")
    public List<StateDTO> searchStates(@RequestParam String query) {
        log.debug("REST request to search States for query {}", query);
        return stateService.search(query);
    }

}
