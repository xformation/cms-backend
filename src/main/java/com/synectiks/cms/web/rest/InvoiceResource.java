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

import com.synectiks.cms.service.InvoiceService;
import com.synectiks.cms.service.dto.InvoiceDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Invoice.
 */
@RestController
@RequestMapping("/api")
public class InvoiceResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceResource.class);

    private static final String ENTITY_NAME = "invoice";

    private final InvoiceService invoiceService;

    public InvoiceResource(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * POST  /invoices : Create a new invoice.
     *
     * @param invoiceDTO the invoiceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new invoiceDTO, or with status 400 (Bad Request) if the invoice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/invoices")
    public ResponseEntity<InvoiceDTO> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) throws URISyntaxException {
        log.debug("REST request to save Invoice : {}", invoiceDTO);
        if (invoiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new invoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceDTO result = invoiceService.save(invoiceDTO);
        return ResponseEntity.created(new URI("/api/invoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /invoices : Updates an existing invoice.
     *
     * @param invoiceDTO the invoiceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated invoiceDTO,
     * or with status 400 (Bad Request) if the invoiceDTO is not valid,
     * or with status 500 (Internal Server Error) if the invoiceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/invoices")
    public ResponseEntity<InvoiceDTO> updateInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) throws URISyntaxException {
        log.debug("REST request to update Invoice : {}", invoiceDTO);
        if (invoiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoiceDTO result = invoiceService.save(invoiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, invoiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /invoices : get all the invoices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of invoices in body
     */
    @GetMapping("/invoices")
    public List<InvoiceDTO> getAllInvoices() {
        log.debug("REST request to get all Invoices");
        return invoiceService.findAll();
    }

    /**
     * GET  /invoices/:id : get the "id" invoice.
     *
     * @param id the id of the invoiceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the invoiceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable Long id) {
        log.debug("REST request to get Invoice : {}", id);
        Optional<InvoiceDTO> invoiceDTO = invoiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceDTO);
    }

    /**
     * DELETE  /invoices/:id : delete the "id" invoice.
     *
     * @param id the id of the invoiceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        log.debug("REST request to delete Invoice : {}", id);
        invoiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/invoices?query=:query : search for the invoice corresponding
     * to the query.
     *
     * @param query the query of the invoice search
     * @return the result of the search
     */
    @GetMapping("/_search/invoices")
    public List<InvoiceDTO> searchInvoices(@RequestParam String query) {
        log.debug("REST request to search Invoices for query {}", query);
        return invoiceService.search(query);
    }

}
