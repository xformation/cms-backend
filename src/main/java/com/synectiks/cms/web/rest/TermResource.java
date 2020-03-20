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

import com.synectiks.cms.service.TermService;
import com.synectiks.cms.service.dto.TermDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Term.
 */
@RestController
@RequestMapping("/api")
public class TermResource {

    private final Logger log = LoggerFactory.getLogger(TermResource.class);

    private static final String ENTITY_NAME = "term";

    private final TermService termService;

    public TermResource(TermService termService) {
        this.termService = termService;
    }

    /**
     * POST  /terms : Create a new term.
     *
     * @param termDTO the termDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new termDTO, or with status 400 (Bad Request) if the term has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/terms")
    public ResponseEntity<TermDTO> createTerm(@Valid @RequestBody TermDTO termDTO) throws URISyntaxException {
        log.debug("REST request to save Term : {}", termDTO);
        if (termDTO.getId() != null) {
            throw new BadRequestAlertException("A new term cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TermDTO result = termService.save(termDTO);
        return ResponseEntity.created(new URI("/api/terms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /terms : Updates an existing term.
     *
     * @param termDTO the termDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated termDTO,
     * or with status 400 (Bad Request) if the termDTO is not valid,
     * or with status 500 (Internal Server Error) if the termDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/terms")
    public ResponseEntity<TermDTO> updateTerm(@Valid @RequestBody TermDTO termDTO) throws URISyntaxException {
        log.debug("REST request to update Term : {}", termDTO);
        if (termDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TermDTO result = termService.save(termDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, termDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /terms : get all the terms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of terms in body
     */
    @GetMapping("/terms")
    public List<TermDTO> getAllTerms() {
        log.debug("REST request to get all Terms");
        return termService.findAll();
    }

    /**
     * GET  /terms/:id : get the "id" term.
     *
     * @param id the id of the termDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the termDTO, or with status 404 (Not Found)
     */
    @GetMapping("/terms/{id}")
    public ResponseEntity<TermDTO> getTerm(@PathVariable Long id) {
        log.debug("REST request to get Term : {}", id);
        Optional<TermDTO> termDTO = termService.findOne(id);
        return ResponseUtil.wrapOrNotFound(termDTO);
    }

    /**
     * DELETE  /terms/:id : delete the "id" term.
     *
     * @param id the id of the termDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/terms/{id}")
    public ResponseEntity<Void> deleteTerm(@PathVariable Long id) {
        log.debug("REST request to delete Term : {}", id);
        termService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/terms?query=:query : search for the term corresponding
     * to the query.
     *
     * @param query the query of the term search
     * @return the result of the search
     */
    @GetMapping("/_search/terms")
    public List<TermDTO> searchTerms(@RequestParam String query) {
        log.debug("REST request to search Terms for query {}", query);
        return termService.search(query);
    }

}
