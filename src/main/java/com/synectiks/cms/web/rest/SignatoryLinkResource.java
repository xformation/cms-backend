package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.SignatoryLinkService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.SignatoryLinkDTO;
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
 * REST controller for managing SignatoryLink.
 */
@RestController
@RequestMapping("/api")
public class SignatoryLinkResource {

    private final Logger log = LoggerFactory.getLogger(SignatoryLinkResource.class);

    private static final String ENTITY_NAME = "signatoryLink";

    private final SignatoryLinkService signatoryLinkService;

    public SignatoryLinkResource(SignatoryLinkService signatoryLinkService) {
        this.signatoryLinkService = signatoryLinkService;
    }

    /**
     * POST  /signatory-links : Create a new signatoryLink.
     *
     * @param signatoryLinkDTO the signatoryLinkDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new signatoryLinkDTO, or with status 400 (Bad Request) if the signatoryLink has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/signatory-links")
    @Timed
    public ResponseEntity<SignatoryLinkDTO> createSignatoryLink(@RequestBody SignatoryLinkDTO signatoryLinkDTO) throws URISyntaxException {
        log.debug("REST request to save SignatoryLink : {}", signatoryLinkDTO);
        if (signatoryLinkDTO.getId() != null) {
            throw new BadRequestAlertException("A new signatoryLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SignatoryLinkDTO result = signatoryLinkService.save(signatoryLinkDTO);
        return ResponseEntity.created(new URI("/api/signatory-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /signatory-links : Updates an existing signatoryLink.
     *
     * @param signatoryLinkDTO the signatoryLinkDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated signatoryLinkDTO,
     * or with status 400 (Bad Request) if the signatoryLinkDTO is not valid,
     * or with status 500 (Internal Server Error) if the signatoryLinkDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/signatory-links")
    @Timed
    public ResponseEntity<SignatoryLinkDTO> updateSignatoryLink(@RequestBody SignatoryLinkDTO signatoryLinkDTO) throws URISyntaxException {
        log.debug("REST request to update SignatoryLink : {}", signatoryLinkDTO);
        if (signatoryLinkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SignatoryLinkDTO result = signatoryLinkService.save(signatoryLinkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, signatoryLinkDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /signatory-links : get all the signatoryLinks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of signatoryLinks in body
     */
    @GetMapping("/signatory-links")
    @Timed
    public List<SignatoryLinkDTO> getAllSignatoryLinks() {
        log.debug("REST request to get all SignatoryLinks");
        return signatoryLinkService.findAll();
    }

    /**
     * GET  /signatory-links/:id : get the "id" signatoryLink.
     *
     * @param id the id of the signatoryLinkDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the signatoryLinkDTO, or with status 404 (Not Found)
     */
    @GetMapping("/signatory-links/{id}")
    @Timed
    public ResponseEntity<SignatoryLinkDTO> getSignatoryLink(@PathVariable Long id) {
        log.debug("REST request to get SignatoryLink : {}", id);
        Optional<SignatoryLinkDTO> signatoryLinkDTO = signatoryLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(signatoryLinkDTO);
    }

    /**
     * DELETE  /signatory-links/:id : delete the "id" signatoryLink.
     *
     * @param id the id of the signatoryLinkDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/signatory-links/{id}")
    @Timed
    public ResponseEntity<Void> deleteSignatoryLink(@PathVariable Long id) {
        log.debug("REST request to delete SignatoryLink : {}", id);
        signatoryLinkService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/signatory-links?query=:query : search for the signatoryLink corresponding
     * to the query.
     *
     * @param query the query of the signatoryLink search
     * @return the result of the search
     */
    @GetMapping("/_search/signatory-links")
    @Timed
    public List<SignatoryLinkDTO> searchSignatoryLinks(@RequestParam String query) {
        log.debug("REST request to search SignatoryLinks for query {}", query);
        return signatoryLinkService.search(query);
    }

}
