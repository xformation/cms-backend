package com.synectiks.cms.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.synectiks.cms.business.service.CmsInvoiceService;
import com.synectiks.cms.domain.Invoice;
import com.synectiks.cms.repository.InvoiceRepository;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Invoice.
 */
@RestController
@RequestMapping("/api")
public class CmsInvoiceRestController {

    private final Logger log = LoggerFactory.getLogger(CmsInvoiceRestController.class);

    private static final String ENTITY_NAME = "invoice";

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private CmsInvoiceService cmsInvoiceService;

    @PostMapping("/cmsinvoices")
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice) throws URISyntaxException {
        log.debug("REST request to save Invoice : {}", invoice);
        if (invoice.getId() != null) {
            throw new BadRequestAlertException("A new invoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Invoice result = this.invoiceRepository.save(invoice);
        return ResponseEntity.created(new URI("/api/cmsinvoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/cmsinvoices")
    public ResponseEntity<Invoice> updateInvoice(@Valid @RequestBody Invoice invoice) throws URISyntaxException {
        log.debug("REST request to update Invoice : {}", invoice);
        if (invoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Invoice result = this.invoiceRepository.save(invoice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, invoice.getId().toString()))
            .body(result);
    }

    @GetMapping("/cmsinvoices")
    public List<Invoice> getAllInvoices() {
        log.debug("REST request to get all Invoices");
        return this.invoiceRepository.findAll();
    }

    @GetMapping("/cmsinvoices/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long id) {
        log.debug("REST request to get Invoice : {}", id);
        Optional<Invoice> invoice = this.invoiceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(invoice);
    }

    @DeleteMapping("/cmsinvoices/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        log.debug("REST request to delete Invoice : {}", id);
        this.invoiceRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    
}
