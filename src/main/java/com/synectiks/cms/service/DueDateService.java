package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.DueDateDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DueDate.
 */
public interface DueDateService {

    /**
     * Save a dueDate.
     *
     * @param dueDateDTO the entity to save
     * @return the persisted entity
     */
    DueDateDTO save(DueDateDTO dueDateDTO);

    /**
     * Get all the dueDates.
     *
     * @return the list of entities
     */
    List<DueDateDTO> findAll();


    /**
     * Get the "id" dueDate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DueDateDTO> findOne(Long id);

    /**
     * Delete the "id" dueDate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the dueDate corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<DueDateDTO> search(String query);
}
