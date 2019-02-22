package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.PaymentRemainderDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PaymentRemainder.
 */
public interface PaymentRemainderService {

    /**
     * Save a paymentRemainder.
     *
     * @param paymentRemainderDTO the entity to save
     * @return the persisted entity
     */
    PaymentRemainderDTO save(PaymentRemainderDTO paymentRemainderDTO);

    /**
     * Get all the paymentRemainders.
     *
     * @return the list of entities
     */
    List<PaymentRemainderDTO> findAll();


    /**
     * Get the "id" paymentRemainder.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PaymentRemainderDTO> findOne(Long id);

    /**
     * Delete the "id" paymentRemainder.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the paymentRemainder corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<PaymentRemainderDTO> search(String query);
}
