package com.synectiks.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.service.AddNewBookService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.AddNewBookDTO;
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
 * REST controller for managing AddNewBook.
 */
@RestController
@RequestMapping("/api")
public class AddNewBookResource {

    private final Logger log = LoggerFactory.getLogger(AddNewBookResource.class);

    private static final String ENTITY_NAME = "addNewBook";

    private final AddNewBookService addNewBookService;

    public AddNewBookResource(AddNewBookService addNewBookService) {
        this.addNewBookService = addNewBookService;
    }

    /**
     * POST  /add-new-books : Create a new addNewBook.
     *
     * @param addNewBookDTO the addNewBookDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new addNewBookDTO, or with status 400 (Bad Request) if the addNewBook has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/add-new-books")
    @Timed
    public ResponseEntity<AddNewBookDTO> createAddNewBook(@Valid @RequestBody AddNewBookDTO addNewBookDTO) throws URISyntaxException {
        log.debug("REST request to save AddNewBook : {}", addNewBookDTO);
        if (addNewBookDTO.getId() != null) {
            throw new BadRequestAlertException("A new addNewBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddNewBookDTO result = addNewBookService.save(addNewBookDTO);
        return ResponseEntity.created(new URI("/api/add-new-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /add-new-books : Updates an existing addNewBook.
     *
     * @param addNewBookDTO the addNewBookDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated addNewBookDTO,
     * or with status 400 (Bad Request) if the addNewBookDTO is not valid,
     * or with status 500 (Internal Server Error) if the addNewBookDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/add-new-books")
    @Timed
    public ResponseEntity<AddNewBookDTO> updateAddNewBook(@Valid @RequestBody AddNewBookDTO addNewBookDTO) throws URISyntaxException {
        log.debug("REST request to update AddNewBook : {}", addNewBookDTO);
        if (addNewBookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AddNewBookDTO result = addNewBookService.save(addNewBookDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, addNewBookDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /add-new-books : get all the addNewBooks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of addNewBooks in body
     */
    @GetMapping("/add-new-books")
    @Timed
    public List<AddNewBookDTO> getAllAddNewBooks() {
        log.debug("REST request to get all AddNewBooks");
        return addNewBookService.findAll();
    }

    /**
     * GET  /add-new-books/:id : get the "id" addNewBook.
     *
     * @param id the id of the addNewBookDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the addNewBookDTO, or with status 404 (Not Found)
     */
    @GetMapping("/add-new-books/{id}")
    @Timed
    public ResponseEntity<AddNewBookDTO> getAddNewBook(@PathVariable Long id) {
        log.debug("REST request to get AddNewBook : {}", id);
        Optional<AddNewBookDTO> addNewBookDTO = addNewBookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(addNewBookDTO);
    }

    /**
     * DELETE  /add-new-books/:id : delete the "id" addNewBook.
     *
     * @param id the id of the addNewBookDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/add-new-books/{id}")
    @Timed
    public ResponseEntity<Void> deleteAddNewBook(@PathVariable Long id) {
        log.debug("REST request to delete AddNewBook : {}", id);
        addNewBookService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/add-new-books?query=:query : search for the addNewBook corresponding
     * to the query.
     *
     * @param query the query of the addNewBook search
     * @return the result of the search
     */
    @GetMapping("/_search/add-new-books")
    @Timed
    public List<AddNewBookDTO> searchAddNewBooks(@RequestParam String query) {
        log.debug("REST request to search AddNewBooks for query {}", query);
        return addNewBookService.search(query);
    }

}
