package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.CourseOfferService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.CourseOfferDTO;
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
 * REST controller for managing CourseOffer.
 */
@RestController
@RequestMapping("/api")
public class CourseOfferResource {

    private final Logger log = LoggerFactory.getLogger(CourseOfferResource.class);

    private static final String ENTITY_NAME = "courseOffer";

    private final CourseOfferService courseOfferService;

    public CourseOfferResource(CourseOfferService courseOfferService) {
        this.courseOfferService = courseOfferService;
    }

    /**
     * POST  /course-offers : Create a new courseOffer.
     *
     * @param courseOfferDTO the courseOfferDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseOfferDTO, or with status 400 (Bad Request) if the courseOffer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-offers")
    @Timed
    public ResponseEntity<CourseOfferDTO> createCourseOffer(@RequestBody CourseOfferDTO courseOfferDTO) throws URISyntaxException {
        log.debug("REST request to save CourseOffer : {}", courseOfferDTO);
        if (courseOfferDTO.getId() != null) {
            throw new BadRequestAlertException("A new courseOffer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CourseOfferDTO result = courseOfferService.save(courseOfferDTO);
        return ResponseEntity.created(new URI("/api/course-offers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-offers : Updates an existing courseOffer.
     *
     * @param courseOfferDTO the courseOfferDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseOfferDTO,
     * or with status 400 (Bad Request) if the courseOfferDTO is not valid,
     * or with status 500 (Internal Server Error) if the courseOfferDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-offers")
    @Timed
    public ResponseEntity<CourseOfferDTO> updateCourseOffer(@RequestBody CourseOfferDTO courseOfferDTO) throws URISyntaxException {
        log.debug("REST request to update CourseOffer : {}", courseOfferDTO);
        if (courseOfferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CourseOfferDTO result = courseOfferService.save(courseOfferDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseOfferDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-offers : get all the courseOffers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of courseOffers in body
     */
    @GetMapping("/course-offers")
    @Timed
    public List<CourseOfferDTO> getAllCourseOffers() {
        log.debug("REST request to get all CourseOffers");
        return courseOfferService.findAll();
    }

    /**
     * GET  /course-offers/:id : get the "id" courseOffer.
     *
     * @param id the id of the courseOfferDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseOfferDTO, or with status 404 (Not Found)
     */
    @GetMapping("/course-offers/{id}")
    @Timed
    public ResponseEntity<CourseOfferDTO> getCourseOffer(@PathVariable Long id) {
        log.debug("REST request to get CourseOffer : {}", id);
        Optional<CourseOfferDTO> courseOfferDTO = courseOfferService.findOne(id);
        return ResponseUtil.wrapOrNotFound(courseOfferDTO);
    }

    /**
     * DELETE  /course-offers/:id : delete the "id" courseOffer.
     *
     * @param id the id of the courseOfferDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-offers/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseOffer(@PathVariable Long id) {
        log.debug("REST request to delete CourseOffer : {}", id);
        courseOfferService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/course-offers?query=:query : search for the courseOffer corresponding
     * to the query.
     *
     * @param query the query of the courseOffer search
     * @return the result of the search
     */
    @GetMapping("/_search/course-offers")
    @Timed
    public List<CourseOfferDTO> searchCourseOffers(@RequestParam String query) {
        log.debug("REST request to search CourseOffers for query {}", query);
        return courseOfferService.search(query);
    }

}
