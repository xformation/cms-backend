package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.AuthorizedSignatoryService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.AuthorizedSignatoryDTO;
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
 * REST controller for managing AuthorizedSignatory.
 */
@RestController
@RequestMapping("/api")
public class AuthorizedSignatoryResource {

    private final Logger log = LoggerFactory.getLogger(AuthorizedSignatoryResource.class);

    private static final String ENTITY_NAME = "authorizedSignatory";

    private final AuthorizedSignatoryService authorizedSignatoryService;

    public AuthorizedSignatoryResource(AuthorizedSignatoryService authorizedSignatoryService) {
        this.authorizedSignatoryService = authorizedSignatoryService;
    }

    /**
     * POST  /authorized-signatories : Create a new authorizedSignatory.
     *
     * @param authorizedSignatoryDTO the authorizedSignatoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new authorizedSignatoryDTO, or with status 400 (Bad Request) if the authorizedSignatory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/authorized-signatories")
    @Timed
    public ResponseEntity<AuthorizedSignatoryDTO> createAuthorizedSignatory(@Valid @RequestBody AuthorizedSignatoryDTO authorizedSignatoryDTO) throws URISyntaxException {
        log.debug("REST request to save AuthorizedSignatory : {}", authorizedSignatoryDTO);
        if (authorizedSignatoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new authorizedSignatory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthorizedSignatoryDTO result = authorizedSignatoryService.save(authorizedSignatoryDTO);
        return ResponseEntity.created(new URI("/api/authorized-signatories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /authorized-signatories : Updates an existing authorizedSignatory.
     *
     * @param authorizedSignatoryDTO the authorizedSignatoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated authorizedSignatoryDTO,
     * or with status 400 (Bad Request) if the authorizedSignatoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the authorizedSignatoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/authorized-signatories")
    @Timed
    public ResponseEntity<AuthorizedSignatoryDTO> updateAuthorizedSignatory(@Valid @RequestBody AuthorizedSignatoryDTO authorizedSignatoryDTO) throws URISyntaxException {
        log.debug("REST request to update AuthorizedSignatory : {}", authorizedSignatoryDTO);
        if (authorizedSignatoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuthorizedSignatoryDTO result = authorizedSignatoryService.save(authorizedSignatoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authorizedSignatoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /authorized-signatories : get all the authorizedSignatories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of authorizedSignatories in body
     */
    @GetMapping("/authorized-signatories")
    @Timed
    public List<AuthorizedSignatoryDTO> getAllAuthorizedSignatories() {
        log.debug("REST request to get all AuthorizedSignatories");
        return authorizedSignatoryService.findAll();
    }

    /**
     * GET  /authorized-signatories/:id : get the "id" authorizedSignatory.
     *
     * @param id the id of the authorizedSignatoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the authorizedSignatoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/authorized-signatories/{id}")
    @Timed
    public ResponseEntity<AuthorizedSignatoryDTO> getAuthorizedSignatory(@PathVariable Long id) {
        log.debug("REST request to get AuthorizedSignatory : {}", id);
        Optional<AuthorizedSignatoryDTO> authorizedSignatoryDTO = authorizedSignatoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authorizedSignatoryDTO);
    }

    /**
     * DELETE  /authorized-signatories/:id : delete the "id" authorizedSignatory.
     *
     * @param id the id of the authorizedSignatoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authorized-signatories/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuthorizedSignatory(@PathVariable Long id) {
        log.debug("REST request to delete AuthorizedSignatory : {}", id);
        authorizedSignatoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/authorized-signatories?query=:query : search for the authorizedSignatory corresponding
     * to the query.
     *
     * @param query the query of the authorizedSignatory search
     * @return the result of the search
     */
    @GetMapping("/_search/authorized-signatories")
    @Timed
    public List<AuthorizedSignatoryDTO> searchAuthorizedSignatories(@RequestParam String query) {
        log.debug("REST request to search AuthorizedSignatories for query {}", query);
        return authorizedSignatoryService.search(query);
    }

}
