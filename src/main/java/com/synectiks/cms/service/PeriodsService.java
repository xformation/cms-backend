package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.PeriodsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Periods.
 */
public interface PeriodsService {

    /**
     * Save a periods.
     *
     * @param periodsDTO the entity to save
     * @return the persisted entity
     */
    PeriodsDTO save(PeriodsDTO periodsDTO);

    /**
     * Get all the periods.
     *
     * @return the list of entities
     */
    List<PeriodsDTO> findAll();


    /**
     * Get the "id" periods.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PeriodsDTO> findOne(Long id);

    /**
     * Delete the "id" periods.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the periods corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<PeriodsDTO> search(String query);
}
