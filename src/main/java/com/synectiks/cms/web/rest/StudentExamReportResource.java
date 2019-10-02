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

import com.synectiks.cms.service.StudentExamReportService;
import com.synectiks.cms.service.dto.StudentExamReportDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing StudentExamReport.
 */
@RestController
@RequestMapping("/api")
public class StudentExamReportResource {

    private final Logger log = LoggerFactory.getLogger(StudentExamReportResource.class);

    private static final String ENTITY_NAME = "studentExamReport";

    private final StudentExamReportService studentExamReportService;

    public StudentExamReportResource(StudentExamReportService studentExamReportService) {
        this.studentExamReportService = studentExamReportService;
    }

    /**
     * POST  /student-exam-reports : Create a new studentExamReport.
     *
     * @param studentExamReportDTO the studentExamReportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studentExamReportDTO, or with status 400 (Bad Request) if the studentExamReport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/student-exam-reports")
    public ResponseEntity<StudentExamReportDTO> createStudentExamReport(@Valid @RequestBody StudentExamReportDTO studentExamReportDTO) throws URISyntaxException {
        log.debug("REST request to save StudentExamReport : {}", studentExamReportDTO);
        if (studentExamReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new studentExamReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentExamReportDTO result = studentExamReportService.save(studentExamReportDTO);
        return ResponseEntity.created(new URI("/api/student-exam-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /student-exam-reports : Updates an existing studentExamReport.
     *
     * @param studentExamReportDTO the studentExamReportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentExamReportDTO,
     * or with status 400 (Bad Request) if the studentExamReportDTO is not valid,
     * or with status 500 (Internal Server Error) if the studentExamReportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/student-exam-reports")
    public ResponseEntity<StudentExamReportDTO> updateStudentExamReport(@Valid @RequestBody StudentExamReportDTO studentExamReportDTO) throws URISyntaxException {
        log.debug("REST request to update StudentExamReport : {}", studentExamReportDTO);
        if (studentExamReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentExamReportDTO result = studentExamReportService.save(studentExamReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentExamReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /student-exam-reports : get all the studentExamReports.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of studentExamReports in body
     */
    @GetMapping("/student-exam-reports")
    public List<StudentExamReportDTO> getAllStudentExamReports() {
        log.debug("REST request to get all StudentExamReports");
        return studentExamReportService.findAll();
    }

    /**
     * GET  /student-exam-reports/:id : get the "id" studentExamReport.
     *
     * @param id the id of the studentExamReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentExamReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/student-exam-reports/{id}")
    public ResponseEntity<StudentExamReportDTO> getStudentExamReport(@PathVariable Long id) {
        log.debug("REST request to get StudentExamReport : {}", id);
        Optional<StudentExamReportDTO> studentExamReportDTO = studentExamReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentExamReportDTO);
    }

    /**
     * DELETE  /student-exam-reports/:id : delete the "id" studentExamReport.
     *
     * @param id the id of the studentExamReportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/student-exam-reports/{id}")
    public ResponseEntity<Void> deleteStudentExamReport(@PathVariable Long id) {
        log.debug("REST request to delete StudentExamReport : {}", id);
        studentExamReportService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/student-exam-reports?query=:query : search for the studentExamReport corresponding
     * to the query.
     *
     * @param query the query of the studentExamReport search
     * @return the result of the search
     */
    @GetMapping("/_search/student-exam-reports")
    public List<StudentExamReportDTO> searchStudentExamReports(@RequestParam String query) {
        log.debug("REST request to search StudentExamReports for query {}", query);
        return studentExamReportService.search(query);
    }

}
