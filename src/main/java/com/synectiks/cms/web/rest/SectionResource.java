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

import com.synectiks.cms.service.SectionService;
import com.synectiks.cms.service.dto.SectionDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Section.
 */
@RestController
@RequestMapping("/api")
public class SectionResource {

    private final Logger log = LoggerFactory.getLogger(SectionResource.class);

    private static final String ENTITY_NAME = "section";

    private final SectionService sectionService;

    public SectionResource(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     * POST  /sections : Create a new section.
     *
     * @param sectionDTO the sectionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sectionDTO, or with status 400 (Bad Request) if the section has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sections")
    public ResponseEntity<SectionDTO> createSection(@Valid @RequestBody SectionDTO sectionDTO) throws URISyntaxException {
        log.debug("REST request to save Section : {}", sectionDTO);
        if (sectionDTO.getId() != null) {
            throw new BadRequestAlertException("A new section cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SectionDTO result = sectionService.save(sectionDTO);
        return ResponseEntity.created(new URI("/api/sections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sections : Updates an existing section.
     *
     * @param sectionDTO the sectionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sectionDTO,
     * or with status 400 (Bad Request) if the sectionDTO is not valid,
     * or with status 500 (Internal Server Error) if the sectionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sections")
    public ResponseEntity<SectionDTO> updateSection(@Valid @RequestBody SectionDTO sectionDTO) throws URISyntaxException {
        log.debug("REST request to update Section : {}", sectionDTO);
        if (sectionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SectionDTO result = sectionService.save(sectionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sectionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sections : get all the sections.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sections in body
     */
    @GetMapping("/sections")
    public List<SectionDTO> getAllSections() {
        log.debug("REST request to get all Sections");
        return sectionService.findAll();
    }

    /**
     * GET  /sections/:id : get the "id" section.
     *
     * @param id the id of the sectionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sectionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sections/{id}")
    public ResponseEntity<SectionDTO> getSection(@PathVariable Long id) {
        log.debug("REST request to get Section : {}", id);
        Optional<SectionDTO> sectionDTO = sectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sectionDTO);
    }

    /**
     * DELETE  /sections/:id : delete the "id" section.
     *
     * @param id the id of the sectionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sections/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        log.debug("REST request to delete Section : {}", id);
        sectionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sections?query=:query : search for the section corresponding
     * to the query.
     *
     * @param query the query of the section search
     * @return the result of the search
     */
    @GetMapping("/_search/sections")
    public List<SectionDTO> searchSections(@RequestParam String query) {
        log.debug("REST request to search Sections for query {}", query);
        return sectionService.search(query);
    }

}
