package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.ReportsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Reports.
 */
public interface ReportsService {

    /**
     * Save a reports.
     *
     * @param reportsDTO the entity to save
     * @return the persisted entity
     */
    ReportsDTO save(ReportsDTO reportsDTO);

    /**
     * Get all the reports.
     *
     * @return the list of entities
     */
    List<ReportsDTO> findAll();


    /**
     * Get the "id" reports.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReportsDTO> findOne(Long id);

    /**
     * Delete the "id" reports.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the reports corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ReportsDTO> search(String query);
}
