package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.TypeOfGradingService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.TypeOfGradingDTO;
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
 * REST controller for managing TypeOfGrading.
 */
@RestController
@RequestMapping("/api")
public class TypeOfGradingResource {

    private final Logger log = LoggerFactory.getLogger(TypeOfGradingResource.class);

    private static final String ENTITY_NAME = "typeOfGrading";

    private final TypeOfGradingService typeOfGradingService;

    public TypeOfGradingResource(TypeOfGradingService typeOfGradingService) {
        this.typeOfGradingService = typeOfGradingService;
    }

    /**
     * POST  /type-of-gradings : Create a new typeOfGrading.
     *
     * @param typeOfGradingDTO the typeOfGradingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeOfGradingDTO, or with status 400 (Bad Request) if the typeOfGrading has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-of-gradings")
    @Timed
    public ResponseEntity<TypeOfGradingDTO> createTypeOfGrading(@Valid @RequestBody TypeOfGradingDTO typeOfGradingDTO) throws URISyntaxException {
        log.debug("REST request to save TypeOfGrading : {}", typeOfGradingDTO);
        if (typeOfGradingDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeOfGrading cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeOfGradingDTO result = typeOfGradingService.save(typeOfGradingDTO);
        return ResponseEntity.created(new URI("/api/type-of-gradings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-of-gradings : Updates an existing typeOfGrading.
     *
     * @param typeOfGradingDTO the typeOfGradingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeOfGradingDTO,
     * or with status 400 (Bad Request) if the typeOfGradingDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeOfGradingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-of-gradings")
    @Timed
    public ResponseEntity<TypeOfGradingDTO> updateTypeOfGrading(@Valid @RequestBody TypeOfGradingDTO typeOfGradingDTO) throws URISyntaxException {
        log.debug("REST request to update TypeOfGrading : {}", typeOfGradingDTO);
        if (typeOfGradingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeOfGradingDTO result = typeOfGradingService.save(typeOfGradingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeOfGradingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-of-gradings : get all the typeOfGradings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of typeOfGradings in body
     */
    @GetMapping("/type-of-gradings")
    @Timed
    public List<TypeOfGradingDTO> getAllTypeOfGradings() {
        log.debug("REST request to get all TypeOfGradings");
        return typeOfGradingService.findAll();
    }

    /**
     * GET  /type-of-gradings/:id : get the "id" typeOfGrading.
     *
     * @param id the id of the typeOfGradingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeOfGradingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/type-of-gradings/{id}")
    @Timed
    public ResponseEntity<TypeOfGradingDTO> getTypeOfGrading(@PathVariable Long id) {
        log.debug("REST request to get TypeOfGrading : {}", id);
        Optional<TypeOfGradingDTO> typeOfGradingDTO = typeOfGradingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeOfGradingDTO);
    }

    /**
     * DELETE  /type-of-gradings/:id : delete the "id" typeOfGrading.
     *
     * @param id the id of the typeOfGradingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-of-gradings/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeOfGrading(@PathVariable Long id) {
        log.debug("REST request to delete TypeOfGrading : {}", id);
        typeOfGradingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/type-of-gradings?query=:query : search for the typeOfGrading corresponding
     * to the query.
     *
     * @param query the query of the typeOfGrading search
     * @return the result of the search
     */
    @GetMapping("/_search/type-of-gradings")
    @Timed
    public List<TypeOfGradingDTO> searchTypeOfGradings(@RequestParam String query) {
        log.debug("REST request to search TypeOfGradings for query {}", query);
        return typeOfGradingService.search(query);
    }

}
