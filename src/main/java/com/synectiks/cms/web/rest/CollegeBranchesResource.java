package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.CollegeBranchesService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.CollegeBranchesDTO;
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
 * REST controller for managing CollegeBranches.
 */
@RestController
@RequestMapping("/api")
public class CollegeBranchesResource {

    private final Logger log = LoggerFactory.getLogger(CollegeBranchesResource.class);

    private static final String ENTITY_NAME = "collegeBranches";

    private final CollegeBranchesService collegeBranchesService;

    public CollegeBranchesResource(CollegeBranchesService collegeBranchesService) {
        this.collegeBranchesService = collegeBranchesService;
    }

    /**
     * POST  /college-branches : Create a new collegeBranches.
     *
     * @param collegeBranchesDTO the collegeBranchesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new collegeBranchesDTO, or with status 400 (Bad Request) if the collegeBranches has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/college-branches")
    @Timed
    public ResponseEntity<CollegeBranchesDTO> createCollegeBranches(@Valid @RequestBody CollegeBranchesDTO collegeBranchesDTO) throws URISyntaxException {
        log.debug("REST request to save CollegeBranches : {}", collegeBranchesDTO);
        if (collegeBranchesDTO.getId() != null) {
            throw new BadRequestAlertException("A new collegeBranches cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CollegeBranchesDTO result = collegeBranchesService.save(collegeBranchesDTO);
        return ResponseEntity.created(new URI("/api/college-branches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /college-branches : Updates an existing collegeBranches.
     *
     * @param collegeBranchesDTO the collegeBranchesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated collegeBranchesDTO,
     * or with status 400 (Bad Request) if the collegeBranchesDTO is not valid,
     * or with status 500 (Internal Server Error) if the collegeBranchesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/college-branches")
    @Timed
    public ResponseEntity<CollegeBranchesDTO> updateCollegeBranches(@Valid @RequestBody CollegeBranchesDTO collegeBranchesDTO) throws URISyntaxException {
        log.debug("REST request to update CollegeBranches : {}", collegeBranchesDTO);
        if (collegeBranchesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CollegeBranchesDTO result = collegeBranchesService.save(collegeBranchesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, collegeBranchesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /college-branches : get all the collegeBranches.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of collegeBranches in body
     */
    @GetMapping("/college-branches")
    @Timed
    public List<CollegeBranchesDTO> getAllCollegeBranches() {
        log.debug("REST request to get all CollegeBranches");
        return collegeBranchesService.findAll();
    }

    /**
     * GET  /college-branches/:id : get the "id" collegeBranches.
     *
     * @param id the id of the collegeBranchesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the collegeBranchesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/college-branches/{id}")
    @Timed
    public ResponseEntity<CollegeBranchesDTO> getCollegeBranches(@PathVariable Long id) {
        log.debug("REST request to get CollegeBranches : {}", id);
        Optional<CollegeBranchesDTO> collegeBranchesDTO = collegeBranchesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(collegeBranchesDTO);
    }

    /**
     * DELETE  /college-branches/:id : delete the "id" collegeBranches.
     *
     * @param id the id of the collegeBranchesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/college-branches/{id}")
    @Timed
    public ResponseEntity<Void> deleteCollegeBranches(@PathVariable Long id) {
        log.debug("REST request to delete CollegeBranches : {}", id);
        collegeBranchesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/college-branches?query=:query : search for the collegeBranches corresponding
     * to the query.
     *
     * @param query the query of the collegeBranches search
     * @return the result of the search
     */
    @GetMapping("/_search/college-branches")
    @Timed
    public List<CollegeBranchesDTO> searchCollegeBranches(@RequestParam String query) {
        log.debug("REST request to search CollegeBranches for query {}", query);
        return collegeBranchesService.search(query);
    }

}
