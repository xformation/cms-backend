package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AcademicHistoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AcademicHistory.
 */
public interface AcademicHistoryService {

    /**
     * Save a academicHistory.
     *
     * @param academicHistoryDTO the entity to save
     * @return the persisted entity
     */
    AcademicHistoryDTO save(AcademicHistoryDTO academicHistoryDTO);

    /**
     * Get all the academicHistories.
     *
     * @return the list of entities
     */
    List<AcademicHistoryDTO> findAll();


    /**
     * Get the "id" academicHistory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcademicHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" academicHistory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the academicHistory corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AcademicHistoryDTO> search(String query);
}
