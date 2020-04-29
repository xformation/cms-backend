package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.LibraryService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.LibraryDTO;
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

//import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Library.
 */
@RestController
@RequestMapping("/api")
public class LibraryResource {

    private final Logger log = LoggerFactory.getLogger(LibraryResource.class);

    private static final String ENTITY_NAME = "library";

    private final LibraryService libraryService;

    public LibraryResource(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    /**
     * POST  /libraries : Create a new library.
     *
     * @param libraryDTO the libraryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new libraryDTO, or with status 400 (Bad Request) if the library has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/libraries")
    public ResponseEntity<LibraryDTO> createLibrary(@RequestBody LibraryDTO libraryDTO) throws URISyntaxException {
        log.debug("REST request to save Library : {}", libraryDTO);
        if (libraryDTO.getId() != null) {
            throw new BadRequestAlertException("A new library cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LibraryDTO result = libraryService.save(libraryDTO);
        return ResponseEntity.created(new URI("/api/libraries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /libraries : Updates an existing library.
     *
     * @param libraryDTO the libraryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated libraryDTO,
     * or with status 400 (Bad Request) if the libraryDTO is not valid,
     * or with status 500 (Internal Server Error) if the libraryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/libraries")
    public ResponseEntity<LibraryDTO> updateLibrary(@RequestBody LibraryDTO libraryDTO) throws URISyntaxException {
        log.debug("REST request to update Library : {}", libraryDTO);
        if (libraryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LibraryDTO result = libraryService.save(libraryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, libraryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /libraries : get all the libraries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of libraries in body
     */
    @GetMapping("/libraries")
    public List<LibraryDTO> getAllLibraries() {
        log.debug("REST request to get all Libraries");
        return libraryService.findAll();
    }

    /**
     * GET  /libraries/:id : get the "id" library.
     *
     * @param id the id of the libraryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the libraryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/libraries/{id}")
    public ResponseEntity<LibraryDTO> getLibrary(@PathVariable Long id) {
        log.debug("REST request to get Library : {}", id);
        Optional<LibraryDTO> libraryDTO = libraryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(libraryDTO);
    }

    /**
     * DELETE  /libraries/:id : delete the "id" library.
     *
     * @param id the id of the libraryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/libraries/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        log.debug("REST request to delete Library : {}", id);
        libraryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/libraries?query=:query : search for the library corresponding
     * to the query.
     *
     * @param query the query of the library search
     * @return the result of the search
     */
    @GetMapping("/_search/libraries")
    public List<LibraryDTO> searchLibraries(@RequestParam String query) {
        log.debug("REST request to search Libraries for query {}", query);
        return libraryService.search(query);
    }

}
