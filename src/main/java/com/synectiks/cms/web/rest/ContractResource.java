package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.ContractService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.ContractDTO;
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
 * REST controller for managing Contract.
 */
@RestController
@RequestMapping("/api")
public class ContractResource {

    private final Logger log = LoggerFactory.getLogger(ContractResource.class);

    private static final String ENTITY_NAME = "contract";

    private final ContractService contractService;

    public ContractResource(ContractService contractService) {
        this.contractService = contractService;
    }

    /**
     * POST  /contracts : Create a new contract.
     *
     * @param contractDTO the contractDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contractDTO, or with status 400 (Bad Request) if the contract has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contracts")
    public ResponseEntity<ContractDTO> createContract(@Valid @RequestBody ContractDTO contractDTO) throws URISyntaxException {
        log.debug("REST request to save Contract : {}", contractDTO);
        if (contractDTO.getId() != null) {
            throw new BadRequestAlertException("A new contract cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractDTO result = contractService.save(contractDTO);
        return ResponseEntity.created(new URI("/api/contracts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contracts : Updates an existing contract.
     *
     * @param contractDTO the contractDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contractDTO,
     * or with status 400 (Bad Request) if the contractDTO is not valid,
     * or with status 500 (Internal Server Error) if the contractDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contracts")
    public ResponseEntity<ContractDTO> updateContract(@Valid @RequestBody ContractDTO contractDTO) throws URISyntaxException {
        log.debug("REST request to update Contract : {}", contractDTO);
        if (contractDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContractDTO result = contractService.save(contractDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contractDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contracts : get all the contracts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contracts in body
     */
    @GetMapping("/contracts")
    public List<ContractDTO> getAllContracts() {
        log.debug("REST request to get all Contracts");
        return contractService.findAll();
    }

    /**
     * GET  /contracts/:id : get the "id" contract.
     *
     * @param id the id of the contractDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contractDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contracts/{id}")
    public ResponseEntity<ContractDTO> getContract(@PathVariable Long id) {
        log.debug("REST request to get Contract : {}", id);
        Optional<ContractDTO> contractDTO = contractService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractDTO);
    }

    /**
     * DELETE  /contracts/:id : delete the "id" contract.
     *
     * @param id the id of the contractDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        log.debug("REST request to delete Contract : {}", id);
        contractService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/contracts?query=:query : search for the contract corresponding
     * to the query.
     *
     * @param query the query of the contract search
     * @return the result of the search
     */
    @GetMapping("/_search/contracts")
    public List<ContractDTO> searchContracts(@RequestParam String query) {
        log.debug("REST request to search Contracts for query {}", query);
        return contractService.search(query);
    }

}
