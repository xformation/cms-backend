package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.PaymentRequestResponseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.commons.entities.cms.PaymentRequestResponse}.
 */
public interface PaymentRequestResponseService {

    /**
     * Save a paymentRequestResponse.
     *
     * @param paymentRequestResponseDTO the entity to save.
     * @return the persisted entity.
     */
    PaymentRequestResponseDTO save(PaymentRequestResponseDTO paymentRequestResponseDTO);

    /**
     * Get all the paymentRequestResponses.
     *
     * @return the list of entities.
     */
    List<PaymentRequestResponseDTO> findAll();


    /**
     * Get the "id" paymentRequestResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymentRequestResponseDTO> findOne(Long id);

    /**
     * Delete the "id" paymentRequestResponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the paymentRequestResponse corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<PaymentRequestResponseDTO> search(String query);
}
