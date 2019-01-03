package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.AcademicDepartmentService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.AcademicDepartmentDTO;
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
 * REST controller for managing AcademicDepartment.
 */
@RestController
@RequestMapping("/api")
public class AcademicDepartmentResource {

    private final Logger log = LoggerFactory.getLogger(AcademicDepartmentResource.class);

    private static final String ENTITY_NAME = "academicDepartment";

    private final AcademicDepartmentService academicDepartmentService;

    public AcademicDepartmentResource(AcademicDepartmentService academicDepartmentService) {
        this.academicDepartmentService = academicDepartmentService;
    }

    /**
     * POST  /academic-departments : Create a new academicDepartment.
     *
     * @param academicDepartmentDTO the academicDepartmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new academicDepartmentDTO, or with status 400 (Bad Request) if the academicDepartment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/academic-departments")
    @Timed
    public ResponseEntity<AcademicDepartmentDTO> createAcademicDepartment(@Valid @RequestBody AcademicDepartmentDTO academicDepartmentDTO) throws URISyntaxException {
        log.debug("REST request to save AcademicDepartment : {}", academicDepartmentDTO);
        if (academicDepartmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new academicDepartment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcademicDepartmentDTO result = academicDepartmentService.save(academicDepartmentDTO);
        return ResponseEntity.created(new URI("/api/academic-departments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /academic-departments : Updates an existing academicDepartment.
     *
     * @param academicDepartmentDTO the academicDepartmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated academicDepartmentDTO,
     * or with status 400 (Bad Request) if the academicDepartmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the academicDepartmentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/academic-departments")
    @Timed
    public ResponseEntity<AcademicDepartmentDTO> updateAcademicDepartment(@Valid @RequestBody AcademicDepartmentDTO academicDepartmentDTO) throws URISyntaxException {
        log.debug("REST request to update AcademicDepartment : {}", academicDepartmentDTO);
        if (academicDepartmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcademicDepartmentDTO result = academicDepartmentService.save(academicDepartmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, academicDepartmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /academic-departments : get all the academicDepartments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of academicDepartments in body
     */
    @GetMapping("/academic-departments")
    @Timed
    public List<AcademicDepartmentDTO> getAllAcademicDepartments() {
        log.debug("REST request to get all AcademicDepartments");
        return academicDepartmentService.findAll();
    }

    /**
     * GET  /academic-departments/:id : get the "id" academicDepartment.
     *
     * @param id the id of the academicDepartmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the academicDepartmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/academic-departments/{id}")
    @Timed
    public ResponseEntity<AcademicDepartmentDTO> getAcademicDepartment(@PathVariable Long id) {
        log.debug("REST request to get AcademicDepartment : {}", id);
        Optional<AcademicDepartmentDTO> academicDepartmentDTO = academicDepartmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(academicDepartmentDTO);
    }

    /**
     * DELETE  /academic-departments/:id : delete the "id" academicDepartment.
     *
     * @param id the id of the academicDepartmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/academic-departments/{id}")
    @Timed
    public ResponseEntity<Void> deleteAcademicDepartment(@PathVariable Long id) {
        log.debug("REST request to delete AcademicDepartment : {}", id);
        academicDepartmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/academic-departments?query=:query : search for the academicDepartment corresponding
     * to the query.
     *
     * @param query the query of the academicDepartment search
     * @return the result of the search
     */
    @GetMapping("/_search/academic-departments")
    @Timed
    public List<AcademicDepartmentDTO> searchAcademicDepartments(@RequestParam String query) {
        log.debug("REST request to search AcademicDepartments for query {}", query);
        return academicDepartmentService.search(query);
    }

}
