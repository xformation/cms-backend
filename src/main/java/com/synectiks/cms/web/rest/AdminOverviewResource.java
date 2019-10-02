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

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.AdminOverviewService;
import com.synectiks.cms.service.dto.AdminOverviewDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing AdminOverview.
 */
@RestController
@RequestMapping("/api")
public class AdminOverviewResource {

    private final Logger log = LoggerFactory.getLogger(AdminOverviewResource.class);

    private static final String ENTITY_NAME = "adminOverview";

    private final AdminOverviewService adminOverviewService;

    public AdminOverviewResource(AdminOverviewService adminOverviewService) {
        this.adminOverviewService = adminOverviewService;
    }

    /**
     * POST  /admin-overviews : Create a new adminOverview.
     *
     * @param adminOverviewDTO the adminOverviewDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adminOverviewDTO, or with status 400 (Bad Request) if the adminOverview has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/admin-overviews")
    @Timed
    public ResponseEntity<AdminOverviewDTO> createAdminOverview(@Valid @RequestBody AdminOverviewDTO adminOverviewDTO) throws URISyntaxException {
        log.debug("REST request to save AdminOverview : {}", adminOverviewDTO);
        if (adminOverviewDTO.getId() != null) {
            throw new BadRequestAlertException("A new adminOverview cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminOverviewDTO result = adminOverviewService.save(adminOverviewDTO);
        return ResponseEntity.created(new URI("/api/admin-overviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /admin-overviews : Updates an existing adminOverview.
     *
     * @param adminOverviewDTO the adminOverviewDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adminOverviewDTO,
     * or with status 400 (Bad Request) if the adminOverviewDTO is not valid,
     * or with status 500 (Internal Server Error) if the adminOverviewDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/admin-overviews")
    @Timed
    public ResponseEntity<AdminOverviewDTO> updateAdminOverview(@Valid @RequestBody AdminOverviewDTO adminOverviewDTO) throws URISyntaxException {
        log.debug("REST request to update AdminOverview : {}", adminOverviewDTO);
        if (adminOverviewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminOverviewDTO result = adminOverviewService.save(adminOverviewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adminOverviewDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /admin-overviews : get all the adminOverviews.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of adminOverviews in body
     */
    @GetMapping("/admin-overviews")
    @Timed
    public List<AdminOverviewDTO> getAllAdminOverviews() {
        log.debug("REST request to get all AdminOverviews");
        return adminOverviewService.findAll();
    }

    /**
     * GET  /admin-overviews/:id : get the "id" adminOverview.
     *
     * @param id the id of the adminOverviewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adminOverviewDTO, or with status 404 (Not Found)
     */
    @GetMapping("/admin-overviews/{id}")
    @Timed
    public ResponseEntity<AdminOverviewDTO> getAdminOverview(@PathVariable Long id) {
        log.debug("REST request to get AdminOverview : {}", id);
        Optional<AdminOverviewDTO> adminOverviewDTO = adminOverviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adminOverviewDTO);
    }

    /**
     * DELETE  /admin-overviews/:id : delete the "id" adminOverview.
     *
     * @param id the id of the adminOverviewDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/admin-overviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdminOverview(@PathVariable Long id) {
        log.debug("REST request to delete AdminOverview : {}", id);
        adminOverviewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/admin-overviews?query=:query : search for the adminOverview corresponding
     * to the query.
     *
     * @param query the query of the adminOverview search
     * @return the result of the search
     */
    @GetMapping("/_search/admin-overviews")
    @Timed
    public List<AdminOverviewDTO> searchAdminOverviews(@RequestParam String query) {
        log.debug("REST request to search AdminOverviews for query {}", query);
        return adminOverviewService.search(query);
    }

}
