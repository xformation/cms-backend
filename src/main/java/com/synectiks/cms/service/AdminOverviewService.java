package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AdminOverviewDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AdminOverview.
 */
public interface AdminOverviewService {

    /**
     * Save a adminOverview.
     *
     * @param adminOverviewDTO the entity to save
     * @return the persisted entity
     */
    AdminOverviewDTO save(AdminOverviewDTO adminOverviewDTO);

    /**
     * Get all the adminOverviews.
     *
     * @return the list of entities
     */
    List<AdminOverviewDTO> findAll();


    /**
     * Get the "id" adminOverview.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AdminOverviewDTO> findOne(Long id);

    /**
     * Delete the "id" adminOverview.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the adminOverview corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AdminOverviewDTO> search(String query);
}
