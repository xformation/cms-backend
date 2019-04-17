package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.AdminAttendanceService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.AdminAttendanceDTO;
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
 * REST controller for managing AdminAttendance.
 */
@RestController
@RequestMapping("/api")
public class AdminAttendanceResource {

    private final Logger log = LoggerFactory.getLogger(AdminAttendanceResource.class);

    private static final String ENTITY_NAME = "adminAttendance";

    private final AdminAttendanceService adminAttendanceService;

    public AdminAttendanceResource(AdminAttendanceService adminAttendanceService) {
        this.adminAttendanceService = adminAttendanceService;
    }

    /**
     * POST  /admin-attendances : Create a new adminAttendance.
     *
     * @param adminAttendanceDTO the adminAttendanceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adminAttendanceDTO, or with status 400 (Bad Request) if the adminAttendance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/admin-attendances")
    public ResponseEntity<AdminAttendanceDTO> createAdminAttendance(@RequestBody AdminAttendanceDTO adminAttendanceDTO) throws URISyntaxException {
        log.debug("REST request to save AdminAttendance : {}", adminAttendanceDTO);
        if (adminAttendanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new adminAttendance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminAttendanceDTO result = adminAttendanceService.save(adminAttendanceDTO);
        return ResponseEntity.created(new URI("/api/admin-attendances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /admin-attendances : Updates an existing adminAttendance.
     *
     * @param adminAttendanceDTO the adminAttendanceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adminAttendanceDTO,
     * or with status 400 (Bad Request) if the adminAttendanceDTO is not valid,
     * or with status 500 (Internal Server Error) if the adminAttendanceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/admin-attendances")
    public ResponseEntity<AdminAttendanceDTO> updateAdminAttendance(@RequestBody AdminAttendanceDTO adminAttendanceDTO) throws URISyntaxException {
        log.debug("REST request to update AdminAttendance : {}", adminAttendanceDTO);
        if (adminAttendanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminAttendanceDTO result = adminAttendanceService.save(adminAttendanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adminAttendanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /admin-attendances : get all the adminAttendances.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of adminAttendances in body
     */
    @GetMapping("/admin-attendances")
    public List<AdminAttendanceDTO> getAllAdminAttendances() {
        log.debug("REST request to get all AdminAttendances");
        return adminAttendanceService.findAll();
    }

    /**
     * GET  /admin-attendances/:id : get the "id" adminAttendance.
     *
     * @param id the id of the adminAttendanceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adminAttendanceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/admin-attendances/{id}")
    public ResponseEntity<AdminAttendanceDTO> getAdminAttendance(@PathVariable Long id) {
        log.debug("REST request to get AdminAttendance : {}", id);
        Optional<AdminAttendanceDTO> adminAttendanceDTO = adminAttendanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adminAttendanceDTO);
    }

    /**
     * DELETE  /admin-attendances/:id : delete the "id" adminAttendance.
     *
     * @param id the id of the adminAttendanceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/admin-attendances/{id}")
    public ResponseEntity<Void> deleteAdminAttendance(@PathVariable Long id) {
        log.debug("REST request to delete AdminAttendance : {}", id);
        adminAttendanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/admin-attendances?query=:query : search for the adminAttendance corresponding
     * to the query.
     *
     * @param query the query of the adminAttendance search
     * @return the result of the search
     */
    @GetMapping("/_search/admin-attendances")
    public List<AdminAttendanceDTO> searchAdminAttendances(@RequestParam String query) {
        log.debug("REST request to search AdminAttendances for query {}", query);
        return adminAttendanceService.search(query);
    }

}
