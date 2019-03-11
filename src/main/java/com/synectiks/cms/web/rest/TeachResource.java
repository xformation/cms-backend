package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.TeachService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.TeachDTO;
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
 * REST controller for managing Teach.
 */
@RestController
@RequestMapping("/api")
public class TeachResource {

    private final Logger log = LoggerFactory.getLogger(TeachResource.class);

    private static final String ENTITY_NAME = "teach";

    private final TeachService teachService;

    public TeachResource(TeachService teachService) {
        this.teachService = teachService;
    }

    /**
     * POST  /teaches : Create a new teach.
     *
     * @param teachDTO the teachDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teachDTO, or with status 400 (Bad Request) if the teach has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teaches")
    @Timed
    public ResponseEntity<TeachDTO> createTeach(@RequestBody TeachDTO teachDTO) throws URISyntaxException {
        log.debug("REST request to save Teach : {}", teachDTO);
        if (teachDTO.getId() != null) {
            throw new BadRequestAlertException("A new teach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TeachDTO result = teachService.save(teachDTO);
        return ResponseEntity.created(new URI("/api/teaches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teaches : Updates an existing teach.
     *
     * @param teachDTO the teachDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teachDTO,
     * or with status 400 (Bad Request) if the teachDTO is not valid,
     * or with status 500 (Internal Server Error) if the teachDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teaches")
    @Timed
    public ResponseEntity<TeachDTO> updateTeach(@RequestBody TeachDTO teachDTO) throws URISyntaxException {
        log.debug("REST request to update Teach : {}", teachDTO);
        if (teachDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeachDTO result = teachService.save(teachDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teachDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teaches : get all the teaches.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of teaches in body
     */
    @GetMapping("/teaches")
    @Timed
    public List<TeachDTO> getAllTeaches() {
        log.debug("REST request to get all Teaches");
        return teachService.findAll();
    }

    /**
     * GET  /teaches/:id : get the "id" teach.
     *
     * @param id the id of the teachDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teachDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teaches/{id}")
    @Timed
    public ResponseEntity<TeachDTO> getTeach(@PathVariable Long id) {
        log.debug("REST request to get Teach : {}", id);
        Optional<TeachDTO> teachDTO = teachService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teachDTO);
    }

    /**
     * DELETE  /teaches/:id : delete the "id" teach.
     *
     * @param id the id of the teachDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teaches/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeach(@PathVariable Long id) {
        log.debug("REST request to delete Teach : {}", id);
        teachService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/teaches?query=:query : search for the teach corresponding
     * to the query.
     *
     * @param query the query of the teach search
     * @return the result of the search
     */
    @GetMapping("/_search/teaches")
    @Timed
    public List<TeachDTO> searchTeaches(@RequestParam String query) {
        log.debug("REST request to search Teaches for query {}", query);
        return teachService.search(query);
    }

}
