package com.synectiks.cms.web.rest;
import com.synectiks.cms.service.PaymentRemainderService;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import com.synectiks.cms.service.dto.PaymentRemainderDTO;
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
 * REST controller for managing PaymentRemainder.
 */
@RestController
@RequestMapping("/api")
public class PaymentRemainderResource {

    private final Logger log = LoggerFactory.getLogger(PaymentRemainderResource.class);

    private static final String ENTITY_NAME = "paymentRemainder";

    private final PaymentRemainderService paymentRemainderService;

    public PaymentRemainderResource(PaymentRemainderService paymentRemainderService) {
        this.paymentRemainderService = paymentRemainderService;
    }

    /**
     * POST  /payment-remainders : Create a new paymentRemainder.
     *
     * @param paymentRemainderDTO the paymentRemainderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new paymentRemainderDTO, or with status 400 (Bad Request) if the paymentRemainder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/payment-remainders")
    public ResponseEntity<PaymentRemainderDTO> createPaymentRemainder(@Valid @RequestBody PaymentRemainderDTO paymentRemainderDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentRemainder : {}", paymentRemainderDTO);
        if (paymentRemainderDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentRemainder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentRemainderDTO result = paymentRemainderService.save(paymentRemainderDTO);
        return ResponseEntity.created(new URI("/api/payment-remainders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /payment-remainders : Updates an existing paymentRemainder.
     *
     * @param paymentRemainderDTO the paymentRemainderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated paymentRemainderDTO,
     * or with status 400 (Bad Request) if the paymentRemainderDTO is not valid,
     * or with status 500 (Internal Server Error) if the paymentRemainderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/payment-remainders")
    public ResponseEntity<PaymentRemainderDTO> updatePaymentRemainder(@Valid @RequestBody PaymentRemainderDTO paymentRemainderDTO) throws URISyntaxException {
        log.debug("REST request to update PaymentRemainder : {}", paymentRemainderDTO);
        if (paymentRemainderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentRemainderDTO result = paymentRemainderService.save(paymentRemainderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paymentRemainderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /payment-remainders : get all the paymentRemainders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of paymentRemainders in body
     */
    @GetMapping("/payment-remainders")
    public List<PaymentRemainderDTO> getAllPaymentRemainders() {
        log.debug("REST request to get all PaymentRemainders");
        return paymentRemainderService.findAll();
    }

    /**
     * GET  /payment-remainders/:id : get the "id" paymentRemainder.
     *
     * @param id the id of the paymentRemainderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the paymentRemainderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/payment-remainders/{id}")
    public ResponseEntity<PaymentRemainderDTO> getPaymentRemainder(@PathVariable Long id) {
        log.debug("REST request to get PaymentRemainder : {}", id);
        Optional<PaymentRemainderDTO> paymentRemainderDTO = paymentRemainderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentRemainderDTO);
    }

    /**
     * DELETE  /payment-remainders/:id : delete the "id" paymentRemainder.
     *
     * @param id the id of the paymentRemainderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/payment-remainders/{id}")
    public ResponseEntity<Void> deletePaymentRemainder(@PathVariable Long id) {
        log.debug("REST request to delete PaymentRemainder : {}", id);
        paymentRemainderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/payment-remainders?query=:query : search for the paymentRemainder corresponding
     * to the query.
     *
     * @param query the query of the paymentRemainder search
     * @return the result of the search
     */
    @GetMapping("/_search/payment-remainders")
    public List<PaymentRemainderDTO> searchPaymentRemainders(@RequestParam String query) {
        log.debug("REST request to search PaymentRemainders for query {}", query);
        return paymentRemainderService.search(query);
    }

}
