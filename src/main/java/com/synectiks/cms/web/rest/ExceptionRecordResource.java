package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

import com.synectiks.cms.service.ExceptionRecordService;
import com.synectiks.cms.service.dto.ExceptionRecordDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.commons.entities.cms.ExceptionRecord}.
 */
@RestController
@RequestMapping("/api")
public class ExceptionRecordResource {

    private final Logger log = LoggerFactory.getLogger(ExceptionRecordResource.class);

    private static final String ENTITY_NAME = "exceptionRecord";

    private final ExceptionRecordService exceptionRecordService;

    public ExceptionRecordResource(ExceptionRecordService exceptionRecordService) {
        this.exceptionRecordService = exceptionRecordService;
    }

    /**
     * {@code POST  /exception-records} : Create a new exceptionRecord.
     *
     * @param exceptionRecordDTO the exceptionRecordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exceptionRecordDTO, or with status {@code 400 (Bad Request)} if the exceptionRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/exception-records")
    public ResponseEntity<ExceptionRecordDTO> createExceptionRecord(@RequestBody ExceptionRecordDTO exceptionRecordDTO) throws URISyntaxException {
        log.debug("REST request to save ExceptionRecord : {}", exceptionRecordDTO);
        if (exceptionRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new exceptionRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExceptionRecordDTO result = exceptionRecordService.save(exceptionRecordDTO);
        return ResponseEntity.created(new URI("/api/exception-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /exception-records} : Updates an existing exceptionRecord.
     *
     * @param exceptionRecordDTO the exceptionRecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exceptionRecordDTO,
     * or with status {@code 400 (Bad Request)} if the exceptionRecordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exceptionRecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exception-records")
    public ResponseEntity<ExceptionRecordDTO> updateExceptionRecord(@RequestBody ExceptionRecordDTO exceptionRecordDTO) throws URISyntaxException {
        log.debug("REST request to update ExceptionRecord : {}", exceptionRecordDTO);
        if (exceptionRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExceptionRecordDTO result = exceptionRecordService.save(exceptionRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, exceptionRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /exception-records} : get all the exceptionRecords.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exceptionRecords in body.
     */
    @GetMapping("/exception-records")
    public List<ExceptionRecordDTO> getAllExceptionRecords() {
        log.debug("REST request to get all ExceptionRecords");
        return exceptionRecordService.findAll();
    }

    /**
     * {@code GET  /exception-records/:id} : get the "id" exceptionRecord.
     *
     * @param id the id of the exceptionRecordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exceptionRecordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exception-records/{id}")
    public ResponseEntity<ExceptionRecordDTO> getExceptionRecord(@PathVariable Long id) {
        log.debug("REST request to get ExceptionRecord : {}", id);
        Optional<ExceptionRecordDTO> exceptionRecordDTO = exceptionRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exceptionRecordDTO);
    }

    /**
     * {@code DELETE  /exception-records/:id} : delete the "id" exceptionRecord.
     *
     * @param id the id of the exceptionRecordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exception-records/{id}")
    public ResponseEntity<Void> deleteExceptionRecord(@PathVariable Long id) {
        log.debug("REST request to delete ExceptionRecord : {}", id);
        exceptionRecordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/exception-records?query=:query} : search for the exceptionRecord corresponding
     * to the query.
     *
     * @param query the query of the exceptionRecord search.
     * @return the result of the search.
     */
    @GetMapping("/_search/exception-records")
    public List<ExceptionRecordDTO> searchExceptionRecords(@RequestParam String query) {
        log.debug("REST request to search ExceptionRecords for query {}", query);
        return exceptionRecordService.search(query);
    }

}
