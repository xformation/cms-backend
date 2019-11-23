package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

import com.synectiks.cms.service.PaymentRequestResponseService;
import com.synectiks.cms.service.dto.PaymentRequestResponseDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.commons.entities.cms.PaymentRequestResponse}.
 */
@RestController
@RequestMapping("/api")
public class PaymentRequestResponseResource {

    private final Logger log = LoggerFactory.getLogger(PaymentRequestResponseResource.class);

    private static final String ENTITY_NAME = "paymentRequestResponse";

    private final PaymentRequestResponseService paymentRequestResponseService;

    public PaymentRequestResponseResource(PaymentRequestResponseService paymentRequestResponseService) {
        this.paymentRequestResponseService = paymentRequestResponseService;
    }

    /**
     * {@code POST  /payment-request-responses} : Create a new paymentRequestResponse.
     *
     * @param paymentRequestResponseDTO the paymentRequestResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentRequestResponseDTO, or with status {@code 400 (Bad Request)} if the paymentRequestResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-request-responses")
    public ResponseEntity<PaymentRequestResponseDTO> createPaymentRequestResponse(@RequestBody PaymentRequestResponseDTO paymentRequestResponseDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentRequestResponse : {}", paymentRequestResponseDTO);
        if (paymentRequestResponseDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentRequestResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentRequestResponseDTO result = paymentRequestResponseService.save(paymentRequestResponseDTO);
        return ResponseEntity.created(new URI("/api/payment-request-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payment-request-responses} : Updates an existing paymentRequestResponse.
     *
     * @param paymentRequestResponseDTO the paymentRequestResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentRequestResponseDTO,
     * or with status {@code 400 (Bad Request)} if the paymentRequestResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentRequestResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-request-responses")
    public ResponseEntity<PaymentRequestResponseDTO> updatePaymentRequestResponse(@RequestBody PaymentRequestResponseDTO paymentRequestResponseDTO) throws URISyntaxException {
        log.debug("REST request to update PaymentRequestResponse : {}", paymentRequestResponseDTO);
        if (paymentRequestResponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentRequestResponseDTO result = paymentRequestResponseService.save(paymentRequestResponseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paymentRequestResponseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payment-request-responses} : get all the paymentRequestResponses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentRequestResponses in body.
     */
    @GetMapping("/payment-request-responses")
    public List<PaymentRequestResponseDTO> getAllPaymentRequestResponses() {
        log.debug("REST request to get all PaymentRequestResponses");
        return paymentRequestResponseService.findAll();
    }

    /**
     * {@code GET  /payment-request-responses/:id} : get the "id" paymentRequestResponse.
     *
     * @param id the id of the paymentRequestResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentRequestResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-request-responses/{id}")
    public ResponseEntity<PaymentRequestResponseDTO> getPaymentRequestResponse(@PathVariable Long id) {
        log.debug("REST request to get PaymentRequestResponse : {}", id);
        Optional<PaymentRequestResponseDTO> paymentRequestResponseDTO = paymentRequestResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentRequestResponseDTO);
    }

    /**
     * {@code DELETE  /payment-request-responses/:id} : delete the "id" paymentRequestResponse.
     *
     * @param id the id of the paymentRequestResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-request-responses/{id}")
    public ResponseEntity<Void> deletePaymentRequestResponse(@PathVariable Long id) {
        log.debug("REST request to delete PaymentRequestResponse : {}", id);
        paymentRequestResponseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/payment-request-responses?query=:query} : search for the paymentRequestResponse corresponding
     * to the query.
     *
     * @param query the query of the paymentRequestResponse search.
     * @return the result of the search.
     */
    @GetMapping("/_search/payment-request-responses")
    public List<PaymentRequestResponseDTO> searchPaymentRequestResponses(@RequestParam String query) {
        log.debug("REST request to search PaymentRequestResponses for query {}", query);
        return paymentRequestResponseService.search(query);
    }

}
