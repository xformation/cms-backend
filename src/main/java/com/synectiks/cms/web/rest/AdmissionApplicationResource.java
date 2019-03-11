package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.AdmissionApplicationService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.AdmissionApplicationDTO;
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
 * REST controller for managing AdmissionApplication.
 */
@RestController
@RequestMapping("/api")
public class AdmissionApplicationResource {

    private final Logger log = LoggerFactory.getLogger(AdmissionApplicationResource.class);

    private static final String ENTITY_NAME = "admissionApplication";

    private final AdmissionApplicationService admissionApplicationService;

    public AdmissionApplicationResource(AdmissionApplicationService admissionApplicationService) {
        this.admissionApplicationService = admissionApplicationService;
    }

    /**
     * POST  /admission-applications : Create a new admissionApplication.
     *
     * @param admissionApplicationDTO the admissionApplicationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new admissionApplicationDTO, or with status 400 (Bad Request) if the admissionApplication has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/admission-applications")
    public ResponseEntity<AdmissionApplicationDTO> createAdmissionApplication(@Valid @RequestBody AdmissionApplicationDTO admissionApplicationDTO) throws URISyntaxException {
        log.debug("REST request to save AdmissionApplication : {}", admissionApplicationDTO);
        if (admissionApplicationDTO.getId() != null) {
            throw new BadRequestAlertException("A new admissionApplication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmissionApplicationDTO result = admissionApplicationService.save(admissionApplicationDTO);
        return ResponseEntity.created(new URI("/api/admission-applications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /admission-applications : Updates an existing admissionApplication.
     *
     * @param admissionApplicationDTO the admissionApplicationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated admissionApplicationDTO,
     * or with status 400 (Bad Request) if the admissionApplicationDTO is not valid,
     * or with status 500 (Internal Server Error) if the admissionApplicationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/admission-applications")
    public ResponseEntity<AdmissionApplicationDTO> updateAdmissionApplication(@Valid @RequestBody AdmissionApplicationDTO admissionApplicationDTO) throws URISyntaxException {
        log.debug("REST request to update AdmissionApplication : {}", admissionApplicationDTO);
        if (admissionApplicationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdmissionApplicationDTO result = admissionApplicationService.save(admissionApplicationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, admissionApplicationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /admission-applications : get all the admissionApplications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of admissionApplications in body
     */
    @GetMapping("/admission-applications")
    public List<AdmissionApplicationDTO> getAllAdmissionApplications() {
        log.debug("REST request to get all AdmissionApplications");
        return admissionApplicationService.findAll();
    }

    /**
     * GET  /admission-applications/:id : get the "id" admissionApplication.
     *
     * @param id the id of the admissionApplicationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the admissionApplicationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/admission-applications/{id}")
    public ResponseEntity<AdmissionApplicationDTO> getAdmissionApplication(@PathVariable Long id) {
        log.debug("REST request to get AdmissionApplication : {}", id);
        Optional<AdmissionApplicationDTO> admissionApplicationDTO = admissionApplicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admissionApplicationDTO);
    }

    /**
     * DELETE  /admission-applications/:id : delete the "id" admissionApplication.
     *
     * @param id the id of the admissionApplicationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/admission-applications/{id}")
    public ResponseEntity<Void> deleteAdmissionApplication(@PathVariable Long id) {
        log.debug("REST request to delete AdmissionApplication : {}", id);
        admissionApplicationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/admission-applications?query=:query : search for the admissionApplication corresponding
     * to the query.
     *
     * @param query the query of the admissionApplication search
     * @return the result of the search
     */
    @GetMapping("/_search/admission-applications")
    public List<AdmissionApplicationDTO> searchAdmissionApplications(@RequestParam String query) {
        log.debug("REST request to search AdmissionApplications for query {}", query);
        return admissionApplicationService.search(query);
    }

}
