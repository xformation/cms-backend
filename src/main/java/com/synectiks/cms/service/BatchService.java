package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.BatchDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Batch.
 */
public interface BatchService {

    /**
     * Save a batch.
     *
     * @param batchDTO the entity to save
     * @return the persisted entity
     */
    BatchDTO save(BatchDTO batchDTO);

    /**
     * Get all the batches.
     *
     * @return the list of entities
     */
    List<BatchDTO> findAll();


    /**
     * Get the "id" batch.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BatchDTO> findOne(Long id);

    /**
     * Delete the "id" batch.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the batch corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<BatchDTO> search(String query);
}
