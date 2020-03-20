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

import com.synectiks.cms.service.AcademicExamSettingService;
import com.synectiks.cms.service.dto.AcademicExamSettingDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing AcademicExamSetting.
 */
@RestController
@RequestMapping("/api")
public class AcademicExamSettingResource {

    private final Logger log = LoggerFactory.getLogger(AcademicExamSettingResource.class);

    private static final String ENTITY_NAME = "academicExamSetting";

    private final AcademicExamSettingService academicExamSettingService;

    public AcademicExamSettingResource(AcademicExamSettingService academicExamSettingService) {
        this.academicExamSettingService = academicExamSettingService;
    }

    /**
     * POST  /academic-exam-settings : Create a new academicExamSetting.
     *
     * @param academicExamSettingDTO the academicExamSettingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new academicExamSettingDTO, or with status 400 (Bad Request) if the academicExamSetting has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/academic-exam-settings")
    public ResponseEntity<AcademicExamSettingDTO> createAcademicExamSetting(@Valid @RequestBody AcademicExamSettingDTO academicExamSettingDTO) throws URISyntaxException {
        log.debug("REST request to save AcademicExamSetting : {}", academicExamSettingDTO);
        if (academicExamSettingDTO.getId() != null) {
            throw new BadRequestAlertException("A new academicExamSetting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcademicExamSettingDTO result = academicExamSettingService.save(academicExamSettingDTO);
        return ResponseEntity.created(new URI("/api/academic-exam-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /academic-exam-settings : Updates an existing academicExamSetting.
     *
     * @param academicExamSettingDTO the academicExamSettingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated academicExamSettingDTO,
     * or with status 400 (Bad Request) if the academicExamSettingDTO is not valid,
     * or with status 500 (Internal Server Error) if the academicExamSettingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/academic-exam-settings")
    public ResponseEntity<AcademicExamSettingDTO> updateAcademicExamSetting(@Valid @RequestBody AcademicExamSettingDTO academicExamSettingDTO) throws URISyntaxException {
        log.debug("REST request to update AcademicExamSetting : {}", academicExamSettingDTO);
        if (academicExamSettingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcademicExamSettingDTO result = academicExamSettingService.save(academicExamSettingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, academicExamSettingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /academic-exam-settings : get all the academicExamSettings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of academicExamSettings in body
     */
    @GetMapping("/academic-exam-settings")
    public List<AcademicExamSettingDTO> getAllAcademicExamSettings() {
        log.debug("REST request to get all AcademicExamSettings");
        return academicExamSettingService.findAll();
    }

    /**
     * GET  /academic-exam-settings/:id : get the "id" academicExamSetting.
     *
     * @param id the id of the academicExamSettingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the academicExamSettingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/academic-exam-settings/{id}")
    public ResponseEntity<AcademicExamSettingDTO> getAcademicExamSetting(@PathVariable Long id) {
        log.debug("REST request to get AcademicExamSetting : {}", id);
        Optional<AcademicExamSettingDTO> academicExamSettingDTO = academicExamSettingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(academicExamSettingDTO);
    }

    /**
     * DELETE  /academic-exam-settings/:id : delete the "id" academicExamSetting.
     *
     * @param id the id of the academicExamSettingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/academic-exam-settings/{id}")
    public ResponseEntity<Void> deleteAcademicExamSetting(@PathVariable Long id) {
        log.debug("REST request to delete AcademicExamSetting : {}", id);
        academicExamSettingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/academic-exam-settings?query=:query : search for the academicExamSetting corresponding
     * to the query.
     *
     * @param query the query of the academicExamSetting search
     * @return the result of the search
     */
    @GetMapping("/_search/academic-exam-settings")
    public List<AcademicExamSettingDTO> searchAcademicExamSettings(@RequestParam String query) {
        log.debug("REST request to search AcademicExamSettings for query {}", query);
        return academicExamSettingService.search(query);
    }

}
