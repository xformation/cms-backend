package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.DocumentsService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.DocumentsDTO;
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
 * REST controller for managing Documents.
 */
@RestController
@RequestMapping("/api")
public class DocumentsResource {

    private final Logger log = LoggerFactory.getLogger(DocumentsResource.class);

    private static final String ENTITY_NAME = "documents";

    private final DocumentsService documentsService;

    public DocumentsResource(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    /**
     * POST  /documents : Create a new documents.
     *
     * @param documentsDTO the documentsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documentsDTO, or with status 400 (Bad Request) if the documents has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/documents")
    public ResponseEntity<DocumentsDTO> createDocuments(@Valid @RequestBody DocumentsDTO documentsDTO) throws URISyntaxException {
        log.debug("REST request to save Documents : {}", documentsDTO);
        if (documentsDTO.getId() != null) {
            throw new BadRequestAlertException("A new documents cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentsDTO result = documentsService.save(documentsDTO);
        return ResponseEntity.created(new URI("/api/documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /documents : Updates an existing documents.
     *
     * @param documentsDTO the documentsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documentsDTO,
     * or with status 400 (Bad Request) if the documentsDTO is not valid,
     * or with status 500 (Internal Server Error) if the documentsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/documents")
    public ResponseEntity<DocumentsDTO> updateDocuments(@Valid @RequestBody DocumentsDTO documentsDTO) throws URISyntaxException {
        log.debug("REST request to update Documents : {}", documentsDTO);
        if (documentsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentsDTO result = documentsService.save(documentsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documentsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /documents : get all the documents.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of documents in body
     */
    @GetMapping("/documents")
    public List<DocumentsDTO> getAllDocuments() {
        log.debug("REST request to get all Documents");
        return documentsService.findAll();
    }

    /**
     * GET  /documents/:id : get the "id" documents.
     *
     * @param id the id of the documentsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the documentsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/documents/{id}")
    public ResponseEntity<DocumentsDTO> getDocuments(@PathVariable Long id) {
        log.debug("REST request to get Documents : {}", id);
        Optional<DocumentsDTO> documentsDTO = documentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentsDTO);
    }

    /**
     * DELETE  /documents/:id : delete the "id" documents.
     *
     * @param id the id of the documentsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocuments(@PathVariable Long id) {
        log.debug("REST request to delete Documents : {}", id);
        documentsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/documents?query=:query : search for the documents corresponding
     * to the query.
     *
     * @param query the query of the documents search
     * @return the result of the search
     */
    @GetMapping("/_search/documents")
    public List<DocumentsDTO> searchDocuments(@RequestParam String query) {
        log.debug("REST request to search Documents for query {}", query);
        return documentsService.search(query);
    }

}
