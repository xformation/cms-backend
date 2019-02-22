package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.LateFeeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LateFee.
 */
public interface LateFeeService {

    /**
     * Save a lateFee.
     *
     * @param lateFeeDTO the entity to save
     * @return the persisted entity
     */
    LateFeeDTO save(LateFeeDTO lateFeeDTO);

    /**
     * Get all the lateFees.
     *
     * @return the list of entities
     */
    List<LateFeeDTO> findAll();


    /**
     * Get the "id" lateFee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LateFeeDTO> findOne(Long id);

    /**
     * Delete the "id" lateFee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the lateFee corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<LateFeeDTO> search(String query);
}
