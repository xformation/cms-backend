package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.IdCardService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.IdCardDTO;
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
 * REST controller for managing IdCard.
 */
@RestController
@RequestMapping("/api")
public class IdCardResource {

    private final Logger log = LoggerFactory.getLogger(IdCardResource.class);

    private static final String ENTITY_NAME = "idCard";

    private final IdCardService idCardService;

    public IdCardResource(IdCardService idCardService) {
        this.idCardService = idCardService;
    }

    /**
     * POST  /id-cards : Create a new idCard.
     *
     * @param idCardDTO the idCardDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new idCardDTO, or with status 400 (Bad Request) if the idCard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/id-cards")
    @Timed
    public ResponseEntity<IdCardDTO> createIdCard(@Valid @RequestBody IdCardDTO idCardDTO) throws URISyntaxException {
        log.debug("REST request to save IdCard : {}", idCardDTO);
        if (idCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new idCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdCardDTO result = idCardService.save(idCardDTO);
        return ResponseEntity.created(new URI("/api/id-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /id-cards : Updates an existing idCard.
     *
     * @param idCardDTO the idCardDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated idCardDTO,
     * or with status 400 (Bad Request) if the idCardDTO is not valid,
     * or with status 500 (Internal Server Error) if the idCardDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/id-cards")
    @Timed
    public ResponseEntity<IdCardDTO> updateIdCard(@Valid @RequestBody IdCardDTO idCardDTO) throws URISyntaxException {
        log.debug("REST request to update IdCard : {}", idCardDTO);
        if (idCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IdCardDTO result = idCardService.save(idCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, idCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /id-cards : get all the idCards.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of idCards in body
     */
    @GetMapping("/id-cards")
    @Timed
    public List<IdCardDTO> getAllIdCards() {
        log.debug("REST request to get all IdCards");
        return idCardService.findAll();
    }

    /**
     * GET  /id-cards/:id : get the "id" idCard.
     *
     * @param id the id of the idCardDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the idCardDTO, or with status 404 (Not Found)
     */
    @GetMapping("/id-cards/{id}")
    @Timed
    public ResponseEntity<IdCardDTO> getIdCard(@PathVariable Long id) {
        log.debug("REST request to get IdCard : {}", id);
        Optional<IdCardDTO> idCardDTO = idCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(idCardDTO);
    }

    /**
     * DELETE  /id-cards/:id : delete the "id" idCard.
     *
     * @param id the id of the idCardDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/id-cards/{id}")
    @Timed
    public ResponseEntity<Void> deleteIdCard(@PathVariable Long id) {
        log.debug("REST request to delete IdCard : {}", id);
        idCardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/id-cards?query=:query : search for the idCard corresponding
     * to the query.
     *
     * @param query the query of the idCard search
     * @return the result of the search
     */
    @GetMapping("/_search/id-cards")
    @Timed
    public List<IdCardDTO> searchIdCards(@RequestParam String query) {
        log.debug("REST request to search IdCards for query {}", query);
        return idCardService.search(query);
    }

}
