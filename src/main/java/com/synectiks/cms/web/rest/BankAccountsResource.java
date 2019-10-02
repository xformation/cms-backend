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

import com.synectiks.cms.service.BankAccountsService;
import com.synectiks.cms.service.dto.BankAccountsDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing BankAccounts.
 */
@RestController
@RequestMapping("/api")
public class BankAccountsResource {

    private final Logger log = LoggerFactory.getLogger(BankAccountsResource.class);

    private static final String ENTITY_NAME = "bankAccounts";

    private final BankAccountsService bankAccountsService;

    public BankAccountsResource(BankAccountsService bankAccountsService) {
        this.bankAccountsService = bankAccountsService;
    }

    /**
     * POST  /bank-accounts : Create a new bankAccounts.
     *
     * @param bankAccountsDTO the bankAccountsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bankAccountsDTO, or with status 400 (Bad Request) if the bankAccounts has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bank-accounts")
    public ResponseEntity<BankAccountsDTO> createBankAccounts(@Valid @RequestBody BankAccountsDTO bankAccountsDTO) throws URISyntaxException {
        log.debug("REST request to save BankAccounts : {}", bankAccountsDTO);
        if (bankAccountsDTO.getId() != null) {
            throw new BadRequestAlertException("A new bankAccounts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankAccountsDTO result = bankAccountsService.save(bankAccountsDTO);
        return ResponseEntity.created(new URI("/api/bank-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bank-accounts : Updates an existing bankAccounts.
     *
     * @param bankAccountsDTO the bankAccountsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bankAccountsDTO,
     * or with status 400 (Bad Request) if the bankAccountsDTO is not valid,
     * or with status 500 (Internal Server Error) if the bankAccountsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bank-accounts")
    public ResponseEntity<BankAccountsDTO> updateBankAccounts(@Valid @RequestBody BankAccountsDTO bankAccountsDTO) throws URISyntaxException {
        log.debug("REST request to update BankAccounts : {}", bankAccountsDTO);
        if (bankAccountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BankAccountsDTO result = bankAccountsService.save(bankAccountsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankAccountsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bank-accounts : get all the bankAccounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bankAccounts in body
     */
    @GetMapping("/bank-accounts")
    public List<BankAccountsDTO> getAllBankAccounts() {
        log.debug("REST request to get all BankAccounts");
        return bankAccountsService.findAll();
    }

    /**
     * GET  /bank-accounts/:id : get the "id" bankAccounts.
     *
     * @param id the id of the bankAccountsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bankAccountsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bank-accounts/{id}")
    public ResponseEntity<BankAccountsDTO> getBankAccounts(@PathVariable Long id) {
        log.debug("REST request to get BankAccounts : {}", id);
        Optional<BankAccountsDTO> bankAccountsDTO = bankAccountsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankAccountsDTO);
    }

    /**
     * DELETE  /bank-accounts/:id : delete the "id" bankAccounts.
     *
     * @param id the id of the bankAccountsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bank-accounts/{id}")
    public ResponseEntity<Void> deleteBankAccounts(@PathVariable Long id) {
        log.debug("REST request to delete BankAccounts : {}", id);
        bankAccountsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/bank-accounts?query=:query : search for the bankAccounts corresponding
     * to the query.
     *
     * @param query the query of the bankAccounts search
     * @return the result of the search
     */
    @GetMapping("/_search/bank-accounts")
    public List<BankAccountsDTO> searchBankAccounts(@RequestParam String query) {
        log.debug("REST request to search BankAccounts for query {}", query);
        return bankAccountsService.search(query);
    }

}
