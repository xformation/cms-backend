package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.AdmissionEnquiryService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.AdmissionEnquiryDTO;
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
 * REST controller for managing AdmissionEnquiry.
 */
@RestController
@RequestMapping("/api")
public class AdmissionEnquiryResource {

    private final Logger log = LoggerFactory.getLogger(AdmissionEnquiryResource.class);

    private static final String ENTITY_NAME = "admissionEnquiry";

    private final AdmissionEnquiryService admissionEnquiryService;

    public AdmissionEnquiryResource(AdmissionEnquiryService admissionEnquiryService) {
        this.admissionEnquiryService = admissionEnquiryService;
    }

    /**
     * POST  /admission-enquiries : Create a new admissionEnquiry.
     *
     * @param admissionEnquiryDTO the admissionEnquiryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new admissionEnquiryDTO, or with status 400 (Bad Request) if the admissionEnquiry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/admission-enquiries")
    @Timed
    public ResponseEntity<AdmissionEnquiryDTO> createAdmissionEnquiry(@Valid @RequestBody AdmissionEnquiryDTO admissionEnquiryDTO) throws URISyntaxException {
        log.debug("REST request to save AdmissionEnquiry : {}", admissionEnquiryDTO);
        if (admissionEnquiryDTO.getId() != null) {
            throw new BadRequestAlertException("A new admissionEnquiry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmissionEnquiryDTO result = admissionEnquiryService.save(admissionEnquiryDTO);
        return ResponseEntity.created(new URI("/api/admission-enquiries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /admission-enquiries : Updates an existing admissionEnquiry.
     *
     * @param admissionEnquiryDTO the admissionEnquiryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated admissionEnquiryDTO,
     * or with status 400 (Bad Request) if the admissionEnquiryDTO is not valid,
     * or with status 500 (Internal Server Error) if the admissionEnquiryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/admission-enquiries")
    @Timed
    public ResponseEntity<AdmissionEnquiryDTO> updateAdmissionEnquiry(@Valid @RequestBody AdmissionEnquiryDTO admissionEnquiryDTO) throws URISyntaxException {
        log.debug("REST request to update AdmissionEnquiry : {}", admissionEnquiryDTO);
        if (admissionEnquiryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdmissionEnquiryDTO result = admissionEnquiryService.save(admissionEnquiryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, admissionEnquiryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /admission-enquiries : get all the admissionEnquiries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of admissionEnquiries in body
     */
    @GetMapping("/admission-enquiries")
    @Timed
    public List<AdmissionEnquiryDTO> getAllAdmissionEnquiries() {
        log.debug("REST request to get all AdmissionEnquiries");
        return admissionEnquiryService.findAll();
    }

    /**
     * GET  /admission-enquiries/:id : get the "id" admissionEnquiry.
     *
     * @param id the id of the admissionEnquiryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the admissionEnquiryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/admission-enquiries/{id}")
    @Timed
    public ResponseEntity<AdmissionEnquiryDTO> getAdmissionEnquiry(@PathVariable Long id) {
        log.debug("REST request to get AdmissionEnquiry : {}", id);
        Optional<AdmissionEnquiryDTO> admissionEnquiryDTO = admissionEnquiryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admissionEnquiryDTO);
    }

    /**
     * DELETE  /admission-enquiries/:id : delete the "id" admissionEnquiry.
     *
     * @param id the id of the admissionEnquiryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/admission-enquiries/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdmissionEnquiry(@PathVariable Long id) {
        log.debug("REST request to delete AdmissionEnquiry : {}", id);
        admissionEnquiryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/admission-enquiries?query=:query : search for the admissionEnquiry corresponding
     * to the query.
     *
     * @param query the query of the admissionEnquiry search
     * @return the result of the search
     */
    @GetMapping("/_search/admission-enquiries")
    @Timed
    public List<AdmissionEnquiryDTO> searchAdmissionEnquiries(@RequestParam String query) {
        log.debug("REST request to search AdmissionEnquiries for query {}", query);
        return admissionEnquiryService.search(query);
    }

}
