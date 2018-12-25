package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.DepartmentsService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.DepartmentsDTO;
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
 * REST controller for managing Departments.
 */
@RestController
@RequestMapping("/api")
public class DepartmentsResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentsResource.class);

    private static final String ENTITY_NAME = "departments";

    private final DepartmentsService departmentsService;

    public DepartmentsResource(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    /**
     * POST  /departments : Create a new departments.
     *
     * @param departmentsDTO the departmentsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new departmentsDTO, or with status 400 (Bad Request) if the departments has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/departments")
    @Timed
    public ResponseEntity<DepartmentsDTO> createDepartments(@Valid @RequestBody DepartmentsDTO departmentsDTO) throws URISyntaxException {
        log.debug("REST request to save Departments : {}", departmentsDTO);
        if (departmentsDTO.getId() != null) {
            throw new BadRequestAlertException("A new departments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepartmentsDTO result = departmentsService.save(departmentsDTO);
        return ResponseEntity.created(new URI("/api/departments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /departments : Updates an existing departments.
     *
     * @param departmentsDTO the departmentsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated departmentsDTO,
     * or with status 400 (Bad Request) if the departmentsDTO is not valid,
     * or with status 500 (Internal Server Error) if the departmentsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/departments")
    @Timed
    public ResponseEntity<DepartmentsDTO> updateDepartments(@Valid @RequestBody DepartmentsDTO departmentsDTO) throws URISyntaxException {
        log.debug("REST request to update Departments : {}", departmentsDTO);
        if (departmentsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepartmentsDTO result = departmentsService.save(departmentsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, departmentsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /departments : get all the departments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of departments in body
     */
    @GetMapping("/departments")
    @Timed
    public List<DepartmentsDTO> getAllDepartments() {
        log.debug("REST request to get all Departments");
        return departmentsService.findAll();
    }

    /**
     * GET  /departments/:id : get the "id" departments.
     *
     * @param id the id of the departmentsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the departmentsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/departments/{id}")
    @Timed
    public ResponseEntity<DepartmentsDTO> getDepartments(@PathVariable Long id) {
        log.debug("REST request to get Departments : {}", id);
        Optional<DepartmentsDTO> departmentsDTO = departmentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(departmentsDTO);
    }

    /**
     * DELETE  /departments/:id : delete the "id" departments.
     *
     * @param id the id of the departmentsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/departments/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepartments(@PathVariable Long id) {
        log.debug("REST request to delete Departments : {}", id);
        departmentsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/departments?query=:query : search for the departments corresponding
     * to the query.
     *
     * @param query the query of the departments search
     * @return the result of the search
     */
    @GetMapping("/_search/departments")
    @Timed
    public List<DepartmentsDTO> searchDepartments(@RequestParam String query) {
        log.debug("REST request to search Departments for query {}", query);
        return departmentsService.search(query);
    }

}
