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

import com.synectiks.cms.service.LectureService;
import com.synectiks.cms.service.dto.LectureDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Lecture.
 */
@RestController
@RequestMapping("/api")
public class LectureResource {

    private final Logger log = LoggerFactory.getLogger(LectureResource.class);

    private static final String ENTITY_NAME = "lecture";

    private final LectureService lectureService;

    public LectureResource(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    /**
     * POST  /lectures : Create a new lecture.
     *
     * @param lectureDTO the lectureDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lectureDTO, or with status 400 (Bad Request) if the lecture has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lectures")
    public ResponseEntity<LectureDTO> createLecture(@Valid @RequestBody LectureDTO lectureDTO) throws URISyntaxException {
        log.debug("REST request to save Lecture : {}", lectureDTO);
        if (lectureDTO.getId() != null) {
            throw new BadRequestAlertException("A new lecture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LectureDTO result = lectureService.save(lectureDTO);
        return ResponseEntity.created(new URI("/api/lectures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lectures : Updates an existing lecture.
     *
     * @param lectureDTO the lectureDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated lectureDTO,
     * or with status 400 (Bad Request) if the lectureDTO is not valid,
     * or with status 500 (Internal Server Error) if the lectureDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lectures")
    public ResponseEntity<LectureDTO> updateLecture(@Valid @RequestBody LectureDTO lectureDTO) throws URISyntaxException {
        log.debug("REST request to update Lecture : {}", lectureDTO);
        if (lectureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LectureDTO result = lectureService.save(lectureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, lectureDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lectures : get all the lectures.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of lectures in body
     */
    @GetMapping("/lectures")
    public List<LectureDTO> getAllLectures() {
        log.debug("REST request to get all Lectures");
        return lectureService.findAll();
    }

    /**
     * GET  /lectures/:id : get the "id" lecture.
     *
     * @param id the id of the lectureDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lectureDTO, or with status 404 (Not Found)
     */
    @GetMapping("/lectures/{id}")
    public ResponseEntity<LectureDTO> getLecture(@PathVariable Long id) {
        log.debug("REST request to get Lecture : {}", id);
        Optional<LectureDTO> lectureDTO = lectureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lectureDTO);
    }

    /**
     * DELETE  /lectures/:id : delete the "id" lecture.
     *
     * @param id the id of the lectureDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lectures/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long id) {
        log.debug("REST request to delete Lecture : {}", id);
        lectureService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/lectures?query=:query : search for the lecture corresponding
     * to the query.
     *
     * @param query the query of the lecture search
     * @return the result of the search
     */
    @GetMapping("/_search/lectures")
    public List<LectureDTO> searchLectures(@RequestParam String query) {
        log.debug("REST request to search Lectures for query {}", query);
        return lectureService.search(query);
    }

}
